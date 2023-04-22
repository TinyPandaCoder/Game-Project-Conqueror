package units;

import exceptions.FriendlyFireException;

public class Infantry extends Unit {
      private static final double[] ARCHER_TARGET_FACTORS = { 0.3, 0.4, 0.5 };
       private static final double[] INFANTRY_TARGET_FACTORS = { 0.1, 0.2, 0.3 };
       private static final double[] CAVALRY_TARGET_FACTORS = { 0.1, 0.2, 0.25 };
	public Infantry(int level, int maxSoldierConunt, double idleUpkeep, double
			marchingUpkeep, double siegeUpkeep) {
		super(level , maxSoldierConunt , idleUpkeep , marchingUpkeep , siegeUpkeep);
	}

    @Override
    public double unitFactor(Unit target, int level) {
           if (target instanceof Archer) {
            return ARCHER_TARGET_FACTORS[level - 1];
          }
          if (target instanceof Infantry) {
            return INFANTRY_TARGET_FACTORS[level - 1];
          }
          return CAVALRY_TARGET_FACTORS[level - 1];
    }


}
