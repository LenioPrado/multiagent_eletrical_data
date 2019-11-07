package enums;

public enum RoomWindow {
	NO (1),
	YES (2);
	
	private final double hasWindow;
	
	private RoomWindow(double hasWindow) {
		this.hasWindow = hasWindow;
	}
	
	public double getValue() {
		return this.hasWindow;
	}
}