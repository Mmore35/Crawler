import java.util.Random;

public class Monster {
	Random Rng=new Random(); 
	public int RNG() {
	return Rng.nextInt(1000)+1;}
    public double HPM;
	public double EXP_GIVEN;
	public int LEVELM;
	public double STRENGTHM;
	public float STRENGTHMOD;
	public double SPEEDM;
	public float SPEEDMOD;
	public long GOLD_GIVEN;
	public String NAMEM;
	public float BONUSDMGM;
	public double AttackM() {
		return STRENGTHM*(1+(RNG()-500)/1000d);}
	

	public Monster() {
		
	}
	
	public double GetHPM() {
		return HPM;
	}
	
	public double GetEXPM() {
		return EXP_GIVEN;
	}

	public long GetGOLDM() {
		return GOLD_GIVEN;
	}
	
	public void MonsHurt(double Hurtamt) {
		HPM-=Hurtamt;
	}
	
	public void SetHPM0() {
		HPM=0;
	}
	
	public String GetNameM() {
		return NAMEM;
	}
}	
