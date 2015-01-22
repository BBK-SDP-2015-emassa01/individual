package sml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
public class Translator {

	// word + line is the part of the current line that's not yet processed
	// word has no whitespace
	// If word and line are not empty, line begins with whitespace
	private String line = "";
	private Labels labels; // The labels of the program being translated
	private ArrayList<Instruction> program; // The program to be created
	private String fileName; // source file of SML code

	private static final String SRC = "src";

	public Translator(String fileName) {
		System.out.println("TranslatorClass");
		this.fileName = SRC + "/" + fileName;
	}

	// translate the small program in the file into lab (the labels) and
	// prog (the program)
	// return "no errors were detected"
	public boolean readAndTranslate(Labels lab, ArrayList<Instruction> prog) {

		try (Scanner sc = new Scanner(new File(fileName))) {
			// Scanner attached to the file chosen by the user
			labels = lab;
			labels.reset();
			program = prog;
			program.clear();

			try {
				line = sc.nextLine();
				System.out.println("line: " + line); // for purposes of viewing
														// program execution.
			} catch (NoSuchElementException ioE) {
				return false;
			}

			// Each iteration processes line and reads the next line into line
			while (line != null) {
				// Store the label in label
				String label = scan();

				if (label.length() > 0) {
					Instruction ins = getInstruction(label);
					if (ins != null) {
						labels.addLabel(label);
						program.add(ins);
					}
				}

				try {
					line = sc.nextLine();
				} catch (NoSuchElementException ioE) {
					return false;
				}
			}
		} catch (IOException ioE) {
			System.out.println("File: IO error " + ioE.getMessage());
			return false;
		}
		return true;
	}

	// line should consist of an MML instruction, with its label already
	// removed. Translate line into an instruction with label label
	// and return the instruction
	public Instruction getInstruction(String label) {
		
		if (line.equals("")) {
			return null;
		}
		
		//get the filename and convert it to classname
		String ClassInstruction = scan() + "Instruction";
		System.out.println(ClassInstruction);//this gives 'linInstruction'
		ClassInstruction = ClassInstruction.substring(0,1).toUpperCase() + ClassInstruction.substring(1, ClassInstruction.length());
		System.out.println(ClassInstruction);
			
		/*
		 * This method uses the fileName, to scan the instructions in the lines
		 * of the program. for each Instruction Class, will find the right
		 * number of parameters (required for the constructor of the class), and
		 * will return the Class constructor with the correct number of
		 * parameters to declare it. Now I will use reflection to do this.
		 */

		System.out.println("Reflecting...");

		
		try {
			Class<?> theInstructionClass = Class.forName("sml."+ ClassInstruction);
			// so we have a class - lets create an instance of it.
			System.out.println("Class Instruction:"+ ClassInstruction);

			
			try {
				
				Object InstructionObject = null;
	
				// Using example here:
				// http://docs.oracle.com/javase/tutorial/reflect/member/ctorLocation.html
				// https://www.youtube.com/watch?v=agnblS47F18 Derek Banas Youtube
			
				Class<?> aClass = Class.forName("sml."+ ClassInstruction);
				Constructor<?>[] allInstructionConstructors = aClass.getDeclaredConstructors();
				
				Constructor<?> theConstructor = allInstructionConstructors[0];
				System.out.println("Constructor Chosen: "+ theConstructor);
				System.out.println("Constructor Parameters: "+ theConstructor.getParameters().toString());
				
					//now get the parameters needed for the constructor
					Object[] parametersForConstructor = new Object [theConstructor.getParameters().length];
					
					for (int i = 0; i < theConstructor.getParameters().length; i++) {
						System.out.println("ParameterTypes :"+ theConstructor.getParameterTypes());
						if (theConstructor.getParameters()[i].getType().equals(java.lang.String.class)) {
							System.out.println("It's a String");
							String parameterString = scan();
							System.out.println("Parameter String: "+parameterString);
							parametersForConstructor[i] =  parameterString;
						} 
						else if (theConstructor.getParameters()[i].getType().equals(int.class)) {
							System.out.println("It's an int");
								int parameterInt = scanInt();
								parametersForConstructor[i] = parameterInt;
						}
						else {
							System.out.println("Didn't find it.");
						}
				}
			} catch (ClassNotFoundException e){
				e.printStackTrace();
				System.err.println("It's all gone wrong.");
				
			}
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			System.err.println("It's all gone wrong.");
		}
		return null;
	}

	/*
	 * Return the first word of line and remove it from line. If there is no
	 * word, return ""
	 */
	private String scan() {
		line = line.trim();
		System.out.println("trimmed spaces off line: " + line);// print this for
																// viewing
		if (line.length() == 0)
			return "";

		int i = 0;
		while (i < line.length() && line.charAt(i) != ' '
				&& line.charAt(i) != '\t') {
			i = i + 1;
		}
		String word = line.substring(0, i);
		System.out.println("word: " + word); // for viewing execution.
		line = line.substring(i);
		return word;
	}

	// Return the first word of line as an integer. If there is
	// any error, return the maximum int
	private int scanInt() {
		String word = scan();
		System.out.println("int: " + word); // for viewing execution.
		if (word.length() == 0) {
			return Integer.MAX_VALUE;
		}

		try {
			return Integer.parseInt(word);
		} catch (NumberFormatException e) {
			return Integer.MAX_VALUE;
		}
	}
}