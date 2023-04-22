package buildings;
import exceptions.*;
public class Farm extends EconomicBuilding {
	Farm(){
		super(1000 , 500);
	}
        
        
        @Override
        public void upgrade() throws BuildingInCoolDownException, MaxLevelException{
            super.upgrade();
            
            setUpgradeCost(700);
            
            
        }
}
