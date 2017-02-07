package cscie97.asn1.knowledge.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.*;

import cscie97.asn1.exception.ImportException;

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
	 * @throws ImportException 
	 * @throws IOException 
	 */
	public static void importTripleFile(String fileName) throws ImportException, IOException {

		File file = new File(fileName);
		if(!file.exists()){
			ImportException importException = new ImportException(0, "", "Input file "+fileName+" does not exist");
			throw importException;
		}

		try (BufferedReader br = new BufferedReader (new FileReader (fileName))) {
			String line;
			int lineNumber = 1;
			while ((line = br.readLine()) != null) {
				// Parses each line to determine what is the subject, predicate, and the object
				StringTokenizer token = new StringTokenizer(line);
				String subjectInput = null;
				String predicateInput = null;
				String objectInput = null;
				
				if(token.hasMoreTokens())
					subjectInput = token.nextToken().trim();
				if(token.hasMoreTokens())
					predicateInput = token.nextToken().trim();
				if(token.hasMoreTokens())
					objectInput = token.nextToken().trim();
				if(token.hasMoreTokens()) {
					ImportException importException = new ImportException(lineNumber, line, "A triple in the file contains too many values");
					throw importException;
				}
				
				if (predicateInput == null && predicateInput == null && objectInput == null) {
					ImportException importException = new ImportException(lineNumber, line, "Triple file contains blank line");
					System.out.println("ImportException on line "+importException.getLineNumber()+": "+importException.getMessage());
				}else if (predicateInput == null || predicateInput == null || objectInput == null) {
					ImportException importException = new ImportException(lineNumber, line, "A triple in the file contains too few values");
					throw importException;
				} else {
					KnowledgeGraph.importTriple(subjectInput, predicateInput, objectInput);
				}
				
				lineNumber++;
			}
		} catch (IOException e) {
			throw e;
		}
	}
}
