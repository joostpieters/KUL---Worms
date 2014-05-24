package worms.model.statements;

import worms.model.Food;
import worms.model.Worm;
import worms.model.expressions.WormSelf;
import worms.model.programs.ProgramFactory.ForeachType;
import worms.model.types.EntitySort;

public class ForEach extends Statement {
	 
	private ForeachType type;
	private String name;
	private Statement s;
	private boolean ran;

	public ForEach(ForeachType type, String name, Statement s){
		this.type = type;
		this.name = name;
		this.s = s;
	}

	@Override
	public String toString() {
		return "foreach("+type.toString()+", "+name+") do {"+s.toString()+"}";
	}

	@Override
	public void run() {
		ran = false;
		if(type == ForeachType.WORM){
			EntitySort<Worm> worm = new EntitySort<Worm>();
			for(Worm w : WormSelf.getWorm().getWorld().getWorms()){
				worm.setValue(w);
				WormSelf.getWorm().getProgram().addVar(name, worm);
				s.run();
				ran = true;
			}
		}
		if(type == ForeachType.FOOD){
			EntitySort<Food> food = new EntitySort<Food>();
			for(Food f : WormSelf.getWorm().getWorld().getFood()){
				food.setValue(f);
				WormSelf.getWorm().getProgram().addVar(name, food);
				s.run();
				ran = true;
			}
		}
	}

	@Override
	public boolean isRan() {
		return ran;
	}

}
