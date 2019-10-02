package tw.kgips.sandbox;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import tw.kgips.util.ConvertUtil;
import tw.kgips.util.JsoupUtil;

import java.io.File;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.Objects;

public class FutureExchange {

	private static String getHtmlFile(String filename) throws Exception {
		ClassLoader classLoader = FutureExchange.class.getClassLoader();
		File file = new File(Objects.requireNonNull(classLoader.getResource(filename)).getFile());
		return new String(Files.readAllBytes(file.toPath()));
	}

	private static Document getOfflineDoc(String filename) throws Exception {
		return Jsoup.parse(getHtmlFile(filename));
	}

	private static void setSSL() {
		String certFileName = "taifex.com.tw.jks";
		ClassLoader classLoader = FutureExchange.class.getClassLoader();
		System.setProperty("javax.net.ssl.trustStore", Objects.requireNonNull(classLoader.getResource(certFileName)).getPath());
	}

	// TODO check date
	// 外資淨多單
	public static String getNetForeignInvestmentFutures() throws Exception {

		setSSL();

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
		return ((TextNode) net.childNode(0)).getWholeText().trim().replace(",", "");
	}

	// TODO check date
	// put/call ratio
	public static String getPutCallRatio() throws Exception {

		setSSL();

		Document doc = Jsoup.connect("https://www.taifex.com.tw/cht/3/largeTraderOptQry")
				.timeout(30000)
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36")
				.get();

		Element table = JsoupUtil.selectFirstRecursively(doc, ".table_f");

		if (table == null) {
			throw new RuntimeException("不符期待的 HTML 內容，網頁格式可能有變動。");
		}

		Elements trs = table.selectFirst("tbody").select("tr");

		// 買權
		Element mCallTr = trs.get(4);
		String callValStr = ((TextNode) mCallTr.select("td").get(9).selectFirst("div").childNode(0)).getWholeText().trim().replace(",", "");
		Long call = ConvertUtil.toLong(callValStr);

		// 賣權
		Element mPutTr = trs.get(7);
		String putValStr = ((TextNode) mPutTr.select("td").get(9).selectFirst("div").childNode(0)).getWholeText().trim().replace(",", "");
		Long put = ConvertUtil.toLong(putValStr);

		// 小數點第二位
		DecimalFormat df = new DecimalFormat("##.00");

		return ConvertUtil.toString(df.format(put * 1.0 / call * 1.0));
	}

	public static void main(String[] args) throws Exception {
		System.out.println(String.format("%s 外資大台淨多單 %s 口，p/c %s，外資現貨買超 %s 億。", null, getNetForeignInvestmentFutures(), getPutCallRatio(), null));
	}
}
