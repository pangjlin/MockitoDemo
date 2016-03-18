/**
 * EmployeeService.java   2016年3月16日 下午5:39:10 by PANGJIANLIN 
 *
 * Copyright (c) 2010 - 2016 jianlin.Pang. All rights reserved.
 * 
 */
package mockito.demo;

import mockito.demo.EmployeeDao.Kind;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    //无参构造方法
    public EmployeeService() {

    }

    //有参构造方法
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    //使用注入的dao
    public int getTotalEmployee() {
        return employeeDao.getTotal();
    }

    public void createEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }

    //在方法中new出dao
    public int getTotalEmployeePower() {
        EmployeeDao employeeDao = new EmployeeDao();
        return employeeDao.getTotal();
    }

    public void createEmployeePower(Employee employee) {
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.addEmployee(employee);
    }

    //测试static方法
    public int getEmployeeCount() {
        return EmployeeUtils.getEmployeeCount();
    }

    public void persistenceEmployee(Employee employee) {
        EmployeeUtils.persistenceEmployee(employee);
    }

    //verify的使用
    public void saveOrUpdate(Employee employee) {
        EmployeeDao employeeDao = new EmployeeDao();
        int count = employeeDao.getCount(employee);
        if (count > 0) {
            employeeDao.updateEmployee(employee);
        } else {
            employeeDao.saveEmployee(employee);
        }
    }

    //powermock测试final
    public void insertEmployee(Employee employee) {
        employeeDao.insertEmployee(employee);
    }

    //测试有参数的构造函数
    public void createEmployeeDB(final Employee employee) {
        EmployeeDao employeeDao = new EmployeeDao(false, Kind.MYSQL);
        employeeDao.insertEmployeeDB(employee);
    }

    //测试private方法
    public boolean exist() {
        return check();
    }

    private boolean check() {
        return false;
    }

}
