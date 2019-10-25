package unifei;

import java.io.Serializable;

public class Measurement implements Serializable {

	private static final long serialVersionUID = -3842844978617110554L;

	private int _id;
	private String _name;
	private int _current;

	public Measurement() {
		
	}
	
	public Measurement(int id, String name, int current ) {
		_id = id;
		_name = name;
		_current = current;
	}
	
	@Override
	public String toString() {		
		return String.format("Id: %d, Name: %s, Age: %d", getId(), getName(), getCurrent());
	}
	
	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public int getCurrent() {
		return _current;
	}

	public void setCurrent(int current) {
		this._current = current;
	}
}
