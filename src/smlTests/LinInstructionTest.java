package smlTests;

import static org.junit.Assert.*;

import org.junit.Test;

import sml.LinInstruction;
import sml.Machine;
import sml.Registers;

public class LinInstructionTest {
	
	private int value = 4;
	private int s1 = 2;
	LinInstruction li = new LinInstruction("lin", value, s1);
	
	@Test
	public void executeTest(){
		Machine m = new Machine();
		Registers r = new Registers();
		m.setRegisters(r);
		
		// check getRegisters() and setRegister() methods.
		m.getRegisters().setRegister(4, 2);
		
		li.execute(m);
		
		int res = m.getRegisters().getRegister(4);
		assertTrue(res == 2); //checked the result
		assertTrue(m.getRegisters().getRegister(4) ==2); //checked the result was in the register.
	}
	
	@Test
	public void toStringTest() {
		String observed = li.toString();
		String expected =  "lin: lin register 4 value is 2";
		assertEquals(observed, expected);
	}

}
