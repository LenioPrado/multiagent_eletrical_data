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

public class SimulatorGroup1Case14 extends BaseSimulationCase {
		
	public SimulatorGroup1Case14(Rates rates) throws Exception {
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
		return "SimulationGroup1Case14";
	}
	
	private void setOnCaseValues(EquipmentState state) throws Exception {
		new AirConditioner(state, 30000, getRates());
		new Clothwasher(state, 240, new CyclicUsageParameter(2, new FixedTimeOfUse(88, new double[] {7,14})), WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(29, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {6,22}, new double[] {4,2})), getRates());
		new Dishwasher(state, 273, new CyclicUsageParameter(1, new FixedTimeOfUse(68, new double[] {7})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(57, new double[] {7,16,21})));
		new IncandescentLighting(77, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {6,22}, new double[] {3,3})), getRates());
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(70, new double[] {11,16,18})));
		new Refrigerator(state, 220, 1.3, 1.3, 1.1, getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5}, new double[] {1.5}), StoveBurnerSize.NINE, new double[] {44,55,86,69});
		new TubeFluorescentLighting(23, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {3,23}, new double[] {1,1})), getRates());
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 50000, 40, 119, 0.5f), getRates());

	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		new Battery(state, new FixedTimeOfUse(50, new double[] {10, 14, 16}), getBatteryHoursPower(), 1500, getRates());				
		new Photovoltaic(state, getPhotovoltaicHoursRadiation(), getRates());			
		new Wind(state, getWindHoursPower(), getRates());
	}
}