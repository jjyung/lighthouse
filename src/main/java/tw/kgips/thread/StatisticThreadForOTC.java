package tw.kgips.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tw.kgips.manager.ExchangeDayReportManager;
import tw.kgips.manager.StatisticReportManager;

import java.time.LocalDate;

@Component
@Scope("prototype")
public class StatisticThreadForOTC extends Thread {

	private StatisticReportManager statisticReportManager;

	@Autowired
	public void setStatisticReportManager(StatisticReportManager statisticReportManager) {
		this.statisticReportManager = statisticReportManager;
	}

	@Override
	public void run() {
		statisticReportManager.createAllLastStatisticReportForOTC(1);
	}

}
