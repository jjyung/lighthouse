package exchange_day_report;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tw.kgips.dto.exchange_day_report.ExchangeDayReportCreateDTO;
import tw.kgips.manager.ExchangeDayReportManager;

import java.time.OffsetDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/spring-core-config.xml"})
public class ExchangeDayReportManagerTest {

    @Autowired
    ExchangeDayReportManager exchangeDayReportManager;

    private static final String testCompanyCode = "1101";

    @Test
    public void testCreateExchangeDayReport() {
        ExchangeDayReportCreateDTO dto = new ExchangeDayReportCreateDTO();
        dto.setCompanyCode(testCompanyCode);
        dto.setDate(OffsetDateTime.now().toLocalDate());
        exchangeDayReportManager.createExchangeDayReport(dto);
    }

    @Test
    public void testGetExchangeDayReport() {

    }

    @Test
    public void testList() {

    }

    @Test
    public void testDelete() {
    }
}
