package tw.kgips.manager;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tw.kgips.dto.DateRangeDTO;
import tw.kgips.dto.exchange_day_report.ExchangeDayReportCreateDTO;
import tw.kgips.dto.exchange_day_report.ExchangeDayReportDTO;
import tw.kgips.dto.listed_security.ListedSecurityDTO;
import tw.kgips.dto.listed_security.MarketCat;
import tw.kgips.persistence.dao.ExchangeDayReportDao;
import tw.kgips.persistence.entity.ExchangeDayReportEntity;
import tw.kgips.util.ConvertUtil;
import tw.kgips.util.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ExchangeDayReportManager {

	private static final Logger logger = Logger.getLogger(ExchangeDayReportManager.class);
	private final static String SII_EXCHANGE_REPORT_STOCK_DAY_URL = "https://www.twse.com.tw/exchangeReport/STOCK_DAY";
	private final static String OTC_DAILY_TRADING_INFO_URL = "https://www.tpex.org.tw/web/stock/aftertrading/daily_trading_info/st43_result.php";

	private final ExchangeDayReportDao exchangeDayReportDao;

	private ListedSecurityManager listedSecurityManager;

	private static final Object lastQuerySIITimestampKey = new Object();
	private static long lastQuerySIITimestamp = 0L;

	private static final Object lastQueryOTCTimestampKey = new Object();
	private static long lastQueryOTCTimestamp = 0L;

	@Autowired
	public ExchangeDayReportManager(ExchangeDayReportDao exchangeDayReportDao) {
		this.exchangeDayReportDao = exchangeDayReportDao;
	}

	@Autowired
	public void setListedSecurityManager(ListedSecurityManager listedSecurityManager) {
		this.listedSecurityManager = listedSecurityManager;
	}

	public static String querySIIStockExchangeDayReport(String companyCode, LocalDate date) throws IOException, InterruptedException {

		// 每次間隔至少 5 秒, 避免被鎖 IP
		while (System.currentTimeMillis() - lastQuerySIITimestamp < 5000) {
			Thread.sleep(5000);
		}

		synchronized (lastQuerySIITimestampKey) {
			lastQuerySIITimestamp = System.currentTimeMillis();
		}

		Util.setSSL();

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		Map<String, String> params = new HashMap<>();

		params.put("response", "json");
		params.put("stockNo", companyCode);
		params.put("date", dateTimeFormatter.format(date));
		params.put("_", ConvertUtil.toString(System.currentTimeMillis()));

		return Jsoup.connect(SII_EXCHANGE_REPORT_STOCK_DAY_URL)
				.timeout(30000)
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36")
				.method(Connection.Method.GET)
				.header("Accept", "application/json, text/javascript, */*; q=0.01")
				.header("Accept-Encoding", "gzip, deflate, br")
				.header("Accept-Language", "zh-TW,zh;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Content-Type", "application/json;charset=UTF-8")
				.data(params)
				.ignoreContentType(true)
				.execute()
				.body();
	}

	public static String queryOTCStockExchangeDayReport(String companyCode, LocalDate date) throws IOException, InterruptedException {

		// 每次間隔至少 5 秒, 避免被鎖 IP
		while (System.currentTimeMillis() - lastQueryOTCTimestamp < 5000) {
			Thread.sleep(5000);
		}

		synchronized (lastQueryOTCTimestampKey) {
			lastQueryOTCTimestamp = System.currentTimeMillis();
		}

		Util.setSSL();

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyy/MM");
		Map<String, String> params = new HashMap<>();

		// ex: l=zh-tw&d=108/11&stkno=1240&_=1574794914120

		params.put("l", "zh-tw");
		params.put("d", dateTimeFormatter.format(date.minusYears(1911))); // to 民國
		params.put("stkno", companyCode);
		params.put("_", ConvertUtil.toString(System.currentTimeMillis()));

		return Jsoup.connect(OTC_DAILY_TRADING_INFO_URL)
				.timeout(30000)
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36")
				.method(Connection.Method.GET)
				.header("Accept", "application/json, text/javascript, */*; q=0.01")
				.header("Accept-Encoding", "gzip, deflate, br")
				.header("Accept-Language", "zh-TW,zh;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Content-Type", "application/json;charset=UTF-8")
				.data(params)
				.ignoreContentType(true)
				.execute()
				.body();
	}

	public static List<ExchangeDayReportCreateDTO> parseSIIStockExchangeDayReport(String queryResponseBody) {

		if (queryResponseBody == null) {
			return null;
		}

		List<ExchangeDayReportCreateDTO> createDTOList = new ArrayList<>();

		// data, state, notes -> JSONArray, data -> JSONArray, title, fields -> JSONArray

		try {
			JSONObject jsonObject = new JSONObject(queryResponseBody);

			String title = ConvertUtil.toString(jsonObject.get("title"));
			assert title != null;
			String companyCode = title.split(" ")[1];

			// fields sample: "fields":["日期","成交股數","成交金額","開盤價","最高價","最低價","收盤價","漲跌價差","成交筆數"]
			// data sample: data:[["108/11/01","9,416,327","381,145,627","40.45","40.60","40.30","40.55","+0.10","2,895"]]
			JSONArray data = (JSONArray) jsonObject.get("data");

			for (int i = 0; i < data.length(); i++) {

				try {
					ExchangeDayReportCreateDTO createDTO = new ExchangeDayReportCreateDTO();
					createDTO.setCompanyCode(companyCode);

					JSONArray row = (JSONArray) data.get(i);

					String date = ConvertUtil.toString(row.get(0));

					assert date != null;
					String[] split = date.split("/");

					LocalDate localDate = LocalDate.of(ConvertUtil.toInt(split[0]) + 1911, ConvertUtil.toInt(split[1]), ConvertUtil.toInt(split[2]));
					createDTO.setDate(localDate);

					String tradedSharesNum = ConvertUtil.toString(row.get(1));
					assert tradedSharesNum != null;
					tradedSharesNum = tradedSharesNum.replaceAll(",", "");
					createDTO.setTradedSharesNumber(ConvertUtil.toLong(tradedSharesNum));

					String txAmount = ConvertUtil.toString(row.get(2));
					assert txAmount != null;
					txAmount = txAmount.replaceAll(",", "");
					createDTO.setTxAmount(ConvertUtil.toLong(txAmount));

					String openingPrice = ConvertUtil.toString(row.get(3));
					createDTO.setOpeningPrice(ConvertUtil.toDouble(openingPrice));

					String highestPrice = ConvertUtil.toString(row.get(4));
					createDTO.setHighestPrice(ConvertUtil.toDouble(highestPrice));

					String lowestPrice = ConvertUtil.toString(row.get(5));
					createDTO.setLowestPrice(ConvertUtil.toDouble(lowestPrice));

					String closingPrice = ConvertUtil.toString(row.get(6));
					createDTO.setClosingPrice(ConvertUtil.toDouble(closingPrice));

					String changeSpread = ConvertUtil.toString(row.get(7));
					createDTO.setChangeSpread(ConvertUtil.toDouble(changeSpread));

					String txNumber = ConvertUtil.toString(row.get(8));
					assert txNumber != null;
					txNumber = txNumber.replace(",", "");
					createDTO.setTxNumber(ConvertUtil.toLong(txNumber));

					createDTOList.add(createDTO);
				} catch (Exception ex) {
					logger.error(ex.getMessage(), ex);
				}

			}

		} catch (Exception ex) {
			logger.debug(queryResponseBody);
			logger.error(ex.getMessage(), ex);
		}

		return createDTOList;
	}

	public static List<ExchangeDayReportCreateDTO> parseOTCStockExchangeDayReport(String queryResponseBody) {

		if (queryResponseBody == null) {
			return null;
		}

		List<ExchangeDayReportCreateDTO> createDTOList = new ArrayList<>();

		// eg "stkNo":"1240","stkName":"\u8302\u751f\u8fb2\u7d93","showListPriceNote":false,"showListPriceLink":false,"reportDate":"108\/11","iTotalRecords":18,"aaData":

		try {
			JSONObject jsonObject = new JSONObject(queryResponseBody);

			String companyCode = ConvertUtil.toString(jsonObject.get("stkNo"));

			// fields: 日期, 成交仟股, 成交仟元, 開盤, 最高, 最低, 收盤, 漲跌, 筆數
			// data sample: data:[["108\/11\/01","49","2,532","51.40","52.00","51.40","51.60","-0.20","40"]]
			JSONArray data = (JSONArray) jsonObject.get("aaData");

			for (int i = 0; i < data.length(); i++) {

				try {
					ExchangeDayReportCreateDTO createDTO = new ExchangeDayReportCreateDTO();
					createDTO.setCompanyCode(companyCode);

					JSONArray row = (JSONArray) data.get(i);

					String date = ConvertUtil.toString(row.get(0));

					assert date != null;
					String[] split = date.split("/");

					LocalDate localDate = LocalDate.of(ConvertUtil.toInt(split[0]) + 1911, ConvertUtil.toInt(split[1]), ConvertUtil.toInt(split[2]));
					createDTO.setDate(localDate);

					String tradedSharesNum = ConvertUtil.toString(row.get(1));
					assert tradedSharesNum != null;
					tradedSharesNum = tradedSharesNum.replaceAll(",", "");
					createDTO.setTradedSharesNumber(ConvertUtil.toLong(tradedSharesNum) * 1000);

					String txAmount = ConvertUtil.toString(row.get(2));
					assert txAmount != null;
					txAmount = txAmount.replaceAll(",", "");
					createDTO.setTxAmount(ConvertUtil.toLong(txAmount) * 1000);

					String openingPrice = ConvertUtil.toString(row.get(3));
					createDTO.setOpeningPrice(ConvertUtil.toDouble(openingPrice));

					String highestPrice = ConvertUtil.toString(row.get(4));
					createDTO.setHighestPrice(ConvertUtil.toDouble(highestPrice));

					String lowestPrice = ConvertUtil.toString(row.get(5));
					createDTO.setLowestPrice(ConvertUtil.toDouble(lowestPrice));

					String closingPrice = ConvertUtil.toString(row.get(6));
					createDTO.setClosingPrice(ConvertUtil.toDouble(closingPrice));

					String changeSpread = ConvertUtil.toString(row.get(7));
					createDTO.setChangeSpread(ConvertUtil.toDouble(changeSpread));

					String txNumber = ConvertUtil.toString(row.get(8));
					assert txNumber != null;
					txNumber = txNumber.replace(",", "");
					createDTO.setTxNumber(ConvertUtil.toLong(txNumber));

					createDTOList.add(createDTO);
				} catch (Exception ex) {
					logger.error(ex.getMessage(), ex);
				}

			}

		} catch (Exception ex) {
			logger.debug(queryResponseBody);
			logger.error(ex.getMessage(), ex);
		}

		return createDTOList;
	}

	public void createExchangeDayReport(ExchangeDayReportCreateDTO createDTO) {
		this.exchangeDayReportDao.createExchangeDayReport(createDTO.toEntity());
	}

	public boolean isExchangeDayReportExist(String companyCode, LocalDate date) {
		return this.exchangeDayReportDao.isExchangeDayReportExist(companyCode, date);
	}

	public List<ExchangeDayReportDTO> listExchangeDayReportByCompanyCodeAndDateRange(String companyCode, DateRangeDTO dateRangeDTO) {

		List<ExchangeDayReportEntity> entities = this.exchangeDayReportDao.listExchangeDayReportByCompanyCodeAndDateRange(companyCode, dateRangeDTO);

		return entities.stream()
				.map(ExchangeDayReportDTO::fromEntity)
				.collect(Collectors.toList());
	}

	public void crawlAllAndCreateForSII(int year, int month) throws InterruptedException {

		List<ListedSecurityDTO> listedSecurities = listedSecurityManager.listListedSecuritiesByMarketCat(MarketCat.SII.getValue());

		for (ListedSecurityDTO listedSecurity : listedSecurities) {

			int errorCount = 0;
			boolean success = false;

			do {
				try {
					String respBody = querySIIStockExchangeDayReport(listedSecurity.getCompanyCode(), LocalDate.of(year, month, 1));

					List<ExchangeDayReportCreateDTO> exchangeDayReportCreateDTOS = parseSIIStockExchangeDayReport(respBody);
					if (exchangeDayReportCreateDTOS == null) continue;

					for (ExchangeDayReportCreateDTO exchangeDayReportCreateDTO : exchangeDayReportCreateDTOS) {
						if (isExchangeDayReportExist(exchangeDayReportCreateDTO.getCompanyCode(), exchangeDayReportCreateDTO.getDate())) {
							continue;
						}

						createExchangeDayReport(exchangeDayReportCreateDTO);

					}

					success = true; // 成功結束

				} catch (Exception ex) {
					logger.error(ex.getMessage(), ex);
					errorCount++;
					// 失敗了, 等三分鐘再繼續
					Thread.sleep(3 * 60 * 1000);
				}
			} while (!success && errorCount < 3); // 失敗再重新嘗試, 至多嘗試三次

			if (errorCount >= 3) {
				throw new RuntimeException("Failed on " + listedSecurity.getCompanyCode());
			}

		}
	}

	public void crawlAllAndCreateForOTC(int year, int month) throws InterruptedException {

		List<ListedSecurityDTO> listedSecurities = listedSecurityManager.listListedSecuritiesByMarketCat(MarketCat.OTC.getValue());

		for (ListedSecurityDTO listedSecurity : listedSecurities) {

			int errorCount = 0;
			boolean success = false;

			do {
				try {
					String respBody = queryOTCStockExchangeDayReport(listedSecurity.getCompanyCode(), LocalDate.of(year, month, 1));

					List<ExchangeDayReportCreateDTO> exchangeDayReportCreateDTOS = parseOTCStockExchangeDayReport(respBody);
					if (exchangeDayReportCreateDTOS == null) continue;

					for (ExchangeDayReportCreateDTO exchangeDayReportCreateDTO : exchangeDayReportCreateDTOS) {
						if (isExchangeDayReportExist(exchangeDayReportCreateDTO.getCompanyCode(), exchangeDayReportCreateDTO.getDate())) {
							continue;
						}

						createExchangeDayReport(exchangeDayReportCreateDTO);

					}

					success = true; // 成功結束

				} catch (Exception ex) {
					logger.error(ex.getMessage(), ex);
					errorCount++;
					// 失敗了, 等三分鐘再繼續
					Thread.sleep(3 * 60 * 1000);
				}
			} while (!success && errorCount < 3); // 失敗再重新嘗試, 至多嘗試三次

			if (errorCount >= 3) {
				throw new RuntimeException("Failed on " + listedSecurity.getCompanyCode());
			}

		}
	}

}
