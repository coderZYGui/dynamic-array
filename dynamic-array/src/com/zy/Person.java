package com.zy;

/**
 * Description:
 *
 * @author zygui
 * @date 2020/3/18 20:23
 */
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    /**
     * 该对象销毁的时候调用该方法
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("person - finalize");
    }
}
