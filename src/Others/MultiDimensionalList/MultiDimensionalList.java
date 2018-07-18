package Others.MultiDimensionalList;

import java.util.ArrayList;

interface Iterator<E> {
    boolean hasNext();
    E next();
}

interface Iterable<E> {
    Iterator<E> iterator();
}

public class MultiDimensionalList<E> implements Iterable{
    private Iterator listIterator;
    ArrayList<ArrayList<E>> list;

    public MultiDimensionalList() {
        listIterator = new Iterator();
        list = new ArrayList<>();
    }

    public E get(int i, int j) {
        ArrayList<E> inner;
        if (i < 0 || list.size() <= i) {
            return null;
        }else{
            inner = list.get(i);
            if(inner==null||j < 0 || list.get(i).size() <= j)
                return null;
        }
        return inner.get(j);
    }

    public void set(int i, int j, E newElem) throws ArrayIndexOutOfBoundsException {
        if (i < 0 || j < 0) {
            throw new ArrayIndexOutOfBoundsException("Index can't be negative. i: " + i + " , j: " + j);
        }
        //if i is bigger than size.
        if(list.size()<=i){
            int size = list.size();
            for (int k = size; k <i ; k++) {
                list.set(k,null);
            }
            list.set(size,new ArrayList<>());//fill indexes with 0s and the last one with ArrayList (Memory saved this way).
        }
        ArrayList<E> innerList = list.get(i);
        if(innerList==null){//we are passing nulls for saving memory, so we need additional check here;
            innerList = new ArrayList<>();
        }
        innerList.set(j,newElem);
    }

    @Override
    public Iterator iterator() {
        return listIterator;
    }

    private class Iterator implements Others.MultiDimensionalList.Iterator{
        private int currArr;
        private java.util.Iterator arrIterator;

        public Iterator(){
            currArr = 0;
            arrIterator = list.get(currArr).iterator();
        }
        @Override
        public boolean hasNext() {
            if(arrIterator.hasNext())
                return true;
            if(list.size()>currArr+1)
                return true;
            return false;
        }

        @Override
        public Object next() {
            if(arrIterator.hasNext())
                return arrIterator.next();
            else if(hasNext()){
                currArr++;
                for (; currArr <list.size() ; currArr++) {
                    ArrayList<E> nextList = list.get(currArr);
                    if(nextList==null||nextList.size()==0)
                        continue;
                    arrIterator = nextList.iterator();
                    return arrIterator.next();
                }
            }
            return null;
        }
    }
}


