package worms.model.types;

public class EntitySort<Sort> extends Type {
	
	private Sort sort;
	
	public EntitySort(Sort sort){
		setValue(sort);
	}
	
	public EntitySort(){
		setValue(null);
	}

	@Override
	public Object getValue() {
		return sort;
	}

	@Override
	public void setValue(Object obj) {
		if(!(obj instanceof worms.model.superclasses.Object)){ //Worm or food or projectile
			return;
		}
		this.sort = (Sort) obj;
	}

}
