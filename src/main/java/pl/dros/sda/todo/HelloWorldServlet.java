package pl.dros.sda.todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloWorld", urlPatterns = "/world")
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.append("<html>");

        writer.append("<head>");
        writer.append("<title>Hello World :)</title>");
        writer.append("</head>");

        writer.append("<body>");
        writer.append("<h1>Hello World :)</h1>");
        writer.append("<p>Nowo dodany akapit</p>");
        writer.append("</body>");

        writer.append("</html>");
    }
}
