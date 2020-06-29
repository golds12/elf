import tools.*;
import org.json.simple.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "tests", urlPatterns = "/tests")
public class tests extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json; charset=utf-8");

        int id = myTools.string2int((String) request.getParameter("id"));
        PrintWriter out = response.getWriter();
        //out.println("{\"test\":\"test\"}");
        out.println(getTests(id).toJSONString());
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private JSONArray getTests(int id) {
        String sql = "SELECT id, name, start_date, end_date FROM test";
        if (id > 0) sql += " where id = " + id;
        return DB.getSqlToJSON(sql);
    }

}