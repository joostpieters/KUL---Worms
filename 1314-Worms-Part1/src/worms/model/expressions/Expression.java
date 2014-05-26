package worms.model.expressions;
import worms.model.types.Type;

public abstract class Expression implements Cloneable{

	public abstract Type getValue();
	public abstract String toString();
	public abstract Expression clone();
	
}
