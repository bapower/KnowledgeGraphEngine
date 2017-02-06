package cscie97.asn1.knowledge.engine;

import java.time.Instant;

/**
 * The Node class represents instances of Subjects and Objects. 
 * A Node has a unique String identifier (e.g. �Bill�, or �Ultimate_Frisbee�). 
 * Note that a single instance of a Node can represent both a Subject and 
 * an Object within the Knowledge Graph.
 * 
 */
public class Node {

	final private String identifier; 	
	final private long createDate; 	
	
	public Node (String identifier) {
		this.identifier = identifier;
		this.createDate = Instant.now().getEpochSecond();
	}
	
	/**
	 * This method returns the Node identifier
	 * @return String
	 */
	public String getIdentifer() {
		return this.identifier;
    }
	

	/**
	 * This method returns the Node createDate
	 * @return long
	 */
	public long getCreateDate() {
		return this.createDate;
    }
}
