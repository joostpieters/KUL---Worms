package worms.model.expressions;

import worms.model.Worm;
import worms.model.types.BooleanSort;

public class SameTeam extends Expression{
	
	private Expression obj;

	public SameTeam(Expression obj){
		this.obj = obj;
	}

	@Override
	public BooleanSort getValue() {
		if(obj.getValue().getValue() instanceof Worm){
			if(WormSelf.getWorm().getTeam().getTName().equals((((Worm) obj.getValue().getValue()).getTeam().getTName()))){
				return new BooleanSort(true);
			}
			else{
				return new BooleanSort(false);
			}
		}
		else{
			return new BooleanSort(false);
		}
	}

	@Override
	public String toString() {
		return "sameteam "+obj.toString();
	}

	@Override
	public Expression clone() {
		return new SameTeam(this.obj);
	}

}
