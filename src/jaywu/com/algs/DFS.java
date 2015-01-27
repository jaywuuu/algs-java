package jaywu.com.algs;

import java.util.Stack;

public class DFS {
	private Graph G;
	private int start;
	private int [] pathTo;
	private int [] allPathTo;
	private boolean [] visited;
	
	public DFS(Graph g, int s) {
		G = g;
		start = s;
		pathTo = new int[G.vertices()];
		visited = new boolean[G.vertices()];
		allPathTo = new int [G.vertices()];
		for (int i = 0; i < G.vertices(); i++) {
			pathTo[i] = -1;
			visited[i] = false;
		}
		
	}
	
	public void findPath(int end) {
		findPath(G, start, end, pathTo);
	}
	
	public static void findPath(Graph g, int start, int end, int [] path) {
		int current = start;
		boolean [] visited = new boolean[g.vertices()];
		for (int v = 0; v < g.vertices(); v++) {
			visited[v] = false;
		}
		Stack<Integer> vStack = new Stack<Integer>();
		vStack.push(current);
		
		while (!vStack.isEmpty()) {
			current = vStack.pop();
			
			/* if end, we found it */
			if (current == end) break;
			
			/* if visited, next */
			if (visited[current]) continue;
			
			/* push all connected vertices onto stack */
			for (int i : g.getAdjList(current)) {
				if (!visited[i]) {
					vStack.push(i);
					path[current] = i;
				}
			}
			
			/* mark visited */
			visited[current] = true;
		}
	}
	
	public void findAllPaths() {
		findAllPaths(G, start, allPathTo, visited);
	}
	
	/* recursive solution to fill out all paths */
	public static void findAllPaths(Graph g, int v, int [] path, boolean [] visited) {
		visited[v] = true;
		for (int node : g.getAdjList(v)) {
			if (!visited[node]) {
				findAllPaths(g, node, path, visited);
				path[node] = v;
			}
		}
	}
	
	/* this doesn't work unless the two vertices are already explored
	 * via dfs.
	 */
	public boolean isConnected(int a, int b) {
		int current = a;
		while (pathTo[current] >= 0) {
			if (pathTo[current] == b) return true;
			current = pathTo[current];
		}
		return false;
	}
	
	/* returns path as a String */
	public String pathToString() {
		StringBuffer strbuf = new StringBuffer();
		for (int i = start; i != -1; i = pathTo[i]) {
			strbuf.append(i + " --> ");
		}
		strbuf.delete(strbuf.length()-5, strbuf.length());
		return strbuf.toString();
	}
	
	public String allPathToString() {
		StringBuffer strbuf = new StringBuffer();
		for (int i = 0; i < allPathTo.length; i ++) {
			strbuf.append("[" + i + " --> " + allPathTo[i] + "], ");
		}
		strbuf.delete(strbuf.length()-2, strbuf.length());
		return strbuf.toString();
	}
	
	public String pathTo(int v) {
		if (!visited[v]) return ("No Path");
		Stack<Integer> path = new Stack<Integer>();
		for (int node = v; node != start; node = allPathTo[node]) {
			path.push(node);
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
