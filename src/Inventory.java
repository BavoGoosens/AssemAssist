import java.util.HashMap;

/**
 * A class that represents an inventory for an factory
 * 
 * @author team 10
 *
 */
public class Inventory {

	
	/**
	 * A Map that contains all the components of an inventory
	 */
	private HashMap<Component,Integer> components;

	
	public Inventory() {
		this.components = new HashMap<Component,Integer>();
	}
	
	/**
	 * A method to add an component to the inventory.
	 * 
	 * @param   component
	 *          An component of the inventory. 
	 * @throws  IllegalArgumentException
	 * 			!canHaveAsComponent(component)
	 */         
	public void addComponent(Component component) throws IllegalArgumentException {
		if(!canHaveAsComponent(component)) 
			throw new IllegalArgumentException();
		else if (this.getComponents().containsKey(component))
			updateListAdd(component);
		else 
			this.getComponents().put(component,1);
	}
	
	private boolean canHaveAsComponent(Component component) {
		return (component != null); // al gedaan in Component dus hier hoeft geen check eig?
	}
	
	private void updateListAdd(Component component){
		int temp = this.getComponents().get(component);
		temp++;
		this.getComponents().put(component,temp);
	}
	
	public void removeComponent(Component component) {
		
	}

	/**
	 * A method to return the components of this inventory.
	 * 
	 * @return this.components
	 */
	private HashMap<Component,Integer> getComponents() {
		return this.components;
	}
		
}
