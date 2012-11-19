package airspace;

import jason.asSyntax.*;
import jason.environment.*;
import java.util.*;
import java.util.logging.*;
import jason.environment.grid.Location;

public class AirspaceEnv extends Environment {

  private Logger logger = Logger.getLogger("testenv.mas2j."+AirspaceEnv.class.getName());
  public AirspaceGUI gui;
  AirspaceModel model;

  public AirspaceEnv() {
	  addPercept(Literal.parseLiteral("max_speed(300)"));
	  addPercept(Literal.parseLiteral("min_speed(120)"));
	  addPercept(Literal.parseLiteral("max_turn_speed(5)"));
	  gui = new AirspaceGUI(this);  
  }
  
  /** Called before the MAS execution with the args informed in .mas2j */
  @Override
  public void init(String[] args) { 
	  model = new AirspaceModel();
	  updatePercepts("fighter");
	  updatePercepts("target");
  }

  @Override
  public boolean executeAction(String agName, Structure action) {
    if (action.getFunctor().equals("burn")) {
      addPercept(agName, Literal.parseLiteral("fire"));
    } else if (action.getFunctor().equals("radarsweep")) {
        updatePercepts(agName);
    // CHANGING HEADING
    } else if (action.getFunctor().equals("heading")) {
    	String newHeading = action.getTerm(0).toString();
    	if (agName == "fighter") {
    		model.fighter.intendedHeading = Integer.parseInt(newHeading);
    	} else if (agName == "target") {
    		model.target.intendedHeading = Integer.parseInt(newHeading);
    	}
    // CHANGING SPEED
    } else if (action.getFunctor().equals("speed")) {
    	String newSpeed = action.getTerm(0).toString();
    	if (agName == "fighter") {
    		model.fighter.intendedSpeed = Integer.parseInt(newSpeed);
    	} else if (agName == "target") {
    		model.target.intendedSpeed = Integer.parseInt(newSpeed);
    	}
    } else {
      logger.info("executing: "+action+", but not implemented!");
    }

    informAgsEnvironmentChanged();
    try { Thread.sleep(100); } catch (Exception e) {}
    return true;
  }
  
  private void updatePercepts(String agent) {
      clearPercepts(agent);
      
	  model.recomputePositions();
	  Location fighterLocation = model.fighterLocation;
      addPercept(agent, Literal.parseLiteral("location(" + fighterLocation.x + "," + fighterLocation.y + ")"));
   
      addPercept(agent, Literal.parseLiteral("heading(" + model.fighter.heading + ")"));
      
      addPercept(agent, Literal.parseLiteral("speed(" + model.fighter.speed + ")"));
  }


  /** Called before the end of MAS execution */
  @Override
  public void stop() {
    super.stop();
  }
}
