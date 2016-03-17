/**
 * PersonServiceTest.java   2016年3月14日 下午4:34:16 by PANGJIANLIN 
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
    private PersonDao personDao;//第一步：mock出来一个对象
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
        Mockito.when(personDao.fetchPerson(1)).thenReturn(person);//第二步：指定mock对象调用时的返回值
        boolean updated = personService.update(1, "David");
        assertTrue(updated);
        Mockito.verify(personDao).fetchPerson(1);//验证被测试的方法是否正确工作
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
        //检查是否有未被验证的互动行为，因为add(1)和add(2)都会被上面的anyInt()验证到，所以下面的代码会通过  
        Mockito.verifyNoMoreInteractions(list);

        //List<Integer> list2 = Mockito.mock(List.class);
        list2.add(1);
        list2.add(2);
        Mockito.verify(list2, Mockito.times(2)).add(Mockito.anyInt());
        Mockito.verify(list2, Mockito.never()).add(3);
        //检查是否有未被验证的互动行为，因为add(2)没有被验证，所以下面的代码会失败抛出异常  
        Mockito.verifyNoMoreInteractions(list2);
    }

}
