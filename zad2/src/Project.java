import org.json.simple.JSONObject;

public class Project {

    public static void main(String[] args) {
        JSONReader jsonReader = new JSONReader();
        JSONObject jo = jsonReader.parseJSON("zad2\\src\\JSONExample.json");
        String firstName = (String) jo.get("firstName");
        String lastName = (String) jo.get("lastName");
        System.out.println(firstName);
        System.out.println(lastName);

    }
}
