/**
 * PersonService.java   2016年3月14日 下午4:31:41 by PANGJIANLIN 
 *
 * Copyright (c) 2010 - 2016 jianlin.Pang. All rights reserved.
 * 
 */
package mockito.demo;


public class PersonService {

    private final PersonDao personDao;

    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public boolean update(Integer personId, String name) {
        System.out.println("更新");
        Person person = personDao.fetchPerson(personId);
        if (person != null) {
            System.out.println("不为空" + name);
            Person updatedPerson = new Person(person.getPersonId(), name);
            personDao.update(updatedPerson);
            return true;
        } else {
            System.out.println("为空");
            return false;
        }
    }

}
