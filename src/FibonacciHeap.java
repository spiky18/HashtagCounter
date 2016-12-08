
import java.util.HashMap;

public class FibonacciHeap {

	FibNodeStructure maxptr; // max node indicator

	/**
	 * Inserts into fibonacci heap ensures max key is always the first key of
	 * the Fibonacci heap
	 * If the inserted node data is greater then maxptr's data update the maxptr  
	 * @param node
	 */
	public void insert(FibNodeStructure node) {
		if (maxptr != null) {
			if (node.getData() > maxptr.getData()) {
				node.setRight(maxptr);
				maxptr.setLeft(node);
				maxptr = node;
			} else {
				node.setLeft(maxptr);
				if (maxptr.getRight() != null) {
					node.setRight(maxptr.getRight());
					maxptr.getRight().setLeft(node);
				}
				maxptr.setRight(node);
			}

		} else {
			maxptr = node;
		}
	}

	/**
	 * removes the max node and perform pairwise combining
	 * 
	 * @returns the max node
	 */
	public FibNodeStructure removeMax() {
		FibNodeStructure temp = maxptr;
		FibNodeStructure count;
		if (maxptr.getChildptr() != null) {
			count = maxptr.getChildptr();
			while (count != null) {
				FibNodeStructure tempNode=count;
				count = count.getRight();
				tempNode.setParent(null);
				tempNode.setRight(null);
				tempNode.setLeft(null);
				insert(tempNode); // inserting all children in top level doubly
								// linked list
				
			}
		}
		if (maxptr.getRight() != null) {
			maxptr = maxptr.getRight(); // setting up temporary max pointer
			maxptr.setLeft(null);
		} else {
			maxptr = null;
		}
		// rearranging the heap by doing pairwise combine operation
		if (maxptr != null) {
			HashMap<Integer, FibNodeStructure> degreeTable = new HashMap<Integer, FibNodeStructure>();
			count = maxptr;
			while (count != null) {
				int degree = count.getDegree();
				FibNodeStructure tmp = count;
				count = count.getRight(); 
				while (degreeTable.containsKey(degree)) {
					FibNodeStructure node = degreeTable.get(degree);
					tmp = cmpNCmb(node, tmp);
					degreeTable.remove(degree);
					degree++;
				}
				degreeTable.put(degree, tmp);
				
			}
			// recreating heap by inserting combined nodes
			maxptr = null;
			for (int d : degreeTable.keySet()) {
				FibNodeStructure tmp=degreeTable.get(d);
				tmp.setLeft(null);
				tmp.setRight(null);
				insert(degreeTable.get(d));
			}
		}
		return temp;
	}

	/**
	 * combines same degree nodes by comparing data values and returns combined
	 * output node
	 * 
	 * @param node1
	 * @param node2
	 * @return
	 */
	private FibNodeStructure cmpNCmb(FibNodeStructure node1, FibNodeStructure node2) {
		if (node1.getData() > node2.getData()) {
			FibNodeStructure child = node1.getChildptr();
			if (node2.getLeft() != null)
				node2.getLeft().setRight(node2.getRight());
			if (node2.getRight() != null)
				node2.getRight().setLeft(node2.getLeft());
			if (child != null) {
				child.setLeft(node2);
				node2.setRight(child);
				node2.setLeft(null);
				node2.setParent(node1);
				node1.setChildptr(node2);
			} else {
				node1.setChildptr(node2);
				node2.setParent(node1);
				node2.setLeft(null);
				node2.setRight(null);
			}
			node1.setDegree(node1.getDegree() + 1);
			return node1;
		} else {
			FibNodeStructure child = node2.getChildptr();
			if (node1.getLeft() != null)
				node1.getLeft().setRight(node1.getRight());
			if (node1.getRight() != null)
				node1.getRight().setLeft(node1.getLeft());
			if (child != null) {
				child.setLeft(node1);
				node1.setRight(child);
				node1.setLeft(null);
				node2.setChildptr(node1);
				node1.setParent(node2);
				// count
			} else {
				node2.setChildptr(node1);
				node1.setParent(node2);
				node1.setLeft(null);
				node1.setRight(null);
			}
			node2.setDegree(node2.getDegree() + 1);
			return node2;
		}

	}

	/**
	 * inserts the node into toplevel doubly linked list if parent's chidCut
	 * flag is false sets it to true else performs cascading cut on parent
	 * 
	 * @param node
	 */
	public void cascadingCut(FibNodeStructure node) {

		FibNodeStructure parent = node.getParent();

		if (node.getLeft() != null)
			node.getLeft().setRight(node.getRight());
		if (node.getRight() != null)
			node.getRight().setLeft(node.getLeft());

		if (parent != null && parent.getChildptr() == node) {
			parent.setChildptr(node.getRight());
			
		}
		node.setParent(null);
		node.setLeft(null);
		node.setRight(null);
		insert(node);
		if (parent != null) {
			parent.setDegree(parent.getDegree()-1);
			boolean childCut = parent.isChildCut();
			if (!childCut)
				parent.setChildCut(true);
			else
				cascadingCut(parent);
		}
	}

	/**
	 * Increase the node data value by d
	 * 
	 * @param node
	 * @param d
	 */
	public void increaseKey(FibNodeStructure node, int d) {
		node.setData(node.getData() + d);
		if (node.getParent() != null && node.getParent().getData() < node.getData()) {
			cascadingCut(node);
		}

		else {
			if(maxptr.getData()<node.getData()){
				if (node.getLeft() != null)
					node.getLeft().setRight(node.getRight());
				if (node.getRight() != null)
					node.getRight().setLeft(node.getLeft());
				maxptr.setLeft(node);
				node.setRight(maxptr);
				node.setLeft(null);
				maxptr=node;
			}
		}

	}
}
