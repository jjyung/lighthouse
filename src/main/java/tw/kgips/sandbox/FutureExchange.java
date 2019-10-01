package tw.kgips.sandbox;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import tw.kgips.util.ConvertUtil;
import tw.kgips.util.JsoupUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class FutureExchange {

	private static String getHtmlFromFile() throws Exception {

		String filename = "futContractsDate.html";
		ClassLoader classLoader = FutureExchange.class.getClassLoader();

		File file = new File(Objects.requireNonNull(classLoader.getResource(filename)).getFile());

		return new String(Files.readAllBytes(file.toPath()));
	}

	private static Long offlineVer() throws Exception {
		Document doc = Jsoup.parse(getHtmlFromFile());
		Element table = ((Element) doc.childNodes().get(1)).selectFirst(".table_f");
		Elements trs = table.selectFirst("tbody").select("tr");
		// 外資
		Element tr = trs.get(5);
		// 多空淨額
		Element net = ((Element) tr.childNodes().get(23)).selectFirst("font");
		String netVal = ((TextNode) net.childNode(0)).getWholeText().trim().replace(",", "");
		return ConvertUtil.toLong(netVal);
	}

	// Foreign Investment Futures
	public static Long getNetForeignInvestmentFutures() throws IOException {

		String certFileName = "taifex.com.tw.jks";
		ClassLoader classLoader = FutureExchange.class.getClassLoader();

		System.setProperty("javax.net.ssl.trustStore", Objects.requireNonNull(classLoader.getResource(certFileName)).getPath());
		Document doc = Jsoup.connect("http://www.taifex.com.tw/cht/3/futContractsDate")
				.timeout(30000)
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36")
				.get();

		Element table = JsoupUtil.selectFirstRecursively(doc, ".table_f");

		if (table == null) {
			throw new RuntimeException("不符期待的 HTML 內容，網頁格式可能有變動。");
		}

		Elements trs = table.selectFirst("tbody").select("tr");
		// 外資
		Element tr = trs.get(5);
		// 多空淨額
		Element net = ((Element) tr.childNodes().get(23)).selectFirst("font");
		String netVal = ((TextNode) net.childNode(0)).getWholeText().trim().replace(",", "");
		return ConvertUtil.toLong(netVal);
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getNetForeignInvestmentFutures());
	}
}
