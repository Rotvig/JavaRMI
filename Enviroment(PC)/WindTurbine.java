import java.io.Serializable;


public class WindTurbine implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private float orientation;
	private float bladePitch;
	private float RPM;
	private float temperature;
	private float production;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getOrientation() {
		return orientation;
	}

	public void setOrientation(float orientation) {
		this.orientation = orientation;
	}

	public float getBladePitch() {
		return bladePitch;
	}

	public void setBladePitch(float bladePitch) {
		this.bladePitch = bladePitch;
	}

	public float getRPM() {
		return RPM;
	}

	public void setRPM(float rPM) {
		RPM = rPM;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getProduction() {
		return production;
	}

	public void setProduction(float production) {
		this.production = production;
	}
}
