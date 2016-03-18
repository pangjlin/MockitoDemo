/**
 * EmployeeServiceTest.java   2016��3��16�� ����5:47:14 by PANGJIANLIN 
 *
 * Copyright (c) 2010 - 2016 jianlin.Pang. All rights reserved.
 * 
 */
package mockito.demo;

import static mockito.demo.EmployeeDao.Kind.MYSQL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
@PrepareForTest({EmployeeService.class, EmployeeUtils.class, EmployeeDao.class})
public class EmployeeServiceTest {

    @Mock
    private EmployeeDao employeeDao;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    //��ͨ����
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
        //����addEmployeeʱʲô��������doNothing��
        Mockito.doNothing().when(employeeDao).addEmployee(employee);
        EmployeeService service = new EmployeeService(employeeDao);
        service.createEmployee(employee);
        Mockito.verify(employeeDao, Mockito.times(1)).addEmployee(employee);
    }

    //�ڲ���new����Ĳ���
    @Test
    public void testGetTotalEmployeePower() {
        try {
            //EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
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
            //EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
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

    //static�����Ĳ���
    @Test
    public void testGetEmployeeCount() {
        //mock��EmployeeUtils������static����
        PowerMockito.mockStatic(EmployeeUtils.class);
        Mockito.when(EmployeeUtils.getEmployeeCount()).thenReturn(5);
        EmployeeService employeeService = new EmployeeService();
        int count = employeeService.getEmployeeCount();
        assertEquals(5, count);
    }

    @Test
    public void testPersistenceEmployee() {
        PowerMockito.mockStatic(EmployeeUtils.class);
        Employee employee = new Employee();
        //EmployeeUtils������void������doNothing
        PowerMockito.doNothing().when(EmployeeUtils.class);
        EmployeeService employeeService = new EmployeeService();
        employeeService.persistenceEmployee(employee);
        PowerMockito.verifyStatic();
    }

    //verify��ʹ��
    @Test
    public void testSave() {
        try {
            //EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
            PowerMockito.whenNew(EmployeeDao.class).withNoArguments().thenReturn(employeeDao);
            Employee employee = new Employee();
            Mockito.when(employeeDao.getCount(employee)).thenReturn(0);
            EmployeeService employeeService = new EmployeeService();
            employeeService.saveOrUpdate(employee);
            Mockito.verify(employeeDao, Mockito.times(1)).saveEmployee(employee);
            Mockito.verify(employeeDao, Mockito.never()).updateEmployee(employee);
            Mockito.verify(employeeDao, Mockito.times(1)).getCount(employee);
            Mockito.verifyNoMoreInteractions(employeeDao);
        } catch (Exception e) {
            fail("fail");
        }
    }

    @Test
    public void testUpdate() {
        try {
            //EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
            PowerMockito.whenNew(EmployeeDao.class).withNoArguments().thenReturn(employeeDao);
            Employee employee = new Employee();
            Mockito.when(employeeDao.getCount(employee)).thenReturn(1);
            EmployeeService employeeService = new EmployeeService();
            employeeService.saveOrUpdate(employee);
            Mockito.verify(employeeDao, Mockito.times(1)).updateEmployee(employee);
            Mockito.verify(employeeDao, Mockito.never()).saveEmployee(employee);
            Mockito.verify(employeeDao, Mockito.times(1)).getCount(employee);
            Mockito.verifyNoMoreInteractions(employeeDao);
        } catch (Exception e) {
            fail("fail");
        }
    }

    //powermock����final����
    @Test
    public void testInsertEmployee() {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        Employee employee = new Employee();
        EmployeeService service = new EmployeeService(employeeDao);
        service.insertEmployee(employee);
        Mockito.verify(employeeDao, Mockito.times(1)).insertEmployee(employee);
    }

    //���Թ��캯��
    @Test
    public void testCreateEmployeeDB() {
        try {
            PowerMockito.whenNew(EmployeeDao.class).withArguments(false, MYSQL).thenReturn(employeeDao);
            Employee employee = new Employee();
            EmployeeService service = new EmployeeService();
            service.createEmployeeDB(employee);
            Mockito.verify(employeeDao, Mockito.times(1)).insertEmployeeDB(employee);
        } catch (Exception e) {
            fail("fail");
        }
    }

    //����private����
    @Test
    public void testExist() {
        try {
            EmployeeService service = PowerMockito.mock(EmployeeService.class);
            PowerMockito.when(service.exist()).thenCallRealMethod();
            PowerMockito.when(service, "check").thenReturn(true);
            assertTrue(service.exist());
            Mockito.verify(service, Mockito.times(3)).exist();
        } catch (Exception e) {
            fail("fail");
        }
    }
}
