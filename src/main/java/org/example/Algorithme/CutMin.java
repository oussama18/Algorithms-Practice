package org.example.Algorithme;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class CutMin {

    private List<List<Integer>> listVer;

    private Map<Integer, AbstractMap.SimpleEntry<Integer,Integer>> edges;

    public int getMin() {
        return min;
    }

    private int min=200;

    public CutMin() {
        listVer = new ArrayList<>(201);
        edges = new HashMap<>();

        readFileGraph("Graph.txt");


        int i=1;

          while(i<1000)   {
              getMinCut();
            i++;
          }
    }

    public void readFileGraph(String fileName) {
        File file = new File(
                fileName);
        try (BufferedReader br
                     = new BufferedReader(new FileReader(file))) {

            String st;

            while ((st = br.readLine()) != null) {
                var lineStrip = st.strip().split("\t");

                var listVarses = new ArrayList<Integer>();

                Arrays.stream(Arrays.copyOfRange(lineStrip, 1, lineStrip.length))
                        .forEach(e -> listVarses.add(Integer.parseInt(e)));


                listVer.add(listVarses);
              //  listVer.set(Integer.parseInt(lineStrip[0]), listVarses);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getMinCut() {
        int i=200;

        int i1 =1;

        Map<Integer, AbstractMap.SimpleEntry<Integer,Integer>> edges = new HashMap<>();



        int nbEdges = 0;
        for (List<Integer> noeudAtract : listVer) {
            for (Integer noued : noeudAtract) {
                int finalI = i1;
                if(edges.values().stream().noneMatch(e -> ((e.getValue().equals(noued)&& e.getKey().equals(finalI))
                || (e.getValue().equals(finalI)&& e.getKey().equals(noued))))) {
                    edges.put(nbEdges,new AbstractMap.SimpleEntry<>(i1,noued));
                    nbEdges++;
                }

            }
            i1++;
        }



        while(i>2) {
            var edgeIndex = edges.keySet().
                    toArray()[(int)(Math.random() * edges.keySet().size())];
            var edge = edges.get(edgeIndex);
           // System.out.println("source: " + edge.getKey() + " , arrivee: " + edge.getValue() + "  , iter : " + i);


            for (Map.Entry<Integer,AbstractMap.SimpleEntry<Integer,Integer>> en:edges.entrySet()) {
                if(en.getValue().getKey().equals(edge.getKey()) || en.getValue().getKey().equals(edge.getValue()) ) {

                    var val = en.getValue().getValue();
                    en.setValue(new AbstractMap.SimpleEntry<>(edge.getKey(),val));
                }
                if(en.getValue().getValue().equals(edge.getKey()) || en.getValue().getValue().equals(edge.getValue())) {
                    en.getValue().setValue(edge.getKey());
                }
            }

            edges.entrySet().removeIf((key) -> (key.getValue().getKey().equals(key.getValue().getValue())));

            i--;
        }



        if(min > edges.size())
            min = edges.size();

//        edges.forEach((key,value)-> System.out.println(value));
//        System.out.println(edges.size());
    }

    public static void main(String[] args) {
        var cutmin = new CutMin();
        System.out.println(cutmin.min);
    }
}
