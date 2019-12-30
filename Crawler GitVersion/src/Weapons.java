import java.util.Random;

public class Weapons {

	static Random Rng=new Random();
	public static int RNG() {
		return Rng.nextInt(1000)+1;}
	private float DMG_MOD;
	private String WEP_NAME;
	private String ABILITY;
	private String ULT;
	private String DIS;
	private float ULT_DMG_MOD;
	private short RATE;
	private short COOLDOWN;
	private short COOL_TOP;
	private short ID;
	private int TIER2CHANCE;
	private int TIER3CHANCE;
	private short SELLWEP;
	private boolean KEEPOG=true;
	private double MHEALTH;
	
	
	Weapons (float DMG_MOD, String WEP_NAME, String ABILITY, String ULT, String DIS, float ULT_DMG_MOD, short rate,short CoolDown, short Cool_Top, short ID) {
		 
		this.DMG_MOD=DMG_MOD;
		this.WEP_NAME=WEP_NAME;
		this.ABILITY=ABILITY;
		this.ULT=ULT;
		this.DIS=DIS;
		this.ULT_DMG_MOD=ULT_DMG_MOD;
		this.RATE=rate;
		this.COOLDOWN=CoolDown;
		this.COOL_TOP=Cool_Top;
		this.ID=ID;
		
	}
	
	Weapons(){SetSword();}
	
	public Weapons SetSword () {
		return new Weapons(1f, "Sword", "Does 100% Damage", "None", "", 1f, (short) 0, (short) 0, (short) 0, (short) 0);
	}
	
	public Weapons SetBabbage1 () {
		return new Weapons(1.25f, "The Babbage", "Does 125% more Damage", "Analytical Destruction (225% more damage, 10 turn cooldown)", "", 2.25f, (short) 1, (short) 10, (short) 10, (short) 1);
	}
	
	public Weapons SetTuring2 () {
		return new Weapons(1.5f, "Turing Sword", "Does 150% more Damage", "Logic (375% more damage, 10 turn cooldown)", "", 3.75f, (short) 2, (short) 10, (short) 10, (short) 2);
	}
	
	public Weapons SetEQP3 () {
		return new Weapons(2.25f, "Equivalence Principle", "Does 225% more Damage", "Energy Conversion (Damage is calculated by E=mc^2, where E=damage, m=strength, and c=speed of light (3×10^8), 10 turn cooldown)", "", 300000000f, (short) 3, (short) 10, (short) 10, (short) 3);
	}
	
	public float GetDmgMod() {
		return DMG_MOD;
	}
	
	public String WepDis(String Ability, String UltDis) {
		return " This weapon "+Ability+".";//+". Ultimate: "+UltDis;
	}
	
	public short ChestRarity (int floor) {
		
		if (floor>=35) {
			TIER2CHANCE = 350;
			TIER3CHANCE = 500;
		} else {
			TIER2CHANCE = floor * 30;
		if (TIER2CHANCE > 600) {
			TIER2CHANCE = 600;
		}
		TIER3CHANCE = floor * 10;
		if (TIER3CHANCE > 300) {
			TIER3CHANCE = 300;
		}
		}//else for floor check
		
		if (TIER3CHANCE>=RNG()) {
			return 3; 
		}
		else if (TIER2CHANCE>=RNG()) {
			return 2;
		}
		else 
			return 1;
	}
	
	public void OpenChest(short CR, short CurrentRarity, short WEP_ID) {
	 
		
		Weapons w = EquipWeapon(WEP_ID);
        System.out.println("You have found found A weapon: "+w.WEP_NAME+" (tier "+w.RATE+")."+WepDis(w.ABILITY,w.ULT)+" Will you use this new weapon?");
        if (CR>CurrentRarity) {
        	System.out.printf("%-35s %-35s \n" , "1: Use new weapon", "2: sell new weapon");
        	KEEPOG=false;
        } 
        else if (CR<=CurrentRarity) {
        	System.out.printf("%-35s %-35s \n" , "1: sell new weapon", "2: use new weapon");
        	KEEPOG=true;
        } 
       
	}
	
	public short DetermineSwordID(short rare) {//Implement an RNG and have it return the weapon ID that it comes up with.
		if (rare==1) {return 1;}
		if (rare==2) {return 2;}
		if (rare==3) {return 3;}
		else return 0;
	}
	
	public Weapons EquipWeapon(short WepID) {
		if (WepID==1) {return SetBabbage1();}
		if (WepID==2) {return SetTuring2();}
		if (WepID==3) {return SetEQP3();}
		return SetSword();
	}
	
	public boolean GetKeepOG() {
		return KEEPOG;
	}
	
	public double AttackP(double STRENGTH) {
		return (STRENGTH*(1+(RNG()-500)/1000d))*DMG_MOD;
}
	
	 public double ULTAttackP(double STRENGTH) {
			return (STRENGTH*(1+(RNG()-500)/1000d))*ULT_DMG_MOD;
	}
	
	public int SellWeapon(short rare) {
		 switch (rare) { //sets sell value for a sold current weapon
	        case 1: 
	        	return 1500;
	        case 2: 
	        	return 5000;
	        case 3: 
	        	return 15000;
	        default: 
	           return 0; 
	        } 
	}
	
	public short GetRare() {
		return RATE;
	}
	
	public String GetName() {
		return WEP_NAME;
	}
	
	public void SetMHealth(double MHealth) {
		this.MHEALTH=MHealth;
	}
	
	
	
	
	public class Babbage extends Weapons {
		
		 Babbage () {
			 
			 DMG_MOD=1.25f;
			 WEP_NAME= "The Babbage";
			 ABILITY="Does 125% more Damage";
			 ULT="Analytical Destruction (225% more damage, 10 turn cooldown)";
			 DIS="";
			 ULT_DMG_MOD=2.25f;
			 RATE=(short) 1;
			 COOLDOWN=(short) 10;
			 COOL_TOP=(short) 10;
			 ID=(short) 1;
		}
		 
		 @Override
		 public double AttackP(double STRENGTH) {
				return (STRENGTH*(1+(RNG()-500)/1000d))*DMG_MOD;
		}
		
	}
	
	public class Turing extends Weapons {
		
		Turing() {
			 
			 DMG_MOD=1.5f;
			 WEP_NAME= "Turing Sword";
			 ABILITY="Does 150% more Damage, 200% more when enemy health under 50%";
			 ULT="Logic (375% more damage, 10 turn cooldown)";
			 DIS="";
			 ULT_DMG_MOD=3.75f;
			 RATE=(short) 2;
			 COOLDOWN=(short) 10;
			 COOL_TOP=(short) 10;
			 ID=(short) 2;
		}
		 
		 @Override
		 public double AttackP(double STRENGTH) {
			 
			 //if (MHEALTH>=)
				return (STRENGTH*(1+(RNG()-500)/1000d))*DMG_MOD;
		}
		
	}
}

