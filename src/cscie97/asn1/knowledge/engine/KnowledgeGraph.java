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
			
}
