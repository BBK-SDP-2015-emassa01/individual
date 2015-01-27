package smlTests;

import static org.junit.Assert.*;

import org.junit.Test;

import sml.DivInstruction;
import sml.Machine;
import sml.Registers;

public class DivInstructionTest {

	public int result = 9;
	public int s1 = 2;
	public int s2 = 7;
	
	public int one = 1;
	public int two = 2;
	
	public Machine m = new Machine();
	public Registers r = new Registers();
//	r.getRegisters().setRegister(one, s1);
	//m.setRegisters(registers);
	
	
	@Test
	public void executeTest() {
		DivInstruction divInstructionTest = new DivInstruction("div", result, s1, s2);
		divInstructionTest.execute(m);
		System.out.println(m.getRegisters());
		
	}

}
