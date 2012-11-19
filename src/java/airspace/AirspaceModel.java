package airspace;

import java.util.logging.Logger;
import jason.environment.grid.Location;

public class AirspaceModel {
	public AircraftModel fighter;
	public AircraftModel target;
	private Logger logger = Logger.getLogger("testenv.mas2j."+AirspaceEnv.class.getName());
	
	public Location fighterLocation = new Location(0,-100);
	public Location targetLocation = new Location(0, 100);
	long timeAtStart = System.currentTimeMillis();;
	long lastTime = timeAtStart;
	
	public AirspaceModel() {
		timeAtStart = System.currentTimeMillis();
		fighter = new AircraftModel(180, 200);
		target = new AircraftModel(180, 200);
	}

	boolean recomputePositions() {
		long currentTime = System.currentTimeMillis();
		long change = currentTime - lastTime;
		long seconds = change / 1000;
		if (seconds >= 1) {
			fighter.workControls();
			logger.info("fighter heading = " + fighter.heading);
			int thirdAngle = 180 - fighter.heading - 90;
			double fighterX = (fighter.speed * Math.sin(Math.toRadians(thirdAngle))) / Math.sin(Math.toRadians(90));
			double fighterY = (fighter.speed * Math.sin(Math.toRadians(fighter.heading))) / Math.sin(Math.toRadians(90));

			fighterLocation.x += fighterX;
			fighterLocation.y += fighterY;
			targetLocation.x += target.speed;
	
			// ted udelat tolik jednotlivych cyklu kolik casu uplynulo
			logger.info("steps= " + seconds + ", change= " + change + ", current= " + currentTime);
			// nastavit posledni cas
	
			lastTime = currentTime;
		}
		return true;
	}
}
