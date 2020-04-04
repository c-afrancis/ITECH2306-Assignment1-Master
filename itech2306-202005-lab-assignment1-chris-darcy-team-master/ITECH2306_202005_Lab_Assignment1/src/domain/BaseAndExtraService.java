package domain;

/**
 * @author Takeogh
 * 
 */
public class BaseAndExtraService extends ServiceType {

	private double base;
	private double extra;
	
	public BaseAndExtraService(String description, double base, double extra) {
		super(description);
		this.setBase(base);
		this.setExtra(extra);
	}

	@Override
	protected double calculateChargeForServiceType() {
		return getBase() + getExtra();
	}

	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}

	public double getExtra() {
		return extra;
	}

	public void setExtra(double extra) {
		this.extra = extra;
	}

	@Override
	public String toString() {
		return super.toString() + "[base=" + base + ", extra=" + extra + "]";
	}

}
