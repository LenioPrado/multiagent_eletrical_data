package agents.phd.unifei.information;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class SenderAgent extends Agent {
	
	private static final long serialVersionUID = 9876543219632581L;
	
	private static int TIMER_PERIOD = 2000;
	private static int filePosition = 1;
	private List<String> fileLines;
	
	protected void setup() {
		System.out.println("Hello! Sender Agent " + getAID().getName() + " is ready.");
		loadFile();
		addBehaviour(new TickerBehaviour(this, TIMER_PERIOD) {			

			private static final long serialVersionUID = 45781296857432L;

			protected void onTick() {				
				//System.out.println("Trying to find receivers...");
				AID[] receivers = getReceiverAgents();				
				if (receivers.length > 0) {					
					String lineToSend = getLine();
					if (lineToSend == null) {
						myAgent.doDelete();
					} 
					System.out.println(String.format("Sending line %d: %s", (filePosition++), lineToSend));
					SenderBehaviour sender = new SenderBehaviour(receivers, lineToSend);
					addBehaviour(sender);
					
				} else {
					System.out.println("No Receivers found...");
				}
			}
		} );
	}
	
	private void loadFile() {
		String fullPath = "E:\\Develop\\electricaldata\\ImportedData\\1\\CERIn-Data-Values.csv";
		fileLines = getFileLines(fullPath);
	}

	private String getLine() {
		if (filePosition < fileLines.size()) {
			return fileLines.get(filePosition);				
		}
		return null;
	}
	
	public static List<String> getFileLines(String fullPath) {
		try {			
		    
			return Files.readAllLines(Paths.get(fullPath), StandardCharsets.ISO_8859_1);			
		} catch(IOException e) {
			e.printStackTrace();
			throw new WebApplicationException(e);	    		
    	} 
	}
	
	private AID[] getReceiverAgents() {		
		AID[] receivers = null;
		ServiceDescription sd = new ServiceDescription();
		sd.setType("receiver");
		
		DFAgentDescription df = new DFAgentDescription();
		df.addServices(sd);
		
		try {
			DFAgentDescription[] results = DFService.search(this, df);
			receivers = new AID[results.length];
			for (int i=0;i<results.length;i++) {
				System.out.println("Receiver found: "+ results[i].getName());
				receivers[i] = results[i].getName();	
			}
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}
		return receivers;
	}
	
	protected void takeDown() {
		System.out.println("Sender-agent " + getAID().getName() + " terminating.");
	}
}
