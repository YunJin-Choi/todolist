package kr.or.connect.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.todo.bean.TodoBean;
import kr.or.connect.todo.persistence.TodoDao;

@Service
public class TodoService {
	
	private TodoDao dao;

	@Autowired
	public TodoService(TodoDao dao) {
		this.dao = dao;
	}
	
	public List<TodoBean> selectAll(){
		return dao.selectAll();
	}
	
	public TodoBean selectById(int id){
		return dao.selectById(id);
	}
	
	public TodoBean insert(TodoBean todo){
		Integer id = dao.insertTodo(todo);
		todo.setId(id);
		return todo;
	}
	
	public void delete(TodoBean todo){
		dao.deleteTodo(todo);
	}
	
	public void completed(TodoBean todo){
		dao.completedTodo(todo);
	}
	
	public void clearCompleted(){
		dao.clearCompletedTodo();
	}
}
