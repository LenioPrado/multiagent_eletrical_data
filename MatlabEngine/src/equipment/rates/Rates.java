package equipment.rates;

import enums.YearSeason;
import equipment.Equipment;

public class Rates extends Equipment {

	private double _offPeak;	
	private double _midPeak;
	private double _onPeak;
	private double _gas;
	private YearSeason _yearSeason;
	
	public Rates(double offPeak, double midPeak, double onPeak, double gas, YearSeason yearSeason) throws Exception {
		_offPeak = offPeak;
		_midPeak = midPeak;
		_onPeak = onPeak;
		_gas = gas;
		_yearSeason = yearSeason;
		
		if(_offPeak == 0 || _midPeak == 0 || _onPeak == 0 || _gas == 0)
			throw new Exception("OffPeak, MidPeak, OnPeak and Gas rates cannot be zero");
		
		if(yearSeason == YearSeason.SUMMER) {
	        addValue("Outdoor Temp/Temp_aux", 1);
	        addValue("Outdoor Temp/Temp_aux1", 0);
		} else {
			addValue("Outdoor Temp/Temp_aux", 0);
	        addValue("Outdoor Temp/Temp_aux1", 1);
		}
	}

	public double getOffPeak() {
		return _offPeak;
	}

	public double getMidPeak() {
		return _midPeak;
	}

	public double getOnPeak() {
		return _onPeak;
	}
	
	public YearSeason getYearSeason() {
		return _yearSeason;
	}
	
	public double getGas() {
		return _gas;
	}

	@Override
	protected String getBaseParamPath() {
		return "Energy_Cost/";
	}
}