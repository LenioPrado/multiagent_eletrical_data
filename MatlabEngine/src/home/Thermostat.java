package home;

import enums.EquipmentState;
import equipment.Equipment;

public class Thermostat extends Equipment {

	private EquipmentState _thermostatState;
	private double _temperature;
	private double[] _temperatures;
	private double[] _hours;
	
	public Thermostat(double temperature, double[] hours) throws Exception {
		
		if (temperature <= 0)
			throw new Exception("Temperature must be positive!!!");
		
		if (_hours == null || _hours.length != 4)
			throw new Exception("Hours vector cannot be null and must have four elements!!!");
		
		_thermostatState = EquipmentState.OFF;
		_hours = hours;
		_temperature = temperature;
	}
	public Thermostat(double[] temperatures, double[] hours) throws Exception {
		
		if (temperatures == null || temperatures.length != 4)
			throw new Exception("Temperatures vector cannot be null and must have four elements!!!");

		if (_hours == null || _hours.length != 4)
			throw new Exception("Hours vector cannot be null and must have four elements!!!");
		
		_thermostatState = EquipmentState.ON;
		_hours = hours;
		_temperatures = temperatures;
	}
	
	@Override
	protected String getBaseParamPath() {
		return "Energy_Cost/Thermostat/";
	}
	
	public void setParameters() throws Exception {
		
		if(_thermostatState == EquipmentState.OFF) {
			addValue("Set_Point_AA", _temperature);
		} else {
			addValue("P1_set", _temperatures[0]);
			addValue("P2_set", _temperatures[1]);
			addValue("P3_set", _temperatures[2]);
			addValue("P4_set", _temperatures[3]);			
		}
		
		addValue("P1_on", _hours[0]);
		addValue("P2_on", _hours[1]);
		addValue("P3_on", _hours[2]);
		addValue("P4_on", _hours[3]);
		
		addValue("Sche", _thermostatState.getValue());
	}
}