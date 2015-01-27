package jaywu.com.algs;

public class Algs_test {
	public static void main(String [] args) {
		testBFS();
	}
	
	public static void testGraph() {
		Graph g = new Graph(5);
		g.addEdge(1, 3);
		g.addEdge(0, 4);
		g.addEdge(1, 2);
		assert (g.isEdge(1, 3));
		assert (g.isEdge(1, 2));
		assert (g.isEdge(0, 4));
		System.out.println(g.toString());
		g.removeEdge(1, 3);
		assert !(g.isEdge(1, 3));
		assert !(g.isEdge(0, 3));
		System.out.println(g.toString());
		System.out.println("All clear.");
	}
	
	public static void testDFS() {
		Graph g = new Graph(5);
		g.addEdge(1, 3);
		g.addEdge(0, 4);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		System.out.println(g.toString());
		DFS dfs = new DFS(g, 1);
		dfs.findPath(4);
		System.out.println(dfs.isConnected(0,  4));
		System.out.println(dfs.pathToString());
	}
	
	public static void tsetDFSAll() {
		Graph g = new Graph(5);
		g.addEdge(1, 3);
		g.addEdge(0, 4);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		System.out.println(g.toString());
		DFS dfs = new DFS(g, 1);
		dfs.findAllPaths();
		System.out.println(dfs.allPathToString());
		System.out.println(dfs.pathTo(4));
	}
	
	public static void testBFS() {
		Graph g = new Graph(11);
		g.addEdge(1, 3);
		g.addEdge(0, 4);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(1, 5);
		g.addEdge(1, 6);
		g.addEdge(6, 7);
		g.addEdge(6, 8);
		g.addEdge(7, 9);
		g.addEdge(9, 10);
		System.out.println(g.toString());
		BFS bfs = new BFS(g, 0);
		bfs.findPath(10);
		System.out.println(bfs.pathTo(10));
	}
}
