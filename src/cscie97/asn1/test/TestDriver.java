package cscie97.asn1.test;

import cscie97.asn1.knowledge.engine.Importer;
import cscie97.asn1.knowledge.engine.QueryEngine;

/**
 * 
 * The TestDriver class implement a single static main() method
 * and is used for testing the program.
 *
 */
public class TestDriver {
	public static void main(String args[]) {
		Importer.importTripleFile(args[0]);
		QueryEngine.executeQueryFile(args[1]);	
	}
}
