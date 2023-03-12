import java.util.Arrays;
import java.util.Scanner;


public class miniProj {

    //finding the component representative
    public static int findRep(int[] represents, int index) {
        int original_index = index;
        while (represents[index] != index) {
            index = represents[index];
        }
        represents[original_index] = index;
        return index;
    }


    public static int[] connections(int n, double p) {
        int[] represents = new int[n];
        int[] component_size = new int[n];
        int max_edges = (int) ((n / 2) * ((n - 1) * p)); //TODO remove this or not
        int rep_j, rep_i;
        Arrays.fill(component_size, 0);
        for (int i = 0; i < n; i++) {
            represents[i] = i;
        }
//        int edges_left = max_edges; //TODO remove this or not
        for (int i = 0; i < n ; i++) {

            for (int j = i + 1; j < n; j++) {
                //checking if the edge exists or not
                if (Math.random() < p) {
                    //reducing the number of edges left
//                    edges_left--; //TODO remove this or not

                    //checking for the representative of the component
                    if (represents[j] == represents[represents[j]])
                        rep_j = represents[j];
                    else
                        rep_j = findRep(represents, j);

                    if (represents[i] == represents[represents[i]])
                        rep_i = represents[i];
                    else
                        rep_i = findRep(represents, i);
                    //update the representative of the current vertices
                    represents[rep_j] = rep_i;
                }
            }
        }
        //sum up the components size
        for (int i = 0; i < n; i++) {
            int rep = findRep(represents,i);
            component_size[rep]++;
        }

        Arrays.sort(component_size);
        return new int[] {component_size[n-1], component_size[n-2]};
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        System.out.println("Enter Number of Vertices: ");
        int vertices = 30000;
//        System.out.println("Enter probability: ");
        double p = (1.0/vertices) + (2.0*Math.pow(vertices,(-4.0/3.0)));
//        System.out.println("Enter number of times to run: ");
        int times = 10;
        double largest = 0;
        double second = 0;
        for (int i = 0; i < times; i++) {
            int[] ret = connections(vertices, p);
            largest += ret[0];
            second += ret[1];
            System.out.println("Run number: " + (i + 1));
            System.out.println("L1 = " + ret[0]);
            System.out.println("L2 = " + ret[1]);
            System.out.println("#################################################################");
        }
        System.out.println("Average largest component: " + largest/times);
        System.out.println("Average second largest component: " + second/times);
    }
}
