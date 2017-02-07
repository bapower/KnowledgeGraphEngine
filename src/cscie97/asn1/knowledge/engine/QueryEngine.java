package cscie97.asn1.knowledge.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import cscie97.asn1.exception.QueryEngineException;

/**
 * The QueryEngine class supports the execution of Knowledge 
 * Graph queries. Queries are specified as Triples in N-Triple 
 * format with the special "?" identifier representing query 
 * or wild card. 
 *
 */
public class QueryEngine {
	/**
	 * Public method for executing a single query on the
	 * knowledge graph. Checks for non null and well formed
	 * query string. Throws QueryEngineException on error.
	 * @param query	the individual query to execute 
	 * @throws QueryEngineException 
	 */
	public static void executeQuery(String query) throws QueryEngineException {
		System.out.println(query);
		KnowledgeGraph knowledgeGraph = KnowledgeGraph.getInstance();
		query = query.replace(".", "").toLowerCase();
		String words[] = query.split("\\s+");
       
		if (words.length != 3) {
			if (words.length == 0) {
				QueryEngineException queryEngineException = new QueryEngineException(query, "Query file contains blank item");
				System.out.println("QueryEngineException for query "+queryEngineException.getQuery()+": "+queryEngineException.getMessage());
			} else if (words.length < 3) {
				QueryEngineException queryEngineException = new QueryEngineException(query, "A query in the file contains blank values");
				throw queryEngineException;
			} else {
				QueryEngineException queryEngineException = new QueryEngineException(query, "A query in the file contains too many values");
				throw queryEngineException;
			}
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
	 * @param fileName	the name of the query input file
	 * @throws IOException 
	 * @throws QueryEngineException 
	 */
	public static void executeQueryFile(String fileName) throws IOException, QueryEngineException {
		// Checks to assure file name is valid
		File file = new File(fileName);
		if(! file.exists()){
			QueryEngineException queryEngineException = new QueryEngineException ("", "Query file "+fileName+" does not exist");
			throw queryEngineException;
		}
		
		try (BufferedReader br = new BufferedReader (new FileReader (fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				executeQuery(line);
			}
		} catch (IOException e) {
			throw e;
		}
				
    }
	
}
