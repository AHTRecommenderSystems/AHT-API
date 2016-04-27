package com.aht.api.recommender;

import com.aht.api.evaluator.ManhattanLength;
import com.aht.api.evaluator.dataStructure.Vector;

/**
 * Created by azu on 25/04/16.
 */
public class Main {
    public static void main(String args[]){
        Object[] v1 = new Object[3];
        Object[] v2 = new Object[4];
        for(int i=0; i < v1.length; i++){
            v1[i] = i +1 ;
        }
        for(int i=0; i < v2.length; i++){
            v2[i] = i +2 ;
        }
        Vector la = new Vector(v1);
        Vector lb = new Vector(v2);
        Vector both = new Vector(la.merge(lb));
        double[] normalized1 = la.normalize(both);
        double[] normalized2 = lb.normalize(both);
        System.out.println(la);
        System.out.println(lb);
        System.out.println(both);
        System.out.println(new Vector(la.normalize(both)));
        System.out.println(new Vector(lb.normalize(both)));

        int distance = 0;
        for(int i=0; i < normalized1.length; i++){
            distance += Math.abs(normalized1[i] - normalized2[i]);
        }
        double result = ((double)both.size() - (double) distance) / (double) both.size();

        System.out.println(distance + "\t" + result);

    }
}
