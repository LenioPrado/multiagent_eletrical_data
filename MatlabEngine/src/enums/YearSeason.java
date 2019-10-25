package enums;

public enum YearSeason {
	SUMMER (1),
	WINTER (2);
	
	private final int season;
	
	private YearSeason(int season) {
		this.season = season;
	}
	
	public int getValue() {
		return this.season;
	}
}