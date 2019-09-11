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
    @Test public void getValue() throws IOException, JSONException {
        JSONReader jsonReader = new JSONReader();
        assertEquals(3.9324, jsonReader.getValue('A',"usd"),DELTA);
        assertEquals(4.011, jsonReader.getValue('B',"usd"),DELTA);
        assertEquals(3.9286000000000003, jsonReader.getValue('C',"usd"),DELTA);
    }

    @Test public void getValues() throws IOException, JSONException {
        JSONReader jsonReader = new JSONReader();
        assertEquals(253, jsonReader.getValues('A',"usd","Jeden Rok").length,DELTA);
    }

    @Test public void calculateGrowthSession() throws IOException, JSONException {
        JSONReader jsonReader = new JSONReader();
        assertEquals(1, jsonReader.calculateGrowthSession('A',"usd","Jeden Tydzien"),DELTA);
    }

    @Test public void calculateDownwardSession() throws IOException, JSONException {
        JSONReader jsonReader = new JSONReader();
        assertEquals(4, jsonReader.calculateDownwardSession('A',"usd","Jeden Tydzien"),DELTA);
    }

    @Test public void calculateUnchangedSession() throws IOException, JSONException {
        JSONReader jsonReader = new JSONReader();
        assertEquals(2, jsonReader.calculateUnchangedSession('A',"usd","Jeden Rok"),DELTA);
    }

    @Test public void calculateMedian() throws IOException, JSONException {
        JSONReader jsonReader = new JSONReader();
        assertEquals(3.93195, jsonReader.calculateMedian('A',"usd","Jeden Tydzien"),DELTA);
    }

    @Test public void calculateDominant() throws IOException {
        JSONReader jsonReader = new JSONReader();
        assertEquals(3.8711, jsonReader.calculateDominant('A',"usd","Jeden Miesiac"),DELTA);
    }

    @Test public void calculateStdDev() throws IOException {
        JSONReader jsonReader = new JSONReader();
        assertEquals(0.0294072792619969, jsonReader.calculateStdDev('A',"usd","Jeden Miesiac"),DELTA);
    }

    @Test public void calculateVariationCoefficient() throws IOException {
        JSONReader jsonReader = new JSONReader();
        assertEquals(0.00747686183322603, jsonReader.calculateVariationCoefficient('A',"usd","Jeden Miesiac"),DELTA);
    }

}
