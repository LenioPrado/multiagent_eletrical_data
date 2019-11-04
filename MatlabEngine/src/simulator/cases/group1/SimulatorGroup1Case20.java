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

public class SimulatorGroup1Case20 extends BaseSimulationCase {
		
	public SimulatorGroup1Case20(Rates rates) throws Exception {
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
		return "SimulationGroup1Case20";
	}
	
	private void setOnCaseValues(EquipmentState state) throws Exception {
		new AirConditioner(state, 30000, getRates());
		new Clothwasher(state, 236, new CyclicUsageParameter(2, new FixedTimeOfUse(42, new double[] {11,13})), WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(36, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {5,20}, new double[] {2,1})), getRates());
		new Dishwasher(state, 167, new CyclicUsageParameter(2, new FixedTimeOfUse(55, new double[] {9,12})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new Dryer(state, new CyclicUsageParameter(2, new FixedTimeOfUse(52, new double[] {11,12})));
		new IncandescentLighting(73, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {7,22}, new double[] {5,3})), getRates());
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(50, new double[] {9,16,20})));
		new Refrigerator(state, 220, 1.2, 1, 2, getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {6,14,20}, new double[] {0.65,1.35,2.7}), StoveBurnerSize.NINE, new double[] {73,73,20,94});
		new TubeFluorescentLighting(27, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {3,20}, new double[] {1,2})), getRates());
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 22, 39000, 36, 81, 0.96f), getRates());

	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		new Battery(state, new FixedTimeOfUse(50, new double[] {10, 14, 16}), getBatteryHoursPower(), 1500, getRates());				
		new Photovoltaic(state, getPhotovoltaicHoursRadiation(), getRates());			
		new Wind(state, getWindHoursPower(), getRates());
	}
}