package smlTests;

import static org.junit.Assert.*;

import org.junit.Test;

import sml.AddInstruction;
import sml.Machine;
import sml.Registers;

public class AddInstructionTest {
	
	private int result = 9;
	private int s1 = 2;
	private int s2 = 7;	
	
	@Test
	public void executeTest(){
		Machine m = new Machine();
		Registers r = new Registers();
		m.setRegisters(r);
		
		m.getRegisters().setRegister(3, 3);// check getRegisters() and setRegister() methods.
		m.getRegisters().setRegister(4, 4);
		
		AddInstruction ai = new AddInstruction("add", 5, 3, 4);
		ai.execute(m);
		
		int res = m.getRegisters().getRegister(5);
		System.out.println(res); //printed result to screen
		assertTrue(res == 7); //checked the result
		assertTrue(m.getRegisters().getRegister(5) ==7); //checked the result was in the register.
	}
	
	@Test
	public void toStringTest() {
		AddInstruction addInstructionTest = new AddInstruction("add", result, s1, s2);
		String observed = addInstructionTest.toString();
		String expected =  "add: add 2 + 7 to 9";
		assertEquals(observed, expected);
	}

}
