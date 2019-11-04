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

public class SimulatorGroup1Case19 extends BaseSimulationCase {
		
	public SimulatorGroup1Case19(Rates rates) throws Exception {
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
		return "SimulationGroup1Case19";
	}
	
	private void setOnCaseValues(EquipmentState state) throws Exception {
		new AirConditioner(state, 30000, getRates());
		new Clothwasher(state, 161, new CyclicUsageParameter(2, new FixedTimeOfUse(34, new double[] {9,16})), WaterTemperature.WARM, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new CompactFluorescentLighting(31, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {0,18}, new double[] {5,1})), getRates());
		new Dishwasher(state, 202, new CyclicUsageParameter(1, new FixedTimeOfUse(30, new double[] {8})), EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new Dryer(state, new CyclicUsageParameter(2, new FixedTimeOfUse(31, new double[] {10,16})));
		new IncandescentLighting(83, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {6,23}, new double[] {5,5})), getRates());
		new PoolPump(state, new CyclicUsageParameter(1, new FixedTimeOfUse(59, new double[] {10})));
		new Refrigerator(state, 127, 1.2, 1.1, 2, getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7}, new double[] {1.01}), StoveBurnerSize.NINE, new double[] {21,25,48,65});
		new TubeFluorescentLighting(40, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {9,19}, new double[] {1,3})), getRates());
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 21, 44000, 55, 90, 0.64f), getRates());

	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		new Battery(state, new FixedTimeOfUse(50, new double[] {10, 14, 16}), getBatteryHoursPower(), 1500, getRates());				
		new Photovoltaic(state, getPhotovoltaicHoursRadiation(), getRates());			
		new Wind(state, getWindHoursPower(), getRates());
	}
}