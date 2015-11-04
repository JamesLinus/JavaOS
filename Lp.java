import java.util.*;
public class Lp {
	private static boolean stop = false;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean loginBool = true; // to determine if user is on the enter user step or the enter password step in the login function it is passed as a variable.
		Map <String, String> userMap = new HashMap<String,String>();
		String username = "";
		String password = "";
		boolean loggedIn = false;
		do {
			if (stop == false) {
				if (loginBool == true && username.length() == 0) {
					String tmp = login(scanner, loginBool, username);
					if (tmp.length() > 0 && tmp.length() < 24) {
						username = tmp;
						userMap.put("username", username);
						loginBool = false;
					}
				}
				if (loginBool == false && username.length() > 0 && username.length() < 24) {
					String tmp = "";
					do {
						tmp = login(scanner, loginBool, username);
						loginBool = true;
					} while (tmp.length() < 0 && tmp.length() > 16);
					password = tmp;
					userMap.put("password", tmp)

				}
				loggedIn = true;
				scanner = run(scanner, loggedIn);
			}
			else {
				System.out.println("System kill signal has been activated, terminating program now.");
			}
		}
		while (scanner.hasNext() && !stop);
	}
	private static Scanner run(Scanner scanner, boolean loggedIn) {
		if (loggedIn) {
			System.out.print("Lp:~ $ ");
			String input = scanner.nextLine();
			if (input.equals("help")) {
				System.out.print("Welcome to the help menu, what seems to be the problem?\n$ ");
			}
			else {
				System.out.print("Sorry, \"" + input + "\" is not a valid command, please type help to view sample usage.\nLp:~ $ ");
			}
			return scanner;
		}
		else return new Scanner(System.in);
	}
	private static String login (Scanner scanner, boolean loginBool, String username) {
		if (loginBool) { // we are on username stage
			System.out.print("Username: ");
			return scanner.nextLine();
		}
		else if (!loginBool) { // we are on pw stage
			System.out.print("Password: ");
			return scanner.nextLine();
		}
		else {
			System.out.println("Uh oh! There was been a problem, possible memory leakage killling program STAT!");
			stop = true;
			return "";
		}
	}
}