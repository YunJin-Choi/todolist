package kr.or.connect.todo.bean;

public class TodoBean {
	
	int id;
	String todo;
	int completed;
	String date;

	public TodoBean() {
	}
	public TodoBean(int id, String todo, int completed, String date){
		this.id = id;
		this.todo = todo;
		this.completed = completed;
		this.date = date;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public void setTodo(String todo) {
		this.todo = todo;
	}
	public String getTodo() {
		return todo;
	}
	
	public void setCompleted(int completed) {
		this.completed = completed;
	}
	public int getCompleted() {
		return completed;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return date;
	}
}
