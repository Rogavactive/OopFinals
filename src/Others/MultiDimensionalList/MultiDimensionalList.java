//package MultiDimensionalList;
//
//import java.util.ArrayList;
//import java.util.List;
//
//interface Iterator<E> {
//    boolean hasNext();
//    E next();
//}
//
//interface Iterable<E> {
//    Iterator<E> iterator();
//}
//
//public class MultiDimensionalList<E> implements Iterable{
//    List<List<E>> list;
//
//    public MultiDimensionalList() {
//        list = new ArrayList<>();
//    }
//
//    public E get(int i, int j) {
//        if (i < 0 || list.size() <= i ||
//                j < 0 || list.get(i).size() <= j) {
//            return null;
//        }
//        return list.get(i).get(j);
//    }
//
//    public void set(int i, int j, E newElem) {
//        if (i < 0 || j < 0) {
//            return;
//        }
//        if (list.size() > i) {    //in bounds
//            if (list.get(i).size() > j) {
//                list.get(i).set(j, newElem);
//            } else {
//                int size = list.get(i).size();
//                for (int k = 0; k < i - size; k++) {
//                    list.get(i).add(null);
//                    if (k == i - size - 1) {
////                        list.get(i).set()
//                    }
//                }
//            }
//        }
//
//    }
//
//    public class Iterator implements Iterator{
//
//        @Override
//        public Iterator iterator() {
//            return null;
//        }
//    }
//}
//

//სალამი გაიზ,
//        რადგან მეორე ამოცანამ ასეთი აჟიოტაჟი გამოიწვია აქვე დავდებ ამოსნას
//        მნიშვნელოვანი მომენტებია:
//        1. Copyable ინტერფეისის შექმნა
//        2. add მეთოდში გადაცემული ელემენტის კოპირება
//        3. მასივის კოპირება და ახალი ობიექტის გაკეთება
//        4. გეთ მეთოდში დაბრუნებული ელემენტის კოპირება
//        5. final ქივორდების მიწერა და <E extends Copyable>


public interface Copyable<E>{
    public E copy();
}
public final class ImmutableArrayList<E extends Copyable>{
    private final int size;
    private static final int DEFAULT_CAPACITY = 10;
    private final Object elements[];

    public ImmutableArrayList() {
        size = 0;
        elements = new Object[DEFAULT_CAPACITY];
    }

    public ImmutableArrayList(ImmutableArrayList<E> list, E newElem){
        this.elements = new Object[list.size + 1];
        this.size = list.size+1;
        for (int i = 0; i<list.size; i++){
            this.elements[i] = list.elements[i];
        }
        this.elements[this.size-1] = newElem.copy();
    }

    public ImmutableArrayList<E> add(E e) {
        return new ImmutableArrayList<E>(this, e);
    }

    @SuppressWarnings("unchecked")
    public E get(int i) {
        if (i>= size || i <0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size " + i );
        }
        return (E)((E) elements[i]).copy();
    }
}