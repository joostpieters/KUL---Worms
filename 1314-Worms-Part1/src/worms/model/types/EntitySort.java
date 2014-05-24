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
	public Object getValue() {
		return sort;
	}

	public void setValue(Object obj) {
		this.sort = (Sort) obj;
	}

}
