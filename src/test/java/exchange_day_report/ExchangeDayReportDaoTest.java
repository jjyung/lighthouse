package exchange_day_report;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tw.kgips.dto.DateRangeDTO;
import tw.kgips.persistence.dao.ExchangeDayReportDao;
import tw.kgips.persistence.entity.ExchangeDayReportEntity;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/spring-core-config.xml"})
public class ExchangeDayReportDaoTest {

    @Autowired
    ExchangeDayReportDao exchangeDayReportDao;

    private static final String testCompanyCode = "1101";

    @Test
    public void testCreateExchangeDayReport() {
        ExchangeDayReportEntity entity = new ExchangeDayReportEntity();
        entity.setCompanyCode(testCompanyCode);
        entity.setDate(OffsetDateTime.now().toLocalDate());
        exchangeDayReportDao.createExchangeDayReport(entity);
    }

    @Test
    public void testIsExchangeDayReportExist() {

        LocalDate date = LocalDate.now();

        ExchangeDayReportEntity entity = exchangeDayReportDao.getExchangeDayReport(testCompanyCode, date);

        if (entity == null) {
            testCreateExchangeDayReport();
        }

        Assert.assertTrue(exchangeDayReportDao.isExchangeDayReportExist(testCompanyCode, date));
    }

    @Test
    public void testGetExchangeDayReport() {

        LocalDate date = LocalDate.now();

        ExchangeDayReportEntity entity = exchangeDayReportDao.getExchangeDayReport(testCompanyCode, date);

        if (entity == null) {
            testCreateExchangeDayReport();
            entity = exchangeDayReportDao.getExchangeDayReport(testCompanyCode, date);
        }

        Assert.assertNotNull(entity);
    }

    @Test
    public void testGetAvgPriceOf() {
        System.out.println(exchangeDayReportDao.getAvgPriceOf(testCompanyCode, LocalDate.now(), 20));
        System.out.println(exchangeDayReportDao.getAvgPriceOf(testCompanyCode, LocalDate.now(), 60));
        System.out.println(exchangeDayReportDao.getAvgPriceOf(testCompanyCode, LocalDate.now(), 120));
    }

    @Test
    public void testList() {

        DateRangeDTO dateRangeDTO = new DateRangeDTO(LocalDate.now(), true, LocalDate.now().plusDays(1L), false);

        List<ExchangeDayReportEntity> exchangeDayReportEntities = exchangeDayReportDao.listExchangeDayReportByCompanyCodeAndDateRange(testCompanyCode, dateRangeDTO);

        if (exchangeDayReportEntities.isEmpty()) {
            testCreateExchangeDayReport();
            exchangeDayReportEntities = exchangeDayReportDao.listExchangeDayReportByCompanyCodeAndDateRange(testCompanyCode, dateRangeDTO);
        }

        Assert.assertThat(exchangeDayReportEntities.size(), Matchers.greaterThan(0));

    }

    @Test
    public void testDelete() {
    }
}
