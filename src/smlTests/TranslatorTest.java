package smlTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import sml.*;

public class TranslatorTest {

	Machine m = new Machine();
	Registers r = new Registers();
	ArrayList<Instruction> prog = new ArrayList<>();
	
	Labels lab = new Labels();
	
	@Before
	public void initialise(){
		m.setRegisters(r);
		m.getRegisters().setRegister(3, 3);// check getRegisters() and setRegister() methods.
		m.getRegisters().setRegister(4, 4);
		m.getRegisters().setRegister(5, 5);
		
		AddInstruction ai = new AddInstruction("add", 5, 3, 4); //create some instructions
		SubInstruction ai2 = new SubInstruction("sub", 6, 8, 11);
		DivInstruction ai3 = new DivInstruction("div",7, 12, 13);
		MulInstruction ai4 = new MulInstruction("mul",9, 10, 14);
		
		prog.add(ai); //add the instructions to the program
		prog.add(ai2);
		prog.add(ai3);
		prog.add(ai4);	
		
		m.setProg(prog);
	}
	
	@Test 
	public void readAndTranslateTest() {
		Translator t = new Translator("testAdd.txt");
		t.readAndTranslate(lab, prog);
//		assertNotNull (t.getProgram()); //the program is not null and it is 3 lines long
		assertEquals (3, t.getProgram().size());
	}
	
	@Test
	public void readAndTranslateTest2() {
		Translator t = new Translator("doesntExist.txt");
		t.readAndTranslate(lab, prog);
		//IOException caught so not thrown
		assertNull (t.getProgram());
	}
	
	@Test
	public void scanTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Translator t = new Translator("testAdd.txt");
		Class<?> c = t.getClass();
		Method method = c.getDeclaredMethod("scan");
		method.setAccessible(true);
		method.invoke(t);
		
		String line = "f0 lin 10 6";
		t.setLine(line);
		System.out.println(t);
	
	}
	
	public void scanIntTest(){
		
		
	}

}
