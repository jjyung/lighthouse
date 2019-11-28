package statistic_report;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tw.kgips.manager.MarketInfoManager;
import tw.kgips.manager.StatisticReportManager;

import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/spring-core-config.xml"})
public class StatisticReportManagerTest {

    private final String testCompanyCode = "1101";

    @Autowired
    StatisticReportManager statisticReportManager;

    @Test
    public void testIsStatisticReportExist() {

        System.out.println(statisticReportManager.isStatisticReportExist(testCompanyCode, LocalDate.now()));

    }

    @Test
    public void testCreateAllLastStatisticReport() {
        statisticReportManager.createAllLastStatisticReport(5);
    }

}
