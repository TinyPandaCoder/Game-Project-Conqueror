package engine;

import java.io.IOException;
import java.util.ArrayList;

public class Game {
	private Player player;
	private ArrayList<City> availableCities;
	private ArrayList<Distance> distances;
	private final int maxTurnCount = 30;
	private int currentTurnCount = 1;
	public Game(String playerName,String playerCity) throws IOException{
		this.player = new Player(playerName);
		City city = new City(playerCity);
                loadCitiesAndDistances();
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
