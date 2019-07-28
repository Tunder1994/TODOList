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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/done")
public class DoneServlet extends HttpServlet {

  TaskRepository repository = TaskRepository.INSTANCE;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<Task> taskStream =
        repository.findAll().stream().filter(s -> !s.isDone()).collect(Collectors.toList());

    resp.setHeader("Content-Type", "text/html; charset=utf-8");
    PrintWriter writer =
        resp.getWriter()
            .append("<html>")
            .append("<head>")
            .append("<title>List</title>")
            .append("<meta charset=\"UTF-8\"/>")
            .append("</head>")
            .append("<body>")
            .append("<h1> Kończenie zadania</h1>")
            .append("<form method=\"POST\">")
            .append("Wpisz zadanie które skończyłeś: <br>");
    for (int i = 0; i < taskStream.size(); i++) {
      Task task = taskStream.get(i);
      String name = "name" + i;
      writer.append(
          "<input type=\"checkbox\" name= "
              + name
              + "  value= "
              + true
              + " >"
              + task.toString()
              + "<br>");
    }
    writer
        .append("<INPUT TYPE=\"SUBMIT\" VALUE=\"Wyslij\">")
        .append("</form>")
        .append("</body>")
        .append("</html>");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<Task> taskStream =
        repository.findAll().stream().filter(s -> !s.isDone()).collect(Collectors.toList());
    for (int i = 0; i < taskStream.size(); i++) {
      String name = "name" + i;
      boolean isDone = Boolean.parseBoolean(req.getParameter(name));
      if (isDone) {
        taskStream.get(i).setDone(true);
      }
    }
    resp.sendRedirect("/list");
  }
}
