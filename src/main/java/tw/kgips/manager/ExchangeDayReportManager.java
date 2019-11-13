package tw.kgips.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tw.kgips.persistence.dao.ExchangeDayReportDao;
import tw.kgips.persistence.entity.ExchangeDayReportEntity;

@Component
public class ExchangeDayReportManager {

    private final ExchangeDayReportDao exchangeDayReportDao;

    @Autowired
    public ExchangeDayReportManager(ExchangeDayReportDao exchangeDayReportDao) {
        this.exchangeDayReportDao = exchangeDayReportDao;
    }

    public void createExchangeDayReport(ExchangeDayReportEntity entity) {
        this.exchangeDayReportDao.createExchangeDayReport(entity);
    }

}
