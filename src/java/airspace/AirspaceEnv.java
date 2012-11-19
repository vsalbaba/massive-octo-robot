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
	  updatePercepts();
  }

  @Override
  public boolean executeAction(String agName, Structure action) {
    if (action.getFunctor().equals("burn")) {
      addPercept(agName, Literal.parseLiteral("fire"));
    } else if (action.getFunctor().equals("radarsweep")) {
    } else if (action.getFunctor().equals("heading")) {

    	String newHeading = action.getTerm(0).toString();
    	model.fighter.intendedHeading = Integer.parseInt(newHeading);
    	logger.info("new heading " + newHeading);
    } else {
      logger.info("executing: "+action+", but not implemented!");
    }
    
    updatePercepts();
    try { Thread.sleep(100); } catch (Exception e) {}
    return true;
  }
  
  private void updatePercepts() {
      clearPercepts("fighter");
      clearPercepts("target");
      
	  model.recomputePositions();
	  Location fighterLocation = model.fighterLocation;
      addPercept("fighter", Literal.parseLiteral("location(" + fighterLocation.x + "," + fighterLocation.y + ")"));

      Location targetLocation = model.targetLocation;
      addPercept("target", Literal.parseLiteral("location(" + targetLocation.x + "," + targetLocation.y + ")"));
      
      addPercept("fighter", Literal.parseLiteral("heading(" + model.fighter.heading + ")"));
      addPercept("target", Literal.parseLiteral("heading(" + model.target.heading + ")"));
      
      addPercept("fighter", Literal.parseLiteral("speed(" + model.fighter.speed + ")"));
      addPercept("target", Literal.parseLiteral("heading(" + model.target.speed + ")"));
  }


  /** Called before the end of MAS execution */
  @Override
  public void stop() {
    super.stop();
  }
}
