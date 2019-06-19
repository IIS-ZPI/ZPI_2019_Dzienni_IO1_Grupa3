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
        /*JSONObject json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/c/usd/?format=json");
        System.out.println(json.get("currency"));
        System.out.println(json.get("code"));
        JSONArray jsonarray = (JSONArray) json.get("rates");
        JSONObject rates=(JSONObject) jsonarray.getJSONObject(0);
        System.out.println(rates.get("no"));
        System.out.println(rates.get("effectiveDate"));
        System.out.println(rates.get("bid"));
        System.out.println(rates.get("ask"));*/

        /*System.out.println(getValue('A',"usd"));
        System.out.println(getValue('B',"usd"));
        System.out.println(getValue('C',"usd"));*/
    }

    public static double getValue(char table, String currency) throws IOException, JSONException {
        String valueInString;
        double value = 0;
        JSONObject json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/"+table+"/"+currency+ "/?format=json");
        JSONArray jsonarray = (JSONArray) json.get("rates");
        JSONObject rates=(JSONObject) jsonarray.getJSONObject(0);
        if(table=='A' || table=='B')
        {
            valueInString = rates.get("mid").toString();
            value = Double.parseDouble(valueInString);
        }
        if(table=='C')
        {
            valueInString = rates.get("bid").toString();
            value = Double.parseDouble(valueInString);
            valueInString = rates.get("ask").toString();
            value = (value + Double.parseDouble(valueInString))/2;
        }
        return value;
    }
}
