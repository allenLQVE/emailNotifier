/**
 * Name: HungHsu(Allen) Chen 
 * Course: CS-665 Software Designs & Patterns 
 * Date: 11/03/2024 
 * File Name: Notifier.java 
 * Description: Interface for a notifier in the observer pattern
 */

package notifier;

import notifyee.Notifyee;

/**
 * interface for a notifier in the observer pattern.
 */
public interface Notifier {

  /**
   * Subscribe the Notifyee to the notify.
   * 
   * @param notifyee Notifyee that subscribe to the Notifier
   */
  void subscribe(Notifyee notifyee);

  /**
   * Unscbscribe the Notifyee from the notify.
   * 
   * @param notifyee Notifyee that unsubscribe from the Notifier
   */
  void unsubscribe(Notifyee notifyee);

  /**
   * Notify the notifyee for the update.
   * @return 
   */
  void notifyNotifyee();
}
