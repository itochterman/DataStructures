import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;
import java.util.Scanner; 
public class CallingClass {
	
	static String[][] dayArr = new String[5][22];
	
	public static void main(String[] args) throws UnmatchedParenthesesException {
		File newDir = new File("project2_inputs _1_");
//		if (newDir.exists()) {
//			System.out.println("Exists");
//		}
//		if (newDir.isDirectory()) {
//			System.out.println("Is directory");
//		}
//		else {
//			System.out.println("Does not exist");
//			
//		}
//		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter number 1-8: ");
		
		int x = input.nextInt()-1;
		File[] subFiles = newDir.listFiles(); 
		
		if(subFiles[x].exists()) {
			
			//System.out.println("Yes");
			try {
				Scanner fileScan = new Scanner(subFiles[x]);
				StringBuilder builder = new StringBuilder("");
				while(fileScan.hasNext()) {
					String delim = fileScan.next();
					builder.append(delim);
				}
				builder.append("\n");
				String tutHours = builder.toString();
				//System.out.println(builder.toString());
				System.out.println(isMatched(tutHours));
				
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch(UnmatchedParenthesesException e) {
				System.out.println(e.getMessage());
			}
			
			printArr(dayArr);
			parseArrayMostTime(dayArr);
		}
	}
	

//		System.out.println("test is matched: " + testIsMatched());
	
	public static boolean isMatched(String hours) throws UnmatchedParenthesesException {
		ArrayStack<Character>buff = new ArrayStack<Character>();
		
		final String openingDelim = ("(");
		final String closingDelim = (")");
		
		StringBuilder sb = new StringBuilder();
		for(char c: hours.toCharArray()) {
			if (openingDelim.indexOf(c)!= -1) {
				buff.push(c);
//				System.out.println("Found an opening delimeter");
				//System.out.println(buff.toString());
				sb = new StringBuilder();
			}
			else if(closingDelim.indexOf(c) != -1) {
//				System.out.println("Found closing delimeter");
//				System.out.println(buff.toString());
				if(buff.isEmpty()) {
					throw new UnmatchedParenthesesException("ERROR: UNMATCHED PARENTHESES"); 
					//return false; 
					
				}
				
				if(closingDelim.indexOf(c) != openingDelim.indexOf(buff.pop())) {
					throw new UnmatchedParenthesesException("ERROR: UNMATCHED PARENTHESES");
				}
				else { // matching ) found
					String content = sb.toString().substring(1);	
					try {
						parseStudentInfo(content);
					}
					catch(UnmatchedParenthesesException e) {
						System.out.println(e.getMessage());
					}
				}
			}
			
			sb.append(c);
		}
		if(!buff.isEmpty()) {
			throw new UnmatchedParenthesesException("ERROR: UNMATCHED PARENTHESES"); 
		}
		
		return true;
	}
	
	public static boolean parseStudentInfo(String content) throws UnmatchedParenthesesException {
		ArrayStack<Character>buff = new ArrayStack<Character>();
		//System.out.println(content);
		
		final String openingDelim = ("<");
		final String closingDelim = (">");
		StringBuilder sb = new StringBuilder();
		
		int n = content.indexOf('<');
		String name = content.substring(0, n);
		content = content.substring(n); // get rid of name
		
		for(char c: content.toCharArray()) {
			
			
			if (openingDelim.indexOf(c) != -1) {
				buff.push(c);

//				System.out.println("Found an opening delimeter");
				//System.out.println(buff.toString());
			}
			if(openingDelim.indexOf(c)==-1&& closingDelim.indexOf(c)==-1) {
				sb.append(c);
			}
		
	
			else if(closingDelim.indexOf(c) != -1) {
//				System.out.println("Found closing delimeter");
//				System.out.println(buff.toString());
				if(buff.isEmpty()) {
					throw new UnmatchedParenthesesException("ERROR: UNMATCHED PARENTHESES");
					
				}
				
				if(closingDelim.indexOf(c) != openingDelim.indexOf(buff.pop())) {
					throw new UnmatchedParenthesesException("ERROR: UNMATCHED PARENTHESES");
				}
				
		
			}
		}
		//System.out.println(sb.toString());
		String timesAndDates = sb.toString();
		try {
		parseTimesAndDates(name, timesAndDates);
		}
		catch(UnmatchedParenthesesException e) {
			System.out.println(e.getMessage());
		}
		
		if (!buff.isEmpty()) {
			throw new UnmatchedParenthesesException("ERROR: UNMATCHED PARENTHESES");
		}
		
		return true;
	}

	
	public static boolean parseTimesAndDates(String name, String content) throws UnmatchedParenthesesException {
		ArrayStack<Character>buff = new ArrayStack<Character>();
		//System.out.println(content);
		
		final String openingDelim = ("[");
		final String closingDelim = ("]");
		StringBuilder sb = new StringBuilder();
		
		for(char c: content.toCharArray()) {
			
			
			if (openingDelim.indexOf(c) != -1) {
				sb.append(" ");
				buff.push(c);

//				System.out.println("Found an opening delimeter");
				//System.out.println(buff.toString());
			}
			if(openingDelim.indexOf(c)==-1&& closingDelim.indexOf(c)==-1) {
				sb.append(c);
			}
			else if(closingDelim.indexOf(c) != -1) {
				sb.append(" ");
//				System.out.println("Found closing delimeter");
//				System.out.println(buff.toString());
				if(buff.isEmpty()) {
					throw new UnmatchedParenthesesException("ERROR: UNMATCHED PARENTHESES");
				}
				
				if(closingDelim.indexOf(c) != openingDelim.indexOf(buff.pop())) {
					System.out.println("UNMATCHED PAREN");

					throw new UnmatchedParenthesesException("ERROR: UNMATCHED PARENTHESES");
				}
			}

		}
		
//		System.out.println(sb.toString());
		storeInArray(name, sb.toString());
		if(!buff.isEmpty()) {
			throw new UnmatchedParenthesesException("ERROR: UNMATCHED PARENTHESES");

		}
		return true;
	}
	
	
	
	static boolean testIsMatched() {
		String str = "(Mario<T[1100][2000]><H[2000]>)"
				+ "(Luigi<M[1600]><T[1100]><W[1400]>)"
				+ "(Bowser<T[2000]><W[1100]><F[2000]>)";
		try {
			return isMatched(str);
		}
		catch (UnmatchedParenthesesException e) {
			System.out.println("Unmatched parentheses Error " + e);
		}
		
		return false;
	}
	

		public static void storeInArray(String name, String datesAndTimes){
			
			int [][] dateTimeMatrix = new int[3][3];
			
			String[] split = datesAndTimes.split("\\s+");
			int[] intStringSplit = new int[split.length];
			
			int k = 0; 	
			String day = null; 
			int dayNum = 0; 

			for(int a = 0; a<split.length; a++) {
				String token = split[a];
				//System.out.println(token);
				if (!Character.isDigit(token.charAt(0))) {
					day = token; 
					switch(day) {
					case "M":
						dayNum = 0;
						break;
					case "T":
						dayNum = 1;
						break;
					case "W":
						dayNum = 2; 
						break;
					case "H":
						dayNum = 3;
						break;
					case "F":
						dayNum = 4;
						break;	
					}
				}
				else {
					int timeNum = Integer.parseInt(split[a]);
					timeNum = timeNum/100;
//					System.out.println(timeNum);
					if (dayArr[dayNum][timeNum] == null) {
						dayArr[dayNum][timeNum] = name;
					}
					else {
						dayArr[dayNum][timeNum] += ", " + name;
					}
				}
			}
			
		}
		
		
				
		
		public static void printArr(String[][] dayArr) {
			
			int width = 25;
			
			StringBuilder sb = new StringBuilder();
			for (int s = 0; s < 6; s++) {
				sb.append("%-"+width+"s");
			}
			sb.append("\n");
			System.out.printf(sb.toString(), " ", "M", "T", "W", "H", "F");
			for (int s = 0; s < 20 * 7; s++) {
				System.out.print("-");
			}
			
			for(int i = 9; i<dayArr[0].length; i++) {
				System.out.printf("\n%-"+(width)+"d", i * 100);
				
				for(int j =0; j<dayArr.length; j++) {
					
					System.out.printf("%-"+width+"s", dayArr[j][i] == null ? "" : dayArr[j][i]);					
					
				}
			}
			
			System.out.println();
		}
		
		private static void parseArrayMostTime(String[][] dayArr) {
			int max = -9999; 
			int time = 0;
			String dayStr = null;
			int day = 0; 
			for(int i = 0; i<dayArr[0].length; i++) {
				for (int j = 0; j<dayArr.length; j++) {
					int count = 0;
					if(dayArr[j][i]!=null) {
						for (char c: dayArr[j][i].toCharArray()) {
							if (c== ',') {
								count ++; 
							}
							
						}
						if (count > max) {
							day = j; 
							time = i; 
							max = count; 
						}
					}
				}
			}
			switch(day) {
			case 0:
				dayStr = "Monday";
				break;
			case 1:
				dayStr = "Tuesday";
				break;
			case 2:
				dayStr= "Wednesday"; 
				break;
			case 3:
				dayStr = "Thursday";
				break;
			case 4:
				dayStr = "Friday";
				break;	
			}
			System.out.println("\n\n");
			System.out.println("The day with maximum students is " + dayStr + " at time "+ time+":00.");
		}
		

		
}
	

