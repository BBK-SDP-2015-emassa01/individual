package smlTests;

import static org.junit.Assert.*;

import org.junit.Test;

import sml.AddInstruction;

public class AddInstructionTest {
	
	private int result = 9;
	private int s1 = 2;
	private int s2 = 7;
	
	@Test
	public void toStringTest() {
		AddInstruction addInstructionTest = new AddInstruction("add", result, s1, s2);
		String observed = addInstructionTest.toString();
		String expected =  "add: add 2 + 7 to 9";
		assertEquals(observed, expected);
	}

}
