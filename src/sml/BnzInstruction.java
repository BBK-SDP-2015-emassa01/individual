package sml;

/**
 * This class checks if the contents of register r is not zero, then 
 * makes the statement labelled Label2 the next one to execute
 * Instruction Format: Label1 bnz r Label2
 * @author Esha Massand
 */

public class BnzInstruction extends Instruction {
	private int register;
	private int result;
	private String labelNext;

	public BnzInstruction(String label, int register, String labelNext) {
		super(label, "bnz");
		this.register = register;
		this.labelNext = labelNext;
	}

	@Override
	public void execute(Machine m) {
		result = m.getRegisters().getRegister(register);
		if(result != 0){
			//make the labelNext be the next instruction to execute
			
			//set the program counter.
			m.setPc(m.getLabels().indexOf(labelNext)); 
		}
	}

	@Override
	public String toString() {
		return super.toString() + ", register: "+ this.register + ", next label: "+  this.labelNext;
	}
}