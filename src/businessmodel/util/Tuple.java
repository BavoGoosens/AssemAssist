package businessmodel.util;

public class Tuple <X , Y>{

	private X x;

	private Y y;

	// no setters for final variable
	public Tuple(X x, Y y) {
		this.setX(x);
		this.setY(y);
	}

	public X getX() { 
		return this.x; 
	}

	public Y getY(){ 
		return this.y; 
	}

	@Override
	public int hashCode() { 
		return this.x.hashCode() ^ this.y.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) 
			return false;
		if (!(o instanceof Tuple)) 
			return false;
		@SuppressWarnings("unchecked")
		Tuple<X , Y> pairo = (Tuple <X, Y>) o;
		return this.x.equals(pairo.getX()) &&
				this.y.equals(pairo.getY());
	}

	private void setX(X x) {
		this.x = x;
	}

	private void setY(Y y) {
		this.y = y;		
	}
}
