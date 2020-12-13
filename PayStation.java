package parkingGarage;

/**
 * A PayStation has methods allowing a ParkingCard object to 
 * be updated to show when payment is made.
 * 
 * There is a method insertCard() to simulate inserting a card 
 * into the machine. At that point a transaction is said to be "in 
 * progress", and the inProgress() method returns true, 
 * until a subsequent call to ejectCard().
 * 
 * The amount due for parking can be obtained from the method 
 * getPaymentDue(). 
 * 
 * The method makePayment() finally updates the ParkingCard 
 * object to record the time of payment. (We assume that all 
 * payments are by credit/debit and are successful.)
 * 
 * In addition, the PayStation includes an accumulator that 
 * records the total amount of money paid into the machine 
 * since it was initialized.
 * 
 * @author AMazzie
 */
public class PayStation {
	
	private TimeClock payStationClock;
	private double totalPaymentsAccepted;
	private boolean cardInserted;
	private ParkingCard cardProcessing;
	private double paymentDue;
	
	/**
	 * Constructs a PayStation that uses the given clock. Initially,
	 * total payments are 0.0.
	 * @param givenClock
	 */
	public PayStation(TimeClock givenClock) {
		payStationClock = givenClock;
		totalPaymentsAccepted = 0.0;
		cardInserted = false;
	}
	
	/**
	 * Simulates inserting the given card into this machine. This method
	 *does not modify the ParkingCard object or perform any calculation with
	 *it. After calling this method, the inProgress() method returns true 
	 *until a subsequent call to ejectCard(). Calling insertCard() while a
	 *transaction is in progress has no effect
	 * @param t
	 */
	public void insertCard(ParkingCard t) {
		cardInserted = true;
		cardProcessing = t;
	}
	
	/**
	 * Returns a reference to the card currently in this machine, or null if
	 * no transaction is in progress
	 * @return
	 */
	public ParkingCard getCurrentCard() {
		return cardProcessing;
	}
	
	/**
	 * Returns true if there is currently a card in this machine, false otherwise
	 * @return
	 */
	public boolean inProgress() {
		
		boolean transaction = false;
		
		if(cardInserted == true) {
			transaction = true;
		}
		return transaction;
	}
	
	/**
	 * Returns the payment due for the card currently in the machine. If no 
	 * transaction is in progress, returns 0.0.
	 * 
	 * This method does not modify the ParkingCard object or update this machine's 
	 * total payments.
	 * 
	 * The payment due is based the current time (according to this 
	 * machine's clock) and on the start time and payment time for the current card. 
	 * 
	 * The basic payment due is the result of calling RateUtil.calculateCost() 
	 * for the difference (current time - start time).
	 * 
	 * However, if the payment time is nonzero (indicating that some payment was already 
	 * made),
	 * 
	 * the cost of parking from start time to payment time (i.e., the amount that 
	 * must have already been paid) is subtracted from the amount due.
	 * 
	 * @return
	 */
	public double getPaymentDue() {
		
		double previousPayment = 0;
		
		if(inProgress()) {
			if(cardProcessing.getPaymentTime() != 0) {
				previousPayment = RateUtil.calculateCost(cardProcessing.getPaymentTime() - cardProcessing.getStartTime());
				paymentDue = RateUtil.calculateCost(payStationClock.getTime() - cardProcessing.getStartTime());
				paymentDue = paymentDue - previousPayment;
			}
			else {
				paymentDue = RateUtil.calculateCost(payStationClock.getTime() - cardProcessing.getStartTime());
			}
		}
		else {
			paymentDue = 0.0;
		}
		return paymentDue;
	}
	
	/**
	 * Updates the current card with the payment time and adds the payment amount 
	 * to this machine's total. If there is no transaction in progress, this method 
	 * has no effect
	 */
	public void makePayment() {
		if(inProgress()) {
			totalPaymentsAccepted += getPaymentDue();
			cardProcessing.setPaymentTime(payStationClock.getTime());
		}
	}
	
	/**
	 * Simulates ejecting a card from this machine, after which another card can be
	 * inserted.
	 * 
	 * This method does not modify the current card object or update this 
	 * machine's total payments.
	 * 
	 * If there is no transaction in progress, this method has no effect.
	 */
	public void ejectCard() {
		if(inProgress()) {
			cardInserted = false;
			cardProcessing = null;
		}
	}
	
	/**
	 * Returns the total payments that have been made at this machine
	 * @return
	 */
	public double getTotalPayments() {
		return totalPaymentsAccepted;
	}
}
