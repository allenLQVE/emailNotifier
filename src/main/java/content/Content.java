/**
 * Name: HungHsu(Allen) Chen 
 * Course: CS-665 Software Designs & Patterns 
 * Date: 11/03/2024 
 * File Name: Content.java 
 * Description: An interface for content.
 */

package content;

/**
 * The interface of the content that will be sent in the email.
 */
public interface Content {
  /**
   * Get content of the news.
   * 
   * @return String that contains the news.
   */
  String getContent();
}
