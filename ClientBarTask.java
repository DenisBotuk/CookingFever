package application;

import java.util.concurrent.TimeUnit;

import javafx.concurrent.Task;

public class ClientBarTask extends Task<Integer> {

	Display dp;
	Client cl;
	int clientIndex;
	int iter;
	
	ClientBarTask(Display dp, Client cl, int clientIndex){
		this.dp = dp;
		this.cl = cl;
		this.clientIndex = clientIndex;
	}
	
	@Override
	public Integer call() throws Exception {
		int max = cl.getOrder().getTime(); 
		for(iter = max; iter >= 0; -- iter){
			updateProgress(iter, max);
			TimeUnit.SECONDS.sleep(1);
			
		}
		
			dp.goAwayClient(clientIndex);
	
		return iter;
	}

}
