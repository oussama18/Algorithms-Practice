package org.example.Algorithme;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SumOf2 {

    private Map<Long,Integer> mapNumber;
    private Set<Long> setS = new HashSet<>(2000);
    private int result;

    public SumOf2(Map<Long, Integer> mapNumber) {
        this.mapNumber = mapNumber;

        for (long i = -10000; i <= 10000; i++)
            setS.add(i);
    }

    public long getResultForSum() {
        Iterator itr = setS.iterator();

        //iterate through million numbers
        for (Long number : mapNumber.keySet()){

            //iterate through what's left of -10000 to 10000
            while (itr.hasNext()){

                long t = (Long)itr.next();
                long complement = t - number;

                //check they are distinct
                if (complement != number){

                    //check if both in our list of million numbers
                    if (mapNumber.containsKey(complement)){

                        //remove current t from our range of -10000 to 10000,
                        //so we don't examine it again.
                        itr.remove();

                        result++;
                    }
                }
            }

            //reset iterator
            itr = setS.iterator();
        }

        return result;
    }
}
