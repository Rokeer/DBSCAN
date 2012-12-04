
public class Point {
	private int id;
	private double x;
	private double y;
	private boolean isCore = false; //0 represent core, 1 represent border
	public Point() {
		
	}
	public Point(int id, double x, double y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public boolean getIsCore() {
		return isCore;
	}
	public void setIsCore(boolean isCore) {
		this.isCore = isCore;
	}
	
	
}
