package worms.model.expressions;
import worms.model.types.BooleanSort;

public class Equals extends Expression {
	
	private Expression left, right;

	public Equals(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}

	@Override
	public BooleanSort getValue() {
		if(left.getValue() == null && right.getValue() == null){
			return new BooleanSort(true);
		}
		else{
			if(left.getValue().getValue().equals(right.getValue().getValue())){
			return new BooleanSort(true);
			}
		}
		return new BooleanSort(false);
	}

	@Override
	public String toString() {
		return left.toString()+" == "+right.toString();
	}

	@Override
	public Expression clone() {
		return new Equals(this.left, this.right);
	}

}
