package smlTests;

import static org.junit.Assert.*;

import org.junit.Test;

import sml.BnzInstruction;
import sml.Machine;

public class BnzInstructionTest {

	private int result = 9;
	private String register = "f0";
	private Machine m = new Machine();
	
	@Test
	public void executeTest() {
		BnzInstruction bnzInstructionTest = new BnzInstruction("bnz", result, register);
		String observed = bnzInstructionTest.execute(m);
		String expected =  "add: add 2 + 7 to 9";
		assertEquals(observed, expected);
	}

}
