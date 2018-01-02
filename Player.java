package application;

public class Player {
	BurgerBun bb;
	BurgerFryPan bfp;
	BurgerPatty bp;
	Cola c;
	ColaDispenser cd;
	FoodWarmer fw;
	HotDog hd;
	HotDogBun hdb;
	HotDogGrillPan hdgp;
	Ketchup k;
	LettuceLeaves ll;
	Sausage s;
	Tomatos t;
	
	String name;
	int level;
	int money;
	int humTableSize;
	int hdTableSize;
	int humFrySize;
	int hdFrySize;
	int warmerSize;
	
	Player(String name){
		this.name = name;
		init();
	}
	
	void init(){
		bb = new BurgerBun();
		bfp = new BurgerFryPan(1);
		bp = new BurgerPatty();
		c = new Cola();
		cd = new ColaDispenser(1);
		fw = null;
		hd = null;
		hdb = null;
		hdgp = null;
		k = null;
		ll = null;
		s = null;
		t = null;
		money = 10000;
		humTableSize = 0;
		hdTableSize = 0;
		humFrySize = 1;
		hdFrySize = 0;
		warmerSize = 0;
		level = 1;
	}
	int getLevel(){
		return level;
	}
	int getMoney(){
		return money;
	}
	int getHumTableSize(){
		return humTableSize;
	}
	int gethdTableSize(){
		return hdTableSize;
	}
	int getHumFrySize(){
		return humFrySize;
	}
	int getHdFrySize(){
		return hdFrySize;
	}
	int getWarmerSize(){
		return warmerSize;
	}
	BurgerBun getBurgerBun(){
		return bb;
	}
	BurgerFryPan getBurgerFryPan(){
		return bfp;
	}
	BurgerPatty getBurgerPatty(){
		return bp;
	}
	Cola getCola(){
		return c;
	}
	ColaDispenser getColaDispenser(){
		return cd;
	}
	FoodWarmer getFoodWarmer(){
		return fw;
	}
	HotDog getHotDog(){
		return hd;
	}
	HotDogBun getHotDogBun(){
		return hdb;
	}
	HotDogGrillPan getHotDogGrillPan(){
		return hdgp;
	}
	Ketchup getKetchup(){
		return k;
	}
	LettuceLeaves getLettuceLeaves(){
		return ll;
	}
	Sausage getSausage(){
		return s;
	}
	Tomatos getTomatos(){
		return t;
	}
}
