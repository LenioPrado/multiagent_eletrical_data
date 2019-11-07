package demo;
import java.util.Arrays;
import java.util.Random;
import com.mathworks.matlab.types.HandleObject;
import com.mathworks.matlab.types.Struct;
import enums.EquipmentState;
import enums.RoomWindow;
import enums.YearSeason;
import equipment.hvac.AirConditioner;
import equipment.rates.Rates;
import helper.MatlabHelper;
import home.Room;

public class Tentativas {

	public Tentativas() throws Exception {
	}

	public static void main(String[] args) throws Exception {
				
		//generateRandom();
		Tentativas t = new Tentativas();
		t.testAcclimatization();
		//t.runVdp();
		// t.callEvalFunction();
		// t.callFunction();
	}

	public static void generateRandom() {
		Double[] hoursPower = { 
				2965.0, 4220.0, 2825.0, 4555.0, 3220.0, 3711.0, 2430.0, 2000.0, 2650.0, 1700.0, 2440.0, 2250.0, 
				2680.0, 3100.0, 4500.0, 5150.0, 4550.0, 3200.0, 4100.0, 3600.0, 4000.0, 2600.0, 5220.0, 3600.0
		};		
		
		//1700 - 5220
		
		Random r = new Random(1000);
		String res = "";
		for (int i=0; i<hoursPower.length;i++) {			
			hoursPower[i] = Double.valueOf(String.valueOf(r.nextInt(3520)+1700));
			res += hoursPower[i];
			if(i != hoursPower.length-1)
				res += ",";
		}		
		
		System.out.println(String.format("HoursPower: \n%s", res));
	}	

	public void PutVar() throws Exception {		
		MatlabHelper.putVariable("x", 7.0);
		MatlabHelper.putVariable("y", 3.0);
		MatlabHelper.eval("z = complex(x, y);");
		String[] w = MatlabHelper.feval("who");
		System.out.println("MATLAB workspace variables " + Arrays.toString(w));
		MatlabHelper.closeEngine();
	}
	
	public void testAcclimatization() throws Exception {
		MatlabHelper.changeToFolder("E:\\Develop\\PhD Softwares\\SRLS - Smart Residential Load Simulator\\SRLS2\\");
		MatlabHelper.loadSimulinkModel("E:\\Develop\\PhD Softwares\\SRLS - Smart Residential Load Simulator\\SRLS2\\", "Energy_Cost.mdl");
		
		Room r1 = new Room(4, 4, 2, 1, 1, RoomWindow.YES);
		Room r2 = new Room(3, 2, 2.5, 1.1, 1.2, RoomWindow.YES);
		Room r3 = new Room(2.5, 2.5, 3, 1.5, 1.6, RoomWindow.YES);
		Room r4 = new Room(3.8, 2.9, 2.5, 1.3, 1.4, RoomWindow.YES);

		Room[] rooms = {r1, r2, r3, r4};
		Rates rates = new Rates(6.2, 9.2, 10.8, 35.7, YearSeason.SUMMER);
		new AirConditioner(EquipmentState.ON, rooms, 48000, rates);
	}

	public void PassStruct() throws Exception {
		MatlabHelper.changeToFolder("E:\\Develop\\PhD Softwares\\SRLS - Smart Residential Load Simulator\\SRLS2\\");
		MatlabHelper.loadSimulinkModel("E:\\Develop\\PhD Softwares\\SRLS - Smart Residential Load Simulator\\SRLS2\\", "Energy_Cost.mdl");

		Struct houseParameters = new Struct(
			"LR1", "4", "LW1", "4", "LH1", "2", "Lwin1", "1", "Wwin1", "1", "noyesWindows1", "2",
			"LR2", "3", "LW2", "2", "LH2", "2.5", "Lwin2", "1.1", "Wwin2", "1.2", "noyesWindows2", "2",
			"LR3", "2.5", "LW3", "2.5", "LH3", "3", "Lwin3", "1.5", "Wwin3", "1.6", "noyesWindows3", "2",
			"LR4", "3.8", "LW4", "2.9", "LH4", "2.5", "Lwin4", "1.3", "Wwin4", "1.4", "noyesWindows4", "2",				
			"No_of_rooms", "4"
		);

		
		Struct h1 = MatlabHelper.getEngine().feval("HouseCalculation", houseParameters);
		//Struct h1 = MatlabHelper.getEngine().feval("HouseCalculationValues", 2);
		
		if(h1 != null) {
			MatlabHelper.showStructValues(h1);
		}
		//Struct s = new Struct("Color", color, "LineWidth", 2);

		//MatlabHelper.getEngine().feval("set", h, s);
		//MatlabHelper.eval("print('myPlot', '-djpeg')");
		MatlabHelper.closeEngine();
	}

	public void callEvalFunction() throws Exception {
		//double[] y = { 1.0, 2.0, 3.0, 4.0, 5.0 };
		MatlabHelper.eval("cd 'E:\\Develop\\PhD Softwares\\Simulink Models\\'");
		MatlabHelper.eval("InSig = defineInputs();");
		Struct InSig = MatlabHelper.getVariable("InSig");
		System.out.println("Simulation result:");
		MatlabHelper.showStructValues(InSig);
		MatlabHelper.closeEngine();
	}
	
	public void callFunction() throws Exception {
		double[] y = { 7.0, 2.0, 3.0, 4.0, 5.0 };
		// proxy.eval("addpath('C:\\some\\path')");
		MatlabHelper.eval("cd 'E:\\Develop\\PhD Softwares\\Simulink Models\\'");
		Object[] h = MatlabHelper.getEngine().feval(2, "firstfunction", y);
		System.out.println("Simulation result " + Arrays.toString(h));
		MatlabHelper.closeEngine();
	}

	public void runVdp() throws Exception {
		//MatlabHelper.loadAndRunSimulinkModel("", "vdp");

		// Get simulation data
		MatlabHelper.eval("y = simOut.get('yOut');");
		MatlabHelper.eval("t = simOut.get('tOut');");
		// Graph results and create image file
		MatlabHelper.eval("plot(t,y);");
		MatlabHelper.sleep(5000);
		MatlabHelper.eval("print('vdpPlot','-djpeg');");
		// Return results to Java
		double[][] y = MatlabHelper.getVariable("y");
		double[] t = MatlabHelper.getVariable("t");
		// Display results
		System.out.println("Simulation result " + Arrays.deepToString(y));
		System.out.println("Time vector " + Arrays.toString(t));
		MatlabHelper.closeEngine();
	}
	
	
	public void passStructValue(String functionName) {
		Struct s = new Struct("YearSeason",1,"Off_Peak","6.2","Mid_Peak","9.2","On_Peak","10.8");
		try {
			Object result = MatlabHelper.getEngine().feval(functionName, s);
			System.out.println(String.format("Result of function %s: %s", functionName, result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exampleHandleObject() {
		MatlabHelper.eval("cm = containers.Map({'id','name'},{11,'mw'});");
		HandleObject handle = MatlabHelper.getVariable("cm");
		try {
			String[] cells = MatlabHelper.getEngine().feval("keys", handle);
			System.out.println("Handle Object: " + Arrays.deepToString(cells));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getResults() {
		System.out.println("Get simulation data...");
		MatlabHelper.eval("y = simOut.get('yOut');");
		MatlabHelper.eval("t = simOut.get('tOut');");

		System.out.println("Return results to Java...");
		double[][] y = MatlabHelper.getVariable("y");
		double[] t = MatlabHelper.getVariable("t");

		System.out.println("Display results...");
		System.out.println("Simulation result " + Arrays.deepToString(y));
		System.out.println("Time vector " + Arrays.toString(t));

		System.out.println("Graph results and create image file...");
		MatlabHelper.eval("plot(t,y);");
		MatlabHelper.sleep(5000);
		MatlabHelper.eval("print('Residencial','-djpeg');");

		System.out.println("Ending App...");
		MatlabHelper.sleep(20000);
		MatlabHelper.closeEngine();
	}
}