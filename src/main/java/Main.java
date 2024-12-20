/**
 * Name: HungHsu(Allen) Chen 
 * Course: CS-665 Software Designs & Patterns 
 * Date: 12/02/2024 
 * File Name: Main.java 
 * Description: entry point of the project
 */

import content.Content;
import content.contentbuilder.ContentBuilder;
import content.contentdecorator.News;
import notifier.Notifier;
import notifier.NotifierImpl;
import notifyee.Notifyee;
import notifyee.NotifyeeImpl;

/**
 * This is the Main class.
 */
public class Main {

  public static void main(String[] args) {
    Main main = new Main();
    main.doIt();
  }

  /**
   * Test flow of the application.
   */
  public void doIt() {
    Notifier notifier = new NotifierImpl(19, 27);

    Content content = new ContentBuilder()
        .weather("02171")
        .news(News.Country.UnitedState.country, "")
        .news(News.Country.Taiwan.country, "")
        .news(News.Country.Japan.country, "")
        .build();

    Notifyee notifyee = new NotifyeeImpl("allenlqve@gmail.com", content);

    notifier.subscribe(notifyee);

    content = new ContentBuilder()
        .weather("02215")
        .news("",News.Category.technology.category)
        .build();
    notifyee = new NotifyeeImpl("chhchen@bu.edu", content);
    notifier.subscribe(notifyee);
  }
}
