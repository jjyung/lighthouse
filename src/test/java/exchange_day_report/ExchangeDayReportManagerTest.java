package exchange_day_report;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tw.kgips.dto.exchange_day_report.ExchangeDayReportCreateDTO;
import tw.kgips.manager.ExchangeDayReportManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import static tw.kgips.manager.ExchangeDayReportManager.parseStockExchangeDayReport;
import static tw.kgips.manager.ExchangeDayReportManager.querySIIStockExchangeDayReport;

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
	public void testQueryStockExchangeDayReportAndCreate() throws IOException, InterruptedException {
		String dayReportStr = querySIIStockExchangeDayReport(testCompanyCode, LocalDate.now());
		List<ExchangeDayReportCreateDTO> createDTOList = parseStockExchangeDayReport(dayReportStr);

		assert createDTOList != null;
		for (ExchangeDayReportCreateDTO createDTO : createDTOList) {
			if (exchangeDayReportManager.isExchangeDayReportExist(createDTO.getCompanyCode(), createDTO.getDate())) {
				continue;
			}
			exchangeDayReportManager.createExchangeDayReport(createDTO);
		}

	}

	@Test
	public void testCrawlAllAndCreateByMarketCat() throws InterruptedException {

		for (int month = 7; month < 11; month++) {
			exchangeDayReportManager.crawlAllAndCreateForSII(2019, month);
		}

	}

	@Test
	public void testList() {

	}

	@Test
	public void testDelete() {
	}
}
