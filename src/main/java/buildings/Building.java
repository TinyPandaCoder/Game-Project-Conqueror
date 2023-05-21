package buildings;
import exceptions.*;
abstract public class Building {
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getUpgradeCost() {
		return upgradeCost;
	}

	public void setUpgradeCost(int upgradeCost) {
		this.upgradeCost = upgradeCost;
	}

	public boolean isCoolDown() {
		return coolDown;
	}

	public void setCoolDown(boolean coolDown) {
		this.coolDown = coolDown;
	}

	public int getCost() {
		return cost;
	}

	private int cost; 
	private int level = 1; 
	private int upgradeCost; 
	private boolean coolDown = true;
	final int MAX_LEVEL = 3;
	public Building(int cost , int upgradeCost) {
		this.cost = cost;
		this.upgradeCost = upgradeCost;
	}
        
        
        // every subclass will implement this method and call the super upgrade method
        // the super upgrade should update the level 
        public void upgrade() throws BuildingInCoolDownException, MaxLevelException{
            
            if(isCoolDown()){
                throw   new BuildingInCoolDownException("Building is cooling down");
            }
            

            //which means that after upgradding its level will exceed the maximum number of levels
            if(getLevel() == MAX_LEVEL){
                throw new MaxLevelException("You exceeded the maximum number of levels");
            }
            
            setLevel(getLevel() + 1);
            
            
            //update coolDown value after performing the action
            
            setCoolDown(true);
            
        }
	
	
	
}
