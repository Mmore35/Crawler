
class Orc extends Monster {
	public Orc () {
		STRENGTHMOD=15f;
		SPEEDMOD=1.1f;
		NAMEM="Monster";
	}
	public void OrcEncounter(int Pot) {
		System.out.printf("A level "+LEVELM+" Monster approaches!!%nWhat will you do?%n1: Attack		2: Run		3: Use A Potion ("+Pot+" Remaining)%n");
	}
		public void SetM(int GetF) {
		LEVELM=(Rng.nextInt(11)-5+GetF);
		if (LEVELM<1) {LEVELM=1;}
		EXP_GIVEN=10d*Math.pow(1.3, LEVELM);
		STRENGTHM=STRENGTHMOD*Math.pow(1.2, LEVELM);
		SPEEDM=LEVELM*SPEEDMOD;
		GOLD_GIVEN=(long) ((LEVELM*1.3)*(Rng.nextInt(300)+50));
		HPM=85*Math.pow(1.3, LEVELM);
		
	}//Sets level and strength gold given
	
	public double AttackM() {
		AttackOmod();
		return super.AttackM()*BONUSDMGM;
	}// attack method
	public void AttackOmod() {
		int Crit_chance=RNG();
		if (40>=Crit_chance) {
			BONUSDMGM=2;
		} else {BONUSDMGM=1;}//Critical chance
	}
}
