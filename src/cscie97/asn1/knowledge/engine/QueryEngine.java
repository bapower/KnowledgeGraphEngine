package cscie97.asn1.knowledge.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * The QueryEngine class supports the execution of Knowledge 
 * Graph queries. Queries are specified as Triples in N-Triple 
 * format with the special �?� identifier representing query 
 * or �wild card�. 
 *
 */
public class QueryEngine {
	/**
	 * Public method for executing a single query on the
	 * knowledge graph. Checks for non null and well formed
	 * query string. Throws QueryEngineException on error.
	 * @param query 
	 */
	public static void executeQuery(String query) {
		System.out.println(query);
		KnowledgeGraph knowledgeGraph = KnowledgeGraph.getInstance();
		query = query.replace(".", "").toLowerCase();
		String words[] = query.split("\\s+");
       
		if (words.length != 3) {
    	  //Throw QueryEngineException
		}
       
		Set<Triple> results = knowledgeGraph.executeQuery(words[0], words[1], words[2]);
		if(results.isEmpty()) {
    	   System.out.println("<null>");
		}else {
    	   Iterator<Triple> iterator = results.iterator();
    	   while(iterator.hasNext()){ 
    		   System.out.println(iterator.next().getIdentifier());
    	   }
       }
       
    }
	
	/**
	 * Public method for executing a set of queries read from a
	 * file. Checks for valid filename. Delegates to executeQuery 
	 * for processing individual queries. Throws QueryEngineException 
	 * on error.
	 * @param fileName 
	 */
	public static void executeQueryFile(String fileName) {
		// Checks to assure file name is valid
		File file = new File(fileName);
		if(! file.exists()){
			// Throw QueryEngineException
		}
		
		try (BufferedReader br = new BufferedReader (new FileReader (fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				executeQuery(line);
			}
		} catch (IOException e) {
			// Add a QueryEngineException
			e.printStackTrace();
		}
				
    }
	
}
