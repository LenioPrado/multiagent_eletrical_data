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

public class SimulatorGroup1Case10 extends BaseSimulationCase {
		
	public SimulatorGroup1Case10(Rates rates) throws Exception {
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
		return "SimulationGroup1Case10";
	}
	
	private void setOnCaseValues(EquipmentState state) throws Exception {
		new AirConditioner(state, 30000, getRates());
		new Clothwasher(state, 261, new CyclicUsageParameter(1, new FixedTimeOfUse(60, new double[] {9})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new CompactFluorescentLighting(21, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {0,22}, new double[] {1,2})), getRates());
		new Dishwasher(state, 198, new CyclicUsageParameter(2, new FixedTimeOfUse(59, new double[] {5,16})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new Dryer(state, new CyclicUsageParameter(2, new FixedTimeOfUse(53, new double[] {5,14})));
		new IncandescentLighting(65, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {0,21}, new double[] {3,1})), getRates());
		new PoolPump(state, new CyclicUsageParameter(1, new FixedTimeOfUse(31, new double[] {6})));
		new Refrigerator(state, 127, 1, 1.2, 1.3, getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,12}, new double[] {2.96,2.25}), StoveBurnerSize.SEVEN, new double[] {13,42,53,10});
		new TubeFluorescentLighting(29, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {0,21}, new double[] {3,3})), getRates());
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 43000, 60, 139, 0.27f), getRates());

	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		new Battery(state, new FixedTimeOfUse(50, new double[] {10, 14, 16}), getBatteryHoursPower(), 1500, getRates());				
		new Photovoltaic(state, getPhotovoltaicHoursRadiation(), getRates());			
		new Wind(state, getWindHoursPower(), getRates());
	}
}