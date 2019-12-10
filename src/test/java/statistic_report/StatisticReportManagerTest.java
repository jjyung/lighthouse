package statistic_report;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;
import tw.kgips.config.AppConfig;
import tw.kgips.manager.StatisticReportManager;
import tw.kgips.thread.StatisticThreadForOTC;
import tw.kgips.thread.StatisticThreadForSII;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppConfig.class})
public class StatisticReportManagerTest {

	private final String testCompanyCode = "1101";

	@Autowired
	StatisticReportManager statisticReportManager;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Test
	public void testIsStatisticReportExist() {

		System.out.println(statisticReportManager.isStatisticReportExist(testCompanyCode, LocalDate.now()));

	}

	@Test
	public void testCreateAllLastStatisticReportForSII() {
		statisticReportManager.createAllLastStatisticReportForSII(1);
	}

	@Test
	public void testCreateAllLastStatisticReportForOTC() {
		statisticReportManager.createAllLastStatisticReportForOTC(1);
	}

	@Test
	public void testCreateAllLastStatisticReport() throws InterruptedException {
		StatisticThreadForOTC statisticThreadForOTC = applicationContext.getBean(StatisticThreadForOTC.class);
		taskExecutor.execute(statisticThreadForOTC);

		StatisticThreadForSII statisticThreadForSII = applicationContext.getBean(StatisticThreadForSII.class);
		taskExecutor.execute(statisticThreadForSII);

		while (taskExecutor.getActiveCount() > 0) {
			Thread.sleep(5000);
		}
	}

}
