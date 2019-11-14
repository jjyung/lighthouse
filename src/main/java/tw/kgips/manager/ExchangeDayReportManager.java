package tw.kgips.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tw.kgips.dto.exchange_day_report.ExchangeDayReportCreateDTO;
import tw.kgips.persistence.dao.ExchangeDayReportDao;

@Component
public class ExchangeDayReportManager {

    private final ExchangeDayReportDao exchangeDayReportDao;

    @Autowired
    public ExchangeDayReportManager(ExchangeDayReportDao exchangeDayReportDao) {
        this.exchangeDayReportDao = exchangeDayReportDao;
    }

    public void createExchangeDayReport(ExchangeDayReportCreateDTO createDTO) {
        this.exchangeDayReportDao.createExchangeDayReport(createDTO.toEntity());
    }

}
