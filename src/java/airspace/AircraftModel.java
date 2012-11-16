package airspace;

public class AircraftModel {
	public int speed = 200;
	public int heading = 0;
	public int intendedHeading = 0;
	
	int max_turn_rate_per_second = 5;
	int max_speed_change_per_second = 5;
	
	public AircraftModel() {
	}
	
	public AircraftModel(int heading_input, int speed_input) {
		speed = speed_input;
		heading = heading_input;
	}
	
	boolean change_heading(int target_heading) {
		if (heading != target_heading) {
			if (Math.abs(target_heading - heading) > max_speed_change_per_second) {
				turn(max_speed_change_per_second);
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
