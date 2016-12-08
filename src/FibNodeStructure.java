
public class FibNodeStructure {

    private int data; //data of node

    private FibNodeStructure childptr; //the child pointer

    private FibNodeStructure left; //left sibling pointer

    private FibNodeStructure parent; //parent pointer

    private FibNodeStructure right; //right sibling pointer

    private boolean childCut; //child cut indicator

    private int degree; //degree of the node
    
    private String hashtag; //hashtag name

	/**
	 * @return the data
	 */
	public int getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(int data) {
		this.data = data;
	}

	/**
	 * @return the childptr
	 */
	public FibNodeStructure getChildptr() {
		return childptr;
	}

	/**
	 * @param childptr the childptr to set
	 */
	public void setChildptr(FibNodeStructure childptr) {
		this.childptr = childptr;
	}

	/**
	 * @return the left
	 */
	public FibNodeStructure getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(FibNodeStructure left) {
		this.left = left;
	}

	/**
	 * @return the parent
	 */
	public FibNodeStructure getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(FibNodeStructure parent) {
		this.parent = parent;
	}

	/**
	 * @return the right
	 */
	public FibNodeStructure getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(FibNodeStructure right) {
		this.right = right;
	}

	/**
	 * @return the childCut
	 */
	public boolean isChildCut() {
		return childCut;
	}

	/**
	 * @param childCut the childCut to set
	 */
	public void setChildCut(boolean childCut) {
		this.childCut = childCut;
	}

	/**
	 * @return the degree
	 */
	public int getDegree() {
		return degree;
	}

	/**
	 * @param degree the degree to set
	 */
	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	
	/**
	 * @return the hashtag
	 */
	public String getHashtag() {
		return hashtag;
	}

	/**
	 * @param hashtag the hashtag to set
	 */
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	/**
	 * creates a Fibonaaci Node structure object by using the data
	 * @param data
	 */
	public FibNodeStructure(int data) {
		super();
		this.data = data;
		this.childCut=false;
		this.degree=0;
	}


    
}
