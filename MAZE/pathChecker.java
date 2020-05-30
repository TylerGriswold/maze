package MAZE;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class pathChecker {

		double pathCheck(int source, int length, int target, double graph[][], String[] nodeNames) {
			euclidean e = new euclidean();
			ArrayList<Integer> openSet = new ArrayList<Integer>(); 
			ArrayList<Integer> closedSet = new ArrayList<Integer>();
			int[] prev = new int[length];
			double[] gScore = new double[length];
			double[] fScore = new double[length];
			for(int i=0;i<graph.length;i++) {
				gScore[i] = Integer.MAX_VALUE;
				fScore[i] = Integer.MAX_VALUE;
				prev[i] = -1;
			}
			gScore[source] = 0;
			fScore[source] = gScore[source];
	    
			openSet.add(source);
			while(openSet.isEmpty()==false) {
				Integer current = openSet.get(0);
				for(int i=0;i<openSet.size();i++) {
					if(fScore[openSet.get(i)]<fScore[current]) {
						current = openSet.get(i);
					}
				}
				openSet.remove(current);
				closedSet.add(current);
				
				if(current==target) {
					ArrayList<Integer> path = new ArrayList<Integer>();
					while(current!=-1) {
						path.add(current);
						current = prev[current];				
					}
					Collections.reverse(path);
					

					double cost=gScore[target];
					String explored ="";
					for(int i = 0; i < path.size(); i++) {
						explored += " " + nodeNames[path.get(i)]+ " " + "->";
						
	        }
				
					return cost;
				}				
				for(int i=0;i<graph[current].length;i++) {
					if(graph[current][i]==-1) {
						continue;
					}
					
					double edgeWeight = graph[current][i];        
					double tentativeGScore = gScore[current]+edgeWeight;
					if(tentativeGScore<gScore[i]) {
						gScore[i] = tentativeGScore;
						fScore[i] = gScore[i]+e.euclidean(i,target,graph);
						prev[i] = current;
						if(closedSet.contains(i)) {
							closedSet.remove(i);
							openSet.add(i);
						}else if(openSet.contains(i)==false) {
							openSet.add(i);
						}
					}
				}
			}
			return (Double) null;
		}		
	}



