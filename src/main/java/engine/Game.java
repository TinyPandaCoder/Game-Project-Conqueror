package engine;
import buildings.EconomicBuilding;
import buildings.MilitaryBuilding;
import java.io.IOException;
import java.util.ArrayList; 
import units.*;
public class Game {
        static public double [][]levelValuesArcher={{60,0.4,0.5,0.6},{60,0.4,0.5,0.6},{70,0.5,0.6,0.7}};
        static public double [][]levelValuesInfanrty={{50,0.5,0.6,0.7},{50,0.5,0.6,0.7},{60,0.6,0.7,0.8}};
        static public double [][]levelValuesCavalry={{40,0.6,0.7,0.75},{40,0.6,0.6,0.75},{60,0.7,0.8,0.9}};

	private Player player;
	private ArrayList<City> availableCities;
	private ArrayList<Distance> distances;
	private final int maxTurnCount = 30;
	private int currentTurnCount = 1;
	public Game(String playerName,String playerCity) throws IOException{
		this.player = new Player(playerName);
                loadArmy(playerCity);
                loadCitiesAndDistances();
	}
        public static int findCity(String cityName, ArrayList<City> availableCities)  {
            for (int i=0;i<availableCities.size();i++ )
            {
                if (availableCities.get(i).getName().equals(cityName))
                    return i;
            }
            return -1;
        }
        
        private void loadCitiesAndDistances() throws IOException
        {
           ArrayList<String[]> citiesDistances = ReadingCSVFile.readFile("./Resources/Distances.csv");
           for (String[] cityDistance : citiesDistances)
           {
               Distance newDistance=new Distance(cityDistance[0],cityDistance[1],Integer.parseInt(cityDistance[2]));
               distances.add(newDistance);
               City newCity=new City(cityDistance[0]);
               if (!availableCities.contains(newCity))
               {
                   availableCities.add(newCity);
               }
               newCity=new City(cityDistance[1]);
               if (!availableCities.contains(newCity))
               {
                   availableCities.add(newCity);
               }   
           }
        }
        public void loadArmy(String cityName) throws IOException
        {
            for ( int i=0;i<availableCities.size();i++ )
            {
                City city=availableCities.get(i);
                if(city.getName().compareTo(cityName)!= 0) {
                    Army army=new Army(city.getName());
                    ArrayList<String[]> cityUnit= ReadingCSVFile.readFile("./Resources/"+city.getName()+".csv");
                    ArrayList<Unit>cityUnits=new ArrayList<Unit>();
                    for (String [] unit : cityUnit)
                    {
                        
                        String type=unit[0];
                        int level=Integer.parseInt(unit[1]);
                        switch (type) {
                            case "Archer":
                                Archer archer;
                                archer = new Archer(level,(int)levelValuesArcher[level-1][0],levelValuesArcher[level-1][1],levelValuesArcher[level-1][2],levelValuesArcher[level-1][3]);
                                archer.setParentArmy(army);
                                cityUnits.add(archer);
                                break;
                            case "Cavalry":
                                Cavalry cavalry;
                                cavalry = new Cavalry(level,(int)levelValuesCavalry[level-1][0],levelValuesCavalry[level-1][1],levelValuesCavalry[level-1][2],levelValuesCavalry[level-1][3]);
                                cavalry.setParentArmy(army);
                                cityUnits.add(cavalry);
                                break;
                            case "Infantry":
                                Infantry infantry;
                                infantry = new Infantry(level,(int)levelValuesInfanrty[level-1][0],levelValuesInfanrty[level-1][1],levelValuesInfanrty[level-1][2],levelValuesInfanrty[level-1][3]);
                                infantry.setParentArmy(army);
                                cityUnits.add(infantry);
                                break;
                            default:
                                break;
                        }
                    }
                    army.setUnits(cityUnits);
                    city.setDefendingArmy(army);
                }
                availableCities.set(i,city);
            }
        }
        
        public void targetCity(Army army, String targetName){
            
            if(army.getCurrrentStatus().toString().equals("MARCHING")){
                return;
            }
            
            army.setTarget(targetName);
            
            army.setCurrentStatus(Status.MARCHING);
            
            for(Distance distance : distances){
                if(distance.getFrom().equals(army.getCurrentLocation()) 
                        && distance.getFrom().equals(targetName)){
                    army.setDistancetoTarget(distance.getDistance());
                }
            }
            
            
        }
        
        public void endTurn(){
            
            
            this.currentTurnCount++;
            
            ArrayList<City> cities = this.player.getControlledCities();
            
            for(City city : cities){
                ArrayList<EconomicBuilding> ecoBuildings = city.getEconomicalBuildings();
                ArrayList<MilitaryBuilding> milBuildings = city.getMilitaryBuildings();
                
                
                
                
                for(var ecoBuilding : ecoBuildings){
                    ecoBuilding.setCoolDown(false);
                    int gold = ecoBuilding.harvest();
                    
                    this.player.setTreasury(this.player.getTreasury() + gold);
                }
                
                for(var milBuilding : milBuildings){
                    milBuilding.setCoolDown(false);
                    
                    milBuilding.setCurrentRecruit(0);
                }
            }
            
            
            ArrayList<Army> armies = this.player.getControlledArmies();
            
            double food = 0;
            
            for(Army army : armies){
                food += army.foodNeeded();
                
                if(army.getDistancetoTarget() > 0){
                    army.setDistancetoTarget(army.getDistancetoTarget() - 1);
                    
                    if(army.getDistancetoTarget() == 0){
                        army.setCurrentLocation(army.getTarget());
                        army.setCurrentStatus(Status.IDLE);
                        army.setTarget("");
                        
                    }
                }
            }
            if(this.player.getFood() >= food){
                    this.player.setFood(this.player.getFood() - food);
            }else{
                this.player.loseArmy();
            }
            
            
        }
        
        // TODO
        void handleSiege(City city){
            if(city.getTurnsUnderSiege() == 3){
                
                city.setUnderSiege(false);
            }else{
                
                city.setTurnsUnderSiege(city.getTurnsUnderSiege() + 1);
            }
        }
        // TODO
        void updateCityUnderSiege(){
            
            for(City city : availableCities){
                if(city.isUnderSiege()){
                    
                }
            }
        }
        
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getCurrentTurnCount() {
		return currentTurnCount;
	}
	public void setCurrentTurnCount(int currentTurnCount) {
		this.currentTurnCount = currentTurnCount;
	}
	public ArrayList<City> getAvailableCities() {
		return availableCities;
	}
	public ArrayList<Distance> getDistances() {
		return distances;
	}
	public int getMaxTurnCount() {
		return maxTurnCount;
	}

	
}
