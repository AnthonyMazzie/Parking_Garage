package parkingGarage;

/**
 * This is a "utility" class, that is, it has no instance 
 * variables, is never instantiated, and serves only as a 
 * container for one or more static methods. (Similar to 
 * the class Math.)
 * 
 * @author AMazzie
 *
 */
public class RateUtil {

	/**
	 * There is one constructor, which is declared private 
	 * since the class should never be instantiated
	 */
	private RateUtil() {
	}
	
	public static final int EXIT_TIME_LIMIT = 15;
	
	/**
	 * Returns the cost of parking for the given total 
	 * number of minutes, based on the current rates 
	 * for the MU garage.
	 * 
	 * @param Minutes
	 * @return
	 */
	public static double calculateCost(int minutes) {
		
		final int minutesInDay = 1440;
		final int minutesInHour = 60;
		final double maxDailyRate = 13.00;
		double totalCost = 0.00;
		
		int daysBilled = minutes / minutesInDay;//calculates "input parameter Minutes" into the amount of corresponding days
		int hoursBilled = (minutes % minutesInDay) / minutesInHour; //calculates "input parameter Minutes" into the amount of corresponding hours
		int minutesBilled = (minutes % minutesInDay) % minutesInHour;//calculates "input parameter Minutes" into the remainder minutes of hoursBilled
		
		double timeCalc = minutesBilled + (hoursBilled * minutesInHour);
		
		if(minutesBilled >= 1 && hoursBilled > 0) { //if a person goes even one minute past the hour, they are billed another hour
		hoursBilled += 1;
		}
		
		if(timeCalc <= 30) { //less than a half hour
			totalCost = 1.00;
		}
		else if(timeCalc > 30 && timeCalc <= 60) { //greater than half hour, less than one hour
			totalCost = 2.00;
		}
		else if(timeCalc > 60 && timeCalc <= 300) { //greater than one hour, less than 5 hours
			totalCost = 2.00 + (hoursBilled - 1) * 1.50;
		}
		else if(timeCalc > 300 && timeCalc <= 480) { //greater than 5 hours, less than eight hours
			totalCost = 8.00 + (hoursBilled - 5) * 1.25;
		}
		else if(timeCalc > 480 && timeCalc <= 1440) {
			totalCost = 13.00;
		}
		
		double daysBill = maxDailyRate * daysBilled;
		
		if(daysBilled > 0) {
			totalCost = totalCost + daysBill;
		}
		
		return totalCost;
	}
}
