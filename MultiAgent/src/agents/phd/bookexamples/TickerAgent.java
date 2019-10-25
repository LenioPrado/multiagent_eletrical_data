package agents.phd.bookexamples;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class TickerAgent extends Agent {

	protected void setup() {
		System.out.println("Adding ticker behaviour");
		
		addBehaviour(new TickerBehaviour(this, 10000) {
			protected void onTick() {
				System.out.println("OnTick");
			}
		});
	}
}
