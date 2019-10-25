package enums;

public enum Hotwater {
	DISCONNECTED (0),
	CONNECTED (1);
	
	private final int status;
	
	private Hotwater(int status) {
		this.status = status;
	}
	
	public int getValue() {
		return this.status;
	}
}