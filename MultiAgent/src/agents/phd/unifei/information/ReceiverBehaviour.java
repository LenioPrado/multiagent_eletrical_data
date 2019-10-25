package agents.phd.unifei.information;

import agents.phd.unifei.services.ElectricalDataServiceClient;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ReceiverBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = 7412589632147896321L;

	@Override
	public void action() {
		ACLMessage aclMessage = myAgent.receive();
		if (aclMessage != null) {
			String content = aclMessage.getContent();
			//TODO: Verificar como encontrar o 'placeId'
			saveMeasurementToDatabase(1, content);			
		} 
		block();
	}
	
	private boolean saveMeasurementToDatabase(int placeId, String measurement) {
		try {
			ElectricalDataServiceClient electricalDataService = new ElectricalDataServiceClient();
			return electricalDataService.insertMeasurement(placeId, measurement);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
