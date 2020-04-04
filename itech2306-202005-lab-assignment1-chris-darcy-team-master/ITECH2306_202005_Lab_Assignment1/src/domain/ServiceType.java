package domain;

/**
 * @author Takeogh
 *
 */
public abstract class ServiceType {
	private String description;
	public ServiceType(String description) {
		this.setDescription(description);
	}
	
	protected abstract double calculateChargeForServiceType();

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ServiceType [description=" + description + "]";
	}

}
