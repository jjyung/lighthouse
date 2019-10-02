package tw.kgips.sandbox;

import org.json.JSONArray;
import org.json.JSONObject;
import tw.kgips.util.ConvertUtil;
import tw.kgips.util.Util;

public class TWSE {

	public static String getFININetBuyAmount() throws Exception {

		Util.setSSL();

		// 三大法人買賣金額統計表
		String json = Util.getJSON("https://www.twse.com.tw/fund/BFI82U?response=json&dayDate=&weekDate=&monthDate=&type=day", 30000);
		JSONObject jsonObject = new JSONObject(json);
		JSONArray dataArray = jsonObject.getJSONArray("data");
		// 外資及陸資(不含外資自營商)
		JSONArray fiArray = dataArray.getJSONArray(3);
		// 買賣差額
		String str = ConvertUtil.toString(fiArray.get(3));

		return str.replace(",", "");
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getFININetBuyAmount());
	}

}
