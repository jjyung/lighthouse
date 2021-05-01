package tw.kgips.sandbox;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.kgips.exception.UnknownHtmlException;
import tw.kgips.util.ConvertUtil;
import tw.kgips.util.JsoupUtil;
import tw.kgips.util.Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FutureExchange {

    public static final Logger logger = LoggerFactory.getLogger(FutureExchange.class);

    private static String getHtmlFile(String filename) throws IOException {
        ClassLoader classLoader = FutureExchange.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(filename)).getFile());
        return new String(Files.readAllBytes(file.toPath()));
    }

    private static Document getOfflineDoc(String filename) throws IOException {
        return Jsoup.parse(getHtmlFile(filename));
    }

    // TODO check date
    // 外資淨多單
    public static Long getFININetAmount() throws IOException {

        Util.setSSL();

        Document doc = Jsoup.connect("https://www.taifex.com.tw/cht/3/futContractsDate")
            .timeout(30000)
            .userAgent(
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36")
            .get();

        Element table = JsoupUtil.selectFirstRecursively(doc, ".table_f");

        if (table == null) {
            throw new UnknownHtmlException("不符期待的 HTML 內容，網頁格式可能有變動。");
        }

        Elements trs = table.selectFirst("tbody").select("tr");
        // 外資
        Element tr = trs.get(5);
        // 多空淨額
        Element net = ((Element) tr.childNodes().get(23)).selectFirst("font");

        String netString = ((TextNode) net.childNode(0)).getWholeText().trim().replace(",", "");

        return ConvertUtil.toLong(netString);
    }

    // TODO check date
    // put/call ratio
    public static Double getPutCallRatio() throws IOException {

        Util.setSSL();

        Document doc = Jsoup.connect("https://www.taifex.com.tw/cht/3/largeTraderOptQry")
            .timeout(30000)
            .userAgent(
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36")
            .get();

        Element table = JsoupUtil.selectFirstRecursively(doc, ".table_f");

        if (table == null) {
            throw new UnknownHtmlException("不符期待的 HTML 內容，網頁格式可能有變動。");
        }

        Elements trs = table.selectFirst("tbody").select("tr");

        // 買權
        Element mCallTr = trs.get(4);
        String callValStr = ((TextNode) mCallTr.select("td").get(9).selectFirst("div").childNode(0)).getWholeText()
            .trim().replace(",", "");
        Long call = ConvertUtil.toLong(callValStr);

        // 賣權
        Element mPutTr = trs.get(7);
        String putValStr = ((TextNode) mPutTr.select("td").get(9).selectFirst("div").childNode(0)).getWholeText().trim()
            .replace(",", "");
        Long put = ConvertUtil.toLong(putValStr);

        if (put == null || put == 0 || call == null || call == 0) {
            return 0.0;
        }

        return put * 1.0D / call;
    }

    public static void main(String[] args) throws Exception {

        OffsetDateTime now = OffsetDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");

        // 小數點第二位
        DecimalFormat df = new DecimalFormat("##.00");

        Long netBuy = ConvertUtil.toLong(TWSE.getFININetBuyAmount());

        String sellBuy = netBuy > 0 ? "買" : "賣";

        String finiNetBuyAmount = df.format(Math.abs(netBuy) / 100000000.0);

        logger.info(String.format("%s 外資大台淨多單 %s 口，p/c %s，外資現貨%s超 %s 億。%n",
            formatter.format(now), getFININetAmount(), df.format(getPutCallRatio()), sellBuy, finiNetBuyAmount));
    }
}
