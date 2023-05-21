package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Market extends EconomicBuilding{
	public Market(){
		super(1500 , 700);
	}
        
        @Override
        public void upgrade() throws BuildingInCoolDownException, MaxLevelException{
            super.upgrade();
            
            setUpgradeCost(1000);
            
            
        }
        
        @Override
        public int harvest(){
            
            int gold = 0;
            
            switch(getLevel()){
                case 1 :
                    gold = 1000;
                    break;
                case 2 :
                    gold = 1500;
                    break;
                case 3 :
                    gold = 2000;
                    break;
                default:
                    break;
                
            }
            return gold;
        }
}
