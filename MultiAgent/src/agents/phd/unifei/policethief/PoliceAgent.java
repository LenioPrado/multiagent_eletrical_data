package agents.phd.unifei.policethief;

import jade.core.Agent;

public class PoliceAgent extends Agent{
    
	@Override
    public void setup()
    {
        System.out.println("Police agent name is: " +getAID().getName());
    }
}       
