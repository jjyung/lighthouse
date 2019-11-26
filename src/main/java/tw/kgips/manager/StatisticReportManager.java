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
        entity.setMaxPrice5(exchangeDayReportDao.getMaxPriceOf(companyCode, date, 5));
        entity.setMaxPrice10(exchangeDayReportDao.getMaxPriceOf(companyCode, date, 10));
        entity.setMaxPrice20(exchangeDayReportDao.getMaxPriceOf(companyCode, date, 20));
        entity.setMaxPrice60(exchangeDayReportDao.getMaxPriceOf(companyCode, date, 60));
        entity.setMaxPrice120(exchangeDayReportDao.getMaxPriceOf(companyCode, date, 120));
        entity.setMaxPrice200(exchangeDayReportDao.getMaxPriceOf(companyCode, date, 200));
        entity.setMaxPrice240(exchangeDayReportDao.getMaxPriceOf(companyCode, date, 240));
        entity.setMinPrice5(exchangeDayReportDao.getMinPriceOf(companyCode, date, 5));
        entity.setMinPrice10(exchangeDayReportDao.getMinPriceOf(companyCode, date, 10));
        entity.setMinPrice20(exchangeDayReportDao.getMinPriceOf(companyCode, date, 20));
        entity.setMinPrice60(exchangeDayReportDao.getMinPriceOf(companyCode, date, 60));
        entity.setMinPrice120(exchangeDayReportDao.getMinPriceOf(companyCode, date, 120));
        entity.setMinPrice200(exchangeDayReportDao.getMinPriceOf(companyCode, date, 200));
        entity.setMinPrice240(exchangeDayReportDao.getMinPriceOf(companyCode, date, 240));
        entity.setAvgPrice5(exchangeDayReportDao.getAvgPriceOf(companyCode, date, 5));
        entity.setAvgPrice10(exchangeDayReportDao.getAvgPriceOf(companyCode, date, 10));
        entity.setAvgPrice20(exchangeDayReportDao.getAvgPriceOf(companyCode, date, 20));
        entity.setAvgPrice60(exchangeDayReportDao.getAvgPriceOf(companyCode, date, 60));
        entity.setAvgPrice120(exchangeDayReportDao.getAvgPriceOf(companyCode, date, 120));
        entity.setAvgPrice200(exchangeDayReportDao.getAvgPriceOf(companyCode, date, 200));
        entity.setAvgPrice240(exchangeDayReportDao.getAvgPriceOf(companyCode, date, 240));
        entity.setAvgTxNum5(exchangeDayReportDao.getAvgTxNumOf(companyCode, date, 5));
        entity.setAvgTxNum10(exchangeDayReportDao.getAvgTxNumOf(companyCode, date, 10));
        entity.setAvgTxNum20(exchangeDayReportDao.getAvgTxNumOf(companyCode, date, 20));
        entity.setAvgTxNum60(exchangeDayReportDao.getAvgTxNumOf(companyCode, date, 60));
        entity.setAvgTxNum120(exchangeDayReportDao.getAvgTxNumOf(companyCode, date, 120));
        entity.setAvgTxNum200(exchangeDayReportDao.getAvgTxNumOf(companyCode, date, 200));
        entity.setAvgTxNum240(exchangeDayReportDao.getAvgTxNumOf(companyCode, date, 240));

        return entity;
    }

    public void createLastStatisticReportIfNotExist(String companyCode) {
        ExchangeDayReportEntity lastExchangeDayReport = exchangeDayReportDao.getLastExchangeDayReport(companyCode);

        if (lastExchangeDayReport == null || statisticReportDao.isStatisticReportExist(lastExchangeDayReport.getCompanyCode(), lastExchangeDayReport.getDate())) {
            return;
        }

        StatisticReportEntity entity = calculateStatistic(companyCode, lastExchangeDayReport.getDate());
        createStatisticReport(entity);
    }

    public void createAllLastStatisticReport() {

        List<ListedSecurityDTO> listedSecurityDTOS = listedSecurityManager.listListedSecuritiesByMarketCat(MarketCat.SII.getValue());
        for (ListedSecurityDTO listedSecurityDTO : listedSecurityDTOS) {
            createLastStatisticReportIfNotExist(listedSecurityDTO.getCompanyCode());
        }
        // TODO OTC
    }

}