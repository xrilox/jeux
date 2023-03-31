package jeux2version;

public abstract class joueur {
		
		public String name;
		public int maxHp, hp, xp;
		
		public joueur (String name, int maxHp, int xp) {
			this.name = name;
			this.maxHp = maxHp;
			this.xp = xp;
			this.hp = maxHp;
			
		}
		
		
		public abstract int attack();
		public abstract int defense();
		
}
