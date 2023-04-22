package units;

import exceptions.MaxCapacityException;
import java.util.ArrayList;

public class Army {
	private Status currentStatus = Status.IDLE;
	private ArrayList<Unit> units;
	private int distancetoTarget = -1;
	private String target = "";
	private String currentLocation;
	private final int maxToHold = 10;
	public Army(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public Status getCurrrentStatus() {
		return currentStatus;
	}
	public void setCurrrentStatus(Status currrentStatus) {
		this.currentStatus = currrentStatus;
	}
	public ArrayList<Unit> getUnits() {
		return units;
	}
	public void setUnits(ArrayList<Unit> units) {
		this.units = units;
	}
	public int getDistancetoTarget() {
		return distancetoTarget;
	}
	public void setDistancetoTarget(int distancetoTarget) {
		this.distancetoTarget = distancetoTarget;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public int getMaxToHold() {
		return maxToHold;
	}
        public void handleAttack(Unit unit)
        {
            if (unit.getCurrentSoldierCount()<=0)
            {
                this.getUnits().remove(unit);
            }
        }
        public void relocateUnit(Unit unit) throws MaxCapacityException
        {
            if (this.getUnits().size()>=maxToHold)
            {
                throw new MaxCapacityException("This Army has the max capcity of units.\nYou can't move the unit to this army");
            }
            else
            {
                this.getUnits().add(unit);
                unit.getParentArmy().getUnits().remove(unit);
                unit.setParentArmy(this);
            }
        }
        public double foodNeeded()
        {
            double foodNeeded=0;
            for (Unit unit : this.getUnits())
            {
                foodNeeded+=calcluateFood(unit);
            }
            return foodNeeded;
        }
        private double calcluateFood(Unit unit) {
            if (this.currentStatus.equals(Status.IDLE)) {
              return unit.getCurrentSoldierCount() * unit.getIdleUpkeep();
            }
            if (this.currentStatus.equals(Status.BESIEGING)) {
              return unit.getCurrentSoldierCount() * unit.getSiegeUpkeep();
            }
            return unit.getCurrentSoldierCount() * unit.getMarchingUpkeep();
         }


}
