package sml;

/**
 * This class ....
 * 
 * @author Esha Massand
 */

public class Bnz extends Instruction {
	private int register;
	private int result;
	private String labelNext;

	public Bnz(String label, String opcode) {
		super(label, opcode);
	}

	public Bnz(String label, int register, String labelNext) {
		super(label, "bnz");
		this.register = register;
		this.labelNext = labelNext;
	}

	@Override
	public void execute(Machine m) {
		result = m.getRegisters().getRegister(register);
		if(result != 0){
			//make the labelNext be the next instruction to execute
			System.out.println(super.toString() + " register " + register + " value is " + result + ".\n"
					+ "Skipping to the next instruction specified within the bnz instruction.");
		} else {
			//do nothing
			System.out.println(super.toString() + " register " + register + " value is " + result + ".\n"
					+ "Continuing with the program execution.");
		}
		
	}

	@Override
	public String toString() {
		return super.toString() + " register " + register + " value is " + result;
	}
}