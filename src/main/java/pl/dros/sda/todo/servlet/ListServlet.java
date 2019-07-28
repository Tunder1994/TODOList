package pl.dros.sda.todo.servlet;

import pl.dros.sda.todo.model.Task;
import pl.dros.sda.todo.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/list")
public class ListServlet extends HttpServlet {

  private TaskRepository repository = TaskRepository.INSTANCE;

  @Override
  public void init() throws ServletException {
    repository.save(
        Task.builder().descrioption("Zrobić zakupy").date(LocalDate.now()).done(false).build());
    repository.save(
        Task.builder()
            .descrioption("Pojechać do pracy")
            .date(LocalDate.now().minusDays(1))
            .done(true)
            .build());
    repository.save(
        Task.builder()
            .descrioption("Zrobić pranie")
            .date(LocalDate.now().plusDays(1))
            .done(false)
            .build());
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<Task> allTask =
        repository.findAll().stream()
            .sorted(Comparator.comparing(Task::getDate))
            .collect(Collectors.toList());

    resp.setHeader("Content-Type", "text/html; charset=utf-8");
    PrintWriter writer = resp.getWriter();

    writer
        .append("<html>")
        .append("<head>")
        .append("<title>List</title>")
        .append("<meta charset=\"UTF-8\"/>")
        .append("<style>")
        .append("tr.done{text-decoration: line-through}")
        .append("</style>")
        .append("</head>")
        .append("<body>")
        .append("<h1> Moje Zadania </h1>")
        .append("<table border=1>")
        .append("<tr>")
        .append("<td>")
        .append("L.p.")
        .append("</td>")
        .append("<td>")
        .append("Data")
        .append("</td>")
        .append("<td>")
        .append("Opis")
        .append("</td>")
        .append("<td>")
        .append("Zakonczone")
        .append("</td>")
        .append("</tr>");

    for (Task task : allTask) {
      writer.append(task.isDone() ? "<tr class = 'done'>" : "<tr>");
      writer.append("<td>");
      writer.print(task.getId());
      writer.append("</td>");
      writer.append("<td>");
      writer.print(task.getDate());
      writer.append("</td>");
      writer.append("<td>");
      writer.print(task.getDescrioption());
      writer.append("</td>");
      writer.append("<td>");
      writer.print(task.isDone());
      writer.append("</td>");
      writer.append("</tr>");
    }
    writer.append("</table>");
    writer.append("<br><br>");
    writer.append(
        "<a href=\"http://localhost:9070/post\"><input type=\"submit\" value = \"Nowe zadanie\"></a>");
    writer.append(
        "<a href=\"http://localhost:9070/done\"><input type=\"submit\" value = \"Zakończ zadanie\"></a>");
    writer.append("</body>");

    writer.append("</html>");
  }
}
