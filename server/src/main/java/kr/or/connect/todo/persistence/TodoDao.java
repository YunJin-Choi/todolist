package kr.or.connect.todo.persistence;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.todo.bean.TodoBean;

@Repository
public class TodoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<TodoBean> rowMapper = BeanPropertyRowMapper.newInstance(TodoBean.class);
	
	@Autowired
	public TodoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("todo")
				.usingGeneratedKeyColumns("id");
	}
	
	public List<TodoBean> selectAll(){
		Map<String, Object> params = Collections.emptyMap();
		return this.jdbc.query(TodoSqls.SELECT_ALL, params, rowMapper);
	}
	
	public TodoBean selectById(int id){
		Map<String, Object> params = Collections.emptyMap();
		params.put("id", id);
		return this.jdbc.query(TodoSqls.SELECT_BY_ID, params, rowMapper).get(0);
	}
	
	public int insertTodo(TodoBean todo){
		long time = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = sdf.format(new Date(Long.valueOf(time)));
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("todo", todo.getTodo());
		params.put("completed", 0);
		params.put("date", nowTime);
		
		return insertAction.executeAndReturnKey(params).intValue();
	}
	
	public void deleteTodo(TodoBean todo){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", todo.getId());
		jdbc.update(TodoSqls.DELETE_BY_ID, params);
	}
	
	public void completedTodo(TodoBean todo){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", todo.getId());
		jdbc.update(TodoSqls.UPDATE, params);
	}
	
	public void clearCompletedTodo(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("completed", "1");
		jdbc.update(TodoSqls.DELETE_BY_COMPLETED, params);
	}
}
