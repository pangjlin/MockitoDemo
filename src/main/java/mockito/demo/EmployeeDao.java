/**
 * EmployeeDao.java   2016年3月16日 下午5:40:44 by PANGJIANLIN 
 *
 * Copyright (c) 2010 - 2016 jianlin.Pang. All rights reserved.
 * 
 */
package mockito.demo;

public class EmployeeDao {

    public EmployeeDao() {

    }

    public enum Kind {
        MYSQL, ORACLE
    }

    public EmployeeDao(boolean lazy, Kind kind) {
        throw new UnsupportedOperationException();
    }

    public int getTotal() {
        throw new UnsupportedOperationException();
    }

    public void addEmployee(Employee employee) {
        throw new UnsupportedOperationException();
    }

    public int getCount(Employee employee) {
        throw new UnsupportedOperationException();
    }

    public void saveEmployee(Employee employee) {
        throw new UnsupportedOperationException();
    }

    public void updateEmployee(Employee employee) {
        throw new UnsupportedOperationException();
    }

    public final boolean insertEmployee(Employee employee) {
        throw new UnsupportedOperationException();
    }

    public boolean insertEmployeeDB(Employee employee) {
        throw new UnsupportedOperationException();
    }

}
