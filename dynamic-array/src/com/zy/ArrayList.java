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
            1、remove最后一个地址也要情况,同clear细节
            2、在indexOf方法中,不用==比较,因为比较的是地址值,一般重写equals
                方法自己定义比较内容即可;
            3、null值处理:
                当往数组传null的时候,indexOf的比较处理: 如果那null.equals来比较
                会出现空指针异常;
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
        // 同clear的细节,当从后往前以后时,最后一个的地址需要释放
        elements[size] = null;
        return delEle;
    }

    /**
     * 删除传入的元素
     * @param element
     */
    public void remove(E element){
        remove(indexOf(element));
    }

    /**
     * 查看元素的索引
     *
     * @param element
     * @return
     */
    public int indexOf(E element) {
        if (element == null) {
            // 循环判断如果element为null,直接返回null的索引
            for (int i = 0; i < size; i++) {
                if (elements[i] == null)
                    return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                // 因为element肯定不为null了,所以放在前面;避免空指针异常
                if (element.equals(elements[i]))
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
