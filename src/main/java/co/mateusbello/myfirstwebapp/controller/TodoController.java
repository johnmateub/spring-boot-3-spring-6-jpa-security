package co.mateusbello.myfirstwebapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import co.mateusbello.myfirstwebapp.model.Todo;
import co.mateusbello.myfirstwebapp.service.TodoService;
import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class TodoController {

	private TodoService todoService;
	
	
	
	
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}




	@GetMapping("list-todos")
	public String getAllTodo(ModelMap model) {
		List<Todo> todos = todoService.findByUsername(getLoggedinUsername());
		model.put("todos", todos);
		return "listTodos";
	}
	
	@GetMapping("add-todo")
	public String showAddTodoPage(ModelMap model) {
		model.put("todo", new Todo(0, getLoggedinUsername(), "", LocalDate.now().plusYears(1), false));
		
		return "todo";
	}
	
	@PostMapping("add-todo")
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		todoService.addTodo((String)model.get("name"), todo.getDescription(), todo.getTargetDate(), false);
		return "redirect:list-todos";
	}
	
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoService.deleteById(id);
		return "redirect:list-todos";
	}
	
	@GetMapping("update-todo")
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todoService.findById(id);
		model.put("todo", todo);
		
		return "todo";
	}
	
	@PostMapping("update-todo")
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		Todo todoUpdated = todoService.updateTodo(todo);
		model.put("todo", todoUpdated);
		return "redirect:list-todos";
	}
	
	private String getLoggedinUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	
}
