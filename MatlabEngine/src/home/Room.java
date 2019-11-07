package home;

import enums.RoomWindow;

public class Room extends House {

	private int _roomNumber;
	private double _length;
	private double _width;
	private	double _height;
	private	double _windowLength; 
	private	double _windowWidth;
	private RoomWindow _hasWindows;
	
	public Room(double length, double width, double height, double windowLength, double windowWidth, RoomWindow hasWindows) {
		_length = length;
		_width = width;
		_height = height;
		_windowLength = windowLength;
		_windowWidth = windowWidth;
		_hasWindows = hasWindows;		
	}

	@Override
	protected String getBaseParamPath() {
		return super.getBaseParamPath() + String.format("Room_%d/", _roomNumber);
	}
	
	public void setParams(double Rw, double Rc, double Cw, double Ci, double b, double bSum) throws Exception {
		setParams(Rw, Rc, Cw, Ci, b, bSum, 1);
	}
	
	public void  setZeroParams() throws Exception {
		setParams(1, 1, 1, 1, 1, 1, 0);
	}
	
	private void setParams(double Rw, double Rc, double Cw, double Ci, double b, double bSum, int multiplier) throws Exception {
		addValue("%Loss", multiplier * (b/bSum));
	    addInitialCondition("Integrator1", multiplier * (21));	
	
		addGain("1st term1", multiplier * (1/Cw));
		addGain("2nd term1", multiplier * (1/(Rw*Cw)));
		addGain("3th term1", multiplier * (1/(Rw*Cw)));
		addGain("4th term1", multiplier * (2/(Rw*Cw)));
			
		addGain("1st term2", multiplier * (1/Ci));
		addGain("2nd term2", multiplier * (1/Ci));
		addGain("3th term2", multiplier * ((1/Ci) * ((1/Rw) + (1/Rc))));
		addGain("4th term2", multiplier * (1/(Rw*Ci)));
		addGain("5th term2", multiplier * (1/(Rc*Ci)));
	}

	public void setRoomNumber(int roomNumber) {
		_roomNumber = roomNumber;
	}

	public double getLength() {
		return _length;
	}

	public double getWidth() {
		return _width;
	}

	public double getHeight() {
		return _height;
	}

	public double getWindowLength() {
		return _windowLength;
	}

	public double getWindowWidth() {
		return _windowWidth;
	}

	public RoomWindow getHasWindows() {
		return _hasWindows;
	}
}