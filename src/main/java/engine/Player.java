package engine;

import buildings.*;
import exceptions.*;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxRecruitedException;
import static engine.Game.findCity;
import java.util.ArrayList;
import units.Army;
import units.Status;
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

        private boolean exist(Building building,City city)
        {
            return (city.getMilitaryBuildings().contains(building)) || (city.getEconomicalBuildings().contains(building));
        }
        private Building createBuilding(String type)
        {
            if (type.equals("Farm"))
            {
                return new Farm();
            }
        
            if (type.equals("Stable"))
            {
                return new Stable();
            }
                    
            if (type.equals("Market"))
            {
                return new Market();
            }
                    
            if (type.equals("ArcheryRange"))
            {
                return new ArcheryRange();
            }
                    
            if (type.equals("Barracks"))
            {
                return new Barracks();
            }
            return null;
        }
        public void build(String type,String cityName) throws NotEnoughGoldException
        {
            int i=findCity(cityName,this.controlledCities);
            if (i==-1) return ;
            var building=createBuilding(type);
            building.setCoolDown(false);
            if (exist(building,this.controlledCities.get(i)))
                return ;
            if (building.getCost()>this.treasury)
            {
                throw new NotEnoughGoldException("You don't have enough gold.");
            }
            this.treasury-=building.getCost();
            if (building instanceof MilitaryBuilding)
            {
                this.controlledCities.get(i).getMilitaryBuildings().add((MilitaryBuilding)building);
            }
            if (building instanceof EconomicBuilding)
            {
                this.controlledCities.get(i).getEconomicalBuildings().add((EconomicBuilding)building);
            }
            
        }
        public void upgradeBuilding(Building b) throws NotEnoughGoldException,
        BuildingInCoolDownException, MaxLevelException
        {
            if(b.getUpgradeCost()>this.treasury)
                throw new NotEnoughGoldException("You don't have enough gold.");
            this.treasury-=b.getUpgradeCost();
            b.upgrade();

        }
        public void laySiege(Army army,City city) throws TargetNotReachedException,
        FriendlyCityException
        {
            if (this.controlledCities.contains(city))
                throw new FriendlyCityException("You can't attack friendly city.");
            if (!army.getCurrentLocation().equals(city.getName()))
                throw new TargetNotReachedException("Target not reached.");
            army.setTarget(city.getName());
            army.setCurrrentStatus(Status.BESIEGING);
            city.setUnderSiege(true);
                    
            
            
        }
	
        
        
        public void initiateArmy(City city,Unit unit){
            Army army = new Army(city.getName());
            
            ArrayList<Unit> units = army.getUnits();
            
            units.add(unit);
            
            city.getDefendingArmy().getUnits().remove(unit);
            
            unit.setParentArmy(army);
            
            controlledArmies.add(army);
        }
}
