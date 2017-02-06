package cscie97.asn1.knowledge.engine;

import java.time.Instant;

/**
 * The Triple class represents a unique Triple (Subject, Predicate, Object)
 * within the KnowledgeGraph. A Triple contains 3 references: Subject, 
 * Predicate and Object. The Triple is uniquely identified as the concatenation 
 * of the identifiers for the associated Subject, Predicate and Object. 
 *
 */
public class Triple {

	final private String identifier; 	
	final private long createDate; 
	
	final public Node subject;
	final private Predicate predicate;
	final private Node object;
	
	public Triple(String subject, String predicate, String object) {
		 if(subject == null){
			 throw new IllegalArgumentException("Invalid Subject.");
		 }
		 if(predicate == null){
			 throw new IllegalArgumentException("Invalid Predicate.");
		 }
		 if(object == null){
			 throw new IllegalArgumentException("Invalid Object.");
		 }
		 this.identifier = subject+" "+predicate+" "+object;
		 this.createDate = Instant.now().getEpochSecond(); 
		 
		 this.subject = new Node(subject);
		 this.predicate = new Predicate(predicate);
		 this.object = new Node(object);
	}
	
	/**
	 * Method to return the Triple identifier.
	 * @return String
	 */
	public String getIdentifier(){
		return this.identifier;
	}
	
	/**
	 * Method to return the Triple createDate.
	 * @return long
	 */
	public long getCreateDate(){
		return this.createDate;
	}
}
