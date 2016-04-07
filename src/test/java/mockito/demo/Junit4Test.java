/**
 * Junit4Test.java   2016年4月7日 上午11:26:18 by PANGJIANLIN 
 *
 * Copyright (c) 2010 - 2016 jianlin.Pang. All rights reserved.
 * 
 */
package mockito.demo;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class Junit4Test {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("进来了！开始准备测试了");
    }

    @Before
    public void before() {
        System.out.println("---这里进行初始化。。。");
    }

    @Test
    public void testMethod1() {
        assertEquals(1 + 1, 2);
        System.out.println("第一个测试，1+1是不是2");
    }

    @Test(timeout = 50)
    @Ignore
    public void testTimeOut() {
        System.out.println("测试会不会超过50ms");
        while (true) {
            // doNothing 无限循环
        }
    }

    @Test(expected = ArithmeticException.class)
    public void testExpected() {
        System.out.println("第二个测试，测试是不是抛出预期的异常");
        throw new ArithmeticException();
    }

    @After
    public void after() {
        System.out.println("---销毁初始化的东西");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("测试完毕撤退！");
    }

}
