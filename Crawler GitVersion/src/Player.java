import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class Player  implements Comparable <Player> {
	Random Rng=new Random(); 
	public int RNG() {
	return Rng.nextInt(1000)+1;}
	private String VERSION="2.0.0";
	private static double HP;
	private static double HP_MAX;
	private static double EXP;
	private static double EXPLEVELUP;
	private short LEVEL;
	private double STRENGTH;
	private double SPEED;
	private short POT_COUNT;
	private static long GOLD;
	private String NAME;
	private String BO;
	private int SC;
	private long TOTALGOLD;
	private int EMNKILL;
	private String WEAPON;
	private int Floor;
	public String STAND;
	Scanner in=new Scanner(System.in);
	public String GetOpenroom (int PotC) {
		return String.format("A narrow cave is ahead, what will you do?%n%-35s %-35s%n", "1: Search for a deeper level", "2: Use a Potion ("+PotC+" Remaining)");
	}
	boolean done=false;
	public int ResCatch (int Res, int ResMax) {
		done=false;
		while (done==false) {
		if (Res<=ResMax & Res>0) { 
			done=true; 
		} else {
			System.out.println("Please try again, key \"1\" will do your first option and \"2\" will do the second ect.");
			if (in.hasNextInt()) {
				ResCatch(in.nextInt(), ResMax);
			} 
		}
	}
	return Res;}
	
	public void UsePot() {
		if (POT_COUNT>0 && HP!=HP_MAX) {
		HP +=1000;
		if (HP>HP_MAX) {HP=HP_MAX;}
		POT_COUNT-=1;
		System.out.printf("%s used a Potion, You now have %.0f Health, and "+POT_COUNT+" Potions.%n", NAME, HP);
		} else if (POT_COUNT==0) {System.out.println("Your POT seems to have run dry");
		} else System.out.println(NAME+" has full HP, retard");
	}
	
	public Player() {
		HP=100d;
		HP_MAX=100d;
		EXP=0d;
		LEVEL=1;
		STRENGTH=60d;
		SPEED=100d;
		POT_COUNT=3;
		GOLD=0;
		TOTALGOLD=0;
		EMNKILL=0;
		WEAPON="Sword";
		STAND="None";
		EXPLEVELUP=10d;//could use tweaking
		
	}
	
	public void SetWeapon(String Weapon) {
		WEAPON=Weapon;
	}
	
	public Player (String name, int floor, short level, long totalgold, int emnkill, String weapon, String stand) {
		 this.NAME=name;
		 this.Floor=floor;
		 this.LEVEL=level;
		 this.TOTALGOLD=totalgold;
		 this.EMNKILL=emnkill;
		 this.WEAPON=weapon;
		 this.STAND=stand;
	}
	
	public Player (int score, String leader) {
		
		this.BO=leader;
		this.SC=score;
	}
	
	public String GetName() {
		return NAME;}
	
	public void YouLost(int floor) throws IOException {
		System.out.printf("%s survived to floor %d%n", NAME, floor);
		System.out.printf("Y%nO%nU%n%nL%nO%nS%nT%n");
		if (floor<=14) {System.out.printf("\nThat was embarrassing.\n");}
		LinkedList<Player> LeaderComp=new LinkedList<>();
		try {
		Path path = Paths.get("LeaderBoards.txt");
		Scanner in = new Scanner(path);
		Path path2 = Paths.get("LeaderComp.txt");
        Scanner in2 = new Scanner(path2);
        String line;
        
 while (in2.hasNextLine()) {
            line=in2.nextLine();
            String[] splitted= line.split("\\t", 2);
            int val = Integer.parseInt(splitted[0]);
            
            LeaderComp.add(new Player(val, splitted[1]));
        }
 
 int FloorGold=floor;
 LeaderComp.add(new Player (FloorGold, String.format("%-13s Floor: %-3d Level: %-5d Total Gold Collected: %,-15d Enemies killed: %,-6d Weapon Of Choice: %-10s   Stand: %s, Version: %s", NAME, floor, LEVEL, TOTALGOLD, EMNKILL, WEAPON, STAND, VERSION)));
 Collections.sort(LeaderComp);
        PrintWriter Out;
        Out=new PrintWriter("LeaderComp.txt");
        PrintWriter outFile;
		outFile = new PrintWriter("LeaderBoards.txt");
        
        for (int i=0; i<5; i++) {
		Out.printf(LeaderComp.get(i).SC+"\t");
		Out.printf(LeaderComp.get(i).BO+"\n");
		outFile.println(i+1+": "+LeaderComp.get(i).BO);
		}
        
		outFile.close();
		in.close();
		Out.close();
		in2.close();
		} catch (FileNotFoundException e) {e.printStackTrace();}
		System.exit(0);
	}
	
	public void SetNameP () {
		System.out.print("What should you be called: ");
		NAME=in.nextLine();
	}//Will use this eventually 
	
	public int GetPot_Count() {
		return POT_COUNT;
	}
	
	public void SetFloor(int Getfloor) {
		this.Floor=Getfloor;
	}
	
	public void HUDV2(int floor) {
		System.out.printf("Health: %,.0f/%,.0f %nEXP:	%,.0f/%,.0f%nLevel:  %d%nGold:   %,d%nFloor:  %d%n", HP, HP_MAX, EXP, EXPLEVELUP, LEVEL, GOLD, floor);
		}
	
	public void HUDV3(int floor) {
		System.out.printf("%s:%nHealth: %,.0f/%,.0f %nEXP:	%,.0f/%,.0f%nLevel:  %d%nGold:   %,d%nFloor:  %d%n", NAME, HP, HP_MAX, EXP, EXPLEVELUP, LEVEL, GOLD, floor);
		}
	
	public void HUD() {
		System.out.printf("Health: %,15.0f out of %,15.0f %nEXP:	%,15.0f out of %,15.0f%nLevel:  %15dGold:  %,15d%n", HP, HP_MAX, EXP, EXPLEVELUP, LEVEL, GOLD);
		}
	
	ArrayList<Player> player = new ArrayList<Player>(); 
	
	public int compareTo(Player P) {
		return P.SC-this.SC;}
	
	public void SpeedUP() {
		SPEED=SPEED+50;
	}
	public void StrengthUP() {
		STRENGTH=STRENGTH*1.2;
	}
	
	public long AddGOLD(long AddG) {
		GOLD+=AddG;
		TOTALGOLD+=AddG;
		return GOLD;
	}
	
	public double AddEXP(double AddEXP) {
		EXP+=AddEXP;
		while (EXP>EXPLEVELUP) {
			LEVELUP();
		}
		return EXP;
	}
	
	public void LEVELUP() {
		EXP-=EXPLEVELUP;
		HP_MAX=HP_MAX*1.15;
		HP=(HP_MAX/2.0)+HP;
		if (HP>HP_MAX) {HP=HP_MAX;}
		EXPLEVELUP=EXPLEVELUP*1.35;
		LEVEL++;
		POT_COUNT++;
		System.out.println("Level up! "+LEVEL);
		StrengthUP();
	}//add strength or speed options later
	
	public double GetEXPLIM() {
		return EXPLEVELUP;
	}
	
	public double GetHP_MAX() {
		return HP_MAX;
	}
	
	public void AddKill() {
		EMNKILL++;
	}

	
	public double GetHP() {
		return HP;
	}
	
	public double GetStrength() {
		return STRENGTH;
	}
	
	public void SetHP0() {
		HP=0;
	}
	
	public void PlayerHurt(double Hurtamt) {
		HP-=Hurtamt;
	}
}
