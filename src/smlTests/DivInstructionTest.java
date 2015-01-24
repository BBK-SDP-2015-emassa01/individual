package smlTests;

import static org.junit.Assert.*;

import org.junit.Test;

import sml.DivInstruction;
import sml.Machine;

public class DivInstructionTest {

	private int result = 9;
	private int s1 = 2;
	private int s2 = 7;
	
	private Machine m = new Machine();
	
	@Test
	public void toStringTest() {
		DivInstruction divInstructionTest = new DivInstruction("div", result, s1, s2);
		divInstructionTest.execute(m);
		
	}

}
