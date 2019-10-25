package equipment.hvac;

import enums.WaterHeaterType;

public class WaterHeaterParameters {

	private WaterHeaterType _waterHeaterType;
	private int _inletTemperature;
	private int _ambientTemperature;
	private int _BTUPower;
	private int _temperatureSetPoint;
	private int _literCapacity;
	private float _efficiency;
	
	public WaterHeaterParameters(WaterHeaterType waterHeaterType, int inletTemperature, int ambientTemperature, int BTUPower, 
			int temperatureSetPoint, int literCapacity, float efficiency) throws Exception {
		
		_waterHeaterType = waterHeaterType;
		_inletTemperature = inletTemperature;
		_ambientTemperature = ambientTemperature;
		_BTUPower = BTUPower;
		_temperatureSetPoint = temperatureSetPoint;
		_literCapacity = literCapacity;
		_efficiency = efficiency;
		
		if(literCapacity <= 0)
			throw new Exception("LiterCapacity must be greater than 0");
		
		if(efficiency <= 0)
			throw new Exception("Efficiency must be greater than 0");
		
		if(BTUPower <= 0)
			throw new Exception("BTUPower must be greater than 0");		
	}

	public WaterHeaterType getWaterHeaterType() {
		return _waterHeaterType;
	}

	public int getInletTemperature() {
		return _inletTemperature;
	}

	public int getAmbientTemperature() {
		return _ambientTemperature;
	}

	public int getBTUPower() {
		return _BTUPower;
	}

	public int getTemperatureSetPoint() {
		return _temperatureSetPoint;
	}

	public int getLiterCapacity() {
		return _literCapacity;
	}

	public float getEfficiency() {
		return _efficiency;
	}
}