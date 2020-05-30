package MAZE;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class geneticAlgo {

	int GENERATION_SIZE = 100;
	int MAX_GENERATIONS = 100;
	int PARENT_COUNT = 2;
	int MUTATION_RATE = 5;

	class Organism implements Comparable<Organism> {
		char[] genotype;
		int fitness;

		public int getFitness() {
			return fitness;
		}

		public void setFitness(int fitness) {
			this.fitness = fitness;
		}

		@Override
		public int compareTo(Organism other) {
			if (fitness > other.fitness)
				return -1;
			else if (fitness < other.fitness)
				return 1;
			else
				return 0;
		}
	}

	public boolean geneticAlgorithm(String password) {
		int GENOTYPE_SIZE = password.length();
		int FITNESS_THRESHOLD = GENOTYPE_SIZE;
		Organism[] generation = new Organism[GENERATION_SIZE];
		Random r = new Random();
		for (int i = 0; i < GENERATION_SIZE; i++) {
			generation[i] = new Organism();
			generation[i].genotype = new char[GENOTYPE_SIZE];
			for (int j = 0; j < GENOTYPE_SIZE; j++) {
				String a = "! \"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
				generation[i].genotype[j] = a.charAt(r.nextInt(93));
			}
		}
		for (int g = 0; g < MAX_GENERATIONS; g++) {
			for (int o = 0; o < generation.length; o++) {
				evaluate(generation[o], password);
			}
			Arrays.sort(generation);
			String output = new String(generation[0].genotype);
			System.out.println(output);

			if (generation[0].fitness >= FITNESS_THRESHOLD) {
				System.out.println("I guessed the password! It was: " + output + "\n");
				return true;
			}

			Organism[] parents = new Organism[PARENT_COUNT];
			for (int p = 0; p < PARENT_COUNT; p++) {
				parents[p] = generation[p];
			}
			Organism[] nextGeneration = new Organism[GENERATION_SIZE];
			for (int i = 0; i < GENERATION_SIZE; i++) {
				nextGeneration[i] = breed(parents, GENOTYPE_SIZE);
			}
			for (int o = 0; o < nextGeneration.length; o++) {
				mutate(nextGeneration[o], GENOTYPE_SIZE);
			}
			generation = nextGeneration;
		}
		return false;
	}

	void evaluate(Organism o, String password) {

		int count = 0;
		for (int i = 0; i < o.genotype.length; i++) {
			if (o.genotype[i] == password.charAt(i))
				count++;
		}
		o.fitness = count;
	}

	Organism breed(Organism[] parents, int GENOTYPE_SIZE) {
		char[] genotype = new char[GENOTYPE_SIZE];
		Random r = new Random();
		for (int i = 0; i < GENOTYPE_SIZE; i++) {
			Organism parentToInheritFrom = parents[r.nextInt(PARENT_COUNT)];
			genotype[i] = parentToInheritFrom.genotype[i];
		}
		Organism o = new Organism();
		o.genotype = genotype;
		return o;
	}

	void mutate(Organism o, int GENOTYPE_SIZE) {
		Random r = new Random();
		for (int i = 0; i < GENOTYPE_SIZE; i++) {
			if ((r.nextInt(100)) < MUTATION_RATE) {
				// easy password = "OPENINGDOOR"
				// hard password = "I'll UnLoCk the 2nd D00R!"
				o.genotype[i] = (char) (r.nextInt(95) + ' ');
			}
		}
	}
}
