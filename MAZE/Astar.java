package MAZE;

import java.io.*;
import java.util.*;

public class Astar {
	euclidean e = new euclidean();
	geneticAlgo ga = new geneticAlgo();

	@SuppressWarnings("resource")
	double Astar(int source, int length, int target, double graph[][], String[] nodeNames) {
		ArrayList<Integer> openSet = new ArrayList<Integer>();
		ArrayList<Integer> closedSet = new ArrayList<Integer>();
		int[] prev = new int[length];
		double[] gScore = new double[length];
		double[] fScore = new double[length];
		for (int i = 0; i < graph.length; i++) {
			gScore[i] = Integer.MAX_VALUE;
			fScore[i] = Integer.MAX_VALUE;
			prev[i] = -1;
		}
		gScore[source] = 0;
		fScore[source] = gScore[source] + e.euclidean(source, target, graph);

		openSet.add(source);
		while (openSet.isEmpty() == false) {
			Integer current = openSet.get(0);
			for (int i = 0; i < openSet.size(); i++) {
				if (fScore[openSet.get(i)] < fScore[current]) {
					current = openSet.get(i);
				}
			}
			openSet.remove(current);
			closedSet.add(current);
			System.out.println("You see a path at " + nodeNames[current]);
			if (current == target) {
				ArrayList<Integer> path = new ArrayList<Integer>();
				while (current != -1) {
					path.add(current);
					current = prev[current];
				}
				Collections.reverse(path);

				double cost = gScore[target];
				String explored = "";
				for (int i = 0; i < path.size(); i++) {
					explored += " " + nodeNames[path.get(i)] + " " + "->";

				}
				System.out.println("You chose to take path: " + explored + " " + "Exit");

				System.out.println("\nIt only costed you " + cost + " to find the exit");

				return cost;
			}
			for (int i = 0; i < graph[current].length; i++) {
				if (graph[current][i] == -1) {
					continue;
				}
				if (graph[current][i] > 1) {

					Scanner scan = new Scanner(System.in);
					System.out.println("NICE! You found Secret Door " + nodeNames[current] + " to " + nodeNames[i]
							+ " \n Enter a password for me to guess " + "so you can unlock the door: ");
					String password = scan.nextLine();
					boolean response = false;
					do {
						response = ga.geneticAlgorithm(password);
						if (response == true) {
							System.out.println("*DOOR UNLOCKS*");
							continue;
						} else {
							System.out.println("*DOOR STAYS SHUT* LET ME TRY AGAIN!*");
							System.out.println("Enter a new password to get throught the door.");
							password = scan.nextLine();
						}
					} while (response != true);
				}

				double edgeWeight = graph[current][i];
				double tentativeGScore = gScore[current] + edgeWeight;
				if (tentativeGScore < gScore[i]) {
					gScore[i] = tentativeGScore;
					fScore[i] = gScore[i] + e.euclidean(i, target, graph);
					prev[i] = current;
					if (closedSet.contains(i)) {
						closedSet.remove(i);
						openSet.add(i);
					} else if (openSet.contains(i) == false) {
						openSet.add(i);
					}
				}
			}
		}
		return (Double) null;
	}
}
