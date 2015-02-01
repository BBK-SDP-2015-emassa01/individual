package smlTests;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import sml.*;

public class TranslatorTest {

	Machine m = new Machine();
	Machine m2 = new Machine();
	Registers r = new Registers();
	Registers r2 = new Registers();
	ArrayList<Instruction> prog = new ArrayList<>();
	ArrayList<Instruction> prog2 = new ArrayList<>();
	
	Labels lab = new Labels();
	Labels lab2 = new Labels();
	
	@Before
	public void initialise(){
		m.setRegisters(r);
		m.getRegisters().setRegister(3, 3);// check getRegisters() and setRegister() methods.
		m.getRegisters().setRegister(4, 4);
		m.getRegisters().setRegister(5, 5);
		
		m2.setRegisters(r2);
		m2.getRegisters().setRegister(3, 3);// check getRegisters() and setRegister() methods.
		m2.getRegisters().setRegister(4, 4);
		m2.getRegisters().setRegister(5, 5);
		
		AddInstruction ai = new AddInstruction("add", 5, 3, 4); //create some instructions
		SubInstruction ai2 = new SubInstruction("sub", 6, 8, 11);
		DivInstruction ai3 = new DivInstruction("div",7, 12, 13);
		MulInstruction ai4 = new MulInstruction("mul",9, 10, 14);
		
		prog.add(ai); //add the instructions to the program
		prog.add(ai2);
		prog.add(ai3);
		prog.add(ai4);	
		
		m.setProg(prog);
		m2.setProg(prog2);
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
	
	@Test //private method - used reflection to test.
	public void scanTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Translator t = new Translator("testAdd.txt");
		t.setLabels(lab);
		t.setProgram(prog);
		t.setLine("f0 lin 10 6");
		
		Class<?> c = t.getClass();
		Method method = c.getDeclaredMethod("scan");
		method.setAccessible(true);
		String obs = (String) method.invoke(t);
		
		System.out.println(t.toString());
		String expected = "f0";
		assertEquals(expected, obs);

	}
	
	@Test //private method - used reflection to test.
	public void scanIntTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Translator t2 = new Translator("testAdd.txt");
		t2.setLabels(lab2);
		t2.setProgram(prog2);
		t2.setLine("10 6");
		
		Class<?> c = t2.getClass();
		Method method = c.getDeclaredMethod("scanInt");
		method.setAccessible(true);
		int obs = (Integer) method.invoke(t2);
		
		System.out.println(t2.toString());
		int expected = 10;
		assertEquals(expected, obs);
	}
	
	@Test
	public void getInstructionLinTest(){
		Translator t3 = new Translator("testLin.txt");
		Labels lab3 = new Labels();
		lab3.addLabel("f0");
		lab3.addLabel("f1");
		lab3.addLabel("f2");
		
		t3.setLabels(lab3);
		t3.setProgram(prog);
		t3.setLine("lin 10 6");
		
		Instruction obs = t3.getInstruction("lin");
		Instruction exp = new LinInstruction("lin", 10,6);
		
		assertEquals(exp,obs);
	}
	
	@Test
	public void getInstructionAddTest(){
		Translator t3 = new Translator("testAdd.txt");
		Labels lab3 = new Labels();
		lab3.addLabel("f0");
		lab3.addLabel("f1");
		lab3.addLabel("f2");
		
		t3.setLabels(lab3);
		t3.setProgram(prog);
		t3.setLine("add 13 10 11");
		
		Instruction obs = t3.getInstruction("add");
		Instruction exp = new AddInstruction("add", 13, 10, 11);
		
		assertEquals(exp,obs);
	}

	@Test
	public void getInstructionSubTest(){
		Translator t3 = new Translator("testSub.txt");
		Labels lab3 = new Labels();
		lab3.addLabel("f0");
		lab3.addLabel("f1");
		lab3.addLabel("f2");
		
		t3.setLabels(lab3);
		t3.setProgram(prog);
		t3.setLine("sub 13 10 11");
		
		Instruction obs = t3.getInstruction("sub");
		Instruction exp = new SubInstruction("sub", 13, 10, 11);
		
		assertEquals(exp,obs);
	}
	
	@Test
	public void getInstructionMulTest(){
		Translator t3 = new Translator("testMul.txt");
		Labels lab3 = new Labels();
		lab3.addLabel("f0");
		lab3.addLabel("f1");
		lab3.addLabel("f2");
		
		t3.setLabels(lab3);
		t3.setProgram(prog);
		t3.setLine("mul 13 10 11");
		
		Instruction obs = t3.getInstruction("mul");
		Instruction exp = new MulInstruction("mul", 13, 10, 11);
		
		assertEquals(exp,obs);
	}
	
	@Test
	public void getInstructionDivTest(){
		Translator t3 = new Translator("testDiv.txt");
		Labels lab3 = new Labels();
		lab3.addLabel("f0");
		lab3.addLabel("f1");
		lab3.addLabel("f2");
		
		t3.setLabels(lab3);
		t3.setProgram(prog);
		t3.setLine("div 13 10 11");
		
		Instruction obs = t3.getInstruction("div");
		Instruction exp = new DivInstruction("div", 13, 10, 11);
		
		assertEquals(exp,obs);
	}
	
	@Test
	public void getInstructionOutTest(){
		Translator t3 = new Translator("testOuttxt");
		Labels lab3 = new Labels();
		lab3.addLabel("f0");
		lab3.addLabel("f1");
		lab3.addLabel("f2");
		
		t3.setLabels(lab3);
		t3.setProgram(prog);
		t3.setLine("out 13");
		
		Instruction obs = t3.getInstruction("out");
		Instruction exp = new OutInstruction("out", 13);
		
		assertEquals(exp,obs);
	}

}
