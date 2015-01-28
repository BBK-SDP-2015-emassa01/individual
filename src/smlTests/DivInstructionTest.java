package smlTests;

import static org.junit.Assert.*;

import org.junit.Test;

import sml.DivInstruction;
import sml.Machine;
import sml.Registers;

public class DivInstructionTest {

	public int result = 2;
	public int s1 = 10;
	public int s2 = 5;
	
	public Machine m = new Machine();
	public Registers r = new Registers();
	
	
	@Test
	public void executeTest(){
		Machine m = new Machine();
		Registers r = new Registers();
		m.setRegisters(r);
		
		m.getRegisters().setRegister(10, 10);// check getRegisters() and setRegister() methods.
		m.getRegisters().setRegister(5, 5);
		
		DivInstruction ai = new DivInstruction("div", 2, 10, 5);
		ai.execute(m);
		
		int res = m.getRegisters().getRegister(2);
		System.out.println(res); //printed result to screen
		assertTrue(res == 2); //checked the result
		assertTrue(m.getRegisters().getRegister(2) ==2); //checked the result was in the register.
	}
	
	@Test
	public void toStringTest() {
		DivInstruction addInstructionTest = new DivInstruction("div", result, s1, s2);
		String observed = addInstructionTest.toString();
		String expected =  "div: div 10 / 5 to 2";
		assertEquals(observed, expected);
	}

}
