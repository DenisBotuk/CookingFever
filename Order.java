package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Order {
	boolean hamburger;
	boolean hotdog;
	boolean cola;
	boolean tomato;
	boolean lettuce;
	boolean ketchup;

	boolean acceptedHamburger;
	boolean accepedHotdog;
	boolean acceptedCola;
	boolean acceptedTomato;
	boolean acceptedLettuce;
	boolean acceptedKetchup;

	ImageView[] images;

	int price;
	int size;
	int time;

	Player player;

	Order(Player player) {
		this.player = player;
		// initAccept();
		acceptedHamburger = true;
		acceptedCola = true;
		generateOrder();
		setImages();
		makePrice();
		setTime();
	}
	
	void setTime(){
		time = size * 20;
	//	time = 5;
	}
	int getTime(){
		return time;
	}

	void initAccept() {
		acceptedHamburger = (player.getBurgerBun().getLevel() == 0) ? false : true;
		accepedHotdog = (player.getHotDogBun().getLevel() == 0) ? false : true;
		acceptedCola = (player.getCola().getLevel() == 0) ? false : true;
		acceptedTomato = (player.getTomatos().getLevel() == 0) ? false : true;
		acceptedLettuce = (player.getLettuceLeaves().getLevel() == 0) ? false : true;
		acceptedKetchup = (player.getKetchup().getLevel() == 0) ? false : true;

	}

	void generateOrder() {
		while (size == 0) {
			if (acceptedHamburger) {
				if (Math.random() > 0.5) {
					hamburger = true;
					++size;
					if (acceptedTomato)
						tomato = (Math.random() > 0.5) ? true : false;
					if (acceptedLettuce)
						lettuce = (Math.random() > 0.5) ? true : false;
				}
			} else {
				hamburger = false;
			}
			if (accepedHotdog) {
				if (Math.random() > 0.5) {
					hotdog = true;
					++size;
					if (acceptedKetchup)
						ketchup = (Math.random() > 0.5) ? true : false;
				}
			} else {
				hotdog = false;
			}
			if (acceptedCola)
				cola = (Math.random() > 0.5) ? true : false;
			size += (cola) ? 1 : 0;
		}
	}

	void setImages() {
		images = new ImageView[size];
		int i = 0;
		if (hamburger) {
			if (tomato && lettuce) {
				images[i] = new ImageView(new Image(""));
			} else if (tomato) {
				images[i] = new ImageView(new Image(""));
			} else if (lettuce) {
				images[i] = new ImageView(new Image(""));
			} else {
				images[i] = new ImageView(new Image("file:///C:/Demo/BurgerWithPatty_order_1.png"));
			}
			++i;
		}
		if (hotdog) {
			if (ketchup) {
				images[i] = new ImageView(new Image(""));
			} else {
				images[i] = new ImageView(new Image(""));
			}
			++i;
		}
		if (cola) {
			images[i] = new ImageView(new Image("file:///C:/Demo/Cola_order_1.png"));
			++i;
		}
	}

	void makePrice() {
		price += getHamburger() ? 25 : 0;
		price += getHotDog() ? 20 : 0;
		price += getCola() ? 10 : 0;
		price += getTomato() ? 2 : 0;
		price += getLettuce() ? 3 : 0;
		price += getKetchup() ? 3 : 0;
	}

	ImageView[] getImages() {
		return images;
	}

	int getSize() {
		return size;
	}

	int getPrice() {
		return price;
	}

	boolean getHamburger() {
		return hamburger;
	}

	boolean getHotDog() {
		return hotdog;
	}

	boolean getCola() {
		return cola;
	}

	boolean getTomato() {
		return tomato;
	}

	boolean getLettuce() {
		return lettuce;
	}

	boolean getKetchup() {
		return ketchup;
	}
	
	boolean acceptHamburger(){
		if(hamburger){
			hamburger = false;
			return true;
		} else return false;
	}
	boolean acceptHotDog(){
		if(hotdog){
			hotdog = false;
			return true;
		} else return false;
	}
	boolean acceptCola(){
		if(cola){
			cola = false;
			return true;
		} else return false;
	}
}
