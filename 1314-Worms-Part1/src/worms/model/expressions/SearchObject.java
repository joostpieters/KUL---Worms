package worms.model.expressions;

public class SearchObject extends Expression{
	
	private Expression obj;

	public SearchObject(Expression obj){
		this.obj = obj;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "searchobj "+obj.toString();
	}

}
