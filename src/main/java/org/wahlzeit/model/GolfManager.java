package org.wahlzeit.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.wahlzeit.services.ObjectManager;

public class GolfManager extends ObjectManager {
 	private static final Logger log = Logger.getLogger(GolfManager.class.getName());

	private static final GolfManager instance = new GolfManager();

	private Set<Golf> golfs = new HashSet<>();
	private Map<String, GolfType> golfTypes = new HashMap<>();

	/**
	 * @methodtype constructor
	 * the constructor is private, the singleton instance has to be used
	 */
	private GolfManager() {}

	/**
	 * @methodtype getter
	 */
	public static GolfManager getInstance() {
		return instance;
	}

	public GolfType getGolfType(String typeName) {
		if (golfTypes.containsKey(typeName)) {
			return golfTypes.get(typeName);
		} else {
			GolfType golfType = new GolfType(typeName);
			golfTypes.put(typeName, golfType);
			return golfType;
		}
	}

	public Golf createGolf(String typeName, int parScore) {
		GolfType golfType = getGolfType(typeName);
		Golf golf = golfType.createInstance(parScore);
		golfs.add(golf);
		return golf;
	}

}
