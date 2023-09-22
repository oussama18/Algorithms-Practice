package org.example.Algorithme;

import org.example.Structures.Graph;
import org.example.Structures.Node;

import java.util.List;
import java.util.Stack;

public class KosarajuSCC {

    private final Graph directGraph;
    private final Graph graphVer;

    private long time = 0;

    private Node leader = null;

    private long [] maxFiveComponentSize ;

    private long nbComponentCurrent = 0;

    private int numItrInAlgorithm = 1;

    public KosarajuSCC(Graph directGraph, Graph graphVer) {
        this.directGraph = directGraph;
        this.graphVer = graphVer;
    }

    private void dfs(Graph g,Node i){
        i.isExplored = true;
        if (numItrInAlgorithm == 2)
            nbComponentCurrent ++;

        List<Node> edgeOutOfi = g.getEdgeOutOf(i);
        for (Node node : edgeOutOfi) {
            if (!node.isExplored) {
                node.isExplored = true;
                dfs(g,node);
            }
        }
        time++;
        if(numItrInAlgorithm == 1) {
            directGraph.pushInStack(directGraph.getNodeIfExist(i.label));
        }

    }

    private void dfs_Loop(Graph g) {
        time = 0;
        restaureLeader();
        while (!g.isEmptyStack()){

            Node node = g.popFromStack();
            if (!node.isExplored){
                setLeader(node);
                dfs(g,node);
            }
        }
        finishLeaders();
    }

    private void restaureLeader() {
        leader = null;
        maxFiveComponentSize = new long[5];
        nbComponentCurrent = 0;
    }

    private void finishLeaders(){
        leader = null;
        setTableMaxFive(nbComponentCurrent);
        nbComponentCurrent = 0;
    }

    private void setLeader(Node le) {
        if (numItrInAlgorithm == 2) {
            setTableMaxFive(nbComponentCurrent);
            nbComponentCurrent = 0;
            leader = le;
        }

    }


    private void setTableMaxFive(long nbComponent) {
        if (nbComponent == 0)
            return;
        for (int i=0;i<5;i++) {
            if (maxFiveComponentSize[i]<nbComponent) {
                long l = maxFiveComponentSize[i];
                maxFiveComponentSize[i] = nbComponent;
                setTableMaxFive(l);
                break;
            }
        }
    }

    public long[] ListOfMaxFiveComponentSize() {
        directGraph.restaureStack();
        dfs_Loop(graphVer);

        numItrInAlgorithm++;

        dfs_Loop(directGraph);

        return maxFiveComponentSize;
    }

    public long getTime() {
        return time;
    }
}
