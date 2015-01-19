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
		System.out.println("BnzClass");
		System.out.println("And the label is: "+ label);
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
		
			System.out.println(m.getLabels());
			
			//Get the Instruction, by finding the element of the ArrayList that 
			//holds the instruction of the next label
			
			System.out.println("labelNext element: "+m.getProg().indexOf(labelNext));
			System.out.println("labelNext: " + labelNext);
			//we are continuing with the program so we must 
			//set the program counter.
			m.setPc(m.getLabels().indexOf(labelNext)); 
		} else {
			//do nothing
			System.out.println(super.toString() + " register " + register + " value is " + result + ".\n"
					+ "Continuing with the program execution.");
		}
		
	}

	@Override
	public String toString() {
		return super.toString() + ", register: "+ this.register + ", next label: "+  this.labelNext;
	}
}