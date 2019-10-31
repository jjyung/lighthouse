package tw.kgips.sandbox;

import org.json.JSONArray;
import org.json.JSONObject;
import tw.kgips.util.ConvertUtil;
import tw.kgips.util.Util;

public class TWSE {

    public static Long getFININetBuyAmount() throws Exception {

        Util.setSSL();

        // 三大法人買賣金額統計表
        String json = Util.getJSON("https://www.twse.com.tw/fund/BFI82U?response=json&dayDate=&weekDate=&monthDate=&type=day", 30000);

        if (json == null) {
            throw new RuntimeException("證交所網頁異常");
        }

        JSONObject jsonObject = new JSONObject(json);
        JSONArray dataArray = jsonObject.getJSONArray("data");
        // 外資及陸資(不含外資自營商)
        JSONArray fiArray = dataArray.getJSONArray(3);
        // 買賣差額
        String strVal = ConvertUtil.toString(fiArray.get(3)).replace(",", "");

        return ConvertUtil.toLong(strVal);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getFININetBuyAmount());
    }

}
