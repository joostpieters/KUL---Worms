package worms.model.statements;

public abstract class Statement implements Cloneable{
	
	public abstract String toString();
	public abstract void run();
	public abstract boolean isRan();

}
