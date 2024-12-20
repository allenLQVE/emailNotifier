import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.junit.Test;
import content.ContentImpl;
import content.Content;
import content.contentbuilder.ContentBuilder;
import content.contentdecorator.News;
import content.contentdecorator.Weather;


public class TestContent {
    @Test
    public void testContent() {
      Content content = new ContentImpl();
      
      String result = content.getContent();
      ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
      assertEquals(now.toLocalDate().toString() + "\n", result);
    }

    @Test
    public void testWeather() {
      Content content = new ContentImpl();
      String oldResult = content.getContent();

      content = new Weather(content, "02171");

      String result = content.getContent();
      assertNotEquals(oldResult, result);
    }

    @Test
    public void testNEWS() {
      Content content = new ContentImpl();
      String oldResult = content.getContent();

      content = new News(content, News.Country.UnitedState.country, "");
      String result = content.getContent();
      assertNotEquals(oldResult, result);
    }

    @Test
    public void testContentBuilder() {
      Content content = new Weather(new ContentImpl(), "02171");
      Content builderContent = new ContentBuilder().weather("02171").build();

      assertEquals(content.getContent(), builderContent.getContent());
    }
}
