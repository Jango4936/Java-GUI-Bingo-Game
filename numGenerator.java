import java.util.ArrayList;
import java.util.Random;

public class numGenerator {
	
	// Array list to store generated numbers
	ArrayList<Integer> numbers = new ArrayList<Integer>();
	Random random = new Random(); // Random number generator

	 // Constructor to generate a series of unique numbers within a range
	numGenerator(int initial_num, int final_num) {
		int count = final_num - initial_num + 1;
		int num;

		while (numbers.size() < count) {
			num = random.nextInt(final_num - initial_num + 1) + initial_num;

			if (!numbers.contains(num)) {
				numbers.add(num);
			}
		}
	}
	
	// Constructor to generate a specific count of unique numbers within a range
	numGenerator(int initial_num, int final_num, int count) {
		int temp = final_num - initial_num + 1;
		if (count == temp + 1) {
			System.out.println("error cant generate number more than " + temp);
		} else {
			int num;

			while (numbers.size() < count) {
				num = random.nextInt(final_num - initial_num + 1) + initial_num;

				if (!numbers.contains(num)) {
					numbers.add(num);
				}
			}
		}
		
		// Print generated numbers in console
		System.out.println("Generated num: ");
		for (int i = 0; i < numbers.size(); i++) {
			System.out.print(numbers.get(i) + " ");
		}
		System.out.println("");

	}
}
