package com.zy;


/**
 * Description: 设计动态数组
 *
 * @author zygui
 * @date 2020/3/15 19:39
 */
public class ArrayList {
    // ===============================
    /*
        重点

        动态扩容思路:
            1: 通过默认容量创建的数组,是在堆空间中随机生成的地址;如此一来
            再申请空间拼接到该数组后,这种方式不可能实现;
            2: 我们只能再创建一个大容量的数组,然后将之前数组中的元素移动到
            这个数组中;然后将引用指向新数组即可!
     */
    // ===============================

    /**
     * 元素的数量
     */
    private int size;

    /**
     * 所有元素
     */
    private int[] elements;

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
        elements = new int[capacity];
    }

    /**
     * 清除所有元素
     */
    public void clear() {
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
    public boolean contains(int element) {
        // 如果element元素可以找到
        return indexOf(element) != ELEMENT_NOT_FOUNT;
    }

    /**
     * 添加元素到尾部
     *
     * @param element
     */
    public void add(int element) {
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
    public int get(int index) {
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
    public int set(int index, int element) {
        rangeCheck(index);

        int oldEle = elements[index];
        elements[index] = element;
        return oldEle;
    }

    /**
     * 在index位置插入一个元素
     *
     * @param index
     * @param element
     */
    public void add(int index, int element) {
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
    public int remove(int index) {
        rangeCheck(index);
        int delEle = elements[index];

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
    public int indexOf(int element) {
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
        /*
            动态扩容思路:
                1: 通过默认容量创建的数组,是在堆空间中随机生成的地址;如此一来
                再申请空间拼接到该数组后,这种方式不可能实现;
                2: 我们只能再创建一个大容量的数组,然后将之前数组中的元素移动到
                这个数组中;然后将数组引用指向新数组即可!
         */
        // 判断是否要扩容
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity)
            return; // 此时不扩容
        // 扩增的容量自己定;这里新容量为旧容量的2倍
        int newCapacity = oldCapacity * 2;
        int[] newElements = new int[newCapacity];
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
