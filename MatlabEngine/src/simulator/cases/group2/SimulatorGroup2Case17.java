package simulator.cases.group2;

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

public class SimulatorGroup2Case17 extends BaseSimulationCase {
		
	public SimulatorGroup2Case17(Rates rates) throws Exception {
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
		return "SimulationGroup2Case17";
	}
	
	private void setOnCaseValues(EquipmentState state) throws Exception {
		new AirConditioner(state, 30000, getRates());
		new Clothwasher(state, 155, new CyclicUsageParameter(3, new FixedTimeOfUse(67, new double[] {9,12,21})), WaterTemperature.WARM, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new CompactFluorescentLighting(23, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {7,19}, new double[] {2,1})), getRates());
		new Dishwasher(state, 204, new CyclicUsageParameter(3, new FixedTimeOfUse(67, new double[] {10,17,22})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new IncandescentLighting(86, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {0,19}, new double[] {5,5})), getRates());
		new Refrigerator(state, 220, 1.2, 1.3, 1.7, getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {10,13,22}, new double[] {1.91,1.75,1.26}), StoveBurnerSize.SIX, new double[] {11,86,96,93});
		new TubeFluorescentLighting(25, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {5,19}, new double[] {3,5})), getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		new Battery(state, new FixedTimeOfUse(50, new double[] {10, 14, 16}), getBatteryHoursPower(), 1500, getRates());				
		new Photovoltaic(state, getPhotovoltaicHoursRadiation(), getRates());			
		new Wind(state, getWindHoursPower(), getRates());
		
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(80, new double[] {10, 15, 20})));
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(180, new double[] {5, 14, 22})));
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 40000, 55, 184,  0.62f), getRates());		
	}
}