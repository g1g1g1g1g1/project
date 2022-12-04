import java.lang.reflect.Array;
import java.util.ArrayList;

public class RegNet
{
    //kruskal
    private static ArrayList<Edge> kruskal(Graph G) {
        ArrayList<Edge> q = G.sortedEdges(); //PQ
        UnionFind uf = new UnionFind(G.V());

        ArrayList<Edge> t = new ArrayList<>();

        while (t.size() < G.V() - 1) {
            Edge uv = q.remove(0);

            if (!uf.connected(uv.ui(), uv.vi())) {
                t.add(uv);
                uf.union(uv.ui(), uv.vi());
            }
        }

        return t;
    }

    private static int[] bfs(Graph G) {
        BetterQueue<Integer> q = new BetterQueue<>();
        int[] dist = new int[G.V()];

        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        int start = G.edges().get(0).ui();

        q.add(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int curr = q.remove();

            for (int v : G.adj(curr)) {
                if (dist[v] == Integer.MAX_VALUE) {
                    dist[v] = dist[curr] + 1;
                    q.add(v);
                }
            }
        }

        for (int i = 0; i < dist.length; i++) {
            System.out.println("DIST: " + dist[i]);
        }

        return dist;
    }

    //find stops
    private static ArrayList<Edge> findStops(Graph G) {
        ArrayList<Edge> edges = G.sortedEdges();
        ArrayList<Integer> stops = new ArrayList<>();

        //get the edges from bfs with distance
        int[] stop = bfs(G);

        for (int i = 0; i < stop.length; i++) {
            stops.add(stop[i]);
        }

        DistQueue heap = new DistQueue(edges.size());

        for (int i = 0; i < heap.size(); i++) {
            heap.insert(i, stops.get(i));
        }

        ArrayList<Edge> flipped = new ArrayList<>();
        BetterStack<Edge> s = new BetterStack<>();

        for (int i = 0; i < edges.size(); i++) {
            s.push(edges.get(heap.delMin()));
        }

        for (int i = 0; i < heap.size(); i++) {
            flipped.add(s.pop());
        }

        return flipped;
    }

    //creates a regional network
    //G: the original graph
    //max: the budget
    public static Graph run(Graph G, int max) 
    {
	    //TODO
        System.out.println("BUDGET: " + max);
        ArrayList<Edge> kruskal = kruskal(G); //MST

        Graph mst = new Graph(G.V()); //New graph with MST
        mst.setCodes(G.getCodes()); //Set airport codes

        for (Edge edge : kruskal) { //add edges to new graph
            mst.addEdge(edge);
        }

        //delete edges if budget is exceeded
        int lastIndex = kruskal.size() - 1;

        while (mst.totalWeight() >= max) {
            Edge temp = kruskal.get(lastIndex);

            //check if it disconnects the graph
            if (mst.deg(temp.u) == 1 || mst.deg(temp.v) == 1) {
                mst.removeEdge(temp);
                kruskal.remove(lastIndex);
                lastIndex = kruskal.size() - 1;
            } else {
                lastIndex--;
            }
        }

        mst = mst.connGraph();

        //System.out.println("CURRENT: " + mst.edges());
        //find number of stops between vertices
        ArrayList<Edge> stops = findStops(mst);

        //add edges until budget
        int firstIndex = 0;
        while (mst.totalWeight() <= max || firstIndex != stops.size()) {
            Edge temp2 = stops.get(firstIndex);

            if (mst.totalWeight() + temp2.w <= max) {
                mst.addEdge(temp2);
            } else {
                firstIndex++;
            }
        }

        return mst;
    }
}