package worms.model.expressions;

import worms.model.types.Type;

public abstract class Expression extends Type{

	public abstract Object getValue();
	public abstract String toString();
	
}
