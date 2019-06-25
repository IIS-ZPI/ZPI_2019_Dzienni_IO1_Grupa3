package JSON;



import org.joda.time.LocalDate;
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
        //System.out.println(getValues('A',"usd","JedenRok").length);
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

    public static double[] getValues(char table, String currency, String period) throws IOException, JSONException {
        String valueInString;
        LocalDate.now();
        double[] value = new double[365];

        if(period=="JedenTydzien")
        {
            JSONObject json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/"+table+"/"+currency+ "/"+ LocalDate.now().minusWeeks(1)+"/"+LocalDate.now()+"/?format=json");
            JSONArray jsonarray = (JSONArray) json.get("rates");
            value = new double[jsonarray.length()];
            for(int index=0; index<jsonarray.length(); index++)
            {
                JSONObject rates=(JSONObject) jsonarray.getJSONObject(index);
                if(table=='A' || table=='B')
                {
                    valueInString = rates.get("mid").toString();
                    value[index] = Double.parseDouble(valueInString);
                }
                if(table=='C')
                {
                    valueInString = rates.get("bid").toString();
                    value[index] = Double.parseDouble(valueInString);
                    valueInString = rates.get("ask").toString();
                    value[index] = (value[index] + Double.parseDouble(valueInString))/2;
                }
            }
        }

        if(period=="DwaTygodnie")
        {
            JSONObject json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/"+table+"/"+currency+ "/"+ LocalDate.now().minusWeeks(2)+"/"+LocalDate.now()+"/?format=json");
            JSONArray jsonarray = (JSONArray) json.get("rates");
            value = new double[jsonarray.length()];
            for(int index=0; index<jsonarray.length(); index++)
            {
                JSONObject rates=(JSONObject) jsonarray.getJSONObject(index);
                if(table=='A' || table=='B')
                {
                    valueInString = rates.get("mid").toString();
                    value[index] = Double.parseDouble(valueInString);
                }
                if(table=='C')
                {
                    valueInString = rates.get("bid").toString();
                    value[index] = Double.parseDouble(valueInString);
                    valueInString = rates.get("ask").toString();
                    value[index] = (value[index] + Double.parseDouble(valueInString))/2;
                }
            }
        }

        if(period=="JedenMiesiac")
        {
            JSONObject json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/"+table+"/"+currency+ "/"+ LocalDate.now().minusMonths(1)+"/"+LocalDate.now()+"/?format=json");
            JSONArray jsonarray = (JSONArray) json.get("rates");
            value = new double[jsonarray.length()];
            for(int index=0; index<jsonarray.length(); index++)
            {
                JSONObject rates=(JSONObject) jsonarray.getJSONObject(index);
                if(table=='A' || table=='B')
                {
                    valueInString = rates.get("mid").toString();
                    value[index] = Double.parseDouble(valueInString);
                }
                if(table=='C')
                {
                    valueInString = rates.get("bid").toString();
                    value[index] = Double.parseDouble(valueInString);
                    valueInString = rates.get("ask").toString();
                    value[index] = (value[index] + Double.parseDouble(valueInString))/2;
                }
            }
        }

        if(period=="JedenMiesiac")
        {
            JSONObject json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/"+table+"/"+currency+ "/"+ LocalDate.now().minusMonths(1)+"/"+LocalDate.now()+"/?format=json");
            JSONArray jsonarray = (JSONArray) json.get("rates");
            value = new double[jsonarray.length()];
            for(int index=0; index<jsonarray.length(); index++)
            {
                JSONObject rates=(JSONObject) jsonarray.getJSONObject(index);
                if(table=='A' || table=='B')
                {
                    valueInString = rates.get("mid").toString();
                    value[index] = Double.parseDouble(valueInString);
                }
                if(table=='C')
                {
                    valueInString = rates.get("bid").toString();
                    value[index] = Double.parseDouble(valueInString);
                    valueInString = rates.get("ask").toString();
                    value[index] = (value[index] + Double.parseDouble(valueInString))/2;
                }
            }
        }

        if(period=="JedenKwartal")
        {
            JSONObject json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/"+table+"/"+currency+ "/"+ LocalDate.now().minusMonths(3)+"/"+LocalDate.now()+"/?format=json");
            JSONArray jsonarray = (JSONArray) json.get("rates");
            value = new double[jsonarray.length()];
            for(int index=0; index<jsonarray.length(); index++)
            {
                JSONObject rates=(JSONObject) jsonarray.getJSONObject(index);
                if(table=='A' || table=='B')
                {
                    valueInString = rates.get("mid").toString();
                    value[index] = Double.parseDouble(valueInString);
                }
                if(table=='C')
                {
                    valueInString = rates.get("bid").toString();
                    value[index] = Double.parseDouble(valueInString);
                    valueInString = rates.get("ask").toString();
                    value[index] = (value[index] + Double.parseDouble(valueInString))/2;
                }
            }
        }

        if(period=="PolRoku")
        {
            JSONObject json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/"+table+"/"+currency+ "/"+ LocalDate.now().minusMonths(6)+"/"+LocalDate.now()+"/?format=json");
            JSONArray jsonarray = (JSONArray) json.get("rates");
            value = new double[jsonarray.length()];
            for(int index=0; index<jsonarray.length(); index++)
            {
                JSONObject rates=(JSONObject) jsonarray.getJSONObject(index);
                if(table=='A' || table=='B')
                {
                    valueInString = rates.get("mid").toString();
                    value[index] = Double.parseDouble(valueInString);
                }
                if(table=='C')
                {
                    valueInString = rates.get("bid").toString();
                    value[index] = Double.parseDouble(valueInString);
                    valueInString = rates.get("ask").toString();
                    value[index] = (value[index] + Double.parseDouble(valueInString))/2;
                }
            }
        }

        if(period=="JedenRok")
        {
            JSONObject json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/"+table+"/"+currency+ "/"+ LocalDate.now().minusYears(1)+"/"+LocalDate.now()+"/?format=json");
            JSONArray jsonarray = (JSONArray) json.get("rates");
            value = new double[jsonarray.length()];
            for(int index=0; index<jsonarray.length(); index++)
            {
                JSONObject rates=(JSONObject) jsonarray.getJSONObject(index);
                if(table=='A' || table=='B')
                {
                    valueInString = rates.get("mid").toString();
                    value[index] = Double.parseDouble(valueInString);
                }
                if(table=='C')
                {
                    valueInString = rates.get("bid").toString();
                    value[index] = Double.parseDouble(valueInString);
                    valueInString = rates.get("ask").toString();
                    value[index] = (value[index] + Double.parseDouble(valueInString))/2;
                }
            }
        }

        return value;
    }
}
