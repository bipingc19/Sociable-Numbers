
import java.util.Scanner;

public class SociableNumbers {
	/** This class has a set of 28 numbers whose cycle length is 28 */
	public static long[] cycle28 = { 14316, 19116, 31704, 47616, 83328, 177792, 295488, 629072, 589786, 294896, 358336,
			418904, 366556, 274924, 275444, 243760, 376736, 381028, 285778, 152990, 122410, 97946, 48976, 45946, 22976,
			22744, 19916, 17716 };

	/**
	 * This is a Global Scanner. It removes the problem of calling the scanner,
	 * everytime we give a new input.
	 */
	public static Scanner scnr = new Scanner(System.in);

	/**
	 * If the number we are looking for is in the long cycle (length 28 cycle),
	 * return the location where that number is. Otherwise, return -1.
	 */
	public static int isInCycle28(long targetNumber) {
		int position = -1;
		/*
		 * since the position starts from 0 to 27, assign -1 to position will help us to
		 * check if the number is there or not
		 */
		for (int index = 0; index < 28; index++) {
			if (targetNumber == cycle28[index]) {
				position = index;
				break;
			}
		}
		return position;
	}

	/**
	 * If the number is in the 28-cycle, it prints the cycle starting from that
	 * number.
	 */
	public static void printCycle28(int startFrom) {
		System.out.print(cycle28[startFrom] + " is sociable of length 28. ");

		/** The if checks position of the number and starts printing from that. */
		if (startFrom == 0) {
			for (int j = 0; j < 28; j++) {
				System.out.print(cycle28[j] + " --> ");
			}
			System.out.print(cycle28[0]);
		} else {
			for (int j = startFrom; j < 28; j++) {
				if (j == (startFrom - 1)) {
					System.out.print(cycle28[j]);
				} else {
					System.out.print(cycle28[j] + " --> ");
				}
			}
			for (int j = 0; j < (startFrom + 1); j++) {
				if (j == startFrom) {
					System.out.print(cycle28[j]);
				} else {
					System.out.print(cycle28[j] + " --> ");
				}
			}
		}

		System.out.println();
	}

	/**
	 * Is "divisor" a proper divisor of "target number"? I.e. does it divide evenly,
	 * while being smaller than the number itself? Returns false if the divisor is
	 * negative or zero.
	 */
	public static boolean isProperDivisor(long targetNumber, long divisor) {
		boolean success = false;
		// Switches its value if the number is proper divisor of targetNumber.

		/** This if statement checks weather the divisor is proper or not. */
		if (divisor > 0) {
			if ((targetNumber % divisor) == 0) {
				success = true;
			}
		} else {
			success = false;
		}
		return success;
	}

	/** Returns the sum of all of the proper divisors of "targetNumber" */
	public static long sigma(long targetNumber) {
		long sigmaValue = 0;
		// add up the factors to form the sigma of the targetNumber.
		boolean checkProperDivisor;
		// calls isProperDivisor and stores the boolean value.

		/**
		 * The loop will find all the proper divisors of the targetNumber with the help
		 * of method-isProperDivisor.
		 */
		for (long divisor = 1; divisor <= (targetNumber / 2); divisor++) {
			checkProperDivisor = isProperDivisor(targetNumber, divisor);
			if (checkProperDivisor == true) {
				sigmaValue = sigmaValue + divisor;
			}
		}
		return sigmaValue;
	}

	/**
	 * Calculates and prints the sociable number cycle of the specified length
	 * beginning at the specified targetNumber. This method assumes that the
	 * starting point and cycle length describe an actual sociable number chain, and
	 * does not validate that assumption. It outputs perfect number as chains of
	 * length 1 and amicable numbers as chains of length 2. It outputs in the format
	 * "6 --> 6", e.g. including the original number at the end of the chain of
	 * numbers.
	 */
	public static void printCycle(long targetNumber, int cycleLength) {
		long sigmaValueReturned = targetNumber;
		/*
		 * This variable stores targetNumber, but also changes to the sigma value of
		 * targetNumber.
		 */

		/** The if statement separates the perfect amicable and sociable numbers. */
		if (cycleLength == 1) {
			System.out.print(targetNumber + " is perfect. ");
		} else if (cycleLength == 2) {
			System.out.print(targetNumber + " is amicable. ");
		} else {
			System.out.print(targetNumber + " is sociable of length " + cycleLength + ". ");
		}

		/**
		 * The loop cycle print the sigmas of the number until we get the targetNumber
		 * again.
		 */
		for (int i = 1; i <= cycleLength; i++) {
			System.out.print(sigmaValueReturned + " --> ");
			sigmaValueReturned = sigma(targetNumber);
		}
		System.out.println(targetNumber);
	}

	/**
	 * Checks whether a particular number is a sociable number. If it is, it returns
	 * the length of the cycle for that number; if it is *not* a sociable number, it
	 * returns 0.
	 */
	public static int checkSociable(long targetNumber) {
		long sigmaValueReturned;
		/*
		 * This variable calls the sigma method and also stores the value returned by
		 * sigma method.
		 */
		long numberToBeChecked;
		// keeps and updates the value returned by sigma. We used it so that it would be
		// easier to compare.//
		int counter = 0;
		/*
		 * returns the scale of the number or returns 0 if the number is not perfect or
		 * amicable.
		 */
		/** (note: the number can still be sociable though) */
		numberToBeChecked = targetNumber;

		for (int i = 1; i <= 10; i++) {
			sigmaValueReturned = sigma(numberToBeChecked);
			if (targetNumber == sigmaValueReturned) {
				counter = i;
				return counter;
			}
			numberToBeChecked = sigmaValueReturned;
		}
		return counter;
	}

	/**
	 * Outputs the specified prompt, with no "return", waits for a number input from
	 * the user, and returns that number. Uses a global Scanner so that the
	 * procedure can be re-used.
	 */
	public static long getUserLong(String prompt) {
		System.out.print(prompt);
		long range = scnr.nextLong();
		return range;
		/*
		 * range stores and returns the starting number and the ending number when
		 * getUserLong is called.
		 */
	}

	/** See program assignment comments. Put your wording for them here. */
	public static void main(String[] args) {
		int cycleLength; // If a sociable cycle has been found, how long was the cycle?
		System.out.println(
				"This program searches a specified range looking for perfect, amicable, and sociable numbers.");

		long startNumber = getUserLong("Where should I start? ");
		long endNumber = getUserLong("How far should I go? ");

		for (long targetNumber = startNumber; targetNumber <= endNumber; targetNumber++) {
			cycleLength = checkSociable(targetNumber);
			if (cycleLength > 0) {
				printCycle(targetNumber, cycleLength);
			} else if (isInCycle28(targetNumber) >= 0) {
				int cycle28Location = isInCycle28(targetNumber);
				printCycle28(cycle28Location);
			}
		}

		System.out.println("All done!"); // Printing "All done!" makes sure the execution has completed.
	}
}