package org.example.Structures;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Graph {

    public Map<Node,List<Node>> adjacentNode = new HashMap<>();

    private final Map<Long,Node> nodesByLabel = new HashMap<>();

    private Stack<Node> nodeStackForIterate = new Stack<>();

    public long nbNode;

    public Node getNodeIfExist(long node) {
        return nodesByLabel.get(node);
    }

    private Node addNode(long node) {
        Node nodeIfExist = getNodeIfExist(node);
        Node node1;

        if (nodeIfExist == null) {
            node1 = new Node(node);
            nodesByLabel.put(node,node1);
            pushInStack(node1);
            nbNode ++;
            return node1;
        }

        return nodeIfExist;
    }

    private void addEdge(long tail,long head) {
        Node v1 = addNode(tail);
        Node v2 = addNode(head);
        adjacentNode.computeIfAbsent(v1, k -> new ArrayList<>());

        adjacentNode.get(v1).add(v2);
    }

    public void addEdge(String node, String node1) {
        addEdge(Long.parseLong(node),Long.parseLong(node1));
    }

    public List<Node> getEdgeOutOf(Node n) {
        List<Node> nodes1 = adjacentNode.get(n);
        return nodes1 == null ? new ArrayList<>():
                nodes1;
    }

    public void restaureStack() {
        nodeStackForIterate = new Stack<>();
    }

    public void pushInStack(Node n) {
         nodeStackForIterate.push(n);
    }

    public Node popFromStack() {
        return nodeStackForIterate.pop();
    }

    public boolean isEmptyStack() {
        return nodeStackForIterate.empty();
    }

}
