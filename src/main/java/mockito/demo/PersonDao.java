/**
 * PersonDao.java   2016年3月14日 下午4:30:16 by PANGJIANLIN 
 *
 * Copyright (c) 2010 - 2016 jianlin.Pang. All rights reserved.
 * 
 */
package mockito.demo;

public interface PersonDao {

    public Person fetchPerson(Integer personId);

    public void update(Person person);

}
