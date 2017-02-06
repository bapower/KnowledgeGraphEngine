package cscie97.asn1.knowledge.engine;

import java.util.*;


/**
 * <h1>Knowledge Graph</h1>
 * <p>
 * The KnowledgeGraph program implements a simple knowledge graph engine. 
 * Knowledge graphs are an important method of capturing semantic information, 
 * and a core building block for the Semantic Web. A knowledge graph is a 
 * graph of nodes, where a node can be a subject, object or both. The links 
 * between nodes are predicates. Predicates are properties of Subjects that
 * connect Subjects to Objects.
 * 
 * @author Bry Power
 * @version 1.0
 * @since 2017-26-01
 * 
 */
public class KnowledgeGraph {
	
		// association for maintaining the active set of Nodes 
		private static Map<String, Node> nodeMap = new HashMap<String, Node>();
		
		// association for maintaining the active set of Predicates 
		private static Map<String, Predicate> predicateMap = new HashMap<String, Predicate>();
		
		// association for maintaining the active set of Predicates 
		private static Map<String, Triple> tripleMap = new HashMap<String, Triple>();
			
		// association for maintaining a fast query lookup map
		private static Map<String, Set<Triple>> queryMapSet = new HashMap<String, Set<Triple>>();
		
		// singleton instance of this class
		private static KnowledgeGraph instance = null;
		
		
		/**
		 * This method returns a reference to the single static 
		 * instance of the KnowledgeGraph
		 * @return knowledgeGraph instance
		 */
		protected KnowledgeGraph() {
		      // instantiation is protected
		}
		
		public static KnowledgeGraph getInstance() {
		      if(instance == null) {
		         instance = new KnowledgeGraph();
		      }
		      return instance;
	    }
				

		/**
		 * Public method for adding a Triple to the KnowledgeGraph.
		 * @param subject
		 * @param predicate
		 * @param obje2ct
		 */
		public static void importTriple(String subjectIdentifier, String predicateIdentifier, String objectIdentifier) {
	        Node subject = new Node(subjectIdentifier);
	        Node object = new Node(objectIdentifier);
	        Predicate predicate = new Predicate(predicateIdentifier);

	        Triple triple = new Triple(subjectIdentifier, predicateIdentifier, objectIdentifier);
	        
			nodeMap.put(subjectIdentifier.toLowerCase(), subject);
			nodeMap.put(objectIdentifier.toLowerCase().replace(".", ""), object);
			predicateMap.put(predicateIdentifier.toLowerCase(), predicate);
			tripleMap.put(triple.getIdentifier().toLowerCase().replace(".", ""), triple);
			
			addPermutationToQueryMapSet(subjectIdentifier+" "+predicateIdentifier+" "+objectIdentifier, triple);
			addPermutationToQueryMapSet(subjectIdentifier+" "+predicateIdentifier+" ?", triple);
			addPermutationToQueryMapSet(subjectIdentifier+" ? "+objectIdentifier, triple);
			addPermutationToQueryMapSet(subjectIdentifier+" ? ?", triple);
			addPermutationToQueryMapSet("? "+predicateIdentifier+" "+objectIdentifier, triple);
			addPermutationToQueryMapSet("? "+predicateIdentifier+" ?", triple);
			addPermutationToQueryMapSet("? ? "+objectIdentifier, triple);
			addPermutationToQueryMapSet("? ? ?", triple);
			
	    }
		
		private static void addPermutationToQueryMapSet (String permutation, Triple triple) {
			if(!queryMapSet.containsKey(permutation.toLowerCase().replace(".", ""))) {
				Set<Triple> tripleSet = new HashSet<Triple>();
				tripleSet.add(triple);
				queryMapSet.put(permutation.toLowerCase().replace(".", ""), tripleSet);
			} else {
				Set<Triple> tripleSet = queryMapSet.get(permutation.toLowerCase().replace(".", ""));
				if(!tripleSet.contains(triple)){
					tripleSet.add(triple);
				}
				queryMapSet.put(permutation.toLowerCase().replace(".", ""), tripleSet);	
			}
		}
		
		// returns a set of matching triples or an empty set if none found

		public Set<Triple> executeQuery(String subject, String predicate, String object) {
			String key = subject.toLowerCase()+" "+predicate.toLowerCase()+" "+object.toLowerCase().replace(".", "");
			Set<Triple> tripleSet = queryMapSet.get(key);
	        return ((tripleSet == null) ? new HashSet<Triple>() : tripleSet);
	    }
			
		/**
		 * 
		 * @param identifier
		 * @return Node
		 */
		Node getNode (String identifier){
			Node node = nodeMap.get(identifier.toLowerCase());
			return ((node == null) ? new Node(identifier.toLowerCase()) : node);
		}
		
		/**
		 * 
		 * @param identifier
		 * @return Predicate
		 */
		Predicate getPredicate (String identifier){
			Predicate predicate = predicateMap.get(identifier.toLowerCase());
			return ((predicate == null) ? new Predicate(identifier.toLowerCase()) : predicate);
		}
		
		/**
		 * 
		 * @param identifier
		 * @return Triple
		 */
		Triple getTriple (Node subject, Predicate predicate, Node object){
			String identifier = subject.getIdentifer()+" "+predicate.getIdentifer()+" "+object.getIdentifer();
			Triple triple = tripleMap.get(identifier.toLowerCase());
			return ((triple == null) ? new Triple(subject.getIdentifer(), predicate.getIdentifer(), object.getIdentifer()) : triple);
		}
}
