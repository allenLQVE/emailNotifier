/**
 * Name: HungHsu(Allen) Chen 
 * Course: CS-665 Software Designs & Patterns 
 * Date: 12/01/2024 
 * File Name: Weather.java 
 * Description: A content decorator that add weather information on the content.
 */

package content.contentdecorator;

import content.Content;
import util.APIUtil;
import io.github.cdimascio.dotenv.Dotenv;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * A content decorator that add weather information on the content.
 */
public class Weather extends ContentDecorator {
  private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?";
  private static final String GEO_URL = "http://api.openweathermap.org/geo/1.0/zip?";
  private String appid;

  // default address - boston
  private String lat = "42.3601";
  private String lon = "71.0589";

  /**
   * Construct a weather decorator of a certain location.
   * 
   * @param content base content
   * @param zipCode String of a zip code in US
   */
  public Weather(Content content, String zipCode) {
    this.content = content;
    appid = Dotenv.load().get("OPEN_WEATHER_APIKEY");
    setLocation(zipCode);
  }

  /**
   * Get the lat and lon from the zip code by using the geo api provided by the openWeather.
   * 
   * @param zipCode String of a zip code in US
   */
  private void setLocation(String zipCode) {
    String url = GEO_URL + "zip=" + zipCode + ",US&appid=" + appid;
    String response = APIUtil.APIRequest(url, null);

    if (response == null) {
      return;
    }

    JSONParser parser = new JSONParser();
    JSONObject data = null;
    try {
      data = (JSONObject)parser.parse(response);
    } catch (ParseException e) {
      e.printStackTrace();
      return;
    }
    this.lat = ((Double)data.get("lat")).toString();
    this.lon = ((Double)data.get("lon")).toString();
  }
    
  @Override
  public String getContent() {
    return content.getContent() + getWeather();
  }

  /**
   * Get the weather by using the weather api provided by the openWeather.
   * 
   * @return String of weather information.
   */
  private String getWeather() {
    String url = WEATHER_URL 
        + "lat=" + this.lat
        + "&lon=" + this.lon
        + "&units=metric" 
        + "&appid=" + appid;
    String response = APIUtil.APIRequest(url, null);

    JSONParser parser = new JSONParser();
    JSONObject data = null;
    try {
      data = (JSONObject)parser.parse(response);
    } catch (ParseException e) {
      e.printStackTrace();
      return "\nUnable to get Weather informaiton.\n";
    }

    JSONObject main = (JSONObject)data.get("main");
    JSONArray weatherArr = (JSONArray)data.get("weather");
    JSONObject weather = (JSONObject)weatherArr.get(0);

    String result = "\nThe weather in " + (String)data.get("name")
        + " is " + (String)weather.get("main") 
        + ", and the temperature is " + (double)main.get("temp") + " Celsius.\n";
    return result;
  }
}
