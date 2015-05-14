package models;
import java.io.Serializable;


public class Enviroment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6937127943926624281L;
	private float windSpeed;
	private float windDirection;

	public float getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(float windSpeed) {
		this.windSpeed = windSpeed;
	}

	public float getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(float windDirection) {
		this.windDirection = windDirection;
	}
}
