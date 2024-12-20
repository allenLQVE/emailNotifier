/**
 * Name: HungHsu(Allen) Chen 
 * Course: CS-665 Software Designs & Patterns 
 * Date: 11/03/2024 
 * File Name: Notifyee.java 
 * Description: interface for notifyee, observer in the observer pattern
 */

package notifyee;

/**
 * Interface for notifyee, observer in the observer pattern.
 */
public interface Notifyee {
  
  /**
   * update from a notifier.
   */
  void update();
}
