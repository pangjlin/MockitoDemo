/**
 * EmployeeServiceTest.java   2016��3��16�� ����5:47:14 by PANGJIANLIN 
 *
 * Copyright (c) 2010 - 2016 jianlin.Pang. All rights reserved.
 * 
 */
package mockito.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeService.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeDao employeeDao;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetTotalEmployee() {
        Mockito.when(employeeDao.getTotal()).thenReturn(10);
        EmployeeService employeeService = new EmployeeService(employeeDao);
        int total = employeeService.getTotalEmployee();
        assertEquals(10, total);
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        Mockito.doNothing().when(employeeDao).addEmployee(employee);
        EmployeeService service = new EmployeeService(employeeDao);
        service.createEmployee(employee);
        Mockito.verify(employeeDao, Mockito.times(1)).addEmployee(employee);
    }

    @Test
    public void testGetTotalEmployeePower() {
        try {
            //����Ԥ��ֵ
            PowerMockito.when(employeeDao.getTotal()).thenReturn(10);
            EmployeeService employeeService = new EmployeeService();
            //ģ��������õķ�����new�Ķ���
            PowerMockito.whenNew(EmployeeDao.class).withNoArguments().thenReturn(employeeDao);
            int total = employeeService.getTotalEmployeePower();
            assertEquals(10, total);
        } catch (Exception e) {
            fail("fail");
        }
    }

    @Test
    public void testCreateEmployeePower() {
        try {
            Employee employee = new Employee();
            EmployeeService service = new EmployeeService();
            //ģ��������õķ�����new�Ķ���
            PowerMockito.whenNew(EmployeeDao.class).withNoArguments().thenReturn(employeeDao);
            service.createEmployeePower(employee);
            //��֤�Ƿ����һ��
            Mockito.verify(employeeDao, Mockito.times(1)).addEmployee(employee);
        } catch (Exception e) {
            fail("fail");
        }
    }

}
