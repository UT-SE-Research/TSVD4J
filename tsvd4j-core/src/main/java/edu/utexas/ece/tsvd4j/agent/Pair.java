package edu.utexas.ece.tsvd4j.agent;

public class Pair{

    Object obj1;
    Object obj2;

    public Pair(Object obj1, Object obj2){
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    @Override
    public int hashCode() {
        return  (int) System.identityHashCode(obj1) * System.identityHashCode(obj2);
    }
}
