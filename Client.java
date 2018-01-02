package application;

public class Client {

	String imageID;
	Order order;
	int size;

	Client(Player player) {
		generateImage();
		order = new Order(player);
	}

	void generateImage() {
		int img = (int) (Math.random() * 5) + 1;
		switch (img) {
		case 1: {
			imageID = "client_1";
			break;
		}
		case 2: {
			imageID = "client_2";
			break;
		}
		case 3: {
			imageID = "client_3";
			break;
		}
		case 4: {
			imageID = "client_4";
			break;
		}
		case 5: {
			imageID = "client_5";
			break;
		}
		}
	}

	boolean isReady() {
		return order.getSize() == size;
	}

	boolean getBurger(Hamburger hum) {
		if (!order.acceptHamburger()) {
			return false;
		} else if (!hum.havePatty()) {
			return false;
		} else if (order.getLettuce() != hum.haveLettuce()) {
			return false;
		} else if (order.getTomato() != hum.haveTomato()) {
			return false;
		} else {
			++size;
			return true;
		}
	}
	boolean getHotDog(HotDog hd) {
		if (!order.acceptHotDog()) {
			return false;
		} else if (!hd.haveSausage()) {
			return false;
		} else if (order.getKetchup() != hd.haveKetchup()) {
			return false;
		} else {
			++size;
			return true;
		}
	}
	boolean getCola() {
		if (!order.acceptCola()) {
			return false;
		} else {
			++size;
			return true;
		}
	}
	Order getOrder(){
		return order;
	}
}
