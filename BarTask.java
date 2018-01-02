package application;

import javafx.concurrent.Task;

public class BarTask extends Task<Integer> {

	Display dp;
	
	BarTask(Display dp){
		this.dp = dp;
	}
	
	@Override
	public Integer call() throws Exception {
		int max = 200;
		updateProgress(dp.moneyCount, max);
		if((dp.moneyCount * 100. / max) >= 50){
			dp.updateStar(1);
		} 
		if((dp.moneyCount * 100. / max) >= 75){
			dp.updateStar(2);
		} 
		if((dp.moneyCount * 100. / max) >= 95){
			dp.updateStar(3);
		}
		return dp.moneyCount;
	}

}
