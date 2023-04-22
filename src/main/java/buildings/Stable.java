package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

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
}
