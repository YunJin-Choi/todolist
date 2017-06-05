package kr.or.connect.todo.api;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.todo.bean.TodoBean;
import kr.or.connect.todo.service.TodoService;

@RestController
public class TodoController {

	private TodoService service;

	@Autowired	
	public TodoController(TodoService service) {
		this.service = service;
	}
	
	@GetMapping("findAll")
	List<TodoBean> findAll(){
		return service.selectAll();
    }
	
	@GetMapping("insert/{text}")
	TodoBean insertTodo(@PathVariable String text){
		TodoBean todo = new TodoBean();
		todo.setTodo(text);
		
		return service.insert(todo);
    }
	
	@DeleteMapping("delete/{id}")
	void deleteTodo(@PathVariable String id){
		TodoBean todo = new TodoBean();
		todo.setId(Integer.parseInt(id));
		
		service.delete(todo);
    }
	
	@PutMapping("completed/{idList}")
	void completedTodo(@PathVariable String idList){
		
		String[] id = idList.split("\\$");
		for(int i=1; i<id.length; i++){
			TodoBean todo = new TodoBean();
			todo.setId(Integer.parseInt(id[i]));
			
			service.completed(todo);
		}
    }
	
	@DeleteMapping("clearCompleted")
	void clearCompletedTodo(HttpServletRequest request){
		service.clearCompleted();
    }
}
