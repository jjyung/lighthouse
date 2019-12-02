package tw.kgips.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tw.kgips.manager.ExchangeDayReportManager;

import java.time.LocalDate;

@Component
@Scope("prototype")
public class CrawlerThreadForOTC extends Thread {

	private ExchangeDayReportManager exchangeDayReportManager;

	@Autowired
	public void setExchangeDayReportManager(ExchangeDayReportManager exchangeDayReportManager) {
		this.exchangeDayReportManager = exchangeDayReportManager;
	}

	@Override
	public void run() {

		try {
			exchangeDayReportManager.crawlAllAndCreateForOTC(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
