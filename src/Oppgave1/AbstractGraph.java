package Oppgave1;

import java.util.*;

public abstract class AbstractGraph<V> implements Graph<V> {
    protected List<V> vertices = new ArrayList<>();
    protected List<List<Edge>> neighbors = new ArrayList<>();

    protected AbstractGraph() {
    }

    protected AbstractGraph(V[] vertices, int[][] edges) {
        for (int i = 0; i < vertices.length; i++)
            addVertex(vertices[i]);

        createAdjacencyLists(edges, vertices.length);
    }

    protected AbstractGraph(List<V> vertices, List<Edge> edges) {
        for (int i = 0; i < vertices.size(); i++)
            addVertex(vertices.get(i));

        createAdjacencyLists(edges, vertices.size());
    }

    protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++)
            addVertex((V)(new Integer(i)));

        createAdjacencyLists(edges, numberOfVertices);
    }

    protected AbstractGraph(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++)
            addVertex((V)(new Integer(i)));

        createAdjacencyLists(edges, numberOfVertices);
    }

    private void createAdjacencyLists(
            int[][] edges, int numberOfVertices) {
        for (int i = 0; i < edges.length; i++) {
            addEdge(edges[i][0], edges[i][1]);
        }
    }

    private void createAdjacencyLists(
            List<Edge> edges, int numberOfVertices) {
        for (Edge edge: edges) {
            addEdge(edge.startVertex, edge.endVertex);
        }
    }

    @Override
    public int getSize() {
        return vertices.size();
    }

    @Override
    public List<V> getVertices() {
        return vertices;
    }

    @Override
    public V getVertex(int index) {
        return vertices.get(index);
    }

    @Override
    public int getIndex(V vertex) {
        return vertices.indexOf(vertex);
    }

    @Override
    public List<Integer> getNeighbors(int index) {
        List<Integer> result = new ArrayList<>();
        for (Edge e: neighbors.get(index))
            result.add(e.endVertex);

        return result;
    }

    @Override
    public int getDegree(int vertex) {
        return neighbors.get(vertex).size();
    }

    @Override
    public void printEdges() {
        for (int u = 0; u < neighbors.size(); u++) {
            System.out.print(getVertex(u) + " (" + u + "): ");
            for (Edge e: neighbors.get(u)) {
                System.out.print("(" + getVertex(e.startVertex) + ", " +
                        getVertex(e.endVertex) + ") ");
            }
            System.out.println();
        }
    }

    @Override
    public void clear() {
        vertices.clear();
        neighbors.clear();
    }

    @Override
    public boolean addVertex(V vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            neighbors.add(new ArrayList<Edge>());
            return true;
        }
        else {
            return false;
        }
    }

    protected boolean addEdge(Edge edge) {
        if (edge.startVertex < 0 || edge.startVertex > getSize() - 1)
            throw new IllegalArgumentException("No such index: " + edge.startVertex);

        if (edge.endVertex < 0 || edge.endVertex > getSize() - 1)
            throw new IllegalArgumentException("No such index: " + edge.endVertex);

        if (!neighbors.get(edge.startVertex).contains(edge)) {
            neighbors.get(edge.startVertex).add(edge);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean addEdge(int startVertex, int endVertex) {
        return addEdge(new Edge(startVertex, endVertex));
    }

    public static class Edge {
        public int startVertex;
        public int endVertex;

        public Edge(int startVertex, int endVertex) {
            this.startVertex = startVertex;
            this.endVertex = endVertex;
        }

        public boolean equals(Object obj) {
            return startVertex == ((Edge)obj).startVertex && endVertex == ((Edge)obj).endVertex;
        }
    }

    @Override
    public Tree dfs(int indexOfVertex) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++)
            parent[i] = -1;

        boolean[] isVisited = new boolean[vertices.size()];

        dfs(indexOfVertex, parent, searchOrder, isVisited);

        return new Tree(indexOfVertex, parent, searchOrder);
    }

    private void dfs(int indexOfVertex, int[] parent, List<Integer> searchOrder, boolean[] isVisited) {

        searchOrder.add(indexOfVertex);
        isVisited[indexOfVertex] = true;

        for (Edge edge : neighbors.get(indexOfVertex)) {
            if (!isVisited[edge.endVertex]) {
                parent[edge.endVertex] = indexOfVertex;
                dfs(edge.endVertex, parent, searchOrder, isVisited);
            }
        }
    }

    public Tree dfsStack(int indexOfVertex) {
        List<Integer> searchOrder = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] isVisited = new boolean[vertices.size()];
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++){
            parent[i] = -1;
        }
        int currentIndex = indexOfVertex;

        stack.push(currentIndex);
        searchOrder.add(currentIndex);
        isVisited[currentIndex] = true;
        while (!stack.isEmpty()){
            int neighbor = getUnvisitedNeighbor(currentIndex, isVisited);
                if(neighbor == -1){
                    stack.pop();
                    if(!stack.isEmpty()) {
                        currentIndex = stack.peek();
                    }
                }else {
                    searchOrder.add(neighbor);
                    parent[neighbor] = currentIndex;
                    isVisited[neighbor] = true;
                    stack.push(neighbor);
                    currentIndex = neighbor;
                }
            }
        return new Tree(indexOfVertex, parent, searchOrder);
    }


    private int getUnvisitedNeighbor (int index, boolean[] isVisited){
        for (Integer neighbor : getNeighbors(index)) {
            if (isVisited[neighbor] == false){
                return neighbor;
            }
        }
        return -1;
    }

    public Boolean isCyclic() {
        for (int j = 0; j < vertices.size(); j++) {
            if (isCyclicHelper(j)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCyclicHelper(int current) {
        boolean[] isvisited = new boolean[vertices.size()];
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        int currentIndex = current;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(currentIndex);
        isvisited[currentIndex] = true;
        while (!queue.isEmpty()) {
            currentIndex = queue.poll();
            for (Edge edge : neighbors.get(currentIndex)) {
                if (isvisited[edge.endVertex] && edge.endVertex != parent[currentIndex]) {
                    return true;
                } else if (!isvisited[edge.endVertex]) {
                    queue.offer(edge.endVertex);
                    parent[edge.endVertex] = currentIndex;
                    isvisited[edge.endVertex] = true;
                }
            }
        }
        return false;
    }

    public boolean isConnected() {
        Tree bfs = bfs(0);
        return  (bfs.getNumberOfVerticesFound() == vertices.size());
    }

    @Override
    public Tree bfs(int v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++)
            parent[i] = -1;

        java.util.LinkedList<Integer> queue =
                new java.util.LinkedList<>();
        boolean[] isVisited = new boolean[vertices.size()];
        queue.offer(v);
        isVisited[v] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            searchOrder.add(u);
            for (Edge e: neighbors.get(u)) {
                if (!isVisited[e.endVertex]) {
                    queue.offer(e.endVertex);
                    parent[e.endVertex] = u;
                    isVisited[e.endVertex] = true;
                }
            }
        }

        return new Tree(v, parent, searchOrder);
    }

    public List<Integer> getPath(int startVertex, int endVertex) {
        LinkedList<Integer> shortestPath = new LinkedList<>();
        Tree shortPathTree = bfs(startVertex);
        if(startVertex == endVertex){
            shortestPath.add(shortPathTree.getRoot());
            return shortestPath;
        }
        int nextParent = endVertex;
        while (true){
            shortestPath.addFirst(nextParent);
            nextParent = shortPathTree.getParent(nextParent);
            if(nextParent == -1){
                break;
            }
        }
        return shortestPath;
    }

    public class Tree {
        private int root;
        private int[] parent;
        private List<Integer> searchOrder;

        public Tree(int root, int[] parent, List<Integer> searchOrder) {
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }

        public int getRoot() {
            return root;
        }

        public int getParent(int v) {
            return parent[v];
        }

        public List<Integer> getSearchOrder() {
            return searchOrder;
        }

        public int getNumberOfVerticesFound() {
            return searchOrder.size();
        }

        public List<V> getPath(int index) {
            ArrayList<V> path = new ArrayList<>();

            do {
                path.add(vertices.get(index));
                index = parent[index];
            }
            while (index != -1);

            return path;
        }

        public void printPath(int index) {
            List<V> path = getPath(index);
            System.out.print("A path from " + vertices.get(root) + " to " +
                    vertices.get(index) + ": ");
            for (int i = path.size() - 1; i >= 0; i--)
                System.out.print(path.get(i) + " ");
        }

        public void printTree() {
            System.out.println("Root is: " + vertices.get(root));
            System.out.print("Edges: ");
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] != -1) {
                    System.out.print("(" + vertices.get(parent[i]) + ", " +
                            vertices.get(i) + ") ");
                }
            }
            System.out.println();
        }
    }
}