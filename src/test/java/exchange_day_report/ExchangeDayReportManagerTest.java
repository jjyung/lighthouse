package exchange_day_report;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tw.kgips.manager.ExchangeDayReportManager;
import tw.kgips.persistence.entity.ExchangeDayReportEntity;

import java.time.OffsetDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/spring-core-config.xml"})
public class ExchangeDayReportManagerTest {

    @Autowired
    ExchangeDayReportManager exchangeDayReportManager;

    private static final String testCompanyCode = "1101";

    @Test
    public void testCreateExchangeDayReport() {
        ExchangeDayReportEntity entity = new ExchangeDayReportEntity();
        entity.setCompanyCode(testCompanyCode);
        entity.setDate(OffsetDateTime.now().toLocalDate());
        exchangeDayReportManager.createExchangeDayReport(entity);
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
