package airspace;

public class AircraftModel {
	public int speed = 200;
	public int heading = 0;
	public int intendedHeading = 0;
	public int intendedSpeed = 200;
	
	int max_turn_rate_per_second = 5;
	int max_speed_change_per_second = 5;
	
	public AircraftModel() {
	}
	
	public AircraftModel(int heading_input, int speed_input) {
		speed = speed_input;
		heading = heading_input;
	}
	
	boolean workControls() {
		changeHeading(intendedHeading);
		changeSpeed();
		return true;
	}
	
	boolean changeSpeed() {
		if (speed != intendedSpeed) {
			if (Math.abs(intendedSpeed - speed) > max_speed_change_per_second) {
				if (speed > intendedSpeed) {
					speed -= max_speed_change_per_second;
				} else {
					speed += max_speed_change_per_second;
				}
			} else {
				speed = intendedSpeed;
			}
		}
		return true;
	}
	
	boolean changeHeading(int target_heading) {
		if (heading != target_heading) {
			if (Math.abs(target_heading - heading) > max_turn_rate_per_second) {
				turn(max_turn_rate_per_second);
			} else {
				heading = target_heading;
			}
		}
		return true;
	}
	
	boolean turn(int ammount) {
		heading = heading + ammount;
		if (heading < 0) {
			heading = 360 - heading;
		}
		if (heading > 360) {
			heading = heading - 360;
		}
		return true;
	}
}
