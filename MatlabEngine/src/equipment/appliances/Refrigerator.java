package equipment.appliances;

import enums.EquipmentState;
import equipment.RatedConnectableEquipment;
import equipment.rates.Rates;

public class Refrigerator extends RatedConnectableEquipment {
	
	public Refrigerator(EquipmentState state, int voltage, double width, double length, double height, Rates rates) throws Exception {
		super(state, rates);

		calculate(voltage, width, length, height);
	}
	
	private void calculate(int voltage, double width, double length, double height) throws Exception {

		double totalArea = (2*(width*height)+2*(length*height)+2*(width*length));
		double totalVolume = width*height*length;

		double L_acero = 0.00318;
		double L_poli = 0.06;
		double h_e = 17;
		double h_i = 9;

		double k_acero = 50;
		double p_acero = 750;
		double cp_acero = 460;

		double k_poli = 0.0015;
		double p_poli = 50;
		double cp_poli = 10;		

		double k_plastico = 0.24;
		double cp_plastico = 10;
		double p_plastico = 9;		

		double K_ext=1/(1/h_e+1/h_i+ L_acero/k_acero + L_poli/k_poli + L_acero/k_plastico);
		
		double Rw_Re = 1/(3600*K_ext*totalArea);

		double Ci_Re1 = 1.2*1000*totalVolume;
		double Ci_Re = Ci_Re1;

		double Cw1 = p_acero*cp_acero*(totalArea*L_acero);
		double Cw2 = p_poli*cp_poli*(totalArea*L_poli);
		double Cw3 = p_plastico*cp_plastico*(totalArea*L_acero);
		
		double Cw_Re = Cw1+Cw2+Cw3;
		double Rc_Re=Double.POSITIVE_INFINITY;

		double term1 = 1/(Rw_Re*Cw_Re);
		double term3 = 2/(Rw_Re*Cw_Re);
		double term4 = 1/Ci_Re;
		double term6 = 1/(Rw_Re*Ci_Re);
		double term7 = 1/(Rc_Re*Ci_Re);
		double term8 = (1/Ci_Re)*( (1/Rw_Re) + (1/Rc_Re));

		addGain("Term1",term1);
		addGain("Term2",term1);
		addGain("Term3",term3);
		addGain("Term4",term4);
		addGain("Term5",term4);
		addGain("Term6",term6);
		addGain("Term7",term7);
		addGain("Term8",term8);		
	}

	@Override
	protected String getEquipmentOnOffKey() {
		return "AA_OnOff";
	}

	@Override
	protected String getBaseParamPath() {
		return "Energy_Cost/Refrigerator/";
	}
	
	@Override
	protected void setEquipmentRates() throws Exception {
		addValue("Rates/Ele_off", getRates().getOffPeak());
		addValue("Rates/Ele_mid", getRates().getMidPeak());
		addValue("Rates/Ele_peak", getRates().getOnPeak());
	}
}