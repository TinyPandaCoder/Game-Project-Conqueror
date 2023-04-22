package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

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
}
