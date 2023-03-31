package jeux2version;

public class Ennemis extends joueur {
	
	int playerXp;

	public Ennemis(String name, int playerXp) {
		super(name,(int) (Math.random()*playerXp+ playerXp/3+5), (int) (Math.random()*(playerXp/4+2)+1));
		this.playerXp = playerXp;
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return (int) (Math.random()*(playerXp/4+1)+xp/4+3);
	}

	@Override
	public int defense() {
		// TODO Auto-generated method stub
		return (int) (Math.random()*playerXp+ playerXp/4+3);
	}

}
