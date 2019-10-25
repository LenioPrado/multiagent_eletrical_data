package home;

public class Room extends House {

	private int _roomNumber;

	public <T> Room(int roomNumber, T[] term1Gains, T[] term2Gains, T loss, T integrator1, T integrator2) throws Exception {
		_roomNumber = roomNumber;		

		if (term1Gains == null || term1Gains.length != 4) {
			throw new Exception("Gains vector 1 must have 4 elements!!!");
		}

		if (term2Gains == null || term2Gains.length != 5) {
			throw new Exception("Gains vector 2 must have 5 elements!!!");
		}

		addValue("%Loss", String.valueOf(loss));
		process(term1Gains, term2Gains, integrator1, integrator2);
	}

	private <T> void process(T[] term1Gains, T[] term2Gains, T integrator1, T integrator2) throws Exception {
		for (int termCount = 1; termCount <= 2; termCount++) {
			T[] current = termCount == 1 ? term1Gains : term2Gains;
			String termName = termCount == 1 ? "term1" : "term2";
			for (int gains = 0; gains < current.length; gains++) {
				String paramName = "";
				
				switch (gains) {
				case 0:
					paramName = "1st";
					break;
				case 1:
					paramName = "2nd";
					break;
				case 2:
					paramName = "3th";
					break;
				case 3:
					paramName = "4th";
					break;	
				case 4:
					paramName = "5th";
					break;	
				}

				String termParamName = String.format("%s %s", paramName, termName);

				addGain(termParamName, Double.parseDouble(current[gains].toString()));
			}
		}
		
		addInitialCondition("Integrator1", String.valueOf(integrator1));
		addInitialCondition("Integrator1", String.valueOf(integrator2));
	}

	@Override
	protected String getBaseParamPath() {
		return super.getBaseParamPath() + String.format("Room_%d/", _roomNumber);
	}
}