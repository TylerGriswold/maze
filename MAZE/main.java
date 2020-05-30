package MAZE;

import java.io.IOException;
import java.util.Scanner;

/*
 *     THE MAZE
 *       BY:
 *  Tyler Griswold
 *
*/
class main {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

		fileRead read = new fileRead();
		String dist1 = "D:\\Senior Year\\AI\\MAZE INFO\\Maze1.csv";
		String dist2 = "D:\\Senior Year\\AI\\MAZE INFO\\Maze2.csv";
		String dist3 = "D:\\Senior Year\\AI\\MAZE INFO\\Maze3.csv";
		String names1 = "D:\\Senior Year\\AI\\MAZE INFO\\Maze1Names.csv";
		String names2 = "D:\\Senior Year\\AI\\MAZE INFO\\Maze2Names.csv";
		String names3 = "D:\\Senior Year\\AI\\MAZE INFO\\Maze3Names.csv";
		String maze2path2file = "D:\\Senior Year\\AI\\MAZE INFO\\Maze2Path2.csv";
		String maze3path2file = "D:\\Senior Year\\AI\\MAZE INFO\\Maze3Path2.csv";
		String maze3path3file = "D:\\Senior Year\\AI\\MAZE INFO\\Maze3Path3.csv";

		String[] nodeNames1 = read.readNames(names1);
		String[] nodeNames2 = read.readNames(names2);
		String[] nodeNames3 = read.readNames(names3);

		int startIndex1 = -1;
		int finishIndex1 = -1;
		for (int i = 0; i < nodeNames1.length; i++) {
			if (nodeNames1[i].equalsIgnoreCase("S")) {
				startIndex1 = i;
				break;
			}
		}
		if (startIndex1 == -1) {
			System.out.print("Node S was not in file!");
		}
		for (int i = 0; i < nodeNames1.length; i++) {
			if (nodeNames1[i].equalsIgnoreCase("H")) {
				finishIndex1 = i;
				break;
			}
		}
		if (finishIndex1 == -1) {
			System.out.print("Node H was not in file!");
		}
		int startIndex2 = -1;
		int finishIndex2 = -1;
		for (int i = 0; i < nodeNames2.length; i++) {
			if (nodeNames2[i].equalsIgnoreCase("S")) {
				startIndex2 = i;
				break;
			}
		}
		if (startIndex2 == -1) {
			System.out.print("Node S was not in file!");
		}
		for (int i = 0; i < nodeNames2.length; i++) {
			if (nodeNames2[i].equalsIgnoreCase("L")) {
				finishIndex2 = i;
				break;
			}
		}
		if (finishIndex2 == -1) {
			System.out.print("Node L was not in file!");
		}

		int startIndex3 = -1;
		int finishIndex3 = -1;

		for (int i = 0; i < nodeNames3.length; i++) {
			if (nodeNames3[i].equalsIgnoreCase("A")) {
				startIndex3 = i;
				break;
			}
		}
		if (startIndex3 == -1) {
			System.out.print("Node A was not in file!");
		}
		for (int i = 0; i < nodeNames3.length; i++) {
			if (nodeNames3[i].equalsIgnoreCase("Y")) {
				finishIndex3 = i;
				break;
			}
		}
		if (finishIndex3 == -1) {
			System.out.print("Node Y was not in file!");
		}
		double[][] maze1 = read.readDistance(dist1);
		double[][] maze2 = read.readDistance2(dist2);
		double[][] maze3 = read.readDistance3(dist3);
		double[][] maze2path2 = read.readDistance2(maze2path2file);
		double[][] maze3path2 = read.readDistance3(maze3path2file);
		double[][] maze3path3 = read.readDistance3(maze3path3file);

		geneticAlgo GA = new geneticAlgo();
		Astar AS = new Astar();
		pathChecker PC = new pathChecker();
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to THE MAZE \n");
		boolean r = false;
		String password = "";
		System.out.println("Enter your starting password: ");

		do {
			password = scan.nextLine();
			r = GA.geneticAlgorithm(password);
			if (r == true) {
				continue;
			} else {
				System.out.println("You cannot start. LET ME TRY AGAIN!*");
				System.out.println("Enter a new password to start the maze.");
				password = scan.nextLine();
			}
		} while (r != true);

		System.out.println("Which map would you like to play? \n" + "Enter 1 for Easy \n" + "Enter 2 for Medium \n"
				+ "Enter 3 for Hard");
		int mapNumber = scan.nextInt();

		if (mapNumber == 1) {
			System.out.println("Loading Maze 1...");
			System.out.println("Good luck!");
			AS.Astar(startIndex1, nodeNames1.length, finishIndex1, maze1, nodeNames1);

			System.out.println("Enter your password to exit the Maze: ");
			password = scan.nextLine();
			r = false;
			do {
				password = scan.nextLine();
				r = GA.geneticAlgorithm(password);
				if (r == true) {
					System.out.println("HAVE A NICE DAY");
					continue;
				} else {
					System.out.println("You cannot exit. LET ME TRY AGAIN!*");
					System.out.println("Enter a new password to exit the maze.");
					password = scan.nextLine();
				}
			} while (r != true);

		} else if (mapNumber == 2) {
			System.out.println("Loading Maze 2...");
			System.out.println("Good luck and keep a lookout for Seceret Doors!");
			double bestPath = AS.Astar(startIndex2, nodeNames2.length, finishIndex2, maze2, nodeNames2);
			double path2 = PC.pathCheck(startIndex2, nodeNames2.length, finishIndex2, maze2path2, nodeNames2);
			double pathDiff = path2 - bestPath;
			System.out
					.println("You saved " + pathDiff + " by taking door G to K instead of door G to F. NICE WORK! \n");
			System.out.println("Enter your password to exit the Maze: ");
			password = scan.nextLine();
			r = false;
			do {
				password = scan.nextLine();
				r = GA.geneticAlgorithm(password);
				if (r == true) {
					System.out.println("HAVE A NICE DAY");
					continue;
				} else {
					System.out.println("You cannot exit. LET ME TRY AGAIN!*");
					System.out.println("Enter a new password to exit the maze.");
					password = scan.nextLine();
				}
			} while (r != true);

		} else if (mapNumber == 3) {
			System.out.println("Loading Maze 3...");
			System.out.println("Good luck and keep a lookout for Seceret Doors!");
			double bestPath = AS.Astar(startIndex3, nodeNames3.length, finishIndex3, maze3, nodeNames3);
			double path2 = PC.pathCheck(startIndex3, nodeNames3.length, finishIndex3, maze3path2, nodeNames3);
			double path3 = PC.pathCheck(startIndex3, nodeNames3.length, finishIndex3, maze3path3, nodeNames3);
			double diffbtwn1and2 = path2 - bestPath;
			double diffbtwn1and3 = path3 - bestPath;
			System.out.println(
					"You saved You saved " + diffbtwn1and2 + " by taking door P to U instead of door M to N and "
							+ diffbtwn1and3 + " by not taking door T to Y. NICE WORK! \n");
			System.out.println("Enter your password to exit the Maze: ");
			password = scan.nextLine();
			r = false;
			do {
				password = scan.nextLine();
				r = GA.geneticAlgorithm(password);
				if (r == true) {
					System.out.println("\n \n HAVE A NICE DAY");
					continue;
				} else {
					System.out.println("You cannot exit. LET ME TRY AGAIN!*");
					System.out.println("Enter a new password to exit the Maze.");
					password = scan.nextLine();
				}
			} while (r != true);
		}

		else {
			System.out.println("Sorry, thats not an option");
		}
	}
}