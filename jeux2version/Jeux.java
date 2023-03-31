package jeux2version;
import java.util.Scanner;


public class Jeux {
	static Scanner scanner = new Scanner (System.in);
	
	static player player;
	
	public static boolean encours;
	public static String [] encounters = {"combat","combat","combat", "shop","repos"};
	public static String [] ennemis = {"ogre","ogre","gobelin","gobelin","élémentaire"};
	
	public static int place = 0, act = 1;
	public static String[] places = {"##","####","###","####"};
	
	
	public static int readInt (String prompt, int userChoices) {
		int input;
		
		do {
			
			System.out.println("prompt");
			
			try {
				input = Integer.parseInt(scanner.next());
			} catch (Exception e){
				input = -1;
				System.out.println("#####");
			}
		}while (input < 1 || input > userChoices );
		return input;
	}
	
	
	
		public static void clearConsole () {
			for (int i = 0; i < 100; i++);
			System.out.println();
		}
			
		public static void printSeperator (int n) {
			for (int i = 0; i < n ; i++);
			System.out.println("-");
			System.out.println(); 
		}
		
		public static void printHeading (String title) {
			printSeperator(30);
			System.out.println(title);
			printSeperator (30);
		}
	
		public static void anythingToContinue() {
			System.out.println("Entrer qqc pour continuer...");
			scanner.next();
		}
		
		
		public static void start() {
			boolean nameset = true ;
			String name;
			clearConsole();
			printSeperator(40);
			printSeperator(30);
			System.out.println("nom du jeux");
			printSeperator(30);
			printSeperator(40);
			anythingToContinue();
			
			do {
				clearConsole();
				printHeading("donne ton nom ?");
				name = scanner.next();
				clearConsole();
				printHeading("ton nom est bien " + name + "?");
				System.out.println("(1) oui");
				System.out.println("(2) non");
				int input = readInt("->" , 2);
				if (input == 1 )
					nameset = true;
				
			} while(!nameset);
			
			histoire.histintro();
			
			player = new player(name);
			histoire.premintro();
			
			
			
			encours = true;
			
			bouclejeux();
		
		}
		
		public static void checkAct() {
			if(player.xp >= 10 && act == 1){
				act = 2;
				place = 1;
				histoire.premoutro();
				player.choisiuntrait();
				histoire.deuxintro();
				ennemis[0] = "Mercenaire";
				ennemis[1] = "Goblin";
				ennemis[2] = "Ogre";
				ennemis[3] = "meute de loup";
				ennemis[4] = "ivrogne";
				
				encounters[0] = "combat";
				encounters[0] = "combat";
				encounters[0] = "combat";
				encounters[0] = "repos";
				encounters[0] = "Boutique";
				
				
				
				
			}else if (player.xp >= 50 && act == 2) {
				act = 3;
				place = 2;
				histoire.deuxoutro();
				player.choisiuntrait();
				histoire.troisintro();
				ennemis[0] = "Mercenaire";
				ennemis[1] = "licorne";
				ennemis[2] = "ours";
				ennemis[3] = "vampire";
				ennemis[4] = "chevalier noir";
				
				encounters[0] = "combat";
				encounters[0] = "combat";
				encounters[0] = "combat";
				encounters[0] = "combat";
				encounters[0] = "Boutique";
				
				player.hp = player.maxHp;
				
			}else if (player.xp >= 100 && act == 3) {
				act = 4;
				place=3;
				histoire.troisoutro();
				player.choisiuntrait();
				histoire.quatreintro();
				player.hp= player.maxHp;
				combatFinal();
			}
		}
		
		public static void randomRencontre() {
			int Rencontre = (int) (Math.random()* encounters.length);
			
			if(encounters[Rencontre].equals("combat")) {
				syscombat();
			}
			else if(encounters[Rencontre].equals("repos")) {
				reposer();
				
			}else{
				shop();
				
			}
		} 
		

		
		
		
		public static void continuelejeux(){
			checkAct();
			if (act != 4)
				randomRencontre();
		}
		
		
		public static void infojoueur() {
			clearConsole();
			printHeading("info du joueur");
			System.out.println(player.name + "hp" + player.hp + "/" + player.maxHp);
			printSeperator(20);
			System.out.println("xp: " + player.xp + "Gold: " + player.gold);
			printSeperator(20);
			System.out.println("potions: " + player.pots);
			printSeperator(20);
			
			
			
			
			if (player.numatplus > 0) { 
				System.out.println("trait offensive :" + player.atplus[player.numatplus - 1]);
			}
			if (player.numdefplus > 0) { 
				System.out.println("trait defensive :" + player.defplus[player.numdefplus - 1]);
			}
			
			anythingToContinue();
			
			
		}
		
		
		public static void shop() {
			clearConsole();
			printHeading("tu as rencontrer un mystériux marchand.\n Il te propose de te montrer ce qu'ils vends");
			int prix = (int) (Math.random()* (10 + player.pots*3) + 10 + player.pots);
			System.out.println("-Potions Magic: " + prix + " gold");
			printSeperator(20);
			System.out.println("veux-tu en acheter une ? \n(1) Oui \n (2) Non");
			int input = readInt("->" , 2);
			if(input ==1) {
				clearConsole();
				if(player.gold >= prix) {
					printHeading("Tu as acheter une potions");
					player.pots++;
					player.gold -= prix;
				}else 
					printHeading("tu n'as pas assé d'argent pour en acheter une ");
					anythingToContinue();
				}
			}
		
		public static void reposer() {
			clearConsole();
			if(player.repos >= 1) {
				printHeading("veux tu te reposer ? ( tu peux te reposer encore " + player.repos+ " fois" );
				System.out.println("(1) oui \n (2) Non");
				int input = readInt("->", 2);
				if (input == 1) {
					clearConsole();
					if(player.hp < player.maxHp) {
						int hpRecup = (int) (Math.random()*(player.xp/4 + 1) + 10);
						player.hp += hpRecup;
						if(player.hp > player.maxHp);
						player.hp = player.maxHp;
						System.out.println("tu as récupérer " + hpRecup + " point de vie");
						System.out.println("tu as maintenant "+player.hp+ "/"+player.maxHp+ " point de vie");
						player.repos--;
					}
						
				}else
					System.out.println("tu as déjà toutes ta vie");
				anythingToContinue();
			}
		}
		
		
		
		
		
		public static void syscombat() {
			clearConsole();
			printHeading("face à toi ce tiens une horrible créature. Tu va devoirs te battre. ");
			anythingToContinue();
			combat(new Ennemis(ennemis[(int)(Math.random()*ennemis.length)], player.xp));
		}
		
		public static void combat(Ennemis ennemis) {
			while(true){
				clearConsole();
				printHeading(ennemis.name + "HP: "+ ennemis.hp + "/" + ennemis.maxHp);
				printHeading(player.name + " HP: "+ player.hp + "/" + player.maxHp);
				System.out.println("choisi une action");
				printSeperator(20);
				System.out.println("(1) combat \n(2) utiliser potion  \n(3) fuir");
				int input = readInt("->" , 3);
				if (input == 1) {
					int dmg = player.attack() - ennemis.defense();
					int dmgpris = ennemis.attack() - player.defense();
					if (dmgpris < 0) {
						dmg -= dmgpris/2;
						dmgpris = 0;
					}
					if (dmg < 0) 
						dmg = 0;
						player.hp -= dmgpris;
						ennemis.hp -= dmg;
					
					
					clearConsole();
					printHeading("COMBAT");
					System.out.println("tu as fais " + dmg + " de dommage à " + ennemis.name);
					printSeperator(15);
					System.out.println(ennemis.name + "t'as fais " + dmgpris + " de dommage");
					anythingToContinue();
					
					if(player.hp<=0) {
						playermort();
						break;
					}else if (ennemis.hp <=0) {
						clearConsole();
						printHeading("tu as battu l'ennemis ");
						player.xp += ennemis.xp;
						System.out.println("tu as gagner " + ennemis.xp + " d'EXP");
						
						boolean addrepos = (Math.random()*5 + 1 <= 2.25);
						int Goldgagner = (int) (Math.random()*ennemis.xp);
						
						if (addrepos) {
							player.repos++;
							System.out.println("tu as gagner le droit de te reposer une fois en plus");
						}
						
						if(Goldgagner > 0) {
							player.gold += Goldgagner;
							System.out.print("tu as trouver " + Goldgagner + "d'or sur le corps de ton ennemis");
						}
						
						anythingToContinue();
						break;
					}
 					 /////////////////////////////////
 					
				}else if (input == 2 ) {
					clearConsole();
					if(player.pots > 0 && player.hp < player.maxHp) {
						printHeading("veux tu boire une potions ? (" + player.pots+ " restante(s)");
						System.out.println("(1) Oui \n (2) Non");
						input = readInt("->" , 2);
						if(input ==1) {
							player.hp = player.maxHp;
							clearConsole();
							printHeading("Tu as bu une potions, tu as retrouver toutes ta santé");
							anythingToContinue();
							
						}
						
						
					}else {
						printHeading("Tu n'as aucune potions ou tu as toutes ta vie ");
						anythingToContinue();
					}
					
				}
				
					/////////////////////////////////////
				else {	
				if(act !=4) {
					clearConsole();
					if(Math.random()*10+1<=5.0) {
						printHeading("tu t'es échapper de " + ennemis.name);
						anythingToContinue();
						break;
					}else {
						printHeading("tu n'as pas réussi à t'enfuir");
						int dmgpris = ennemis.attack();
						System.out.println("dans ta fuite tu as pris " + dmgpris + "de dommage");
						anythingToContinue();
						if (player.hp <= 0)
							playermort();
					}
				}
				else {
					printHeading("TU NE PEUX PAS T'ENFUIRE");
					anythingToContinue();
				}
					
				}
			}
		}
		
		
		
		public static void printMenu() {
			clearConsole();
			printHeading(places[place]);
			System.out.println("choisi une action");
			printSeperator(20);
			System.out.println("(1) continue ton aventure ");
			System.out.println("(2) info du personnage");
			System.out.println("(3) sortir du jeux");
		}
		
		
		public static void combatFinal() {
			combat(new Ennemis ("le grand méchant", 300));
			histoire.finoutro();
			encours = false; 
			
		}
		
		
		
		
		public static void playermort() {
			clearConsole();
			printHeading("Tu est mort ...");
			printHeading("tu as gagner " + player.xp + " XP durant ton voyage, gagnes en plus la pochaine fois");
			encours = false;
		}
		
		
		
		
		
		
		
		
		public static void bouclejeux() {
				while(encours) {
					printMenu();
					int input = readInt("->", 3);
					if (input == 1)
						continuelejeux();
					else if (input == 2 )
						infojoueur();
					else
						encours = false;
				}
		}
		
}
		
		
			
			


