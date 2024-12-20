/**
 * Name: HungHsu(Allen) Chen 
 * Course: CS-665 Software Designs & Patterns 
 * Date: 11/03/2024 
 * File Name: ContentImpl.java 
 * Description: An class for content.
 */

package content;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * The basic content class that only sending out today's date.
 */
public class ContentImpl implements Content {
  /**
   * get the content for the news.
   * 
   * @return String of today's date
   */
  public String getContent() {
    return ZonedDateTime.now(ZoneId.systemDefault()).toLocalDate().toString() + "\n";
  }
}
