package sml;

import lombok.Data;

/**
 * This class ....
 * 
 * @author Esha Massand
 */

@Data
public class DivInstruction extends Instruction {
	
	private int result;
	private int op1;
	private int op2;

	public DivInstruction(String label, String op) {
		super(label, op);
		System.out.println("DivInstructionClass");
	}
	
	public DivInstruction(String label, int result, int op1, int op2) {
		this(label, "div");
		this.result = result;
		this.op1 = op1;
		this.op2 = op2;
	}
	
	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1);
		int value2 = m.getRegisters().getRegister(op2);
		try{
		m.getRegisters().setRegister(result, value1 / value2);
		} catch (ArithmeticException ae){
			if (value2 ==0){
			System.out.println("Note: Illegal Operation Ignored! \nYou can't divide by zero.");
			}
		}
		System.out.println(m.getProg());
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + op1 + " / " + op2 + " to " + result;
	}

}
