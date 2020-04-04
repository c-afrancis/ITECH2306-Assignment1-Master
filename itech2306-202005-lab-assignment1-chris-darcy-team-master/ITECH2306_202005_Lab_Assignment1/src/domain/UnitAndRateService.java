package domain;

/**
 * @author Takeogh
 *
 */
public class UnitAndRateService extends ServiceType {
	
	private int unit;
	private double rate;
	
	public UnitAndRateService(String description, int unit, double rate) {
		super(description);
		this.setUnit(unit);
		this.setRate(rate);
	}

	@Override
	protected double calculateChargeForServiceType() {
		return getUnit() * getRate();
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return super.toString() + "[unit=" + unit + ", rate=" + rate + "]";
	}
	
	

}
