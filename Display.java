package application;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.sun.javafx.geom.Line2D;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class Display {
	GameProcess gp;

	Stage levelStage;
	Stage menuStage;
	Stage startStage;
	Scene endOfLevelScene;
	Scene menuScene;
	Scene loginStage;
	Scene upgradeStage;

	boolean storageIsOpen;

	int moneyCount;

	Button selected;
	Button[] bPatty;
	Button bPattyPlate;
	Button bDump;
	Button bMakeOrder;
	Button bArrow;

	Button bBurgerBun;
	Button[] bPlateBurger;
	Button bCola;
	Button[] bClients;
	Button[] bFee;

	DropShadow shadow;
	BarTask barTask;
	ProgressBar bar;
	ProgressIndicator indicator;
	ProgressBar[] barTime;
	ClientBarTask[] cbr;

	ImageView imgViewColaStream;
	ImageView[] imgOrder;
	ImageView imgSmoke;
	ImageView[] imgPatty;
	ImageView imgPattyRed;
	ImageView imgPattyReady;
	ImageView imgPattyBurnt;
	ImageView imgColaDispencer;
	ImageView imgEmptyCola;
	ImageView imgHumTable;
	ImageView imgHdTable;
	ImageView[] imgBurgerFryPan;
	ImageView imgViewMoney;
	ImageView imgCount;
	ImageView imgTruck;
	ImageView star_1 = new ImageView(new Image("file:///C:/Demo/star_unact.png"));
	ImageView star_2 = new ImageView(new Image("file:///C:/Demo/star_unact.png"));
	ImageView star_3 = new ImageView(new Image("file:///C:/Demo/star_unact.png"));

	AnchorPane centralPane;
	VBox leftBar;
	VBox rightBar;
	GridPane[] order;
	GridPane storage;

	Label money;
	Label clientCount;
	Label pattyCount;
	Label hmBunCount;
	Label sausageCount;
	Label hdBunCount;
	BorderPane root;

	Label title;
	Button level;

	Scene beginStage;

	ClientThread[] clTh;

	Display() {
		levelStage=new Stage();
		levelStage.setTitle("Cooking Fever by Denis Botuk v1.0");
		shadow = new DropShadow();
		shadow.setColor(Color.YELLOW);
		setLevelStage();
		levelStage.setOnCloseRequest((t) -> System.exit(0));
	}

	public void setMenuStage() {

		menuStage = new Stage();
		BorderPane root = new BorderPane();

		title = new Label("SELECT A LEVEL");
		title.setId("title");
		root.setTop(title);
		title.setTranslateX(425);
		level = new Button();
		level.setId("level");
		root.setCenter(level);
		level.setTranslateX(-476);
		level.setTranslateY(-113);
		level.setText("1");
		Scene scene = new Scene(root, 1245, 700);
		scene.getStylesheets().add(getClass().getResource("menu.css").toExternalForm());
		menuStage.setScene(scene);
		menuStage.show();
		initMenuListeners();

	}

	private void initMenuListeners() {
		level.setOnMouseClicked((e) -> {
			setLevelStage();
			menuStage.hide();
		});
		level.addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> level.setEffect(shadow));
		level.addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> level.setEffect(null));
	}

	public void setBeginStage() {
		// beginStage = new Scene();
		startStage = new Stage();
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 1245, 700);
		ProgressBar barLoad = new ProgressBar();
		LoadingBar lbar = new LoadingBar(this);
		barLoad.setPrefWidth(500);
		barLoad.progressProperty().bind(lbar.progressProperty());

		Thread th = new Thread(lbar);
		th.start();

		barLoad.setTranslateX(0);
		barLoad.setTranslateY(310);

		root.setCenter(barLoad);
		startStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("beginStage.css").toExternalForm());
		startStage.show();
		setMenuStage();
		// setMenuStage();
		// startStage.hide();
	}

	public void setEndOfLevelStage() {

		BorderPane end = new BorderPane();
		end.setId("end");
		endOfLevelScene = new Scene(end, 1245, 700);
		end.setCenter(new Label("End of Game"));
		levelStage.setScene(endOfLevelScene);
		endOfLevelScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		levelStage.show();

	}

	// public void setLevel(Level level, Player player) {
	public void setLevelStage() {
		// initLevel(level, player);
		initLevel();

		centralPane = new AnchorPane();
		// Display dp = this;

		comingAnimation(0);
		comingAnimation(1);
		comingAnimation(2);
		levelStage = new Stage();
		levelStage.setTitle("Cooking Fever by Denis Botuk v1.0");
		root = new BorderPane();

		Scene scene = new Scene(root, 1245, 700);

		/* ************* LEFT BAR ************* */
		leftBar = new VBox();
		leftBar.setPadding(new Insets(5, 5, 5, 5));
		leftBar.setSpacing(5);
		leftBar.setId("leftBar");
		root.setLeft(leftBar);

		bar.setId("progressBar");
		bar.setPrefWidth(500);
		bar.progressProperty().bind(barTask.progressProperty());

		new Thread(barTask).start();
		bar.getTransforms().setAll(new Translate(25, 540), new Rotate(-90, 0, 0));
		root.setTop(bar);

		imgViewMoney.setTranslateX(0);
		imgViewMoney.setTranslateY(475);
		leftBar.getChildren().add(imgViewMoney);

		star_1.setTranslateX(-45);
		star_1.setTranslateY(180);
		centralPane.getChildren().add(star_1);
		star_2.setTranslateX(-45);
		star_2.setTranslateY(55);
		centralPane.getChildren().add(star_2);
		star_3.setTranslateX(-45);
		star_3.setTranslateY(-40);
		centralPane.getChildren().add(star_3);

		money.setId("money");
		money.setTranslateX(55);
		money.setTranslateY(415);
		leftBar.getChildren().add(money);

		/* ************* RIGHT BAR ************* */
		rightBar = new VBox();
		rightBar.setPadding(new Insets(15, 12, 15, 12));
		rightBar.setSpacing(10);
		rightBar.setId("rightBar");

		imgCount.setTranslateX(0);
		imgCount.setTranslateY(20);
		rightBar.getChildren().add(imgCount);

		clientCount.setId("clientCount");
		clientCount.setTranslateX(35);
		clientCount.setTranslateY(-45);
		rightBar.getChildren().add(clientCount);

		imgTruck.setTranslateX(35);
		imgTruck.setTranslateY(350);
		rightBar.getChildren().add(imgTruck);
		root.setRight(rightBar);

		/* ************* CENTRAL PANE ************* */

		imgSmoke.setTranslateX(718);
		imgSmoke.setTranslateY(440);

		bPattyPlate.setId("pattyPlate");
		bPattyPlate.setTranslateX(714);
		bPattyPlate.setTranslateY(520);
		centralPane.getChildren().add(bPattyPlate);

		bBurgerBun.setId("burgerBun");
		bBurgerBun.setTranslateX(321);
		bBurgerBun.setTranslateY(479);
		centralPane.getChildren().add(bBurgerBun);

		for (int i = 0; i < bPlateBurger.length; ++i) {
			bPlateBurger[i] = new Button();
			bPlateBurger[i].setId("plateBurger_" + (i + 1));
			bPlateBurger[i].setTranslateX(321 + i * 13);
			bPlateBurger[i].setTranslateY(375 - i * 60);
		}

		bCola.setId("cola");
		bCola.setTranslateX(164);
		bCola.setTranslateY(368);
		centralPane.getChildren().add(bCola);

		bDump.setId("dump");
		bDump.setTranslateX(7);
		bDump.setTranslateY(462);
		centralPane.getChildren().add(bDump);

		imgViewColaStream = new ImageView(new Image("file:///C:/Demo/colaStream.png"));
		imgViewColaStream.setTranslateX(176);
		imgViewColaStream.setTranslateY(365);

		for (int i = 0; i < bClients.length; ++i) {
			bFee[i].setId("feeWithTip");
			// bFee[i].setTranslateX(180 + 320 * i);
			// bFee[i].setTranslateY(202);
			bFee[i].setTranslateX(60 * i);
			bFee[i].setTranslateY(240 - i * 40);
		}

		storage = new GridPane();
		storage.setId("storage");
		storage.setPadding(new Insets(25, 25, 25, 25));
		storage.setTranslateX(1200);
		storage.setTranslateY(300);
		storage.add(new ImageView(new Image("file:///C:/Demo/pattyStorage.png")), 0, 0);
		pattyCount = new Label("" + gp.getPattyCount());
		pattyCount.setId("storageText");
		pattyCount.setStyle("-fx-text-fill: linear-gradient(orange, gold, orangered);");
		storage.add(pattyCount, 0, 1);

		storage.add(new ImageView(new Image("file:///C:/Demo/burgerbunsStorage.png")), 1, 0);
		hmBunCount = new Label("" + gp.getHumBunCount());
		hmBunCount.setId("storageText");
		hmBunCount.setStyle("-fx-text-fill: linear-gradient(orange, gold, orangered);");
		storage.add(hmBunCount, 1, 1);

		storage.add(new ImageView(new Image("file:///C:/Demo/sausagesStorage.png")), 0, 2);
		sausageCount = new Label("" + gp.getSausageCount());
		sausageCount.setId("storageText");
		sausageCount.setStyle("-fx-text-fill: linear-gradient(orange, gold, orangered);");
		storage.add(sausageCount, 0, 3);

		storage.add(new ImageView(new Image("file:///C:/Demo/hotdogbunsStorage.png")), 1, 2);
		hdBunCount = new Label("" + gp.getHdBunCount());
		hdBunCount.setId("storageText");
		hdBunCount.setStyle("-fx-text-fill:linear-gradient(orange, gold, orangered);");
		storage.add(hdBunCount, 1, 3);

		bMakeOrder.setId("makeOrder");
		storage.add(bMakeOrder, 3, 4);
		storage.toFront();

		GridPane.setHalignment(pattyCount, HPos.CENTER);
		GridPane.setHalignment(hmBunCount, HPos.CENTER);
		GridPane.setHalignment(sausageCount, HPos.CENTER);
		GridPane.setHalignment(hdBunCount, HPos.CENTER);

		centralPane.getChildren().add(storage);

		bArrow.setId("arrow");
		bArrow.setTranslateX(975);
		bArrow.setTranslateY(550);
		centralPane.getChildren().add(bArrow);

		root.setCenter(centralPane);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		levelStage.setScene(scene);
		levelStage.show();
		initListeners();
	}

	boolean isColaEmpty() {
		return !centralPane.getChildren().contains(bCola);
	}

	private void initListeners() {
		bPattyPlate.setOnMouseClicked((e) -> {
			if (gp.putPattyToFrypan()) {
				for (int i = 0; i < bPatty.length; ++i) {
					if (!centralPane.getChildren().contains(bPatty[i])
							&& !centralPane.getChildren().contains(imgPatty[i])) {
						imgPatty[i] = new ImageView(new Image("file:///C:/Demo/Patty1.png"));
						imgPatty[i].setId("patty");
						imgPatty[i].setTranslateX(721 - i * 13);
						imgPatty[i].setTranslateY(452 - i * 67);
						centralPane.getChildren().add(imgPatty[i]);
						pattyAnimation(i);
						centralPane.requestLayout();
						pattyCount.setText("" + gp.getPattyCount());

						break;
					}
				}
			}
		});

		bBurgerBun.setOnMouseClicked((e) -> {
			if (gp.putHamburgerToTable()) {
				for (int i = 0; i < bPlateBurger.length; ++i) {
					if (!centralPane.getChildren().contains(bPlateBurger[i])) {
						centralPane.getChildren().add(bPlateBurger[i]);
						centralPane.requestLayout();
						hmBunCount.setText("" + gp.getHumBunCount());
						break;
					}
				}
			}
			selected = new Button();
		});

		bCola.setOnMouseClicked((e) -> selected = bCola);

		bPatty[0].setOnMouseClicked((e) -> selected = bPatty[0]);
		bPatty[1].setOnMouseClicked((e) -> selected = bPatty[1]);
		bPatty[2].setOnMouseClicked((e) -> selected = bPatty[2]);

		bFee[0].setOnMouseClicked((e) -> {
			centralPane.getChildren().remove(bFee[0]);
			gp.takeMoney(0);
			moneyCount = getGameProcess().getMoney();
			selected = new Button();
			try {
				barTask.call();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			moneyCount = gp.getMoney();
			money.setText("" + moneyCount);
		});
		bFee[1].setOnMouseClicked((e) -> {
			centralPane.getChildren().remove(bFee[1]);
			gp.takeMoney(1);
			moneyCount = getGameProcess().getMoney();
			selected = new Button();
			try {
				barTask.call();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			moneyCount = gp.getMoney();
			money.setText("" + moneyCount);
		});
		bFee[2].setOnMouseClicked((e) -> {
			centralPane.getChildren().remove(bFee[2]);
			gp.takeMoney(2);
			moneyCount = getGameProcess().getMoney();
			selected = new Button();
			try {
				barTask.call();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			moneyCount = gp.getMoney();
			money.setText("" + moneyCount);
		});

		if (bPlateBurger[0] != null) {
			bPlateBurger[0].setOnMouseClicked((e) -> {
				for (int i = 0; i < bPatty.length; i++) {
					if (selected.equals(bPatty[i]) && !gp.getHamburger(0).havePatty()) {
						bPlateBurger[0].setStyle("-fx-background-image: url(\"file:///C:/Demo/BurgerWithPatty.png\");");
						centralPane.getChildren().remove(bPatty[i]);
						centralPane.getChildren().remove(imgSmoke);
						gp.putPattyToHamburger(i, 0);
						centralPane.requestLayout();
						selected = new Button();
					}
				}
				selected = bPlateBurger[0];

			});
		}
		if (bPlateBurger[1] != null) {
			bPlateBurger[1].setOnMouseClicked((e) -> {
				for (int i = 0; i < bPatty.length; i++) {
					if (selected.equals(bPatty[i]) && !gp.getHamburger(1).havePatty()) {
						bPlateBurger[1].setStyle("-fx-background-image: url(\"file:///C:/Demo/BurgerWithPatty.png\");");
						centralPane.getChildren().remove(bPatty[i]);
						centralPane.getChildren().remove(imgSmoke);
						gp.putPattyToHamburger(i, 1);
						centralPane.requestLayout();
						selected = new Button();
					}
				}
				selected = bPlateBurger[1];
			});
		}
		if (bPlateBurger[2] != null) {
			bPlateBurger[2].setOnMouseClicked((e) -> {
				for (int i = 0; i < bPatty.length; i++) {
					if (selected.equals(bPatty[i]) && !gp.getHamburger(1).havePatty()) {
						bPlateBurger[1].setStyle("-fx-background-image: url(\"file:///C:/Demo/BurgerWithPatty.png\");");
						centralPane.getChildren().remove(bPatty[1]);
						centralPane.getChildren().remove(imgSmoke);
						gp.putPattyToHamburger(i, 1);
						centralPane.requestLayout();
						selected = new Button();
					}
				}
				selected = bPlateBurger[2];
			});
		}
		if (bClients[0] != null) {
			bClients[0].setOnMouseClicked((e) -> {
				if (selected.equals(bCola) && gp.getClient(0).getCola()) {
					centralPane.getChildren().remove(bCola);
					colaAnimation();
					centralPane.requestLayout();
					selected = new Button();
				}
				if (selected.equals(bPlateBurger[0]) && gp.giveHamburger(0, 0)) {
					centralPane.getChildren().remove(bPlateBurger[0]);
					bPlateBurger[0].setStyle("-fx-background-image: url(\"file:///C:/Demo/plateBurger.png\");");
					centralPane.requestLayout();
					selected = new Button();
				}
				if (selected.equals(bPlateBurger[1]) && gp.giveHamburger(1, 0)) {
					centralPane.getChildren().remove(bPlateBurger[1]);
					bPlateBurger[1].setStyle("-fx-background-image: url(\"file:///C:/Demo/plateBurger.png\");");
					centralPane.requestLayout();
					selected = new Button();
				}
				if (selected.equals(bPlateBurger[2]) && gp.giveHamburger(2, 0)) {
					centralPane.getChildren().remove(bPlateBurger[2]);
					bPlateBurger[2].setStyle("-fx-background-image: url(\"file:///C:/Demo/plateBurger.png\");");
					centralPane.requestLayout();
					selected = new Button();
				}
				if (gp.checkReady(0)) {
					centralPane.getChildren().add(bFee[0]);
					centralPane.getChildren().remove(bClients[0]);
					centralPane.getChildren().remove(order[0]);
					clientCount.setText("" + gp.getClienNum());
					clTh[0].ready();
					comingAnimation(0);
					if (gp.endOfGame()) {
						setEndOfLevelStage();
					}

				}

			});
		}
		if (bClients[1] != null) {
			bClients[1].setOnMouseClicked((e) -> {
				if (selected.equals(bCola) && gp.getClient(1).getCola()) {
					centralPane.getChildren().remove(bCola);
					colaAnimation();
					centralPane.requestLayout();
					selected = new Button();
				}
				if (selected.equals(bPlateBurger[0]) && gp.giveHamburger(0, 1)) {
					centralPane.getChildren().remove(bPlateBurger[0]);
					bPlateBurger[0].setStyle("-fx-background-image: url(\"file:///C:/Demo/plateBurger.png\");");
					centralPane.requestLayout();
					selected = new Button();
				}
				if (selected.equals(bPlateBurger[1]) && gp.giveHamburger(1, 1)) {
					centralPane.getChildren().remove(bPlateBurger[1]);
					bPlateBurger[1].setStyle("-fx-background-image: url(\"file:///C:/Demo/plateBurger.png\");");
					centralPane.requestLayout();
					selected = new Button();
				}
				if (selected.equals(bPlateBurger[2]) && gp.giveHamburger(2, 1)) {
					centralPane.getChildren().remove(bPlateBurger[2]);
					bPlateBurger[2].setStyle("-fx-background-image: url(\"file:///C:/Demo/plateBurger.png\");");
					centralPane.requestLayout();
					selected = new Button();
				}
				if (gp.checkReady(1)) {
					centralPane.getChildren().add(bFee[1]);
					centralPane.getChildren().remove(bClients[1]);
					centralPane.getChildren().remove(order[1]);
					clientCount.setText("" + gp.getClienNum());
					clTh[1].ready();
					comingAnimation(1);
					if (gp.endOfGame()) {
						setEndOfLevelStage();
					}
				}
			});
		}
		if (bClients[2] != null) {
			bClients[2].setOnMouseClicked((e) -> {
				if (selected.equals(bCola) && gp.getClient(2).getCola()) {
					centralPane.getChildren().remove(bCola);
					colaAnimation();
					centralPane.requestLayout();
					selected = new Button();
				}
				if (selected.equals(bPlateBurger[0]) && gp.giveHamburger(0, 2)) {
					centralPane.getChildren().remove(bPlateBurger[0]);
					bPlateBurger[0].setStyle("-fx-background-image: url(\"file:///C:/Demo/plateBurger.png\");");
					centralPane.requestLayout();
					selected = new Button();
				}
				if (selected.equals(bPlateBurger[1]) && gp.giveHamburger(1, 2)) {
					centralPane.getChildren().remove(bPlateBurger[1]);
					bPlateBurger[1].setStyle("-fx-background-image: url(\"file:///C:/Demo/plateBurger.png\");");
					centralPane.requestLayout();
					selected = new Button();
				}
				if (selected.equals(bPlateBurger[2]) && gp.giveHamburger(2, 2)) {
					centralPane.getChildren().remove(bPlateBurger[2]);
					bPlateBurger[2].setStyle("-fx-background-image: url(\"file:///C:/Demo/plateBurger.png\");");
					centralPane.requestLayout();
					selected = new Button();
				}
				if (gp.checkReady(2)) {
					centralPane.getChildren().add(bFee[2]);
					centralPane.getChildren().remove(bClients[2]);
					centralPane.getChildren().remove(order[2]);
					clientCount.setText("" + gp.getClienNum());
					clTh[2].ready();
					comingAnimation(2);
					if (gp.endOfGame()) {
						setEndOfLevelStage();
					}
				}
			});
		}
		bDump.setOnMouseClicked((e) -> {
			if (selected.equals(bCola)) {
				centralPane.getChildren().remove(bCola);
				colaAnimation();
				centralPane.requestLayout();
			}
			if (selected.equals(bPlateBurger[0])) {
				centralPane.getChildren().remove(bPlateBurger[0]);
				gp.deleteBurger(0);
				centralPane.requestLayout();
			}
			if (selected.equals(bPlateBurger[1])) {
				centralPane.getChildren().remove(bPlateBurger[1]);
				gp.deleteBurger(1);
				centralPane.requestLayout();
			}
			if (selected.equals(bPlateBurger[2])) {
				centralPane.getChildren().remove(bPlateBurger[2]);
				gp.deleteBurger(2);
				centralPane.requestLayout();
			}
			if (selected.equals(bPatty[0])) {
				centralPane.getChildren().remove(bPatty[0]);
				gp.deletePatty(0);
				if (centralPane.getChildren().contains(imgSmoke)) {
					centralPane.getChildren().remove(imgSmoke);
				}
				centralPane.requestLayout();

			}
			if (selected.equals(bPatty[1])) {
				centralPane.getChildren().remove(bPatty[1]);
				gp.deletePatty(1);
				if (centralPane.getChildren().contains(imgSmoke)) {
					centralPane.getChildren().remove(imgSmoke);
				}
				centralPane.requestLayout();

			}
			if (selected.equals(bPatty[2])) {
				centralPane.getChildren().remove(bPatty[2]);
				gp.deletePatty(2);
				if (centralPane.getChildren().contains(imgSmoke)) {
					centralPane.getChildren().remove(imgSmoke);
				}
				centralPane.requestLayout();

			}
			selected = new Button();
		});
		bArrow.setOnMouseClicked((e) -> {
			storageAnimation();
			if (storageIsOpen) {
				bPattyPlate.setDisable(false);
				bCola.setDisable(false);
				bPatty[0].setDisable(false);
				bPatty[1].setDisable(false);
				bPatty[2].setDisable(false);
				bBurgerBun.setDisable(false);
				bPlateBurger[0].setDisable(false);
				bPlateBurger[1].setDisable(false);
				bPlateBurger[2].setDisable(false);
				bClients[0].setDisable(false);
				bClients[1].setDisable(false);
				bClients[2].setDisable(false);
				bDump.setDisable(false);

				bMakeOrder.setDisable(true);

				storageIsOpen = false;
			} else {
				bPattyPlate.setDisable(true);
				bCola.setDisable(true);
				bPatty[0].setDisable(true);
				bPatty[1].setDisable(true);
				bPatty[2].setDisable(true);
				bBurgerBun.setDisable(true);
				bPlateBurger[0].setDisable(true);
				bPlateBurger[1].setDisable(true);
				bPlateBurger[2].setDisable(true);
				bClients[0].setDisable(true);
				bClients[1].setDisable(true);
				bClients[2].setDisable(true);
				bDump.setDisable(true);

				bMakeOrder.setDisable(false);
				storageIsOpen = true;
			}
		});
		bMakeOrder.setOnMouseClicked((e) -> {
			makeOrderAnimation();
		});

		/* ************* DROP SHADOW EFFECT ************* */
		bPattyPlate.addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> bPattyPlate.setEffect(shadow));
		bPattyPlate.addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> bPattyPlate.setEffect(null));

		bCola.addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> bCola.setEffect(shadow));
		bCola.addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> bCola.setEffect(null));

		bPatty[0].addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> bPatty[0].setEffect(shadow));
		bPatty[0].addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> bPatty[0].setEffect(null));

		bPatty[1].addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> bPatty[1].setEffect(shadow));
		bPatty[1].addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> bPatty[1].setEffect(null));

		bPatty[2].addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> bPatty[2].setEffect(shadow));
		bPatty[2].addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> bPatty[2].setEffect(null));

		bBurgerBun.addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> bBurgerBun.setEffect(shadow));
		bBurgerBun.addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> bBurgerBun.setEffect(null));

		if (bPlateBurger[0] != null) {
			bPlateBurger[0].addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> bPlateBurger[0].setEffect(shadow));
			bPlateBurger[0].addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> bPlateBurger[0].setEffect(null));
		}

		if (bPlateBurger[1] != null) {
			bPlateBurger[1].addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> bPlateBurger[1].setEffect(shadow));
			bPlateBurger[1].addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> bPlateBurger[1].setEffect(null));
		}
		if (bPlateBurger[2] != null) {
			bPlateBurger[2].addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> bPlateBurger[2].setEffect(shadow));
			bPlateBurger[2].addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> bPlateBurger[2].setEffect(null));
		}
		if (bClients[0] != null) {
			bClients[0].addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> bClients[0].setEffect(shadow));
			bClients[0].addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> bClients[0].setEffect(null));
		}
		if (bClients[1] != null) {
			bClients[1].addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> bClients[1].setEffect(shadow));
			bClients[1].addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> bClients[1].setEffect(null));
		}
		if (bClients[2] != null) {
			bClients[2].addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> bClients[2].setEffect(shadow));
			bClients[2].addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> bClients[2].setEffect(null));
		}
		bDump.addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> bDump.setEffect(shadow));
		bDump.addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> bDump.setEffect(null));

		bFee[0].addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> bFee[0].setEffect(shadow));
		bFee[0].addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> bFee[0].setEffect(null));

		bFee[1].addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> bFee[1].setEffect(shadow));
		bFee[1].addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> bFee[1].setEffect(null));

		bFee[2].addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> bFee[2].setEffect(shadow));
		bFee[2].addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> bFee[2].setEffect(null));

		bArrow.addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> bArrow.setEffect(shadow));
		bArrow.addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> bArrow.setEffect(null));

		bMakeOrder.addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> bMakeOrder.setEffect(shadow));
		bMakeOrder.addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> bMakeOrder.setEffect(null));
	}

	void colaAnimation() {
		centralPane.getChildren().add(imgViewColaStream);
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.seconds(0.25), evt -> imgViewColaStream.setVisible(false)),
				new KeyFrame(Duration.seconds(0.5), evt -> imgViewColaStream.setVisible(true)));
		timeline.setCycleCount(20);
		timeline.setOnFinished(evt -> {
			centralPane.getChildren().remove(imgViewColaStream);
			centralPane.getChildren().add(bCola);
			imgViewColaStream.setVisible(false);
		});
		timeline.play();
	}

	void pattyAnimation(int index) {
		bPatty[index].setId("readyPatty");
		bPatty[index].setTranslateX(723);
		bPatty[index].setTranslateY(452);
		Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(1)));
		timeline1.setCycleCount(10);
		Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(0.05), evt -> {
			centralPane.getChildren().remove(imgPatty[index]);
			centralPane.getChildren().add(bPatty[index]);
			smokeAnimation();
			storage.toFront();
			bArrow.toFront();
		}));
		Timeline timeline3 = new Timeline(new KeyFrame(Duration.seconds(0.5), evt -> {
			bPatty[index].setStyle("-fx-background-image: url(\"file:///C:/Demo/red_patty.png\");");
		}), new KeyFrame(Duration.seconds(0.4), evt -> {
			bPatty[index].setStyle("-fx-background-image: url(\"file:///C:/Demo/brown_patty.png\");");
		}));
		timeline3.setCycleCount(20);
		Timeline timeline4 = new Timeline(new KeyFrame(Duration.seconds(1), evt -> {
			bPatty[index].setStyle("-fx-background-image: url(\"file:///C:/Demo/patty_burnt.png\");");
			centralPane.getChildren().remove(imgSmoke);
		}));
		timeline4.setCycleCount(Animation.INDEFINITE);
		timeline1.play();
		timeline1.setOnFinished(e -> timeline2.play());
		timeline2.setOnFinished(e -> timeline3.play());
		timeline3.setOnFinished(e -> timeline4.play());

	}

	void smokeAnimation() {
		centralPane.getChildren().add(imgSmoke);
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.7), evt -> {
			FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.7), imgSmoke);
			fadeTransition.setFromValue(0.7);
			fadeTransition.setToValue(0.0);
			fadeTransition.setCycleCount(Animation.INDEFINITE);
			fadeTransition.play();
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		timeline.setOnFinished(e -> centralPane.getChildren().remove(imgSmoke));
	}

	void storageAnimation() {
		if (storageIsOpen) {
			Line line = new Line();
			line.setStartX(800);
			line.setStartY(450);
			line.setEndX(1350);
			line.setEndY(450);
			new PathTransition(new Duration(500.), line, storage).play();
			centralPane.getChildren().remove(bArrow);
			centralPane.getChildren().add(bArrow);
			RotateTransition rt = new RotateTransition(Duration.millis(500), bArrow);
			rt.setByAngle(-180);
			rt.setAutoReverse(true);
			rt.play();
		} else {
			Line line = new Line();
			line.setStartX(1350);
			line.setStartY(450);
			line.setEndX(800);
			line.setEndY(450);
			new PathTransition(new Duration(500.), line, storage).play();
			RotateTransition rt = new RotateTransition(Duration.millis(500), bArrow);
			rt.setByAngle(180);
			rt.setAutoReverse(true);
			rt.play();
			storage.toFront();
			bArrow.toFront();

		}
	}

	void comingAnimation(int x) {
		if (gp.comingClient(x)) {
			int time = gp.getClient(x).getOrder().getTime();
			bClients[x].setId("client");
			bClients[x].setStyle("-fx-background-image: url(\"file:///C:/Demo/client_1.png\");");
			bClients[x].setTranslateX(150 + 320 * (x));
			bClients[x].setTranslateY(30);
			order[x] = new GridPane();
			order[x].setId("order");
			order[x].setTranslateX(320 * (x));
			order[x].setTranslateY(-65);

			for (int j = 0; j < gp.getClient(x).getOrder().getImages().length; ++j) {
				order[x].add(gp.getClient(x).getOrder().getImages()[j], 0, j);
			}

			//barTime[x] = new ProgressBar();
			//barTime[x].setId("clientPB");
			//cbr[x] = new ClientBarTask(this, gp.getClient(x), x);
			//barTime[x].progressProperty().bind(cbr[x].progressProperty());
			//new Thread(cbr[x]).start();
			//order[x].add(barTime[x], 0, 4);
			clTh[x] = new ClientThread(this, x, time);
			new Thread(clTh[x]).start();
		}

	}

	void addClient(int clientId) {
		centralPane.getChildren().add(bClients[clientId]);
		centralPane.getChildren().add(order[clientId]);
	}

	void initLevel(/* Level level, Player player */) {
		// gp = new GameProcess(level, player);
		gp = new GameProcess(null, null);

		moneyCount = 0;

		selected = new Button();
		bPatty = new Button[3];
		for (int i = 0; i < bPatty.length; ++i)
			bPatty[i] = new Button();
		bPattyPlate = new Button();
		bDump = new Button();
		bBurgerBun = new Button();
		bPlateBurger = new Button[3];
		bCola = new Button();
		bClients = new Button[3];
		bMakeOrder = new Button("Make an order");
		bArrow = new Button();
		for (int i = 0; i < bClients.length; ++i)
			bClients[i] = new Button();

		imgViewColaStream = new ImageView(new Image("file:///C:/Demo/colaStream.png"));
		// imgOrder = new ImageView[3];
		imgSmoke = new ImageView(new Image("file:///C:/Demo/smoke.png"));
		imgPatty = new ImageView[3];
		for (int i = 0; i < imgPatty.length; ++i)
			imgPatty[i] = new ImageView(new Image("file:///C:/Demo/Patty1.png"));
		// imgHumTable = new ImageView(new Image(""));
		// imgHdTable = new ImageView(new Image(""));
		// imgBurgerFryPan = new ImageView[3];
		imgViewMoney = new ImageView(new Image("file:///C:/Demo/money.png"));
		imgCount = new ImageView(new Image("file:///C:/Demo/clientCount.png"));
		imgTruck = new ImageView(new Image("file:///C:/Demo/truck.png"));
		star_1 = new ImageView(new Image("file:///C:/Demo/star_unact.png"));
		star_2 = new ImageView(new Image("file:///C:/Demo/star_unact.png"));
		star_3 = new ImageView(new Image("file:///C:/Demo/star_unact.png"));
		bFee = new Button[gp.getMaxClient()];
		for (int i = 0; i < bFee.length; ++i)
			bFee[i] = new Button();

		money = new Label("" + moneyCount);
		clientCount = new Label("" + gp.getClienNum());

		order = new GridPane[3];

		barTime = new ProgressBar[3];
		barTask = new BarTask(this);
		bar = new ProgressBar();
		Display dp = this;
		storageIsOpen = false;
		cbr = new ClientBarTask[3];
		clTh = new ClientThread[3];

	}

	void makeOrderAnimation() {
		new Thread() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Platform.runLater(() -> {
					gp.makeOrder();
					pattyCount.setText("" + gp.getPattyCount());
					hmBunCount.setText("" + gp.getHumBunCount());
					sausageCount.setText("" + gp.getSausageCount());
					hdBunCount.setText("" + gp.getHdBunCount());

				});
			}
		}.start();

	}

	GameProcess getGameProcess() {
		return gp;
	}

	int getMoneyCount() {
		return moneyCount;
	}

	void updateStar(int state) {
		switch (state) {
		case 1:
			star_1.setImage(new Image("file:///C:/Demo/star_act.png"));
			break;
		case 2:
			star_2.setImage(new Image("file:///C:/Demo/star_act.png"));
			break;
		case 3:
			star_3.setImage(new Image("file:///C:/Demo/star_act.png"));
			break;

		}
	}

	void goAwayClient(int clientId) {
		gp.goAwayClient(clientId);
		centralPane.getChildren().remove(bClients[clientId]);
		centralPane.getChildren().remove(order[clientId]);
		clientCount.setText("" + gp.getClienNum());

		comingAnimation(clientId);
		if (gp.endOfGame()) {
			setEndOfLevelStage();
		}

	}

}
