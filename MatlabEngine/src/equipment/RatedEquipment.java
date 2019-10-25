package equipment;

import equipment.rates.Rates;

public abstract class RatedEquipment extends Equipment {

	private Rates _rates;
	
	public RatedEquipment(Rates rates) throws Exception {	
		_rates = rates;
		
		if(rates == null)
			throw new Exception("Rates cannot be null");

		setEquipmentRates();
	};
	
	protected abstract void setEquipmentRates() throws Exception;

	public Rates getRates() {
		return _rates;
	}	
}