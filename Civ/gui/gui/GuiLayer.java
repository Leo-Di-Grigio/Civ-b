package gui;

public class GuiLayer implements Comparable<GuiLayer> {
	
	public int value = 0;
	
	public GuiLayer(int layer) {
		this.value = layer;
	}

	@Override
	public int compareTo(GuiLayer arg) {
		if(value < arg.value){
			return -1;
		}
		else{
			if(value > arg.value){
				return 1;
			}
			else{
				return 0;
			}
		}
	}
}
