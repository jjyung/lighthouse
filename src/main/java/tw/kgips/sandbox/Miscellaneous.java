package tw.kgips.sandbox;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Miscellaneous {

	public static void main(String[] args) {

		OffsetDateTime now = OffsetDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd");
		DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyyMMdd");
		DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("yyy/MM");

		System.out.println(formatter.format(now));
		System.out.println(formatter2.format(now));
		System.out.println(formatter3.format(now));
		System.out.println(formatter4.format(now.minusYears(1911)));

	}

}
