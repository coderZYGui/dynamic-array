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

        arrayList.remove(1);

        arrayList.add(0, 100);

        System.out.println(arrayList);
    }
}
