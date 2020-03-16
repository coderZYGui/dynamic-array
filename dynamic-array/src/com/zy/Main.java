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

        for (int i = 0; i < 50; i++) {
            arrayList.add(i);
        }

        System.out.println(arrayList);
    }
}
