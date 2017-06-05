package kr.or.connect.todo.persistence;

public class TodoSqls {
	static final String DELETE_BY_ID =
			"DELETE FROM todo WHERE id = :id";

	static final String DELETE_BY_COMPLETED =
			"DELETE FROM todo WHERE completed = :completed";
	
	static final String UPDATE =
			"UPDATE todo SET completed = 1 WHERE id = :id";
	
	static final String SELECT_ALL = 
			"SELECT id, todo, completed FROM todo";
	
	static final String SELECT_BY_ID = 
			"SELECT id, todo, completed FROM todo WHERE id = :id";
}
