package buildings;

import engine.Game;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Unit;
import units.Infantry;

public class Barracks extends MilitaryBuilding {
	public Barracks(){
		super(2000 , 1000 , 500);
	}
        
        @Override
        public void upgrade() throws BuildingInCoolDownException, MaxLevelException{
            super.upgrade();
            
            if(getLevel() == 2){
                setUpgradeCost(1500);
                setRecruitmentCost(550);
            }else if(getLevel() == 3){
                setRecruitmentCost(600);
            }
            
            
        }

    @Override
        public Unit recruit() throws BuildingInCoolDownException,
MaxRecruitedException{
            
            if(isCoolDown()){
                throw new BuildingInCoolDownException("Building is cool down");
            }
            
            if(getCurrentRecruit() == getMaxRecruit()){
                throw new MaxRecruitedException("Exceeded the number of maximum recruit");
            }
            
            Infantry archer = new Infantry(getLevel() , (int)Game.levelValuesInfanrty[getLevel() - 1][0]
            ,Game.levelValuesInfanrty [getLevel() - 1][1]
            ,Game.levelValuesInfanrty [getLevel() - 1][2]
            ,Game.levelValuesInfanrty [getLevel() - 1][3]);
            
            setCurrentRecruit(getCurrentRecruit() + 1);
            
            return (Unit)archer;
            
            
        }
        
        
}
