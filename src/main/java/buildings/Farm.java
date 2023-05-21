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
        
        @Override
        public int harvest(){
            
            int gold = 0;
            
            switch(getLevel()){
                case 1 :
                    gold = 500;
                    break;
                case 2 :
                    gold = 700;
                    break;
                case 3 :
                    gold = 1000;
                    break;
                default:
                    break;
                
            }
            return gold;
        }
}
