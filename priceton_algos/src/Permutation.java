


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
	
    public static void main(String[] args) {
        int k = 3;

        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String string = StdIn.readString();
            queue.enqueue(string);
        }

        for (int i = 0; i < k; i ++) {
            StdOut.println(queue.dequeue());
        }
    }
    
}
