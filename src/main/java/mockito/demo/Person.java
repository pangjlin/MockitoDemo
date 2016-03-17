/**
 * Person.java   2016年3月14日 下午4:25:09 by PANGJIANLIN 
 *
 * Copyright (c) 2010 - 2016 jianlin.Pang. All rights reserved.
 * 
 */
package mockito.demo;

public class Person {

    private Integer personId;

    private String personName;

    public Person(Integer personId, String personName) {
        System.out.println("写入");
        this.personId = personId;
        this.personName = personName;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

}
