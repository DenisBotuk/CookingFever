package application;

import java.util.concurrent.TimeUnit;

import javafx.concurrent.Task;

public class LoadingBar extends Task<Integer>{
	
	Display dp;
	
	LoadingBar(Display dp){
		this.dp = dp;
	}
	@Override
	protected Integer call() throws Exception {
		int i;
		int max = 50000000;
		for (i = 0; i <= max; i++) {
			updateProgress(i, max);
		}
		if(this.isDone()){
			dp.setMenuStage();
			dp.startStage.hide();
		}
		return i;
	}
}
