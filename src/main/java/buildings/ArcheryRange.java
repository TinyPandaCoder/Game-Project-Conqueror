package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Archer;
import units.Unit;
import engine.Game;
public class ArcheryRange extends MilitaryBuilding {
	ArcheryRange(){
		super(1500 , 800 , 400);
	}
        
        @Override
        public void upgrade() throws BuildingInCoolDownException, MaxLevelException{
            super.upgrade();
            
            if(getLevel() == 2){
                setUpgradeCost(700);
                setRecruitmentCost(450);
            }else if(getLevel() == 3){
                setRecruitmentCost(500);
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
            
            Archer archer = new Archer(getLevel() , (int)Game.levelValuesArcher[getLevel() - 1][0]
            ,Game.levelValuesArcher[getLevel() - 1][1]
            ,Game.levelValuesArcher[getLevel() - 1][2]
            ,Game.levelValuesArcher[getLevel() - 1][3]);
            
            setCurrentRecruit(getCurrentRecruit() + 1);
            
            return (Unit)archer;
            
            
        }
        
    public String getUnitTypeName(){
        return "Archer";
    }
}
