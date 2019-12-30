import java.io.IOException;
import java.util.ArrayList;

import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

public class Crawler{
	private static int Resp;
	static Floor F=new Floor();
	static Player P1=new Player();
	static Weapons W=new Weapons();
	static Random Rng=new Random(); 
	public static int RNG() {
	return Rng.nextInt(1000)+1;}
	static Scanner in =new Scanner(System.in);
	static boolean done=false;
	private static boolean EXTRA_HUD;
	static boolean MonDead=false;
	ArrayList<Player> LeaderBoard = new ArrayList<Player>();
	public static void Step() {
		F.TakeStep();
		if (F.GetFloorDist()==0) {
		SuperCatch(2);
		F.TakeStep2(Resp);}
	}//use this when step going forward as a combo of Take steps
	public static void SuperCatch(int ResMax) {
		boolean Fin=false;
		while (Fin==false) {
			if (in.hasNextInt()) { 
				Resp=P1.ResCatch(in.nextInt(), ResMax);
				Fin=true;
			} else {
				System.out.println("Please try again, key \"1\" will do your first option and \"2\" will do the second ect. ");
				in.nextLine();
			}
			}
	}
	public static void Forward() throws IOException {
		EXTRA_HUD=true;
		F.SetFloorSpawns();
		if (F.GetMonSpawn()>=RNG()) {
			MonDead=false;
			Orc M= new Orc();
			M.SetM(F.Getfloor());
			while (MonDead==false) {
			M.OrcEncounter(P1.GetPot_Count());
			SuperCatch(3);
			if (Resp==1) {
				W.SetMHealth(M.GetHPM());
				double Mhurt=W.AttackP(P1.GetStrength());
				M.MonsHurt(Mhurt);
				if (M.GetHPM()<0) {M.SetHPM0();}
				System.out.printf(M.GetNameM()+" -%.1f damage! %.1f health remaining.%n", Mhurt, M.GetHPM());
				if (M.GetHPM()==0) {
					P1.AddGOLD(M.GetGOLDM());
					P1.AddEXP(M.GetEXPM());
					System.out.printf("The %s has died. + %d Gold + %.0f EXP.%nYou now have %.0f/%.0f EXP. %d Gold%n", M.GetNameM(), M.GetGOLDM(), M.GetEXPM(), P1.AddEXP(0), P1.GetEXPLIM(), P1.AddGOLD(0));
					MonDead=true;
					P1.AddKill();
				} else {
					double Phurt=M.AttackM();
					P1.PlayerHurt(Phurt);
					if (P1.GetHP()<0) {P1.SetHP0();}
					System.out.printf("%s took -%.1f damage. %.1f/%.1f%n", P1.GetName(), Phurt, P1.GetHP(), P1.GetHP_MAX());
					if (P1.GetHP()==0) {P1.YouLost(F.Getfloor());;}
				}
			}
			if (Resp==2) {
				double Phurt=M.AttackM();
				RunOpt(Phurt);
			}
			if (Resp==3) {
				P1.UsePot();
			}
			}//Monster Dead while loop
		} else if (F.GetTrapSpawn()>=RNG()) {
			double Phurt=F.GetTrapDMG();
			P1.PlayerHurt(Phurt);
			if (P1.GetHP()<0) {P1.SetHP0();}
			System.out.printf("You fell into a trap and took %.1f damage. Your current health is %.1f%n", Phurt, P1.GetHP());
			if (P1.GetHP()==0) {P1.YouLost(F.Getfloor());}
		} else if (415>=RNG()) {
			short CR=W.ChestRarity(F.Getfloor());
			short WEPID=W.DetermineSwordID(CR);
			W.OpenChest(CR, W.GetRare(), WEPID);
			SuperCatch(2);
			if (W.GetKeepOG()==true) {
				if (Resp==1) {P1.AddGOLD(W.SellWeapon(CR)); System.out.println(P1.GetName()+" sold the weapon for "+W.SellWeapon(CR)+"$");}
				if (Resp==2) {P1.AddGOLD(W.SellWeapon(W.GetRare())); System.out.println(P1.GetName()+" sold the "+W.GetName()+" for "+W.SellWeapon(W.GetRare())+"$");
				W=W.EquipWeapon(WEPID);}
			} if (W.GetKeepOG()==false) {
				if (Resp==2) {P1.AddGOLD(W.SellWeapon(CR)); System.out.println(P1.GetName()+" sold the weapon for "+W.SellWeapon(CR)+"$");}
				if (Resp==1) {P1.AddGOLD(W.SellWeapon(W.GetRare())); System.out.println(P1.GetName()+" sold the "+W.GetName()+" for "+W.SellWeapon(W.GetRare())+"$");
				W=W.EquipWeapon(WEPID);}
			}
P1.SetWeapon(W.GetName());
		} else {System.out.println("There seems to be nothing here."); EXTRA_HUD=false;}
		if (F.GetFloorDist()!=-1) {Step();}
	}
	
	
	public static void RunOpt(double Phurt) throws IOException {
		if (300>=RNG()) {
			System.out.println("You managed to get away. ");
			MonDead=true;
		} else {
			System.out.println("You could not get away. ");
			P1.PlayerHurt(Phurt);
			if (P1.GetHP()<0) {P1.SetHP0();}
			System.out.printf("%s Took %.1f damage. Your current health is %.1f%n", P1.GetName(), Phurt, P1.GetHP());
			if (P1.GetHP()==0) {P1.YouLost(F.Getfloor());}
		}
	}

	public static int ActionR() {
		if (F.GetFloorDist()==-1) {
			System.out.printf("A Massive Opening lays ahead, what will you do?%n%-35s %-35s %-35s %n" , "1: Explore around", "2: Use a Potion ("+P1.GetPot_Count()+" Remaining)", "3: Climb to floor "+(F.Getfloor()+1));//Add option 4 to escape and also format to -35
			return 3;
		} else System.out.printf(P1.GetOpenroom(P1.GetPot_Count())); return 2;
		
	}
	
	public static void main(String[] args) throws IOException {
		
		//Beginning of the game
		
		P1.SetNameP();
		W=W.EquipWeapon((short) 0);
		while (true) {
			//GUI go=new GUI();
			//go.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//go.setSize(300,200);
			//go.setVisible(true);
		SuperCatch(ActionR());
		P1.HUDV2(F.Getfloor());
		if (Resp==1) {
			Forward();
			Resp=1;}
		if (Resp==2) {
			P1.UsePot();
			Resp=2;}
		if (Resp==3) {
			F.TakeStep2(1);
			Resp=3;}
		
		
		if (EXTRA_HUD==true) {P1.HUDV2(F.Getfloor());}
		}
		
	}
}
