package worms.model.expressions;

import worms.model.Worm;

public class SameTeam extends Expression{
	
	private Expression obj;

	public SameTeam(Expression obj){
		this.obj = obj;
	}

	@Override
	public Object getValue() {
		if(obj.getValue() instanceof Worm){
			if(WormSelf.getWorm().getTeam().getTName().equals(((Worm) obj.getValue()).getTeam().getTName())){
				return new BooleanLit(true);
			}
			else{
				return new BooleanLit(false);
			}
		}
		else{
			return new BooleanLit(false);
		}
	}

	@Override
	public String toString() {
		return "sameteam "+obj.toString();
	}

}
