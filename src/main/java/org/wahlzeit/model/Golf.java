package org.wahlzeit.model;

/*
 * The domain class describing a real world golf object.
 * 
 * Takes part in the client-service-collaboration as service with GolfPhoto.
 * Takes part in the base-type-collaboration as base with GolfType.
 * Takes part in the manager-element-collaboration as element with GolfManager.
 */
public class Golf {
	private GolfManager manager = GolfManager.getInstance();
	private GolfType type;
	int parScore;
	
	public Golf(GolfType type, int parScore) {
		if (type == null) {
			throw new IllegalArgumentException("Type can not be null");
		}
		if (parScore <= 0) { 
			throw new IllegalArgumentException("Par score must be positive");
		}
		
		assert parScore > 0;
		
		this.type = type;
		this.parScore = parScore;
	}
	
	public GolfManager getManager() {
		return manager;
	}
	public void setManager(GolfManager manager) {
		this.manager = manager;
	}
	
	public GolfType getType() {
		return type;
	}
	public void setType(GolfType type) {
		this.type = type;
	}
	
	public int getParScore() {
		return parScore;
	}

}
