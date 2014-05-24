package worms.model.expressions;

import worms.model.Worm;
import worms.model.types.EntitySort;

public class WormSelf extends Expression{

	public static Worm worm;

	public WormSelf(){
		//Nothing to declare
	}
	
	public WormSelf(Worm worm){
		this.worm = worm;
	}

	@Override
	public EntitySort<Worm> getValue() {
		EntitySort<Worm> worm = new EntitySort<Worm>();
		worm.setValue(this.worm);
		return worm;
	}

	@Override
	public String toString() {
		return "self";
	}
	
	public static Worm getWorm(){
		return worm;
	}

	public static void setWorm(Worm activeWorm) {
		worm = activeWorm;		
	}
}
