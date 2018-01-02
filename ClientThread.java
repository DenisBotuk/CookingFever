package application;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;

public class ClientThread implements Runnable {
	Display dp;
	int clientId;
	int time;
	boolean ready;

	ClientThread(Display dp, int clientId, int time) {
		this.dp = dp;
		this.clientId = clientId;
		this.time = time;
		ready = false;
	}

	@Override
	public void run() {
		try {
			int time = ((int) (Math.random() * 10) + 5);
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Platform.runLater(() -> {
			dp.addClient(clientId);
		});
		int maxTime = dp.getGameProcess().getClient(clientId).getOrder().getTime();
		for (int i = 0; i < maxTime; ++i) {
			if(ready){
				break;
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			time--;
			
		}
		if(!ready){
			Platform.runLater(() -> {
				dp.goAwayClient(clientId);
			});
		}
		

	}
	void ready(){
		ready = true;
	}

}
