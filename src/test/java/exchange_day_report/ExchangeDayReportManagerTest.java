package exchange_day_report;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tw.kgips.dto.exchange_day_report.ExchangeDayReportCreateDTO;
import tw.kgips.manager.ExchangeDayReportManager;
import tw.kgips.thread.CrawlerThreadForOTC;
import tw.kgips.thread.CrawlerThreadForSII;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import static tw.kgips.manager.ExchangeDayReportManager.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/spring-core-config.xml"})
public class ExchangeDayReportManagerTest {

	@Autowired
	ExchangeDayReportManager exchangeDayReportManager;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	private static final String testCompanyCodeSII = "1101";
	private static final String testCompanyCodeOTC = "1240";

	@Test
	public void testCreateExchangeDayReport() {
		ExchangeDayReportCreateDTO dto = new ExchangeDayReportCreateDTO();
		dto.setCompanyCode(testCompanyCodeSII);
		dto.setDate(OffsetDateTime.now().toLocalDate());
		exchangeDayReportManager.createExchangeDayReport(dto);
	}

	@Test
	public void testQueryStockExchangeDayReportAndCreate() throws IOException, InterruptedException {
		String dayReportStr = querySIIStockExchangeDayReport(testCompanyCodeSII, LocalDate.now());
		List<ExchangeDayReportCreateDTO> createDTOList = parseSIIStockExchangeDayReport(dayReportStr);

		assert createDTOList != null;
		for (ExchangeDayReportCreateDTO createDTO : createDTOList) {
			if (exchangeDayReportManager.isExchangeDayReportExist(createDTO.getCompanyCode(), createDTO.getDate())) {
				continue;
			}
			exchangeDayReportManager.createExchangeDayReport(createDTO);
		}

	}

	@Test
	public void testQueryOTCStockExchangeDayReport() throws IOException, InterruptedException {
		String dayReportStr = queryOTCStockExchangeDayReport(testCompanyCodeOTC, LocalDate.now());
		System.out.println(dayReportStr);
		List<ExchangeDayReportCreateDTO> createDTOList = parseOTCStockExchangeDayReport(dayReportStr);
	}

	@Test
	public void testCrawlAllAndCreate() throws InterruptedException {

		CrawlerThreadForOTC crawlerThreadForOTC = applicationContext.getBean(CrawlerThreadForOTC.class);
		taskExecutor.execute(crawlerThreadForOTC);

		CrawlerThreadForSII crawlerThreadForSII = applicationContext.getBean(CrawlerThreadForSII.class);
		taskExecutor.execute(crawlerThreadForSII);

		while (taskExecutor.getActiveCount() > 0) {
			Thread.sleep(5000);
		}
	}

	@Test
	public void testCrawlSIIAllAndCreate() throws InterruptedException {

		exchangeDayReportManager.crawlAllAndCreateForSII(2019, LocalDate.now().getMonth().getValue());

	}

	@Test
	public void testCrawlOTCAllAndCreate() throws InterruptedException {

		exchangeDayReportManager.crawlAllAndCreateForOTC(2019, LocalDate.now().getMonth().getValue());

	}

	@Test
	public void testList() {

	}

	@Test
	public void testDelete() {
	}
}
