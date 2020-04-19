package com.company.data_struture;

public class DoubleIcreatLinkList<T> {


    //定义node节点
    class Node<T> {
        //前一个节点指针
        private Node pre;
        //下一个节点指着
        private Node next;
        //数据
        private T data;

        Node(Node pre, Node next, T data) {
            this.pre = pre;
            this.next = next;
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }

    //链表数据数量
    private int size;
    //头节点
    private Node<T> head;


    //初始化构造函数
    public DoubleIcreatLinkList() {
        /**
         * 头结点不存储值 并且头结点初始化时 就一个头结点。
         * 所以头结点的前后节点都是自己
         * 并且这个链表的长度为0；
         */
        head = new Node<>(null, null, null);
        head.pre = head;
        head.next = head;
        size = 0;
    }


    //获取数据量
    public int getSize() {
        return size;
    }

    //判断是为空数组
    public boolean isEmpty() {
        return size == 0;
    }

    //判断索引是否越界
    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return;
    }

    //通过索引获取链表中节点
    public Node<T> getNode(int index) {

        /**
         * 检查该索引是否超出范围
         */
        checkIndex(index);

        if (index < size / 2) {  //当索引的值小于该链表长度的一半时，那么从链表的头结点开始向后找是最快的
            Node<T> cur = head.next;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur;
        } else { // 当索引值位于链表的后半段时，则从链表的另端开始找是最快的
            int newIndex = size - index - 1;
            Node<T> cur = head.pre;
            for (int i = 0; i < newIndex; i++) {
                cur = cur.pre;
            }
            return cur;
        }

    }

    /**
     * 获取节点当中的值
     */
    public T getValue(Node<T> cur) {
        return cur.data;
    }

    /**
     * 获取第一个节点的值
     */
    public T getFirst() {
        return getValue(getNode(0));
    }

    /**
     * 获取最后一个节点的值
     */
    public T getLast() {
        return getValue(getNode(size - 1));
    }


    /**
     * 插入节点
     */
    public Node<T> inesert(int index, T value) {
        checkIndex(index);
        if (index == 0){
            insertToHead(value);
            return getNode(0);
        }else if (index == (size-1)){
            insertTotatil(value);
            return getNode(size-1);
        }else {
            Node<T> node = getNode(index);
            Node<T> newNode = new Node<>(node,node.next,value);
            node.next.pre = newNode;
            node.next = newNode;
            size++;
            return newNode;
        }
    }


    /**
     * 将元素插入链表头部
     */
    public synchronized void insertToHead(T value) {

        //创建这个节点的
        Node<T> cur = new Node<T>(head, head.next, value);
        head.next.pre = cur;
        head.next = cur;
        size++;

    }

    /**
     * 将元素插入到链表的尾部
     */
    public synchronized void insertTotatil(T vlaue) {
        //链表不为空，插入元素
        Node<T> cur = new Node<>(head.pre, head, vlaue);
        head.pre.next = cur;
        head.pre = cur;
        size++;
    }


    /**
     * 删除节点的方法
     */
    public void del(int index) {
        checkIndex(index);
        Node<T> cur = getNode(index);
        //记住此时的指针还没断开 赋值以后才相当于断开
        cur.pre.next = cur.next;
        cur.next.pre = cur.pre;
        size--;
        cur = null;
        return;
    }

    /**
     * 删除第一个节点
     */
    public void delFirst() {
        del(0);
    }

    /**
     * 删除最后一个节点
     */
    public void delLast() {
        del(size - 1);
    }

    Node<T> getHead() {
        return head;
    }


}
