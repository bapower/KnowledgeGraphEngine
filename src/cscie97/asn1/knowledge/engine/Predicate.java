package cscie97.asn1.knowledge.engine;

import java.time.Instant;

/**
 * 
 * The Predicate class represents the predicate portion of a Triple. 
 * Like Node, the Predicate includes a unique String identifier 
 * that uniquely identifies the predicate (e.g. �has_friend�).
 *
 */
public class Predicate {

	final private String identifier; 	
	final private long createDate; 	
	
	public Predicate (String identifier) {
		this.identifier = identifier;
		this.createDate = Instant.now().getEpochSecond();
	}
	
	/**
	 * This method returns the Predicate identifier
	 * @return String
	 */
	public String getIdentifer() {
		return this.identifier;
    }
	

	/**
	 * This method returns the Predicate createDate
	 * @return long
	 */
	public long getCreateDate() {
		return this.createDate;
    }
	
}
