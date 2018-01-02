package application;

public class Hamburger {
	boolean bPatty;
	boolean bTomato;
	boolean bLettuce;
	BurgerPatty patty;
	Tomatos tomato;
	LettuceLeaves lettuce;
	BurgerBun bun;

	Hamburger() {
		bPatty = false;
		bTomato = false;
		bLettuce = false;
	}

	void addPatty(BurgerPatty patty) {
		this.patty = patty;
		bPatty = true;
	}
	boolean havePatty(){
		return bPatty;
	}
	boolean haveLettuce(){
		return bLettuce;
	}
	boolean haveTomato(){
		return bTomato;
	}

	void addTomato(Tomatos tomato) {
		this.tomato = tomato;
		bTomato = true;
	}

	void addLettuce(LettuceLeaves lettuce) {
		this.lettuce = lettuce;
		bLettuce = true;
	}

}
