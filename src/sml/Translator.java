package sml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
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
		/*
		 * This method uses the fileName, to scan the instructions in the lines
		 * of the program. for each Instruction Class, will find the right
		 * number of parameters (required for the constructor of the class), and
		 * will return the Class constructor with the correct number of
		 * parameters to declare it. Now I will use reflection to do this.
		 */

		System.out.println("Reflecting...");
		
		
		//get the filename and convert it to classname

		try {
			Class<?> theInstructionClassArguments = Class.forName("sml."+ fileName);
			// so we have a class - lets create an instance of it.

			Object InstructionObject;
			try {
				// Using example here:
				// http://docs.oracle.com/javase/tutorial/reflect/member/ctorLocation.html
				Class<?> aClass = Class.forName("src."+ fileName);
				Constructor<?>[] allInstructionConstructors = aClass.getDeclaredConstructors();
				for (Constructor<?> c : allInstructionConstructors) {
					Class<?>[] parameterTypes = c.getParameterTypes();
					for (int i = 0; i < parameterTypes.length; i++) {
						if (parameterTypes[i]
								.equals(theInstructionClassArguments)) {
							InstructionObject = c.newInstance();
							return (Instruction) InstructionObject;
						}
					}
				}
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				System.err.println("It's all gone wrong.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("It's all gone wrong.");
		}
		return null;
	}

	// -----
	// int s1; // Possible operands of the instruction
	// int s2;
	// int r;
	// String x;
	//
	// if (line.equals(""))
	// return null;
	//
	// String ins = scan();
	// switch (ins) {
	// case "add":
	// r = scanInt();
	// s1 = scanInt();
	// s2 = scanInt();
	// return new AddInstruction(label, r, s1, s2);
	// case "lin":
	// r = scanInt();
	// s1 = scanInt();
	// return new LinInstruction(label, r, s1);
	// case "sub":
	// r = scanInt();
	// s1 = scanInt();
	// s2 = scanInt();
	// return new SubInstruction(label, r, s1, s2);
	// case "mul":
	// r = scanInt();
	// s1 = scanInt();
	// s2 = scanInt();
	// return new MulInstruction(label, r, s1, s2);
	// case "div":
	// r = scanInt();
	// s1 = scanInt();
	// s2 = scanInt();
	// System.out.println("String:"+ins+" label: " + label + ", r: "+ r +
	// " s1: " + s1 + ", s2: " + s2);
	// return new DivInstruction(label, r, s1, s2);
	// case "out":
	// r = scanInt();
	// s1 = scanInt();
	// s2 = scanInt();
	// return new Out(label, r);
	// case "bnz":
	// r = scanInt();
	// x = scan();
	// return new Bnz(label, r, x);
	// }
	// return null;
	// }
	// -----

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