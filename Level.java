package application;

public class Level {
	int clientNum;
	int money;
	int level;
	Player player;

	
	Level(int level){
		this.level= level;
		init();
	}
	
	void init(){
		clientNum = 5;
		money = 100;
	}
	int getLevel(){
		return level;
	}
	int getMoney(){
		return money;
	}
	int getClientNum(){
		return clientNum;
	}

	
}
