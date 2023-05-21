package units;

import exceptions.FriendlyFireException;

abstract public class Unit {
	private int level;
	private int maxSoldierCount;
	private int currentSoldierCount;
	private double idleUpkeep;
	private double marchingUpkeep;
	private double siegeUpkeep;
        private Army parentArmy;        
	public Unit(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep,
			double siegeUpkeep) {
		this.level = level;
		this.maxSoldierCount = maxSoldierCount;
		this.idleUpkeep = idleUpkeep;
		this.marchingUpkeep = marchingUpkeep;
		this.siegeUpkeep = siegeUpkeep;
	}
        public void setParentArmy(Army parentArmy) {
            this.parentArmy = parentArmy;
        }

        public Army getParentArmy() {
            return parentArmy;
        }
	public int getCurrentSoldierCount() {
		return currentSoldierCount;
	}
	public void setCurrentSoldierCount(int currentSoldierCount) {
		this.currentSoldierCount = currentSoldierCount;
	}
	public int getLevel() {
		return level;
	}
	public int getMaxSoldierCount() {
		return maxSoldierCount;
	}
	public double getIdleUpkeep() {
		return idleUpkeep;
	}
	public double getMarchingUpkeep() {
		return marchingUpkeep;
	}
	public double getSiegeUpkeep() {
		return siegeUpkeep;
	}
        public abstract double unitFactor(Unit target, int level);
	public void attack(Unit target) throws FriendlyFireException
        {
            if (this.getParentArmy().equals(target.getParentArmy())){
                throw new FriendlyFireException("You cannot attack a friendly unit");
            }
            else
            {
                target.setCurrentSoldierCount(target.currentSoldierCount - (int) 
                        (this.currentSoldierCount * this.unitFactor(target, this.level)));
                target.getParentArmy().handleAttack(target);
                
            }
                
        }
        

        
}
