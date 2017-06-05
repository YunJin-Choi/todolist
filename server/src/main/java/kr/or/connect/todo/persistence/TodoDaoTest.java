package kr.or.connect.todo.persistence;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.connect.todo.bean.TodoBean;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoDaoTest {
	
	@Autowired
	private TodoDao dao;
	
	@Test
	public void shouldInsert(){
		TodoBean todo = new TodoBean();
		todo.setTodo("Test");
		
		Integer id = dao.insertTodo(todo);
		
		assertThat(dao.selectById(id).getTodo(), is("Test"));
	}
}
