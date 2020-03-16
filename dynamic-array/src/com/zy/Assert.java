package com.zy;

/**
 * Description: 断言测试小工具
 *
 * @author zygui
 * @date 2020/3/16 22:26
 */
public class Assert {
    public static void test(boolean value) {
        try {
            if (!value)
                throw new Exception("测试未通过");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
