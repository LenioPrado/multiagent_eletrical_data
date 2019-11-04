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

public class SimulatorGroup1Case16 extends BaseSimulationCase {
		
	public SimulatorGroup1Case16(Rates rates) throws Exception {
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
		return "SimulationGroup1Case16";
	}
	
	private void setOnCaseValues(EquipmentState state) throws Exception {
		new AirConditioner(state, 30000, getRates());
		new Clothwasher(state, 182, new CyclicUsageParameter(1, new FixedTimeOfUse(50, new double[] {6})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(29, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {7,22}, new double[] {4,1})), getRates());
		new Dishwasher(state, 169, new CyclicUsageParameter(2, new FixedTimeOfUse(53, new double[] {11,14})), EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new Dryer(state, new CyclicUsageParameter(1, new FixedTimeOfUse(80, new double[] {7})));
		new IncandescentLighting(63, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {7,19}, new double[] {1,2})), getRates());
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(33, new double[] {6,13,18})));
		new Refrigerator(state, 220, 1.2, 1, 1.2, getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5}, new double[] {2.71}), StoveBurnerSize.NINE, new double[] {69,34,19,38});
		new TubeFluorescentLighting(35, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {7,20}, new double[] {1,3})), getRates());
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 21, 44000, 65, 88, 0.81f), getRates());

	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		new Battery(state, new FixedTimeOfUse(50, new double[] {10, 14, 16}), getBatteryHoursPower(), 1500, getRates());				
		new Photovoltaic(state, getPhotovoltaicHoursRadiation(), getRates());			
		new Wind(state, getWindHoursPower(), getRates());
	}
}