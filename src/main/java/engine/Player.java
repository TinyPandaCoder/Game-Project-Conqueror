package engine;

import buildings.*;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import java.util.ArrayList;
import units.Army;
import units.Unit;
public class Player {
	private String name;
	private ArrayList<City> controlledCities;
	private ArrayList<Army> controlledArmies;
	private double treasury;
	private double food;
	public Player(String name) {
		this.name = name;
	}
	public double getTreasury() {
		return treasury;
	}
	public void setTreasury(double treasury) {
		this.treasury = treasury;
	}
	public double getFood() {
		return food;
	}
	public void setFood(double food) {
		this.food = food;
	}
	public String getName() {
		return name;
	}
	public ArrayList<City> getControlledCities() {
		return controlledCities;
	}
	public ArrayList<Army> getControlledArmies() {
		return controlledArmies;
	}
  
	public void recruitUnit(String type,String cityName) throws
BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException{
            
            int cityIndex = Game.findCity(cityName , controlledCities);
            
            if(cityIndex == -1){
                return;
            }
            
            int militaryIndex = -1;
            
            ArrayList< MilitaryBuilding >buildings = controlledCities.get(cityIndex).getMilitaryBuildings();
            for(int i = 0; i < buildings.size(); i++){
                if(buildings.get(i).getUnitTypeName().equals(type)){
                    militaryIndex = i;
                    break;
                }
            }
            
            
            
            if(militaryIndex == -1){
                return;
            }
            
            int cost = buildings.get(militaryIndex).getRecruitmentCost();
            
            
            if(cost > this.treasury){
                throw new NotEnoughGoldException("You don't have enough gold to recruit");
            }
            
            this.treasury -= cost;
            
            Unit unit = buildings.get(militaryIndex).recruit();
            
           ArrayList<Unit> units =  controlledCities.get(cityIndex).getDefendingArmy().getUnits();
            
            units.add(unit);
            controlledCities.get(cityIndex).getDefendingArmy().setUnits(units);
            
            unit.setParentArmy(controlledCities.get(cityIndex).getDefendingArmy());
        }
}
