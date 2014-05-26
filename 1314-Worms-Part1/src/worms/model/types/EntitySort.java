package worms.model.types;

public class EntitySort<Sort> extends Type {
	
	private Sort sort;
	
	public EntitySort(Sort sort){
		setValue(sort);
	}
	
	public EntitySort(){
		this.sort = null;
	}

	@Override
	public Sort getValue() {
		return sort;
	}

	public void setValue(Sort obj) {
		this.sort = obj;
	}

	@Override
	public Type clone() {
		return new EntitySort<Sort>(this.sort);
	}

}
