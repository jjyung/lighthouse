package tw.kgips.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tw.kgips.dto.listed_security.ListedSecurityDTO;
import tw.kgips.dto.listed_security.MarketCat;
import tw.kgips.persistence.dao.ExchangeDayReportDao;
import tw.kgips.persistence.dao.StatisticReportDao;
import tw.kgips.persistence.entity.ExchangeDayReportEntity;
import tw.kgips.persistence.entity.StatisticReportEntity;
import tw.kgips.util.ConvertUtil;

import java.time.LocalDate;
import java.util.List;

@Component
public class StatisticReportManager {

	private final StatisticReportDao statisticReportDao;

	private ExchangeDayReportDao exchangeDayReportDao;

	private ListedSecurityManager listedSecurityManager;

	@Autowired
	public StatisticReportManager(StatisticReportDao statisticReportDao) {
		this.statisticReportDao = statisticReportDao;
	}

	@Autowired
	public void setExchangeDayReportDao(ExchangeDayReportDao exchangeDayReportDao) {
		this.exchangeDayReportDao = exchangeDayReportDao;
	}

	@Autowired
	public void setListedSecurityManager(ListedSecurityManager listedSecurityManager) {
		this.listedSecurityManager = listedSecurityManager;
	}

	public void createStatisticReport(StatisticReportEntity entity) {
		statisticReportDao.createStatisticReport(entity);
	}

	public boolean isStatisticReportExist(String companyCode, LocalDate date) {
		return statisticReportDao.isStatisticReportExist(companyCode, date);
	}

	@Transactional
	public StatisticReportEntity calculateStatistic(String companyCode, LocalDate date) {

		StatisticReportEntity entity = new StatisticReportEntity();

		entity.setCompanyCode(companyCode);
		entity.setDate(date);

		Object[] maxPriceMinPriceAvgPriceAvgTradedSharesNumOf5 = exchangeDayReportDao.getMaxPriceMinPriceAvgPriceAvgTradedSharesNumOf(companyCode, date, 5);
		Object[] maxPriceMinPriceAvgPriceAvgTradedSharesNumOf10 = exchangeDayReportDao.getMaxPriceMinPriceAvgPriceAvgTradedSharesNumOf(companyCode, date, 10);
		Object[] maxPriceMinPriceAvgPriceAvgTradedSharesNumOf20 = exchangeDayReportDao.getMaxPriceMinPriceAvgPriceAvgTradedSharesNumOf(companyCode, date, 20);
		Object[] maxPriceMinPriceAvgPriceAvgTradedSharesNumOf60 = exchangeDayReportDao.getMaxPriceMinPriceAvgPriceAvgTradedSharesNumOf(companyCode, date, 60);
		Object[] maxPriceMinPriceAvgPriceAvgTradedSharesNumOf120 = exchangeDayReportDao.getMaxPriceMinPriceAvgPriceAvgTradedSharesNumOf(companyCode, date, 120);
		Object[] maxPriceMinPriceAvgPriceAvgTradedSharesNumOf200 = exchangeDayReportDao.getMaxPriceMinPriceAvgPriceAvgTradedSharesNumOf(companyCode, date, 200);
		Object[] maxPriceMinPriceAvgPriceAvgTradedSharesNumOf240 = exchangeDayReportDao.getMaxPriceMinPriceAvgPriceAvgTradedSharesNumOf(companyCode, date, 240);

		entity.setMaxPrice5(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf5[0]));
		entity.setMaxPrice10(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf10[0]));
		entity.setMaxPrice20(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf20[0]));
		entity.setMaxPrice60(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf60[0]));
		entity.setMaxPrice120(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf120[0]));
		entity.setMaxPrice200(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf200[0]));
		entity.setMaxPrice240(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf240[0]));

		entity.setMinPrice5(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf5[1]));
		entity.setMinPrice10(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf10[1]));
		entity.setMinPrice20(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf20[1]));
		entity.setMinPrice60(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf60[1]));
		entity.setMinPrice120(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf120[1]));
		entity.setMinPrice200(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf200[1]));
		entity.setMinPrice240(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf240[1]));

		entity.setAvgPrice5(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf5[2]));
		entity.setAvgPrice10(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf10[2]));
		entity.setAvgPrice20(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf20[2]));
		entity.setAvgPrice60(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf60[2]));
		entity.setAvgPrice120(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf120[2]));
		entity.setAvgPrice200(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf200[2]));
		entity.setAvgPrice240(ConvertUtil.toDouble(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf240[2]));

		entity.setAvgTradedSharesNum5(ConvertUtil.toLong(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf5[3]));
		entity.setAvgTradedSharesNum10(ConvertUtil.toLong(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf10[3]));
		entity.setAvgTradedSharesNum20(ConvertUtil.toLong(maxPriceMinPriceAvgPriceAvgTradedSharesNumOf20[3]));
		entity.setAvgTradedSharesNum60(exchangeDayReportDao.getAvgTradedSharesNumOf(companyCode, date, 60));
		entity.setAvgTradedSharesNum120(exchangeDayReportDao.getAvgTradedSharesNumOf(companyCode, date, 120));

		return entity;
	}

	public void createLastStatisticReportIfNotExist(String companyCode, int recentDays) {
		List<ExchangeDayReportEntity> lastExchangeDayReportList = exchangeDayReportDao.getLastExchangeDayReport(companyCode, recentDays);

		for (ExchangeDayReportEntity exchangeDayReport : lastExchangeDayReportList) {
			if (statisticReportDao.isStatisticReportExist(exchangeDayReport.getCompanyCode(), exchangeDayReport.getDate())) {
				return;
			}

			StatisticReportEntity entity = calculateStatistic(companyCode, exchangeDayReport.getDate());
			createStatisticReport(entity);
		}

	}

	public void createAllLastStatisticReportForSII(int recentDays) {

		List<ListedSecurityDTO> listedSecurityDTOS = listedSecurityManager.listListedSecuritiesByMarketCat(MarketCat.SII.getValue());
		for (ListedSecurityDTO listedSecurityDTO : listedSecurityDTOS) {
			createLastStatisticReportIfNotExist(listedSecurityDTO.getCompanyCode(), recentDays);
		}

	}

	public void createAllLastStatisticReportForOTC(int recentDays) {

		List<ListedSecurityDTO> listedSecurityDTOS = listedSecurityManager.listListedSecuritiesByMarketCat(MarketCat.OTC.getValue());
		for (ListedSecurityDTO listedSecurityDTO : listedSecurityDTOS) {
			createLastStatisticReportIfNotExist(listedSecurityDTO.getCompanyCode(), recentDays);
		}
	}

}
