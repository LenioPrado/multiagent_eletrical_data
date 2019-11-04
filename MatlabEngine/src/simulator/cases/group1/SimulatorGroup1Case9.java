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

public class SimulatorGroup1Case9 extends BaseSimulationCase {
		
	public SimulatorGroup1Case9(Rates rates) throws Exception {
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
		return "SimulationGroup1Case9";
	}
	
	private void setOnCaseValues(EquipmentState state) throws Exception {
		new AirConditioner(state, 30000, getRates());
		new Clothwasher(state, 173, new CyclicUsageParameter(1, new FixedTimeOfUse(41, new double[] {5})), WaterTemperature.WARM, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new CompactFluorescentLighting(24, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {9,20}, new double[] {5,5})), getRates());
		new Dishwasher(state, 259, new CyclicUsageParameter(1, new FixedTimeOfUse(46, new double[] {9})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new Dryer(state, new CyclicUsageParameter(2, new FixedTimeOfUse(47, new double[] {8,12})));
		new IncandescentLighting(60, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {6,21}, new double[] {3,5})), getRates());
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(52, new double[] {5,13,19})));
		new Refrigerator(state, 220, 1.3, 1, 1.3, getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5,16}, new double[] {2.71,2.13}), StoveBurnerSize.NINE, new double[] {79,36,83,7});
		new TubeFluorescentLighting(29, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {7,20}, new double[] {4,1})), getRates());
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 21, 47000, 48, 126, 0.09f), getRates());

	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		new Battery(state, new FixedTimeOfUse(50, new double[] {10, 14, 16}), getBatteryHoursPower(), 1500, getRates());				
		new Photovoltaic(state, getPhotovoltaicHoursRadiation(), getRates());			
		new Wind(state, getWindHoursPower(), getRates());
	}
}