package model.dungeon;

import java.util.LinkedList;

public class DungeonGraph {
    public class Edge {
        public int weight;
        public int v1, v2;

        public Edge(int v1, int v2) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }

    LinkedList<Edge>[] edgeAdjacencyLists;

    public DungeonGraph(Tile[][] map) {
        edgeAdjacencyLists = (LinkedList<Edge>[])new LinkedList[map.length*map.length];
        for (int i = 0; i < edgeAdjacencyLists.length; i++) {
            edgeAdjacencyLists[i] = new LinkedList<>();
        }
        arrayToAdjacencyList(map);
        System.out.print("Hallo");
    }

    public int V() {
        return edgeAdjacencyLists.length;
    }

    public void addEdge(int v1, int v2) {
        edgeAdjacencyLists[v1].add(new Edge(v1, v2));
        if (v1 != v2) {
            edgeAdjacencyLists[v2].add(new Edge(v1, v2));
        }
    }

    private void arrayToAdjacencyList(Tile[][] map) {
        for (int i = 1; i < map.length-1; i++) {
            for (int j = 1; j < map[i].length-1; j++) {
                if (!(map[i][j] instanceof Unwalkable)) {
                    if (!(map[i-1][j] instanceof Unwalkable)) {
                        addEdge(i*map.length+j, (i-1)*map.length+j);
                    }
                    if (!(map[i+1][j] instanceof Unwalkable)) {
                        addEdge(i*map.length+j, (i+1)*map.length+j);
                    }
                    if (!(map[i][j-1] instanceof Unwalkable)) {
                        addEdge(i*map.length+j, i*map.length+j-1);
                    }
                    if (!(map[i][j+1] instanceof Unwalkable)) {
                        addEdge(i*map.length+j, i*map.length+j+1);
                    }
                }
            }
        }
    }
}
