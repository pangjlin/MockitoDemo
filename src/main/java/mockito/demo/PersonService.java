/**
 * PersonService.java   2016��3��14�� ����4:31:41 by PANGJIANLIN 
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
        System.out.println("����");
        Person person = personDao.fetchPerson(personId);
        if (person != null) {
            System.out.println("��Ϊ��" + name);
            Person updatedPerson = new Person(person.getPersonId(), name);
            personDao.update(updatedPerson);
            return true;
        } else {
            System.out.println("Ϊ��");
            return false;
        }
    }

}
