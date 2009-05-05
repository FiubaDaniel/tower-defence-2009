package pruebasIndividuales;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.JUnit4TestAdapter;

public class AllTests {
		
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for logica");
		//$JUnit-BEGIN$
		suite.addTestSuite(JugadorTest.class);
		suite.addTestSuite(EscenarioTest.class);
		suite.addTest(new JUnit4TestAdapter(EnemigoTest.class));
		//$JUnit-END$
		return suite;
	}

	
	
}
