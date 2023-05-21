package units;

import java.util.ArrayList;

public class Army {
	private Status currrentStatus = Status.IDLE;
	private ArrayList<Unit> units;
	private int distancetoTarget = -1;
	private String target = "";
	private String currentLocation;
	private final int maxToHold = 10;
	public Army(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public Status getCurrrentStatus() {
		return currrentStatus;
	}
	public void setCurrrentStatus(Status currrentStatus) {
		this.currrentStatus = currrentStatus;
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
}
