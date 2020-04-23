package Code;

import java.util.*;


public class TSP {

    private  double totalTourWeight(ArrayList<Integer> tour, Graph g) {
        double res = 0;

        for(int i = 0; i < tour.size()-1; i++) {
            int current = tour.get(i);
            int next = tour.get(i+1);

            res += g.weightMap[current][next];
        }

        return res;
    }
    // The minHamiltonCycleBased on weight ( StackOverflow.com )
    private  double minHamiltonCycleWeight(Graph g, ArrayList<Integer> optTour) {
        double res = Double.MAX_VALUE;
        optTour = new ArrayList<Integer>();
        int n = g.n;
        
        Integer[] vertices = new Integer[n];
        for(int i = 0; i < n; i++)
            vertices[i] = i;

        Permutator<Integer> permutator = new Permutator<Integer>(vertices);
        ArrayList<Integer> cycle = new ArrayList<Integer>();
        while(permutator.hasNext()) {
            Integer[] perm = permutator.next();

            cycle.clear();
            for(int i = 0; i < n; i++)
                cycle.add(perm[i]);
            cycle.add(perm[0]);

            double weights = totalTourWeight(cycle, g);
            if(weights < res) {
                res = weights;
                optTour.clear();
                optTour.addAll(cycle);
            }
        }

        System.out.println("The Opt Tour: " + cycle);
        return res;
    }

        public void startExp() {
     	
        	
            // start with a random graph
            Graph g = Graph.randomCompleteGraph(4, 20);

       
            
            // calculate 2 approximation
            System.out.println("----------- 2-Approximation-------------");
            Graph mst = MST.kruskal(g);
            ArrayList<Integer> dfs = DFS.dfs(mst);
            ArrayList<Integer> shortcut = DFS.shortcut(dfs);
            double twoApproxWeight = totalTourWeight(shortcut, g);
            System.out.println("The Tour:\t"+shortcut);
            System.out.println("The tour weight :\t"+twoApproxWeight);
            System.out.println("-------------------------------------");
            // run brute force algorithm for TSP
            ArrayList<Integer> optTour = new ArrayList<Integer>();
            System.out.println("----------- Optimal TSP -------------");
            double optWeight = minHamiltonCycleWeight(g,optTour);

                    
            System.out.println("The tour weight :\t"+optWeight);

         
        }
    

    public static void main(String[] args) {
        TSP test1 = new TSP();
        test1.startExp();
    }
}