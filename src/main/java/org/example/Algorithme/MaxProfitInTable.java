package org.example.Algorithme;

public class MaxProfitInTable {

    static class SubTable {
        int iMin;
        int iMax;
        int profit;

        public SubTable(int iMin, int iMax, int profit) {
            this.iMin = iMin;
            this.iMax = iMax;
            this.profit = profit;
        }
    }

    private int getSum(int[] ints ,int iMin,int iMax) {
        int sum = 0;
        for (int i=iMin;i<iMax;i++) {
            sum += ints[i];
        }

        return sum;
    }

    private SubTable getSubTableWithMaxProfit(int[] ints, int iMin, int iMax) {
        if(iMax-iMin == 1) {
            return new SubTable(iMin,iMax,ints[iMin]);
        }

        SubTable subR = getSubTableWithMaxProfit(ints,iMin+1,iMax);
        SubTable subL = getSubTableWithMaxProfit(ints,iMin,iMax-1);

        SubTable sub = subL.profit > subR.profit ? subL: subR;

        int lD = getSum(ints,iMin,sub.iMax);
        int rD = getSum(ints,sub.iMin,iMax);

        if(lD > sub.profit) {
            sub = new SubTable(iMin,sub.iMax,lD);
        }

        if(rD > sub.profit) {
            sub = new SubTable(sub.iMin,iMax,lD);
        }

        return sub;
    }

    public int[] getMaxProfitArray(int[] ints) {
        SubTable sub = getSubTableWithMaxProfit(ints,0,ints.length);

        return new int[]{sub.iMin,sub.iMax};
    }
}
