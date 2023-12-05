package co.mateusbello.myfirstwebapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.mateusbello.myfirstwebapp.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

	public List<Todo> findByUsername(String username);
}
