import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExerciseOne {
	 
	public static void main(String argv[]) throws IOException {
		
		String input;
		boolean flag = false;
		//This line will create a buffered reader that reads from the system input using an input stream reader
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		while (flag == false) {
			//input will contain the first line of input from the user
			System.out.println("What is your input?");
			input = bufferedReader.readLine();
			if (input.equals("exit")) {
			    System.out.println("GOODBYE!");
				flag = true;
			}
			else {
				System.out.println(input);
				//TODO: Complete this methods as requested on the handout
				System.out.println(numberOfWords(input));
				System.out.println(numberOfSpaces(input));
				System.out.println(numberOfCharacters(input));
			}
		}
		
	}
	
	//TODO: Finish this method
	public static String numberOfWords(String words) {
		String output = null;
		words = words.trim();
		if (words.isEmpty() || words.length() == 0) {
			output = "no words";
		}
		else {
			String[] words_array = words.split("\\s+");
			int total = words_array.length;
			if (total > 1) {
				output = total + " words";
			}
			else if (total == 1) {
				output = total + " word";
			}
		}
		return output;	
	}
		  
	//TODO: Finish this method
	public static String numberOfSpaces(String words) {
		String ret_str;
		int spaces = 0;
		for (char c: words.toCharArray()) {
			if (c == ' ') {
				spaces++; 
			}
		}
		if (spaces > 1) {
			ret_str = spaces + " spaces";
		}
		else if (spaces == 1) {
			ret_str = spaces + " space";
		}
		else {
			ret_str = "no spaces";
		}
		
		return ret_str;
		
	}
		  
	//TODO: Finish this method
	public static String numberOfCharacters(String words) {
		int count = 0;
		String ret_str;
		char[] c = words.toCharArray();
		count = c.length;
		if (count > 1) {
			ret_str = count + " characters";
		}
		else if (count == 1) {
			ret_str = count + " character";
		}
		else {
			ret_str = "no characters";
		}
		return ret_str;
		
		}
}


