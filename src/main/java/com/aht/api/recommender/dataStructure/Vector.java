package com.aht.api.recommender.dataStructure;

import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by azu on 20/04/16.
 */
public class Vector{
    private Object[] vector;

    public void setVector(Object[] vector){
        this.vector = vector;
    }

    public Object[] getVector(){
        return this.vector;
    }

    public Vector(Object[] vector){
        this.vector = vector;
    }

    public Object[] merge(Vector vector2) {
        Object[] both = ArrayUtils.addAll(this.vector, vector2.getVector());
        List<Object> list = new ArrayList<Object>();
        for(Object val: both){
            if (!list.contains(val)) {
                list.add(val);
            }
        }
        return list.toArray(new Object[list.size()]);
    }

    public void add(Object object){
        this.vector = ArrayUtils.add(this.vector,object);
    }

    public Object get(){
        return this.vector[this.vector.length - 1];
    }

    public Object get(int index){
        return this.vector[index];
    }

    public boolean remove(Object object){
        int index = indexOf(object);
        if(index > -1 ){
            List<Object> list = new ArrayList<Object>();
            for(int i = 0; i < this.size(); i++){
                if (index != i) {
                    list.add(this.vector[i]);
                }
            }
            this.vector = list.toArray(new Object[list.size()]);
            return true;
        } else{
            return false;
        }
    }

    public int indexOf(Object object){
        for(int i=0; i < vector.length; i++){
            if(vector[i].equals(object)){
                return i;
            }
        }
        return -1;
    }
    public boolean includes(Object object){
        List<Object> list = new ArrayList<Object>();
        for(Object val: this.vector) {
            if(!list.contains(val)) {
                list.add(val);
            }
        }
        if(list.contains(object)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString(){
        String string = "[";
        for(int i=0; i < this.vector.length; i++){
            string = string + this.vector[i];
            if(i < this.vector.length -1)
                string = string + ", ";
        }
        string = string + "]";
        return string;
    }

    public int size(){
        return this.vector.length;
    }

    public Vector normalize(Vector bigger){
        Vector temp = new Vector(bigger.getVector());
        for(Object obj : vector){
            if(bigger.includes(obj)){

            }
        }


        return temp;
    }
}
