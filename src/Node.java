import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Node {
	public String word;
	private HashMap<String, Node> children;
	private int size = 0;

	public Node(String value) {
		this.word = value;
		this.children = new HashMap<>();
	}

	public void addChild(Node node) {
		this.children.put(node.word, node);
		this.size++;
	}

	public Node getChild(String word) {
		return this.children.getOrDefault(word, null);
	}

	public boolean contains(String word) {
		return this.children.containsKey(word);
	}

	public int size() {
		return this.size;
	}

	public Iterator<Node> children() {
		return this.children.values().iterator();
	}

	public Set<String> values() {
		HashSet<String> set = new HashSet<>();
		Iterator iterator = this.children();
		while (iterator.hasNext()) {
			Node node = (Node) iterator.next();
			set.add(node.word);
		}
		return set;
	}

	public String toString() {
		return this.word + ": " + this.values();
	}
}
