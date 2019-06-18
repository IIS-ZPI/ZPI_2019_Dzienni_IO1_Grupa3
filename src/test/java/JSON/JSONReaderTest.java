package JSON;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class JSONReaderTest {

    @Test public void parseJSON() {
        try {
            JSONReader jsonReader = new JSONReader();
            JSONObject jo = jsonReader.readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/c/usd/?format=json");
            assertEquals("dolar amerykański", jo.get("currency"));
            assertEquals("USD", jo.get("code"));
        } catch (IOException ioe) {
            fail("JSON nie pobrał/otworzył się poprawnie");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
