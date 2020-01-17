import java.text.SimpleDateFormat;

public class Array<T> {
    private T[] data;
    private int size;

    //构造函数，
    public Array(int capacity){
        data = (T[])new Object[capacity];
    }
    //五参构造函数
    public Array(){
        this(10);
    }
    //TO-DO 其他构造函数


    public int getSize() {
        return size;
    }

    public int getData() {
        return data.length;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //最后一个添加
    public void addLast(T e){
//        if (size == data.length)
//            throw new IllegalArgumentException("over length");
//        data[size] = e;
//        size++;
        insertElement(size,e);
    }

    //最前面添加
    public void addBefore(T e){
        insertElement(0,e);
    }

    //制定位置添加
    public void insertElement(int index,T e){
        if (size == data.length)
            throw new IllegalArgumentException("over length bu neng bei cha");
        if (index < 0 || index > size)
            throw new IllegalArgumentException("index exception");
        for (int i = size -1; i >= index ; i--) {
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

    //制定位置，获取元素
    T get(int index){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("index exception");
        return data[index];
    }

    //制定位置，设定元素
    void set(int index,T e){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("index exception");
        data[index] = e;
    }

    //是否含有制定的元素
    public boolean containsElement(T e){
       int res = findElement(e);
       if (res == -1)
           return false;
       return true;
    }

    //查找第一个为制定元素的位置
    public int findElement(T e){
        for (int i = 0; i < size; i++) {
            if (data[i] == e)
                return i;
        }
        return -1;
    }



    //删除制定位置的元素
    public T remove(int index){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("index exception");
        T ret = data[index];
        for (int i = index; i < size-1 ; i++) {
            data[i] =data[i+1];
        }
        size--;
        return ret;
    }

    //删除头元素
    public T removeFirst(){
        return remove(0);
    }

    //删除尾元素
    public T removeLast(){
        return remove(size);
    };

    //移除第一个制定的元素
    public void removeElement(T e){
        int index = findElement(e);
        if (index != -1)
            remove(index);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array : size=%d , capacity=%d \n",size,data.length));
        builder.append("[");
        for (int i =0;i<size;i++){
            builder.append(data[i]);
            if (i!=(size-1)){
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }













}
