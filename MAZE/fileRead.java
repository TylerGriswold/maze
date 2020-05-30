package MAZE;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;

public class fileRead {
	int count = 0;

	public String[] readNames(String fileName) throws IOException {

		String[] names = new String[count];
		BufferedReader br = new BufferedReader(new FileReader(fileName)); // start bufferedReader on file
		String line = br.readLine();
		names = line.split(",");
		count = names.length;
		br.close();

		return names;
	}

	int count1 = 9;

	public double[][] readDistance(String fileDist) throws IOException {
		double[][] distances = new double[count1][count1];
		String[][] array = new String[count1][count1];
		File f = new File(fileDist);
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line;
		int i = 0;
		while ((line = br.readLine()) != null) {
			array[i] = line.split(",");
			i++;
		}
		for (int a = 0; a < array.length; a++) {
			distances[a] = new double[array[a].length];
			for (int b = 0; b < array.length; b++) {
				distances[a][b] = Double.parseDouble(array[a][b]);
			}
		}
		br.close();
		return distances;
	}

	int count2 = 16;

	public double[][] readDistance2(String fileDist) throws IOException {
		double[][] distances = new double[count2][count2];
		String[][] array = new String[count2][count2];
		File f = new File(fileDist);
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line;
		int i = 0;
		while ((line = br.readLine()) != null) {
			array[i] = line.split(",");
			i++;
		}
		for (int a = 0; a < array.length; a++) {
			distances[a] = new double[array[a].length];
			for (int b = 0; b < array.length; b++) {
				distances[a][b] = Double.parseDouble(array[a][b]);
			}
		}
		br.close();
		return distances;
	}

	int count3 = 25;

	public double[][] readDistance3(String fileDist) throws IOException {
		double[][] distances = new double[count3][count3];
		String[][] array = new String[count3][count3];
		File f = new File(fileDist);
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line;
		int i = 0;
		while ((line = br.readLine()) != null) {
			array[i] = line.split(",");
			i++;
		}
		for (int a = 0; a < array.length; a++) {
			distances[a] = new double[array[a].length];
			for (int b = 0; b < array.length; b++) {
				distances[a][b] = Double.parseDouble(array[a][b]);
			}
		}
		br.close();
		return distances;
	}

}
