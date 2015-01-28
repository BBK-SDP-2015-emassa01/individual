package smlTests;

import static org.junit.Assert.*;

import sml.Labels;
import smlExceptions.*;
import org.junit.Test;

public class LabelsTest {
	
	private Labels test = new Labels();
	
	
	@Test (expected = DuplicatesException.class)
	public void addLabelTest() {
		
		test.addLabel("1");
		test.addLabel("2");
		test.addLabel("3");
		test.addLabel("4");
		
		test.addLabel("1"); // throws DuplicatesException !!! YAY!

		
	}
	
	@Test
	public void addLabelTest2() {
		
		test.addLabel("1");
		test.addLabel("2");
		test.addLabel("3");
		test.addLabel("4");

		test.addLabel("5"); //should work
		int observed = test.indexOf("5");
		int expected = 4;
		
		assertEquals(expected, observed);
		
	}

}
