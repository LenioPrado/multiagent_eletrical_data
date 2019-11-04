package simulator.cases.group1;

import enums.EquipmentState;
import enums.EquipmentType;
import enums.Hotwater;
import enums.StoveBurnerSize;
import enums.WaterHeaterType;
import enums.WaterTemperature;
import equipment.Equipment;
import equipment.appliances.Refrigerator;
import equipment.appliances.Stove;
import equipment.cyclicusage.Clothwasher;
import equipment.cyclicusage.CyclicUsageParameter;
import equipment.cyclicusage.Dishwasher;
import equipment.cyclicusage.Dryer;
import equipment.cyclicusage.PoolPump;
import equipment.energysources.Battery;
import equipment.energysources.Photovoltaic;
import equipment.energysources.Wind;
import equipment.hvac.AirConditioner;
import equipment.hvac.WaterHeater;
import equipment.hvac.WaterHeaterParameters;
import equipment.lighting.CompactFluorescentLighting;
import equipment.lighting.IncandescentLighting;
import equipment.lighting.LightingParameter;
import equipment.lighting.TubeFluorescentLighting;
import equipment.rates.Rates;
import simulator.BaseSimulationCase;
import time.AllDayPeriodsTimeOfUse;
import time.FixedTimeOfUse;
import time.MorningNightTimeOfUse;

public class SimulatorGroup1Case2 extends BaseSimulationCase {

	public SimulatorGroup1Case2(Rates rates) throws Exception {
		super(rates);
	}

	@Override
	protected Equipment getEquipment(EquipmentState state) throws Exception {
		return null;
	}
	
	@Override
	public void setSimulationValues() throws Exception {		
		setOnCaseValues(EquipmentState.ON);
		setOffCaseValues(EquipmentState.OFF);
	}

	@Override
	public void resetSimulationValues() throws Exception {
		setOnCaseValues(EquipmentState.OFF);
		setOffCaseValues(EquipmentState.OFF);
		
		setRoomsValues();
	}
	
	@Override
	public String getSimulationDescription() {
		return "SimulationGroup1Case2";
	}

	private void setOnCaseValues(EquipmentState state) throws Exception {
		new AirConditioner(state, 30000, getRates());
		new Clothwasher(state, 235, new CyclicUsageParameter(2, new FixedTimeOfUse(40, new double[] {6,13})), WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(24, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {3,21}, new double[] {1,4})), getRates());
		new Dishwasher(state, 180, new CyclicUsageParameter(1, new FixedTimeOfUse(82, new double[] {9})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new Dryer(state, new CyclicUsageParameter(1, new FixedTimeOfUse(56, new double[] {9})));
		new IncandescentLighting(72, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {8,19}, new double[] {3,5})), getRates());
		new PoolPump(state, new CyclicUsageParameter(2, new FixedTimeOfUse(60, new double[] {6,13})));
		new Refrigerator(state, 127, 1.3, 1.1, 1.8, getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7}, new double[] {1}), StoveBurnerSize.SEVEN, new double[] {94,79,48,52});
		new TubeFluorescentLighting(32, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {3,23}, new double[] {3,3})), getRates());
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 22, 51000, 60, 137, 0.68f), getRates());		
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		new Battery(state, new FixedTimeOfUse(50, new double[] {10, 14, 16}), getBatteryHoursPower(), 1500, getRates());				
		new Photovoltaic(state, getPhotovoltaicHoursRadiation(), getRates());			
		new Wind(state, getWindHoursPower(), getRates());
	}
}