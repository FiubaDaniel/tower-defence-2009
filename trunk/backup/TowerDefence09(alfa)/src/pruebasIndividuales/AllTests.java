package pruebasIndividuales;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.JUnit4TestAdapter;
import pruebasIntegradas.*;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for logica");
		// $JUnit-BEGIN$
		suite.addTestSuite(JugadorTest.class);
		suite.addTestSuite(EscenarioTest.class);
		suite.addTestSuite(PosicionTest.class);
		suite.addTestSuite(PegoteTest.class);
		suite.addTestSuite(ArenaTest.class);
		suite.addTestSuite(pruebaTorreBlanca.class);
		suite.addTestSuite(pruebaTorrePlateada.class);
		suite.addTestSuite(pruebaTorreDorada.class);
		suite.addTest(new JUnit4TestAdapter(EnemigoTest.class));
		suite.addTest(new JUnit4TestAdapter(EnemigoYEscenarioTest.class));
		// $JUnit-END$
		return suite;
	}

}
