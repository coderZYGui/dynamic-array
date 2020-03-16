package com.zy;

/**
 * Description:
 *
 * @author zygui
 * @date 2020/3/15 19:39
 */
public class Main {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(10);
        arrayList.add(20);
        arrayList.add(30);
        arrayList.add(40);
        arrayList.add(50);

        // 往数组最后插入元素
        arrayList.add(arrayList.size(), 100);
        arrayList.remove(0);
        arrayList.set(0, 10000);
        int i = arrayList.get(0);
        System.out.println(i);

        // 通过断言来判断,是否达到预期的结果
        Assert.test(arrayList.get(3) == 50);

        System.out.println(arrayList);
    }
}
