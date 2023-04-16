package engine;

public class Distance {
	private String to;
	private String from;
	private int distance;
	public Distance(String to, String from, int distance) {
		this.to = to;
		this.from = from;
		this.distance = distance;
	}
	public String getTo() {
		return to;
	}
	public String getFrom() {
		return from;
	}
	public int getDistance() {
		return distance;
	}
	
	
}
