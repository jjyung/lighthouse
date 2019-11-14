package tw.kgips.sandbox;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import tw.kgips.dto.exchange_day_report.ExchangeDayReportCreateDTO;
import tw.kgips.util.ConvertUtil;
import tw.kgips.util.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExchangeDayReportDev {

    // https://www.twse.com.tw/exchangeReport/STOCK_DAY?response=json&date=20191113&stockNo=1101&_=1573655328534
    private final static String url = "https://www.twse.com.tw/exchangeReport/STOCK_DAY";

    public static String queryStockExchangeDayReport(String companyCode, LocalDate date) throws IOException {

        Util.setSSL();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        Map<String, String> params = new HashMap<>();

        params.put("response", "json");
        params.put("stockNo", companyCode);
        params.put("date", dateTimeFormatter.format(date));
        params.put("_", ConvertUtil.toString(System.currentTimeMillis()));

        return Jsoup.connect(url)
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

    public static List<ExchangeDayReportCreateDTO> parseStockExchangeDayReport(String queryResponseBody) throws JSONException {

        if (queryResponseBody == null) {
            return null;
        }

        List<ExchangeDayReportCreateDTO> createDTOList = new ArrayList<>();

        // data, state, notes -> JSONArray, data -> JSONArray, title, fields -> JSONArray
        JSONObject jsonObject = new JSONObject(queryResponseBody);

        String title = ConvertUtil.toString(jsonObject.get("title"));
        String companyCode = title.split(" ")[1];

        // fields sample: "fields":["日期","成交股數","成交金額","開盤價","最高價","最低價","收盤價","漲跌價差","成交筆數"]
        // data sample: data:[["108/11/01","9,416,327","381,145,627","40.45","40.60","40.30","40.55","+0.10","2,895"]]
        JSONArray data = (JSONArray) jsonObject.get("data");
        for (int i = 0; i < data.length(); i++) {

            ExchangeDayReportCreateDTO createDTO = new ExchangeDayReportCreateDTO();
            createDTO.setCompanyCode(companyCode);

            JSONArray row = (JSONArray) data.get(i);

            String date = ConvertUtil.toString(row.get(0));
            String[] split = date.split("/");
            LocalDate localDate = LocalDate.of(ConvertUtil.toInt(split[0]) + 1911, ConvertUtil.toInt(split[1]), ConvertUtil.toInt(split[2]));
            createDTO.setDate(localDate);

            String tradedSharesNum = ConvertUtil.toString(row.get(1));
            tradedSharesNum = tradedSharesNum.replaceAll(",", "");
            createDTO.setTradedSharesNumber(ConvertUtil.toLong(tradedSharesNum));

            String txAmount = ConvertUtil.toString(row.get(2));
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
            txNumber = txNumber.replace(",", "");
            createDTO.setTxNumber(ConvertUtil.toLong(txNumber));

            createDTOList.add(createDTO);
        }


        return createDTOList;
    }

    public static void main(String[] args) throws Exception {

        String dayReport = queryStockExchangeDayReport("1101", LocalDate.now());

        List<ExchangeDayReportCreateDTO> createDTOList = parseStockExchangeDayReport(dayReport);

    }

}