/**
 * Junit4Test.java   2016��4��7�� ����11:26:18 by PANGJIANLIN 
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
        System.out.println("�����ˣ���ʼ׼��������");
    }

    @Before
    public void before() {
        System.out.println("---������г�ʼ��������");
    }

    @Test
    public void testMethod1() {
        assertEquals(1 + 1, 2);
        System.out.println("��һ�����ԣ�1+1�ǲ���2");
    }

    @Test(timeout = 50)
    @Ignore
    public void testTimeOut() {
        System.out.println("���Ի᲻�ᳬ��50ms");
        while (true) {
            // doNothing ����ѭ��
        }
    }

    @Test(expected = ArithmeticException.class)
    public void testExpected() {
        System.out.println("�ڶ������ԣ������ǲ����׳�Ԥ�ڵ��쳣");
        throw new ArithmeticException();
    }

    @After
    public void after() {
        System.out.println("---���ٳ�ʼ���Ķ���");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("������ϳ��ˣ�");
    }

}
