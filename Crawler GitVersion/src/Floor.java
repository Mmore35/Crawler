import java.util.Random;

public class Floor {
	private double MONSTER_SPAWN;
	private double TRAP_SPAWN;
	private double FLOOR_DISMOD;
	private int FLOORNUM;
	private double FLOOR_DIST;
	Random Rng = new Random();

	public int RNG() {
		return Rng.nextInt(1000) + 1;
	}
	
	public int Getfloor() {
		return FLOORNUM;
	}

	public void NextFloor() {
		FLOORNUM++;
		SetFloorDist();
		System.out.printf("You take the staircase down and enter Floor %d%n%n", FLOORNUM);
	}

	public Floor() {
		FLOORNUM = 1;
	}

	public void SetFloorSpawns() {
		MONSTER_SPAWN = (FLOORNUM * .01+1) * 300;
		if (MONSTER_SPAWN > 600) {
			MONSTER_SPAWN = 600;
		}
		TRAP_SPAWN = (FLOORNUM * .01+1) * 40;
		if (TRAP_SPAWN > 80) {
			TRAP_SPAWN = 80;
		}
	}
	
	public void SetFloorDist() {
		FLOOR_DISMOD = FLOORNUM * .07 + 1;
		FLOOR_DIST=(FLOOR_DISMOD * RNG());
	}//Sets Distance
	
	public void TakeStep() {
		FLOOR_DIST-=250;
		if (FLOOR_DIST<0) {FLOOR_DIST=0;}
		if (FLOOR_DIST==0) {System.out.printf("You have found the entrance to the next floor down, where you will encounter harder enemies and find better loot.%nWill you go down A floor (If you choose not to, you can come back at any time to go deeper)%n%-35s %-35s%n" , "1: Go deeper", "2: Stay on this floor longer");}
	}//Takes away from floor dist and looks to see if you get to next floor
	
	public void TakeStep2(int res) {
		if (res==1) {NextFloor(); System.out.printf("You%n%nTravel%n%nDown%n%n");}
		if (res==2) {
			FLOOR_DIST=-1;
			//res 2 will povide a good training ground for the player by having an escape route, and a constant 4th option to go down a floor.
		}
	}
	
	public double GetFloorDist() {
		return FLOOR_DIST;
	}
	
	public double GetMonSpawn() {
		return MONSTER_SPAWN;
	}
	
	public double GetTrapSpawn() {
		return TRAP_SPAWN;
	}
	
	public double GetTrapDMG() {
		double trpdmgmod=FLOORNUM*15;
		return trpdmgmod*(1+(RNG()-500)/1000d);
	}

}
