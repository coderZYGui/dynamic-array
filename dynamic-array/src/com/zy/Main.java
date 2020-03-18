package com.zy;

/**
 * Description:
 *
 * @author zygui
 * @date 2020/3/15 19:39
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("zy", 22));
        persons.add(new Person("cy", 23));
        persons.add(new Person("xy", 25));

        persons.clear();
        // 通知JVM进行垃圾回收
        System.gc();
    }

    public static void test(){
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("zy", 22));
        persons.add(new Person("cy", 23));
        System.out.println(persons);

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        System.out.println(list);
    }
}
