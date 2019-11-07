package equipment.hvac;

import com.mathworks.matlab.types.Struct;

import enums.EquipmentState;
import enums.RoomWindow;
import equipment.RatedConnectableEquipment;
import equipment.rates.Rates;
import helper.MatlabHelper;
import home.Room;

public abstract class Acclimatization extends RatedConnectableEquipment {
	
	private Room[] _rooms;
	private Room[] _fourRooms;
	
	public Acclimatization(EquipmentState state, Room[] rooms, Rates rates) throws Exception {
		super(state, rates);
		
		_rooms = rooms;
		
		if (_rooms == null || _rooms.length < 1 || _rooms.length > 4) {
			throw new Exception("Rooms vector must have 1 to 4 elements");
		}

		addGain("Cost_AA/AcHt", 1);
		
		Struct thermalValues = getThermalValues();
		setSimulationParameters(thermalValues);
	}
	
	protected double calculateCoolCap(double BTU) {
		return BTU*3600/3.412;
	}	
	
	private Struct getThermalValues() throws Exception {
		
		Struct houseParameters = getFilledStruct();
		return MatlabHelper.getEngine().feval("HouseCalculation", houseParameters);
	}
	
	private Struct getFilledStruct() {
		int roomsCount = _rooms.length;
		_fourRooms = new Room[4];
		
		for (int i = 0; i < 4; i++) {
			if(i<_rooms.length) {
				_fourRooms[i] = _rooms[i];
			}
			else {
				_fourRooms[i] = new Room(0, 0, 0, 0, 0, RoomWindow.NO);
			}
			_fourRooms[i].setRoomNumber(i+1);
		}
		
		Struct houseParameters = new Struct(
				"LR1", _fourRooms[0].getLength(), "LW1", _fourRooms[0].getWidth(), "LH1", _fourRooms[0].getHeight(), "Lwin1", _fourRooms[0].getWindowLength(), "Wwin1", _fourRooms[0].getWindowWidth(), "noyesWindows1", _fourRooms[0].getHasWindows().getValue(),
				"LR2", _fourRooms[1].getLength(), "LW2", _fourRooms[1].getWidth(), "LH2", _fourRooms[1].getHeight(), "Lwin2", _fourRooms[1].getWindowLength(), "Wwin2", _fourRooms[1].getWindowWidth(), "noyesWindows2", _fourRooms[1].getHasWindows().getValue(),
				"LR3", _fourRooms[2].getLength(), "LW3", _fourRooms[2].getWidth(), "LH3", _fourRooms[2].getHeight(), "Lwin3", _fourRooms[2].getWindowLength(), "Wwin3", _fourRooms[2].getWindowWidth(), "noyesWindows3", _fourRooms[2].getHasWindows().getValue(),
				"LR4", _fourRooms[3].getLength(), "LW4", _fourRooms[3].getWidth(), "LH4", _fourRooms[3].getHeight(), "Lwin4", _fourRooms[3].getWindowLength(), "Wwin4", _fourRooms[3].getWindowWidth(), "noyesWindows4", _fourRooms[3].getHasWindows().getValue(),				
				"No_of_rooms", roomsCount
			);
		return houseParameters;
	}
	
	private void setSimulationParameters(Struct results) throws Exception {

		if (results.containsKey("resultsJava")) {
			Struct res = (Struct)results.get("resultsJava");
			
			double[] rw = (double[])res.get("Rw");
			double[] cw = (double[])res.get("Cw");
			double[] rc = (double[])res.get("Rc");
			double[] ci = (double[])res.get("Ci");
			double[] b = (double[])res.get("b");				
			double bSum = 0;
			
			for (int c = 0; c < 4; c++)
				bSum += b[c];					
			
			for (int c = 0; c < 4; c++) {
				if(c<_rooms.length) {
					_fourRooms[c].setParams(rw[c], rc[c], cw[c], ci[c], b[c], bSum);
				}
				else {
					_fourRooms[c].setZeroParams();
				}
			}		
		}		
	}
	
	@Override
	protected void setEquipmentRates() throws Exception {
		addValue("Cost_AA/Rates/Constant", getRates().getOffPeak());
		addValue("Cost_AA/Rates/Constant1", getRates().getMidPeak());
		addValue("Cost_AA/Rates/Constant2", getRates().getOnPeak());
	}

	public Room[] getRooms() {
		return _rooms;
	}
}