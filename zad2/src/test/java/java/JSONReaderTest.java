package java;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

class JSONReaderTest {
    @Test
    void parseJSON() {
        try {
            JSONReader jsonReader = new JSONReader();
            JSONObject jo = jsonReader.readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/c/usd/?format=json");
            assertEquals("dolar amerykański", jo.get("currency"));
            assertEquals("USD", jo.get("code"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
