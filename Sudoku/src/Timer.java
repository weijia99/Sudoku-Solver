
public class Timer {
	
	// Expressed in nanos
	private double startTime;	
	private double endTime;	
	
	private static final int NANOS_TO_MILIS = 1000000;
	
	public Timer() { 
		
		startTime = 0;
		endTime = 0;
	}
	
	public void start() {
		
		startTime = System.nanoTime();
	}
	
	public void stop() {
		
		endTime = System.nanoTime();
	}
	
	@Override
	public String toString() {
		
		return "Time: " + ((endTime - startTime)/NANOS_TO_MILIS) + " ms";		
	}
	
	public double getTimeInMiliSecs() {
		
		return ((endTime - startTime)/NANOS_TO_MILIS);
	}
}
