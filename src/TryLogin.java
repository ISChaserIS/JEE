import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * Created by Olcha on 06.11.2015.
 */
@WebServlet("/login")
public class TryLogin extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{

        JsonReader reader = Json.createReader(req.getInputStream());
        JsonObject newJson = reader.readObject();
        reader.close();

        newJson = newJson.getJsonObject("json");

        String username = newJson.getString("login");
        String password = newJson.getString("password");

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        if (username.equals("master") && password.equals("yoda")){
            out.println("Welcome");
        }else {
            out.println("Incorrect login or password");
        }
    }
}
