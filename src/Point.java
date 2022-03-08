import java.util.ArrayList;
import java.util.Random;

public class Point {
	private ArrayList<Point> neighbors;
	private int currentState;
	private int nextState;
	private int numStates = 6;
	private boolean rain;

	public Point(boolean rain) {
		currentState = 0;
		nextState = 0;
		neighbors = new ArrayList<Point>();
		this.rain = rain;
	}

	public void clicked() {
		currentState=(++currentState)%numStates;	
	}
	
	public int getState() {
		return currentState;
	}

	public void setState(int s) {
		currentState = s;
	}

	public void calculateNewState() {
		//TODO: insert logic which updates according to currentState and 
		//number of active neighbors
		if(!rain) {
			if (currentState == 0) {
				if (aliveNeighbors() == 3) {
					nextState = 1;
				} else {
					nextState = 0;
				}
			} else {
				if (aliveNeighbors() == 2 || aliveNeighbors() == 3) {
					nextState = 1;
				} else {
					nextState = 0;
				}
			}
		}else{
			if(currentState>0){
				nextState=currentState-1;
			}else if(currentState==0 && neighbors.size()>0 && neighbors.get(0).getState()>0){
				nextState=6;
			}else{
				nextState=0;
			}
		}
	}


	public void changeState() {
		currentState = nextState;
	}
	
	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}
	
	//TODO: write method counting all active neighbors of THIS point
	public int aliveNeighbors(){
		int result = 0;
		for(Point neighbor: neighbors){
			result+=neighbor.getState();
		}
		return result;
	}

	public void drop(){
		Random rand = new Random();
		int upperbound = 100;
		int int_random = rand.nextInt(upperbound);
		if(int_random<5){
			nextState=6;
		}
	}
}
