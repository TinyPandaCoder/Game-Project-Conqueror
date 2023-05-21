package engine;

import buildings.*;
import static engine.Game.findCity;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.NotEnoughGoldException;
import java.util.ArrayList;
import units.Army;
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
 
	
}
