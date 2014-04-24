
package businessmodel.order;

import java.util.ArrayList;
import java.util.Locale;

import org.joda.time.DateTime;

import businessmodel.category.CarOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.user.User;

/**
 * A class representing an abstract order.
 * 
 * @author SWOP Team 10 2014
 *
 */
public abstract class Order {

	private User user;

	private DateTime estimatedDeliveryDate;

	private DateTime timestamp;

	private DateTime orderPlacedOnWorkPost;

	private DateTime standardTimeOnAssemblyLine;

	private DateTime completionDate;

	private boolean completed;

	public Order(User user) throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {
		setUser(user);
	}
	
	public User getUser() {
		return this.user;
	}
	
	public boolean isCompleted() {
		return this.completed;
	}

	public void updateEstimatedDate(int delay) {
		this.setEstimatedDeliveryDateOfOrder(this.getEstimatedDeliveryDate().plusMinutes(delay));		
	}
	
	public abstract ArrayList<CarOption> getOptions();

	public DateTime getTimestamp() {
		return timestamp;
	}

	public DateTime getCompletionDate() {
		return completionDate;
	}

	public DateTime getStandardTimeOnAssemblyLine() {
		return standardTimeOnAssemblyLine;
	}

	public DateTime getUserEndDate() {
		return null;
	}
	
	public DateTime getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}

	public DateTime getOrderPlacedOnWorkPost() {
		return orderPlacedOnWorkPost;
	}

	public void setCompleted() {
		this.completed = true;
	}

	public void setTimestampOfOrder(DateTime timestamp) {
		this.setTimestamp(timestamp);
	}

	public void setCompletionDateOfOrder(DateTime deliverydate) throws IllegalArgumentException {
		this.setCompletionDate(deliverydate);
	}

	public void setPlacedOnWorkpostOfOrder(DateTime placedOnWorkPost) {		
		this.setOrderPlacedOnWorkPost(placedOnWorkPost);
	}

	public void setOrderPlacedOnWorkPostOfOrder(DateTime orderPlacedOnWorkPost) throws IllegalArgumentException {
		this.setOrderPlacedOnWorkPost(orderPlacedOnWorkPost);
	}

	public void setEstimatedDeliveryDateOfOrder(DateTime estimatedDeliveryDate) {
		this.setEstimatedDeliveryDate(estimatedDeliveryDate);
	}

	private void setTimestamp(DateTime timestamp){
		this.timestamp = timestamp;
	}

	private void setCompletionDate(DateTime completionDate) throws IllegalArgumentException{
	if(completionDate == null) 
		throw new IllegalArgumentException("Bad completion date!");
		this.completionDate = completionDate;
	}

	private void setEstimatedDeliveryDate(DateTime estimatedDeliveryDate) throws IllegalArgumentException {
		if(estimatedDeliveryDate == null) 
			throw new IllegalArgumentException("Bad estimated delivery date!");
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}

	private void setUser(User user) throws IllegalArgumentException, NoClearanceException {
		if (user == null) 
			throw new IllegalArgumentException("Bad user!");
		if (!user.canPlaceOrder()) 
			throw new NoClearanceException(user);
		this.user = user;
	}

	private void setOrderPlacedOnWorkPost(DateTime orderPlacedOnWorkPost) throws IllegalArgumentException{
		if(orderPlacedOnWorkPost == null) 
			throw new IllegalArgumentException("Bad order placed on work post date!");
		this.orderPlacedOnWorkPost = orderPlacedOnWorkPost;		
	}

	//TODO
	private void setStandardTimeOnAssemblyLine(DateTime placedOnWorkPost) {
		this.standardTimeOnAssemblyLine = placedOnWorkPost;
	}

	@Override
	public String toString() {
		return "user: " + this.user.toString() + ", delivery date= " + this.estimatedDeliveryDate.toString("EEE, dd MMM yyyy HH:mm:ss", Locale.ROOT); 
	}
}
