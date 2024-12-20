/**
 * Name: HungHsu(Allen) Chen 
 * Course: CS-665 Software Designs & Patterns 
 * Date: 11/03/2024 
 * File Name: NotifierImpl.java 
 * Description: A class for notifier
 */

package notifier;

import notifyee.Notifyee;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * An implementation of the notifier interface.
 */
public class NotifierImpl implements Notifier, Runnable {
  private ArrayList<Notifyee> notifyees = new ArrayList<>();

  /**
   * Notify notifyee everyday.
   * 
   * @param hour 1-24, hour of when to notify
   * @param minute 0-60, minute of when to notify
   */
  public NotifierImpl(int hour, int minute) {
    this(hour, minute, 24);
  }

  /**
   * Notify notifyee in every certain time range.
   * 
   * @param hour 1-24, hour of when to notify
   * @param minute 0-60, minute of when to notify
   * @param eachHour how many hour between each notification
   */
  public NotifierImpl(int hour, int minute, int eachHour) {
    ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
    int year = now.getYear();
    int month = now.getMonthValue();
    int day = now.getDayOfMonth();
    
    ZonedDateTime nextRun = ZonedDateTime.of(year,
        month,
        day,
        hour,
        minute,
        0,
        0,
        ZoneId.systemDefault()
    );

    while (now.compareTo(nextRun) > 0) {
      nextRun = nextRun.plusHours(eachHour);
    }

    Duration duration = Duration.between(now, nextRun);
    long initialDelay = duration.getSeconds();

    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    scheduler.scheduleAtFixedRate(this,
        initialDelay,
        TimeUnit.HOURS.toSeconds(eachHour),
        TimeUnit.SECONDS
    );
  }

  @Override
  public void subscribe(Notifyee notifyee) {
    notifyees.add(notifyee);
  }

  @Override
  public void unsubscribe(Notifyee notifyee) {
    notifyees.remove(notifyee);
  }

  @Override
  public void notifyNotifyee() {
    for (Notifyee notifyee : notifyees) {
      notifyee.update();
    }
  }

  @Override
  public void run() {
    notifyNotifyee();
  }
}
