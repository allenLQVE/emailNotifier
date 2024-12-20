/**
 * Name: HungHsu(Allen) Chen 
 * Course: CS-665 Software Designs & Patterns 
 * Date: 12/02/2024 
 * File Name: ContentBuilder.java 
 * Description: A builder class for the content with any decorator.
 */

package content.contentbuilder;

import content.Content;
import content.ContentImpl;
import content.contentdecorator.News;
import content.contentdecorator.Weather;

/**
 * A builder class for the content with any decorator.
 */
public class ContentBuilder {
  private Content content = new ContentImpl();

  /**
   * Build content with weather decorator.
   * 
   * @param zip String location for the weather
   * @return ContentBuilder
   */
  public ContentBuilder weather(String zip) {
    content = new Weather(content, zip);
    return this;
  }

  /**
   * Build content with news decorator.
   * 
   * @param country String country of the news
   * @param category String category of the news
   * @return ContentBuilder
   */
  public ContentBuilder news(String country, String category) {
    content = new News(content, country, category);
    return this;
  }

  /**
   * Build the completed content.
   * 
   * @return Content that added all needed decorators.
   */
  public Content build() {
    Content result = content;

    this.content = new ContentImpl();
    return result;
  }
}
