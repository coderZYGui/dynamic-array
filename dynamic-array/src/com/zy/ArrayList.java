package com.zy;


/**
 * Description: 设计动态数组
 *
 * @author zygui
 * @date 2020/3/15 19:39
 */
public class ArrayList<E> {
    // ===============================
    /*
        重点:
            1、使用泛型,使动态数组可以添加任何类型的数据
                elements = (E[]) new Object[capacity];

            2、clear方法的细节
                1.1: 因为之前存储的都是int数据,直接设置size=0时,开辟的存储
                        int类型的空间就不会被访问,当add后,就可以访问后面的空间,所以此时的
                        空间可以重复利用;
                1.2: 当使用泛型后,动态数组中存储的都是对象类型,实际存储的都是对象的地址,每一个
                    对象的地址又有一块空间引用着;此时如果仍设置 size=0,当clear后,开辟的存储空间
                    中的地址没有被销毁,地址仍被对象空间引用着;这样以来存储对象的空间就不会被释放;
                    但是存储地址的数组可以重复利用; 所以要将地址数组都置为null,然后size=0,这样以来,引用
                    该地址的对象空间也就释放了!
     */
    // ===============================

    /**
     * 元素的数量
     */
    private int size;

    /**
     * 所有元素
     */
    private E[] elements;

    /**
     * 数组的默认容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 找不到元素返回-1
     */
    private static final int ELEMENT_NOT_FOUNT = -1;

    public ArrayList() {
        // 默认容量
        //elements = new int[DEFAULT_CAPACITY];
        this(DEFAULT_CAPACITY); // 调用下面的构造器
    }

    public ArrayList(int capacity) {
        // 设置默认容量为 10
        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;

        // 因为泛型(所以传一个Object数组,然后通过强转)
        elements = (E[]) new Object[capacity];
    }

    /**
     * 清除所有元素
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 元素的数量
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean inEmpty() {
        return size == 0;
    }

    /**
     * 是否包含某个元素
     *
     * @param element
     * @return
     */
    public boolean contains(E element) {
        // 如果element元素可以找到
        return indexOf(element) != ELEMENT_NOT_FOUNT;
    }

    /**
     * 添加元素到尾部
     *
     * @param element
     */
    public void add(E element) {
        // elements[size++] = element;
        // 传入数组数量(相当于在最后插入元素)
        add(size, element);
    }

    /**
     * 获取index位置的元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        // 约束Index
        rangeCheck(index);
        return elements[index];
    }

    /**
     * 设置index位置的元素
     *
     * @param index
     * @param element
     * @return 原来的元素
     */
    public E set(int index, E element) {
        rangeCheck(index);

        E oldEle = elements[index];
        elements[index] = element;
        return oldEle;
    }

    /**
     * 在index位置插入一个元素
     *
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        // 在添加元素的时候,判断index是否有效
        rangeCheckForAdd(index);

        /*
            该方法确保默认容量为多少,为了验证是否超过给定的默认容量,然后进行判断是否要扩容;
            这里size+1为数组当前数量+1, 因为每次add都会增加一个容量
        */
        ensureCapacity(size + 1);

        // 注意: 插入元素后,元素是从后开始往后挪
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        size++;
    }


    /**
     * 删除index位置的元素
     *
     * @param index
     * @return 被删除的元素
     */
    public E remove(int index) {
        rangeCheck(index);
        E delEle = elements[index];

        // 当删除一个元素时,需要挪动后面元素的范围
        for (int i = index + 1; i <= size - 1; i++) {
            elements[i - 1] = elements[i];
        }
        size--;
        return delEle;
    }

    /**
     * 查看元素的索引
     *
     * @param element
     * @return
     */
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element) {
                return i;
            }
        }
        return ELEMENT_NOT_FOUNT;
    }

    /**
     * 确保至少要有capacity个容量
     *
     * @param capacity
     */
    private void ensureCapacity(int capacity) {

        // 判断是否要扩容
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity)
            return; // 此时不扩容

        // 这种方式是扩容1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);

        E[] newElements = (E[]) new Object[newCapacity];
        // 将原来数组中的元素移动到新数组中
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        // 将数组引用指向新数组
        elements = newElements;

        System.out.println(oldCapacity + "扩容为:" + newCapacity);
    }

    /**
     * 封装数组越界异常
     *
     * @param index
     */
    private void indexOutOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
    }

    /**
     * 检查get,remove传入的index是否有效
     *
     * @param index
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            indexOutOfBounds(index);
        }
    }

    /**
     * 根据index插入元素时,判断index是否有效
     *
     * @param index
     */
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            indexOutOfBounds(index);
        }
    }

    @Override
    public String toString() {
        // 打印格式: size=3, [10, 20, 30]
        // 使用StringBuilder 效率高一些
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            string.append(elements[i]);
            if (i != size - 1) {
                string.append(", ");
            }
        }
        string.append("]");
        return string.toString();
    }
}
