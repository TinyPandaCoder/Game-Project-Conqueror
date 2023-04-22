package buildings;

import engine.Game;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Cavalry;
import units.Unit;

public class Stable extends MilitaryBuilding {
	public Stable(){
		super(2500 , 1500 , 600);
	}
        
         @Override
        public void upgrade() throws BuildingInCoolDownException, MaxLevelException{
            super.upgrade();
            
            if(getLevel() == 2){
                setUpgradeCost(2000);
                setRecruitmentCost(650);
            }else if(getLevel() == 3){
                setRecruitmentCost(700);
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
            
            Cavalry archer = new Cavalry(getLevel() , (int)Game.levelValuesCavalry[getLevel() - 1][0]
            ,Game.levelValuesCavalry [getLevel() - 1][1]
            ,Game.levelValuesCavalry [getLevel() - 1][2]
            ,Game.levelValuesCavalry [getLevel() - 1][3]);
            
            setCurrentRecruit(getCurrentRecruit() + 1);
            
            return (Unit)archer;
            
            
        }
}
