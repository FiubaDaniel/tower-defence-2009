package logica;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for logica");
		//$JUnit-BEGIN$
		suite.addTestSuite(JugadorTest.class);
		suite.addTestSuite(EscenarioTest.class);
		//$JUnit-END$
		return suite;
	}

	
	
}
