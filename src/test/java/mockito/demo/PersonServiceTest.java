/**
 * PersonServiceTest.java   2016��3��14�� ����4:34:16 by PANGJIANLIN 
 *
 * Copyright (c) 2010 - 2016 jianlin.Pang. All rights reserved.
 * 
 */
package mockito.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PersonServiceTest {

    @Mock
    private PersonDao personDao;//��һ����mock����һ������
    private PersonService personService;
    @Mock
    private List<Integer> list;
    @Mock
    private List<Integer> list2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        personService = new PersonService(personDao);
    }

    @Test
    public void shouldUpdatePersonName() {
        Person person = new Person(1, "Phillip");
        Mockito.when(personDao.fetchPerson(1)).thenReturn(person);//�ڶ�����ָ��mock�������ʱ�ķ���ֵ
        boolean updated = personService.update(1, "David");
        assertTrue(updated);
        Mockito.verify(personDao).fetchPerson(1);//��֤�����Եķ����Ƿ���ȷ����
        //Mockito.verify(personDao).fetchPerson(2);
        ArgumentCaptor<Person> personCaptor = ArgumentCaptor.forClass(Person.class);
        Mockito.verify(personDao).update(personCaptor.capture());
        Person updatedPerson = personCaptor.getValue();
        assertEquals("David", updatedPerson.getPersonName());
        // asserts that during the test, there are no other calls to the mock object.
        Mockito.verifyNoMoreInteractions(personDao);
    }

    @Test
    public void shouldNotUpdateIfPersonNotFound() {
        //Person person = new Person(1, "Phillip");
        Mockito.when(personDao.fetchPerson(1)).thenReturn(null);
        boolean updated = personService.update(1, "David");
        assertFalse(updated);
        Mockito.verify(personDao).fetchPerson(1);
        Mockito.verifyZeroInteractions(personDao);
        Mockito.verifyNoMoreInteractions(personDao);
    }

    @Test
    public void find_redundant_interaction() {
        //List<Integer> list = Mockito.mock(List.class);
        list.add(1);
        list.add(2);
        Mockito.verify(list, Mockito.times(2)).add(Mockito.anyInt());
        //����Ƿ���δ����֤�Ļ�����Ϊ����Ϊadd(1)��add(2)���ᱻ�����anyInt()��֤������������Ĵ����ͨ��  
        Mockito.verifyNoMoreInteractions(list);

        //List<Integer> list2 = Mockito.mock(List.class);
        list2.add(1);
        list2.add(2);
        Mockito.verify(list2, Mockito.times(2)).add(Mockito.anyInt());
        Mockito.verify(list2, Mockito.never()).add(3);
        //����Ƿ���δ����֤�Ļ�����Ϊ����Ϊadd(2)û�б���֤����������Ĵ����ʧ���׳��쳣  
        Mockito.verifyNoMoreInteractions(list2);
    }

}
