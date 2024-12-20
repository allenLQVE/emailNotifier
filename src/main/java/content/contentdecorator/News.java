/**
 * Name: HungHsu(Allen) Chen 
 * Course: CS-665 Software Designs & Patterns 
 * Date: 12/02/2024 
 * File Name: News.java 
 * Description: A content decorator that add news to the content.
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
 * A content decorator that add news to the content.
 */
public class News extends ContentDecorator {
  /**
   * A enum class for the conutry code used in news.
   */
  public static enum Country {
    UnitedState("us"), 
    Japan("jp"), 
    Taiwan("tw");
    
    public final String country;

    private Country(String string) {
      this.country = string;
    }
  }

  /**
   * A enum class for the category used in news.
   */
  public static enum Category {
    business("business"), 
    entertainment("entertainment"), 
    general("general"), 
    health("health"), 
    science("science"), 
    sports("sports"), 
    technology("technology");

    public final String category;
    
    private Category(String string) {
      this.category = string;
    }
  }

  private static final String NEWSAPI_URL = "https://newsapi.org/v2/top-headlines?";
  private String apiKey;

  // default all country and all category
  private String country;
  private String category;

  /**
   * News constroctor that decorate content with news from a spesific country and category.
   * 
   * @param content Content
   * @param country String the country for the news. Empty string if all country is wanted.
   * @param category String the category for the news. Empty string if all category is wanted.
   */
  public News(Content content, String country, String category) {
    this.content = content;
    this.country = country;
    this.category = category;
    apiKey = Dotenv.load().get("NEWSAPI_APIKEY");
  }

  @Override
  public String getContent() {
    return content.getContent() + getNews();
  }
  
  /**
   * Get News from News api.
   * 
   * @return String of the news title and its url.
   */
  private String getNews() {
    String url = NEWSAPI_URL + "country=" + this.country
        + "&category=" + this.category
        + "&apiKey=" + apiKey;
    String response = APIUtil.APIRequest(url, null);

    JSONParser parser = new JSONParser();
    JSONObject data = null;
    try {
      data = (JSONObject)parser.parse(response);
    } catch (ParseException e) {
      e.printStackTrace();
      return "\nUnable to get News informaiton of " + this.category 
          + " from " + this.country + ".\n";
    }

    JSONArray articles = (JSONArray)data.get("articles");
    String result = "\n" + this.country + " " + this.category + " news" + ":\n";
    for (Object obj : articles) {
      JSONObject article = (JSONObject)obj;
      String title = (String)article.get("title");
      if (title.equals("[Removed]")) {
        continue;
      }
      result += title + "\n" + article.get("url") + "\n\n";
    }

    return result;
  }
}
