import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/login")
public class TryLogin extends HttpServlet{
    DatabaseHelper dbHelper;
    /*public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{

        String username = req.getParameter("login");
        String password = req.getParameter("password");

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        if (username.equals("master") && password.equals("yoda")){
            out.println("Welcome");
        }else {
            out.println("Incorrect login or password");
        }
    }*/

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonReader reader = Json.createReader(new InputStreamReader(req.getInputStream()));
        JsonObject newJson = reader.readObject();
        reader.close();

        String username = newJson.getString("login");
        String password = newJson.getString("password");

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        dbHelper = new DatabaseHelper();
        out.print(dbHelper.checkUser(username,password));
    }

}
