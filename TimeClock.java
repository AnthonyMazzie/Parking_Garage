package parkingGarage;

/**
 * The TimeClock class represents a simple counter to simulate the passage
 * of time.
 * 
 * There is no actual connection to any real-world timer
 * or clock.
 * 
 * The passage of time is simulated by the method
 * timePasses().
 * 
 * Time is represented in minutes, by a single integer.
 * 
 * @author AMazzie
 */
public class TimeClock
{
  /**
   * Abstract representation of time in minutes from initialization.
   */
  private int time;

  /**
   * Constructs a new clock with a current time of zero.
   */
  public TimeClock()
  {
    time = 0;
  } 
  
  /**
   * Returns this clock's current time.
   * @return current time
   */
  public int getTime()
  {
    return time;
  }
  
  /**
   * Simulates the passage of time for a given number of minutes.
   * @param minutes
   *   number of minutes to be added to this clock's time
   */
  public void timePasses(int minutes)
  {
    time = time + minutes;
  }

  /**
   * Simulates the passage of time for the given hours and minutes.
   * @param hours
   *   number of hours to be added
   * @param minutes
   *   number of minutes to be added
   */
  public void timePasses(int hours, int minutes)
  {
    time = time + (hours * 60 + minutes);
  }

}
