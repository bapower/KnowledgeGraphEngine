package cscie97.asn1.knowledge.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.*;

/**
 * The Importer class is responsible for reading triples 
 * from input files using N-Triple format.
 * 
 */
public class Importer {
	/**
	 * Public method for importing triples from N_Triple
	 * formatted file into the KnowledgeGraph. Checks for 
	 * valid input file  name. Throws ImportException on 
	 * error accessing or processing the input Triple File.
	 * @param fileName - Should be valid .nt file containing triples.
	 */
	public void importTripleFile(String fileName) {

		File file = new File(fileName);
		if(! file.exists()){
			// Throw ImportException
		}

		List<Triple> tripleList = new ArrayList<Triple>();

		try (BufferedReader br = new BufferedReader (new FileReader (fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				// Parses each line to determine what is the subject, predicate, and the object
				StringTokenizer token = new StringTokenizer(line);
				String subjectInput = null;
				String predicateInput = null;
				String objectInput = null;
				if(token.hasMoreTokens())
					subjectInput = token.nextToken();
					Node subject = new Node(subjectInput);
				if(token.hasMoreTokens())
					predicateInput = token.nextToken();
					Predicate predicate = new Predicate(predicateInput);
				if(token.hasMoreTokens())
					objectInput = token.nextToken();
					Node object = new Node(objectInput);

				Triple triple = new Triple(subjectInput, predicateInput, objectInput);
				tripleList.add(triple);
			    KnowledgeGraph.importTriple(tripleList);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
