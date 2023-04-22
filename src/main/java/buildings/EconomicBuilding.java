package buildings;

abstract public class EconomicBuilding extends Building {
	public EconomicBuilding(int cost , int upgradeCost){
		super(cost , upgradeCost);
	}
        
        // will be implemented in every subclass
        public abstract int harvest();
}
