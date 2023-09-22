package org.example.Algorithme;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MyQuickSortSolution {

    public static int nbFirst=0;
    public static int nbLast =0;
    public static int nbMiddle=0;

    public static void quickSortFirst(int [] tab,int l,int r) {
        if (r-l ==1 || r-l==0){
            return;
        }

        nbFirst += r-l-1;

        int pivot = tab[l];
        int i=l+1;
        for (int j=l+1;j<r;j++) {
            if (tab[j]<pivot){
                int aide = tab[j];
                tab[j] = tab[i];
                tab[i] = aide;
                i++;
            }
        }
        tab[l] = tab[i-1];
        tab[i-1]=pivot;

        quickSortFirst(tab,l,i-1);
        quickSortFirst(tab,i,r);
    }
    public static void quickSortLast(int [] tab,int l,int r) {
        if (r-l==1 || r-l==0){
            return;
        }

        nbLast += r-l-1;

        int pivot = tab[r-1];
        tab[r-1] = tab[l];
        tab[l] = pivot;
        int i=l+1;
        for (int j=l+1;j<r;j++) {
            if (tab[j]<pivot){
                int aide = tab[j];
                tab[j] = tab[i];
                tab[i] = aide;
                i++;
            }
        }
        tab[l] = tab[i-1];
        tab[i-1]=pivot;

        quickSortLast(tab,l,i-1);
        quickSortLast(tab,i,r);
    }

    public static void quickSortMiddle(int [] tab,int l,int r) {

        if (r-l ==1 || r-l==0){
            return;
        }
        if(r-l== 2) {
            if (tab[l] > tab[r-1]) {
                int aide = tab[l];
                tab[l]= tab[r-1];
                tab[r-1] = aide;
                nbMiddle++;
            }
            return;
        }

        nbMiddle += r-l-1;


        int first = tab[l];
        int last = tab[r-1];
        int median=0;
        int indmedian=0;
        int middle;
        int indMiddle;
        if ((r-l)%2 == 0) {
            indMiddle = ((r+l)/2)-1;
            middle = tab[indMiddle];
        } else {
            indMiddle = (r + l)/2 ;
            middle = tab[indMiddle];
        }

        if ((first<middle && middle<last) || (last<middle && middle<first)){
            median = middle;
            indmedian = indMiddle;
        }
        if((middle<first && first<last) ||(last<first && first<middle)){
            median = first;
            indmedian = l;
        }

        if((middle<last && last<first) ||(first<last && last<middle)){
            median = last;
            indmedian = r-1;
        }

        //System.out.println("left " + first + " right " + last + " middle " + middle + " median : " + median);

        int pivot = median;
        tab[indmedian] = tab[l];
         tab[l] = pivot;
        int i=l+1;
        for (int j=l+1;j<r;j++) {
            if (tab[j]<pivot){
                int aide = tab[j];
                tab[j] = tab[i];
                tab[i] = aide;
                i++;
            }
        }
        tab[l] = tab[i-1];
        tab[i-1]=pivot;

        quickSortMiddle(tab,l,i-1);
        quickSortMiddle(tab,i,r);
    }

    public static void main(String[] args) throws Exception {
        // File path is passed as parameter
        File file = new File(
                "Array.txt");
        BufferedReader br
                = new BufferedReader(new FileReader(file));

        int []tab = new int[10000];
        int i=0;
        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null) {
            // Print the string
            tab[i] = Integer.parseInt(st.strip());
            i++;
        }

        quickSortMiddle(tab,0,tab.length);



        System.out.println(nbMiddle);
    }
}
