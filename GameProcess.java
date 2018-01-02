package application;

public class GameProcess {
	Level level;
	Player player;

	Hamburger[] humTable;
	// HotDog[] hdTable;
	BurgerPatty[] humFryPan;
	// HotDogGrillPan[] hdGrillPan;
	Client[] clients;

	int pattyCount;
	int hmBunCount;
	int sausageCount;
	int hdBunCount;

	int maxClient;
	int clientNum;
	int clientNumAct;
	int maxMoney;
	int money;
	boolean pattyBurnt;

	int[] fee;

	GameProcess(Level level, Player player) {
		this.level = level;
		this.player = new Player("Denis");
		money = 0;
		init();
	}

	void init() {
		/*
		 * humTable = new Hamburger[player.humTableSize]; humFryPan = new
		 * BurgerPatty[player.humFrySize]; clientNum = level.getClientNum();
		 * maxMoney = level.getMoney(); clients = new Client[clientNum];
		 */
		humTable = new Hamburger[1];
		humFryPan = new BurgerPatty[1];
		maxClient = 10;
		clientNum = maxClient;
		clientNumAct = maxClient;
		maxMoney = 200;
		fee = new int[3];
		clients = new Client[3];
		hmBunCount = (player.getBurgerBun() != null) ? 5 : 0;
		pattyCount = (player.getBurgerPatty() != null) ? 5 : 0;
		sausageCount = (player.getSausage() != null) ? 5 : 0;
		hdBunCount = (player.getHotDogBun() != null) ? 5 : 0;
		// for(int i = 0; i < clients.length; ++i) clients[i] = new
		// Client(null);
	}

	void makeOrder(){
		hmBunCount += (player.getBurgerBun() != null) ? 5 : 0;
		pattyCount += (player.getBurgerPatty() != null) ? 5 : 0;
		sausageCount += (player.getSausage() != null) ? 5 : 0;
		hdBunCount += (player.getHotDogBun() != null) ? 5 : 0;
	}
	
	int getMaxClient() {
		return maxClient;
	}

	boolean endOfGame() {
		return clientNum == 0;
	}

	boolean putHamburgerToTable() {
		for (int i = 0; i < humTable.length; ++i) {
			if (hmBunCount > 0 && humTable[i] == null) {
				humTable[i] = new Hamburger();
				--hmBunCount;
				return true;
			}
		}
		return false;
	}

	boolean putPattyToFrypan() {
		for (int i = 0; i < humFryPan.length; ++i) {
			if (pattyCount > 0 && humFryPan[i] == null) {
				humFryPan[i] = new BurgerPatty();
				--pattyCount;
				return true;
			}
		}
		return false;
	}

	Hamburger getHamburger(int index) {
		return humTable[index];
	}

	void putPattyToHamburger(int indexPatty, int indexHum) {
		humTable[indexHum].addPatty(humFryPan[indexPatty]);
		humFryPan[indexPatty] = null;
	}

	int getClienNum() {
		return clientNum;
	}

	boolean checkReady(int clientId) {
		if (clients[clientId].isReady()) {
			fee[clientId] = clients[clientId].getOrder().getPrice();
			clients[clientId] = null;
			--clientNum;
			return true;
		} else {
			return false;
		}

	}

	void goAwayClient(int clientId) {
		clients[clientId] = null;
		--clientNum;
	}

	boolean giveHamburger(int humId, int clientId) {
		if (clients[clientId].getBurger(humTable[humId])) {
			humTable[humId] = null;
			return true;
		} else
			return false;
	}

	boolean giveHotDog(HotDog hd, int clientId) {
		return clients[clientId].getHotDog(hd);
	}

	boolean giveCola(int clientId) {
		return clients[clientId].getCola();
	}

	void burnPatty() {
		pattyBurnt = true;
	}

	boolean isPattyBurnt() {
		return pattyBurnt;
	}

	Client getClient(int index) {
		return clients[index];
	}

	void deletePatty(int index) {
		humFryPan[index] = null;
	}

	void deleteBurger(int index) {
		humTable[index] = null;
	}

	void takeMoney(int index) {
		money += fee[index];
		fee[index] = 0;
	}

	int getMoney() {
		return money;
	}

	boolean comingClient(int index) {
		if (clients[index] == null && clientNumAct > 0) {
			clients[index] = new Client(player);
			--clientNumAct;
			return true;
		} else {
			return false;
		}
	}
	int getPattyCount(){
		return pattyCount;
	}
	int getHumBunCount(){
		return hmBunCount;
	}
	int getSausageCount(){
		return sausageCount;
	}
	int getHdBunCount(){
		return hdBunCount;
	}

}
