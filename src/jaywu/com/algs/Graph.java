package jaywu.com.algs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Graph {
	private List<List<Integer>> adj;
	private List<List<Integer>> weight;
	private int edges;
	private int vertices;
	public boolean directed = false;
		
	public Graph(int v) {
		vertices = v;
		edges = 0;
		adj = new ArrayList<List<Integer>>(v);
		weight = new ArrayList<List<Integer>>(v);
		for (int i = 0; i < v; i++) {
			adj.add(new ArrayList<Integer>());
			weight.add(new ArrayList<Integer>());
		}
	}
	
	public Graph(int v, boolean d) {
		vertices = v;
		edges = 0;
		adj = new ArrayList<List<Integer>>(v);
		weight = new ArrayList<List<Integer>>(v);
		for (int i = 0; i < v; i++) {
			adj.add(new ArrayList<Integer>());
			weight.add(new ArrayList<Integer>());
		}
		directed = d;
	}
	
	public int edges() { return edges; }
	public int vertices() { return vertices; }

	public List<Integer> getAdjList(int v) {
		return adj.get(v);
	}
	
	/* get list iterator */
	public ListIterator<Integer> getAdjListIterator(int v) {
		return adj.get(v).listIterator();
	}
	
	public void addEdge(int a, int b) {
		if (!checkBounds(a) || !checkBounds(b)) return;
		if (isEdge(a, b)) return;
		
		adj.get(a).add(b);
		if (!directed) adj.get(b).add(a);
		edges++;
	}
	
	public boolean isEdge(int a, int b) {
		if (!checkBounds(a) || !checkBounds(b)) return false;
		for (int i : adj.get(a)) {
			if (i == b) return true;
		}
		return false;
	}
	
	public void removeEdge(int a, int b) {
		if (!checkBounds(a) || !checkBounds(b)) return;
		int index = -1;
		for (int i = 0; i < adj.get(a).size(); i++) {
			if (adj.get(a).get(i) == b) {
				index = i;
				break;
			}
		}
		
		if (index >= 0) adj.get(a).remove(index);
		
		if (!directed) {
			index = -1;
			for (int i = 0; i < adj.get(b).size(); i++) {
				if (adj.get(b).get(i) == a) {
					index = i;
					break;
				}
			}
			if (index >= 0) adj.get(b).remove(index);
		}
		edges--;
	}
	
	public Iterator<List<Integer>> iterator() { return new GraphIterator(); }
	
	private class GraphIterator implements Iterator<List<Integer>> {
		private int i = 0;
		
		@Override
		public boolean hasNext() {
			if (i < vertices) return true;
			return false;
		}

		@Override
		public List<Integer> next() {
			return adj.get(i++);
		}

		@Override
		public void remove() {
			// not supproted
		}
	}
	
	private boolean checkBounds(int n) {
		if (n < 0) return false;
		if (n > vertices) return false;
		return true;
	}
	
	public String toString() {
		StringBuffer strbuf = new StringBuffer();
		int v = 0;
		for (List<Integer> adjList : adj) {
			strbuf.append("{" + v + " --> ");
			v++;
			for (int i : adjList) {
				strbuf.append(i + ", ");
			}
			
			if (!adjList.isEmpty()) {
				strbuf.delete(strbuf.length()-2, strbuf.length());
			}
			
			strbuf.append("}, ");
		}
		strbuf.delete(strbuf.length()-2, strbuf.length());
		
		return strbuf.toString();
	}
}
