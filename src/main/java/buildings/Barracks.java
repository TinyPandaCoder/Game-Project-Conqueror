package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

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
}
