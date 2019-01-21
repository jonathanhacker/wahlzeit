package org.wahlzeit.model;

public class GolfType {
	private GolfManager manager = GolfManager.getInstance();

	private String type;

	public GolfType(String type) {
		if (type == null) {
			throw new IllegalArgumentException("Type can not be null");
		}
		this.type = type;
	}

	public GolfManager getManager() {
		return manager;
	}

	/*
	 * @methodtype factory
	 */
	public Golf createInstance(int parScore) {
		if (parScore <= 0) {
			throw new IllegalArgumentException("Par score has to be positive");
		}
		return new Golf(this, parScore);
	}

	public String getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GolfType other = (GolfType) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
