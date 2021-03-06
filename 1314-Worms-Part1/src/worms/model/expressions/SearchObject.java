package worms.model.expressions;

import worms.model.superclasses.Object;
import worms.model.types.EntitySort;

public class SearchObject extends Expression{
	
	private Expression obj;

	public SearchObject(Expression obj){
		this.obj = obj;
	}

	@Override
	public EntitySort<?> getValue() {
		Object best = null;
		double distance = 100;
		for(Object obj : WormSelf.getWorm().getWorld().getObjects()){
			if((Math.sqrt(Math.pow(((((Object) obj).getY() - WormSelf.getWorm().getY())),2)+Math.pow(((Object) obj).getX()-WormSelf.getWorm().getX(), 2))) <= distance){
				best = obj;
				distance = (Math.sqrt(Math.pow(((((Object) obj).getY() - WormSelf.getWorm().getY())),2)+Math.pow(((Object) obj).getX()-WormSelf.getWorm().getX(), 2)));
			}
		}
		return new EntitySort<>(best);
	}

	@Override
	public String toString() {
		return "searchobj "+obj.toString();
	}

	@Override
	public Expression clone() {
		return new SearchObject(this.obj);
	}

}
