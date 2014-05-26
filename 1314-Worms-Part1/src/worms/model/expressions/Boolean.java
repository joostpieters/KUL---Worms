package worms.model.expressions;
import worms.model.types.BooleanSort;

public abstract class Boolean extends Expression{

	@Override
	public abstract BooleanSort getValue();
}
