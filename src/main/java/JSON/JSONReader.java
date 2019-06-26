package JSON;


import org.joda.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;


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

        System.out.println(getValue('A',"usd"));
        System.out.println(getValue('B',"usd"));
        System.out.println(getValue('C',"usd"));
        System.out.println(getValues('A',"usd","JedenRok").length);
        System.out.println(calculateGrowthSession('A',"usd","JedenTydzien"));
        System.out.println(calculateDownwardSession('A',"usd","JedenTydzien"));
        System.out.println(calculateUnchangedSession('A',"usd","JedenRok"));*/
        //System.out.println(calculateMedian('A',"usd","JedenTydzien"));
        //System.out.println(calculateDominant('A',"usd","JedenMiesiac"));
        System.out.println(calculateUnchangedSession('A',"usd","JedenRok"));
        System.out.println(calculateMedian('A',"usd","JedenTydzien"));
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
        LocalDate.now();
        JSONObject json;
        double[] value = new double[365];

        switch(period) {
            case "JedenTydzien":
                json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/" + table + "/" + currency + "/" + LocalDate.now().minusWeeks(1) + "/" + LocalDate.now() + "/?format=json");
                value = getDoubles(table, json);
                break;

            case "DwaTygodnie":
                json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/" + table + "/" + currency + "/" + LocalDate.now().minusWeeks(2) + "/" + LocalDate.now() + "/?format=json");
                value = getDoubles(table, json);
                break;

            case "JedenMiesiac":
                json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/" + table + "/" + currency + "/" + LocalDate.now().minusMonths(1) + "/" + LocalDate.now() + "/?format=json");
                value = getDoubles(table, json);
                break;

            case "JedenKwartal":
                json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/" + table + "/" + currency + "/" + LocalDate.now().minusMonths(3) + "/" + LocalDate.now() + "/?format=json");
                value = getDoubles(table, json);
                break;
            case "PolRoku":
                json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/" + table + "/" + currency + "/" + LocalDate.now().minusMonths(6) + "/" + LocalDate.now() + "/?format=json");
                value = getDoubles(table, json);
                break;
            case "JedenRok":
                json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/" + table + "/" + currency + "/" + LocalDate.now().minusYears(1) + "/" + LocalDate.now() + "/?format=json");
                value = getDoubles(table, json);
                break;
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

        return value;
    }

    private static double[] getDoubles(char table, JSONObject json) {
        double[] value;
        String valueInString;
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
        return value;
    }

    public static int calculateGrowthSession(char table, String currency, String period ) throws IOException {

        int sessionAmount = 0;

        double[] value = getValues(table, currency, period);

        for(int index=0;index<value.length-1;index++)
        {
            if(value[index+1]>value[index])
            {
                sessionAmount++;
            }
        }

        return  sessionAmount;
    }

    public static int calculateDownwardSession(char table, String currency, String period ) throws IOException {

        int sessionAmount = 0;

        double[] value = getValues(table, currency, period);

        for(int index=0;index<value.length-1;index++)
        {
            if(value[index+1]<value[index])
            {
                sessionAmount++;
            }
        }

        return  sessionAmount;
    }

    public static int calculateUnchangedSession(char table, String currency, String period ) throws IOException {

        int sessionAmount = 0;

        double[] value = getValues(table, currency, period);

        for(int index=0;index<value.length-1;index++)
        {
            if(value[index+1]==value[index])
            {
                sessionAmount++;
            }
        }

        return  sessionAmount;
    }

    public static double calculateMedian(char table, String currency, String period)throws IOException {

        double[] value = getValues(table, currency, period);
        Arrays.sort(value);

        int middle = value.length/2;
        if (value.length%2 == 1) {
            return value[middle];
        } else {
            return (value[middle-1] + value[middle]) / 2.0;
        }

    }

    public static double calculateDominant(char table, String currency, String period) throws IOException{

        double[] value = getValues(table, currency, period);

        double maxValue = 0;
        int maxCount = 0;

        for (int i = 0; i < value.length; ++i) {
            int count = 0;
            for (int j = 0; j < value.length; ++j) {
                if (value[j] == value[i]) ++count;
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = value[i];
            }
        }

        return maxValue;
    }
}
