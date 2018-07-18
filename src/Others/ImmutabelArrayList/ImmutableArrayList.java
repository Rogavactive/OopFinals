package Others.ImmutabelArrayList;
//სალამი გაიზ,
//        რადგან მეორე ამოცანამ ასეთი აჟიოტაჟი გამოიწვია აქვე დავდებ ამოსნას
//        მნიშვნელოვანი მომენტებია:
//        1. Copyable ინტერფეისის შექმნა
//        2. add მეთოდში გადაცემული ელემენტის კოპირება
//        3. მასივის კოპირება და ახალი ობიექტის გაკეთება
//        4. გეთ მეთოდში დაბრუნებული ელემენტის კოპირება
//        5. final ქივორდების მიწერა და <E extends Copyable>


interface Copyable<E>{//აქ public ეწრა ინტერფეისს, მაგრამ ეგ ერორს აგდებდა და წავუშალე, დანარჩენი ისეა, როგორც შოთამ დადო.
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