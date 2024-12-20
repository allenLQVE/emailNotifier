/**
 * Name: HungHsu(Allen) Chen 
 * Course: CS-665 Software Designs & Patterns 
 * Date: 11/03/2024 
 * File Name: ContentDecorator.java 
 * Description: An abstract class for the content decorator.
 */

package content.contentdecorator;

import content.Content;

/**
 * A decorator for the content to add more contents into the notifier.
 */
public abstract class ContentDecorator implements Content {
  Content content;

  /**
   * Get a content based on a decorator.
   * 
   * @return String of the old content plus the new content.
   */
  public abstract String getContent();
}
