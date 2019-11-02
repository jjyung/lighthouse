package tw.kgips.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

//@Entity
//@Table(name = "exchange_day_report")
public class ExchangeDayReportEntity {

	private String companyCode;
	private LocalDateTime date;
	private Long number;


}
