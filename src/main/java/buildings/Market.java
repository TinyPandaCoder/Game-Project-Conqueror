package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Market extends EconomicBuilding{
	Market(){
		super(1500 , 700);
	}
        
        @Override
        public void upgrade() throws BuildingInCoolDownException, MaxLevelException{
            super.upgrade();
            
            setUpgradeCost(1000);
            
            
        }
}
