package jaywu.com.algs;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class BFS {
	private Graph G;
	private int [] pathTo;
	private int start;
	
	public BFS(Graph g, int s) {
		start = s;
		G = g;
		pathTo = new int[G.vertices()];
	}
	
	public void findPath(int end) {
		findPath(G, start, end, pathTo);
	}
	
	public static void findPath(Graph g, int start, int end, int [] pathTo) {
		int current = start;
		boolean [] visited = new boolean[g.vertices()];
		Queue<Integer> pathQ = new ArrayDeque<Integer>();
		pathQ.add(current);
		while (!pathQ.isEmpty()) {
			current = pathQ.remove();
			
			if (visited[current]) continue;
			
			if (current == end) break;
			
			for (int node : g.getAdjList(current)) {
				if (!visited[node]) {
					pathQ.add(node);
					pathTo[node] = current;
				}
			}
			
			visited[current] = true;
		}
	}
	
	public String pathTo(int end) {
		Stack<Integer> path = new Stack<Integer>();
		for (int v = end; v != start; v = pathTo[v]) {
			path.push(v);
		}
		path.push(start);
		StringBuffer strbuf = new StringBuffer();
		while (!path.isEmpty()) {
			strbuf.append(path.pop() + " --> ");
		}
		strbuf.delete(strbuf.length()-5, strbuf.length());
		return strbuf.toString();
	}
}
