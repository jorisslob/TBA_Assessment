package nl.jslob.tba.assessment.model;

public interface Harbor {

	public abstract void add(HarborLocation entry);
	public abstract String showSchedule();
	public abstract boolean isActive();
	public abstract void nextStep();
}
