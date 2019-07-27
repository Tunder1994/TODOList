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
import java.util.List;

@WebServlet(urlPatterns = "/list")
public class ListServlet extends HttpServlet {

  private TaskRepository repository = TaskRepository.INSTANCE;

  @Override
  public void init() throws ServletException {
    repository.save(
        Task.builder().descrioption("Zrobić zakupy").date(LocalDate.now()).done(false).build());
    repository.save(
        Task.builder().descrioption("Wyprowadzic psa").date(LocalDate.now().minusDays(1)).done(true).build());
    repository.save(
        Task.builder().descrioption("Zrobić pranie").date(LocalDate.now().plusDays(1)).done(false).build());
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<Task> allTask = repository.findAll();

    resp.setHeader("Content-Type",
            "text/html; charset=utf-8");
    PrintWriter writer =resp.getWriter();

    writer.append("<html>");
    writer.append("<head>");
    writer.append("<title>List</title>");
    writer.append("<meta charset=\"UTF-8\"/>");
    writer.append("</head>");

    writer.append("<body>");
      writer.append("<table border=1>");
        writer.append("<tr>");
          writer.append("<td>");
            writer.print("L.p.");
          writer.append("</td>");
          writer.append("<td>");
            writer.print("Data");
          writer.append("</td>");
          writer.append("<td>");
            writer.print("Opis");
          writer.append("</td>");
          writer.append("<td>");
            writer.print("Zakonczone");
          writer.append("</td>");
        writer.append("</tr>");
    for (Task task : allTask ) {
        writer.append("<tr>");
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

    writer.append("</body>");

    writer.append("</html>");
  }
}
