package MAZE;

public class euclidean {
	public double euclidean(int source, int target, double[][] graph) {
		int currentx = -1;
		int currenty = -1;
		int targetx = -1;
		int targety = -1;
		int row = (int) Math.sqrt(graph.length);
		int counter = -1;

		double[][] newGraph = new double[row][row];
		for (int i = 0; i < newGraph.length; i++) {
			for (int j = 0; j < newGraph[i].length; j++) {
				newGraph[i][j] = counter;
				counter++;
			}
		}
		for (int i = 0; i < newGraph.length; i++) {
			for (int j = 0; j < newGraph[i].length; j++) {
				if (newGraph[i][j] == source) {
					currentx = i;
					currenty = j;
				}
				if (newGraph[i][j] == target) {
					targetx = i;
					targety = j;
				}
			}
		}
		return Math.sqrt((targety - currenty) * (targety - currenty) + (targetx - currentx) * (targetx - currentx));
	}
}