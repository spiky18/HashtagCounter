
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

public class hashtagcounter {

	public static void main(String[] args) throws IOException {
		// hashtable which contains hashtag and node pointer
		Hashtable<String, FibNodeStructure> hashtags = new Hashtable<String, FibNodeStructure>();
		// heap instance
		FibonacciHeap heap = new FibonacciHeap();
		// dynamic array to store removed array
		ArrayList<FibNodeStructure> removedNodes = new ArrayList<FibNodeStructure>();

		String fileName = args[0];

		FileReader fileReader = new FileReader(fileName);

		BufferedReader bufferedReader = new BufferedReader(fileReader);
		// reads input line by line from the given file
		String input = bufferedReader.readLine();
		PrintWriter writer = new PrintWriter("output_file.txt", "UTF-8");

		while (!input.equalsIgnoreCase("stop")) {
			String[] tempArr = input.split(" ");
			if (input.startsWith("#")) {
				if (hashtags.containsKey(tempArr[0])) {
					//if hashtag key is already present increase the key
					heap.increaseKey(hashtags.get(tempArr[0]), Integer.parseInt(tempArr[1]));
				} else {
					//if hashtag key is not present then get the node from hashtable and increase
					FibNodeStructure node = new FibNodeStructure(Integer.parseInt(tempArr[1]));
					node.setHashtag(tempArr[0]);
					hashtags.put(tempArr[0], node);
					heap.insert(node);
				}
			} else {
				int n = Integer.parseInt(input);
				//initialize the string s as empty string
				String s = "";
				while (n > 0) {
					//remove the max node from heap
					FibNodeStructure node = heap.removeMax();
					if (n == 1) {
						//get the hashtag and append to s
						s = s.concat(node.getHashtag().substring(1));
					} else {
						//get the hashtag and append to s
						s = s.concat(node.getHashtag().substring(1) + ",");
					}
					//add removed max node to the dynamic array
					removedNodes.add(node);
					hashtags.put(node.getHashtag(), node);
					//repeat the loop for n queries
					n--;
				}
				//write the string s to output_file
				writer.println(s);
				//reinsert the removed nodes to heap
				for (FibNodeStructure f : removedNodes) {
					f.setChildCut(false);
					f.setChildptr(null);
					f.setDegree(0);
					f.setLeft(null);
					f.setParent(null);
					f.setRight(null);
					heap.insert(f);
				}
				removedNodes.clear();

			}
			input = bufferedReader.readLine();
		}
		writer.close();
		bufferedReader.close();
		fileReader.close();
	}

}
