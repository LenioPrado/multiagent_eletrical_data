package agents.phd.bookexamples;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;

public class WakerAgent extends Agent {
	
	protected void setup() {
		System.out.println("Adding waker behaviour");
		
		addBehaviour(new WakerBehaviour(this, 10000) {
			protected void onWake() {
				System.out.println("OnWake Called");
			}
		});
	}
}
