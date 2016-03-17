/**
 * EmployeeService.java   2016��3��16�� ����5:39:10 by PANGJIANLIN 
 *
 * Copyright (c) 2010 - 2016 jianlin.Pang. All rights reserved.
 * 
 */
package mockito.demo;

import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    //�޲ι��췽��
    public EmployeeService() {

    }

    //�вι��췽��
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    //ʹ��ע���dao
    public int getTotalEmployee() {
        return employeeDao.getTotal();
    }

    public void createEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }

    //�ڷ�����new��dao
    public int getTotalEmployeePower() {
        EmployeeDao employeeDao = new EmployeeDao();
        return employeeDao.getTotal();
    }

    public void createEmployeePower(Employee employee) {
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.addEmployee(employee);
    }

    //����static����
    public int getEmployeeCount() {
        return EmployeeUtils.getEmployeeCount();
    }

    public void persistenceEmployee(Employee employee) {
        EmployeeUtils.persistenceEmployee(employee);
    }

}
