package airspace;

import java.util.logging.Logger;

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
			int thirdAngle = 180 - fighter.heading - 90;
			double y = (fighter.speed * Math.sin(Math.toRadians(fighter.heading))) / Math.sin(Math.toRadians(90));
			double x = (fighter.speed * Math.sin(Math.toRadians(thirdAngle))) / Math.sin(Math.toRadians(90));
			logger.info("x = " + x + ", y = " + y);
			fighterLocation.x += fighter.speed;
			targetLocation.x += target.speed;
	
			// ted udelat tolik jednotlivych cyklu kolik casu uplynulo
			logger.info("steps= " + seconds + ", change= " + change + ", current= " + currentTime);
			// nastavit posledni cas
	
			lastTime = currentTime;
		}
		return true;
	}
}
