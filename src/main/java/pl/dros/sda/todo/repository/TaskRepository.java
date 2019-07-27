package pl.dros.sda.todo.repository;

import pl.dros.sda.todo.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum TaskRepository {
  INSTANCE;

  private Map<Integer, Task> data = new HashMap<>();
  private Integer lastInstertedId = 0;

  public Task findById(Integer id) {
    return data.get(id);
  }

  public List<Task> findAll() {
    return new ArrayList<>(data.values());
  }

  public Task save(Task task) {
    if (task.getId() == null) {
      task.setId(++lastInstertedId);
    }
    data.put(task.getId(), task);
    return task;
  }
}
