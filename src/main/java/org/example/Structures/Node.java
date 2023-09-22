package org.example.Structures;

import java.util.Objects;

public class Node {

    public long label;

    public boolean isExplored;

    // l ' ordre dans la topologie
    public long f;
    public Node(long labelle) {
        this.label = labelle;
        f = labelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return label == node.label;
    }

}
