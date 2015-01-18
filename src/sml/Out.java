package sml;

/**
 * This class ....
 * 
 * @author Esha Massand
 */

public class Out extends Instruction {
	private int register;
	private int result;

	public Out(String label, String opcode) {
		super(label, opcode);
	}

	public Out(String label, int register) {
		super(label, "out");
		this.register = register;
	}

	@Override
	public void execute(Machine m) {
		result = m.getRegisters().getRegister(register);
		System.out.println(super.toString() + " register " + register + " value is " + result);
	}

	@Override
	public String toString() {
		return super.toString() + " register " + register + " value is " + result;
	}
}