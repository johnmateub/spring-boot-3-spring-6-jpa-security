package co.mateusbello.myfirstwebapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.mateusbello.myfirstwebapp.model.Todo;
import co.mateusbello.myfirstwebapp.repository.TodoRepository;

@Service
public class TodoService {
	
	private TodoRepository todoRepository;
	

	public TodoService(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}

	private static List<Todo> todos = new ArrayList<>();
	static {
		todos.add(new Todo(1, "mathaus", "Learn AWS", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(2, "mathaus", "Learn DevOps", LocalDate.now().plusYears(2), false));
		todos.add(new Todo(3, "mathaus", "Learn Full Stack Dev", LocalDate.now().plusYears(3), false));
	}
	
	public List<Todo> findByUsername(String username) {
		return todos.stream().filter(filter -> filter.getUsername().equalsIgnoreCase(username)).toList();
	}
	
	public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(todos.size()+1, username, description, targetDate, done);
		todos.add(todo);
	}
	
	public void deleteById(int id) {
		todoRepository.deleteById(id);
	}
	
	public Todo findById(int id) {
		return todoRepository.findById(id).get();
	}
	
	public Todo updateTodo(Todo todo) {
		Todo todoUpdated = this.findById(todo.getId());
		todoUpdated.setDescription(todo.getDescription());
		todoUpdated.setTargetDate(todo.getTargetDate());
		return todoRepository.save(todo);
	}
	
	public List<Todo> getAllTodos(String username) {
		return todoRepository.findByUsername(username);
	}
	
	public void addNewTodo(Todo todo) {
		todoRepository.save(todo);
	}
	
	
}
