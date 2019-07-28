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
import java.time.DateTimeException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/post")
public class PostServlet extends HttpServlet {
  private TaskRepository repository = TaskRepository.INSTANCE;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setHeader("Content-Type", "text/html; charset=utf-8");
    PrintWriter writer =
        resp.getWriter()
            .append("<html>")
            .append("<head>")
            .append("<title>List</title>")
            .append("<meta charset=\"UTF-8\"/>")
            .append("</head>")
            .append("<body>")
            .append("<h1> Dodaj nowe zadanie do listy </h1>")
            .append("<form method=\"POST\">")
            .append("Wpisz zadanie: <br>")
            .append("<INPUT TYPE=\"TEXT\" NAME=\"description\"><br>")
            .append("Wpisz date jego wykonania w formacie yyyy-MM-dd: <br>")
            .append("<INPUT TYPE=\"DATE\" NAME=\"date\"><br><br>")
            .append("<INPUT TYPE=\"SUBMIT\" VALUE=\"Wyslij\">")
            .append("</form>")
            .append("</body>")
            .append("</html>");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String description = req.getParameter("description");
    try {
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        repository.save(Task.builder().descrioption(description).date(date).done(false).build());
    } catch (DateTimeException e) {
      resp.sendError(400, "Podałeś złą datę!");
    }

    resp.sendRedirect("/list");
  }
}
