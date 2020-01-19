public class MyStack<E> implements Stack<E> {

    //final int defaultCapacity= 20;
    Array<E> array;

    public MyStack(int capacity){
        array = new Array<>(capacity);
    }


    public MyStack(){
        array = new Array<>();
    }




    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }


//    public int getCapacity() {
//        return array.;
//    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return null;
    }
}
