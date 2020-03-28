package com.zy;

/**
 * Description:
 *
 * @author zygui
 * @date 2020/3/15 19:39
 */
public class Main {
    public static void main(String[] args) {
        java.util.ArrayList arrayList = new java.util.ArrayList();
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("zy", 22));
        persons.add(null);
        persons.add(new Person("cy", 23));
        persons.add(new Person("xy", 25));

        //int i = persons.indexOf(new Person("sss", 25));
        //System.out.println(i);

        persons.add(null);
        System.out.println(persons);

        int i = persons.indexOf(null);
        System.out.println("null的索引为:" + i);
        System.out.println("HelloWorld");
        System.out.println("HelloWorld");
        System.out.println("add second branch");
    }
}
