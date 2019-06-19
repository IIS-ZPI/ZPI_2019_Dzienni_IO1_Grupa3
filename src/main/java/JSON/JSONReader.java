package JSON;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;


public class JSONReader {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static void main(String[] args) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/c/usd/?format=json");
        System.out.println(json.get("currency"));
        System.out.println(json.get("code"));
        JSONArray jsonarray = (JSONArray) json.get("rates");
        JSONObject rates=(JSONObject) jsonarray.getJSONObject(0);
        System.out.println(rates.get("no"));
        System.out.println(rates.get("effectiveDate"));
        System.out.println(rates.get("bid"));
        System.out.println(rates.get("ask"));
    }
}