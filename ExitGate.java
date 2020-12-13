package parkingGarage;

/**
 * Models an exit gate at a parking garage.
 * 
 * @author AMazzie
 *
 */
public class ExitGate {
	
	private TimeClock exitGateTime;
	private boolean gateOpen;
	private int exitCounter;
		
	/**
	 * Constructs an ExitGate that uses the given 
	 * clock and has an initial count of zero.
	 * @param givenClock
	 */
	public ExitGate(TimeClock givenClock) {
		exitGateTime = givenClock;
	}
	
	/**
	 * Simulates inserting a card into this machine. 
	 * 
	 * If the card's payment time is within 
	 * RateUtil.EXIT_TIME_LIMIT minutes of this machine's 
	 * clock time (and is greater than zero), the method 
	 * returns true.
	 * 
	 * Otherwise the method returns false.
	 * 
	 * The ParkingCard object is not modified.
	 * 
	 * If the method returns true, this machine's
	 * exit count is incremented.
	 * 
	 * (Note that this method is a mutator method that also 
	 * returns a value.)
	 * 
	 * If the exit gate determines (using its internal clock) 
	 * that it has been 15 minutes or less from the time you 
	 * made payment, then the gate goes up and you exit the garage. 
	 * 
	 * @param c
	 * @return
	 */
	public boolean insertCard(ParkingCard c) {
		
		int cardPaymentTime = c.getPaymentTime();
		
		int timeDiff = exitGateTime.getTime() - cardPaymentTime;
		
		if(timeDiff <= RateUtil.EXIT_TIME_LIMIT && timeDiff > 0 && c.getPaymentTime() != 0) {
			gateOpen = true;
			exitCounter += 1;
		}
		else if(timeDiff > RateUtil.EXIT_TIME_LIMIT || c.getPaymentTime() == 0) {
			gateOpen = false;
		}
		return gateOpen;
	}
	
	/**
	 * Returns a count of the total number of successful 
	 * exits.
	 * 
	 * A "successful exit" is defined to be a call 
	 * to insertCard() that returns true.
	 * 
	 * @return exitCounter
	 */
	public int getExitCount() {
		return exitCounter;
	}
	
}

