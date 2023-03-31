package jeux2version;

public class player extends joueur{
	
	public int numatplus, numdefplus;
	
	int gold,repos,pots;
	
	public String[] atplus = {"force", "pouvoir","untruc","unautretruc"};
	public String[] defplus = {"def1", "def2", "def3", "def4" };
	

	public player(String name) {
		super(name, 100, 0);
		// TODO Auto-generated constructor stub
		this.numatplus = 0;
		this.numdefplus = 0;
		
		this.gold= 5;
		this.repos = 1;
		this.pots = 1;
		
		choisiuntrait();
	}

	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return (int)  (Math.random()*(xp/4 + numatplus*3+3) + xp/10 + numatplus*2+numdefplus+1);
	}

	@Override
	public int defense() {
		// TODO Auto-generated method stub
		return (int) (Math.random()*(xp/4 + numdefplus*3+3) + xp/10 + numdefplus*2+numatplus+1);
	}
	
	
	public void choisiuntrait() {
		Jeux.clearConsole();
		Jeux.printHeading("choisi");
		System.out.println("(1)" + atplus[numatplus]);
		System.out.println("(2)" + defplus[numdefplus]);
		
		int input = Jeux.readInt("->", 2);
			Jeux.clearConsole();
		if(input == 1) {
			Jeux.printHeading("tu a choisi" + atplus[numatplus]);
			numatplus++;
		}else {
			Jeux.printHeading("tu a choisi" + defplus[numdefplus]);
		}
		Jeux.anythingToContinue();
		
	}

}
