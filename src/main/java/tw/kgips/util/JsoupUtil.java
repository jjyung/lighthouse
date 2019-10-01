package tw.kgips.util;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

public class JsoupUtil {

	public static Element selectFirstRecursively(Element startEl, String cssQuery) {

		Element selectFirst = startEl.selectFirst(cssQuery);

		if (selectFirst != null) {
			return selectFirst;
		}

		for (int i = 0; i < startEl.childNodeSize(); i++) {

			Node child = startEl.childNodes().get(i);

			if (child instanceof TextNode) {
				continue;
			}

			Element childQueryResult = selectFirstRecursively((Element) child, cssQuery);
			if (childQueryResult != null) {
				return childQueryResult;
			}
		}

		return null;
	}

}
