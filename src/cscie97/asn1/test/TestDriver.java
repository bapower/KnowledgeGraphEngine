package cscie97.asn1.test;

import java.io.IOException;

import cscie97.asn1.knowledge.engine.Importer;
import cscie97.asn1.knowledge.engine.QueryEngine;
import cscie97.asn1.exception.ImportException;
import cscie97.asn1.exception.QueryEngineException;

/**
 * 
 * Class used for testing the program.
 *
 */

public class TestDriver {
	public static void main(String args[]) throws ImportException, IOException, QueryEngineException {
		Importer.importTripleFile(args[0]);
		QueryEngine.executeQueryFile(args[1]);	
	}
}
