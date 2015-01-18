package sml;

/**
 * This class ....
 * 
 * @author Esha Massand
 */

public class Out extends Instruction {
	private int register;

	public Out(String label, String opcode) {
		super(label, opcode);
	}

	public Out(String label, int register) {
		super(label, "out");
		this.register = register;
	}

	@Override
	public void execute(Machine m) {
		//do nothing
	}

	@Override
	public String toString() {
		return super.toString() + " register " + register + " value is " + getRegister(register);
	}
}