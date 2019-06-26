package JSON;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class JSONReaderTest {
    private static final double DELTA = 1e-15;
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
    @Test public void getValue() throws IOException {
        JSONReader jsonReader = new JSONReader();
        assertEquals(3.754, jsonReader.getValue('A',"usd"),DELTA);
        assertEquals(4.011, jsonReader.getValue('B',"usd"),DELTA);
        assertEquals(3.7357, jsonReader.getValue('C',"usd"),DELTA);
    }

    @Test public void getValues() throws IOException {
        JSONReader jsonReader = new JSONReader();
        assertEquals(253, jsonReader.getValues('A',"usd","JedenRok").length,DELTA);
    }

    @Test public void calculateGrowthSession() throws IOException {
        JSONReader jsonReader = new JSONReader();
        assertEquals(2, jsonReader.calculateGrowthSession('A',"usd","JedenTydzien"),DELTA);
    }

    @Test public void calculateDownwardSession() throws IOException {
        JSONReader jsonReader = new JSONReader();
        assertEquals(2, jsonReader.calculateDownwardSession('A',"usd","JedenTydzien"),DELTA);
    }

    @Test public void calculateUnchangedSession() throws IOException {
        JSONReader jsonReader = new JSONReader();
        assertEquals(2, jsonReader.calculateUnchangedSession('A',"usd","JedenRok"),DELTA);
    }

    @Test public void calculateMedian() throws IOException {
        JSONReader jsonReader = new JSONReader();
        assertEquals(3.754, jsonReader.calculateMedian('A',"usd","JedenTydzien"),DELTA);
    }

}
