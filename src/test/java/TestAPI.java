import static org.junit.Assert.assertEquals;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import util.APIUtil;


public class TestAPI {
    @Test
    public void testAPIRequest() {      
      String result = APIUtil.APIRequest("https://pokeapi.co/api/v2/pokemon/ditto", null);
      
      JSONParser parser = new JSONParser();
      Object body = null;
      try {
        body = parser.parse(result);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      JSONObject data = (JSONObject)body;
      JSONArray formsArr = (JSONArray)data.get("forms");
      JSONObject forms = (JSONObject)formsArr.get(0);
      
      assertEquals("ditto", forms.get("name"));
      assertEquals("https://pokeapi.co/api/v2/pokemon-form/132/", forms.get("url"));
    }
}
