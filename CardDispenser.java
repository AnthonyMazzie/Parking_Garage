package parkingGarage;

/**
 * The CardDispenser class models a card dispenser with a TimeClock.
 * 
 * When constructed, the CardDispenser is given a stamp time of the then-current time.
 * 
 * A user can take a parking card from the CardDispenser.
 * 
 * @author AMazzie
 *
 */
public class CardDispenser {
	
	private TimeClock stampTime;
	
	/**
	 * Constructs a CardDispenser that uses the given clock.
	 * @param givenClock
	 */
	public CardDispenser(TimeClock givenClock) {
		stampTime = givenClock;
	}

	/**
	 * Constructs and returns a new ParkingCard object. 
	 * 
	 * The constructed card will have a start time based 
	 * on the current value of the card dispenser's clock 
	 * and a payment time of zero.
	 * 
	 * @return
	 */
	public ParkingCard takeCard() {
		ParkingCard testCard = new ParkingCard(stampTime.getTime());
		testCard.setPaymentTime(0);
		return testCard;
	}
}
