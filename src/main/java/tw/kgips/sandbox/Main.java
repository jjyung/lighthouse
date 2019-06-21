package tw.kgips.sandbox;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

	private static String getHtmlFromFile() throws Exception {
		return new String(Files.readAllBytes(Paths.get("./futContractsDate")));
	}


	public static void main(String[] args) throws Exception {
//		Document doc = Jsoup.parse("http://www.taifex.com.tw/cht/3/futContractsDate");
		Document doc = Jsoup.parse(getHtmlFromFile());
		return;
	}
}
