package application;

public abstract class Tool {
	int cost;
	String imagePath;
	int level;
	boolean isUsed;
	Product product;
	Tool(int level){
		this.level = level;
	}
}
