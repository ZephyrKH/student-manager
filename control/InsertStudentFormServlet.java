package control;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertStudentFormServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><title>新增学生</title>");
        out.println("<style>body{font-family:Arial;margin:20px;background:#f5f5f5}");
        out.println("h1{text-align:center;color:#333}");
        out.println(".form-box{width:500px;margin:0 auto;background:white;padding:30px;border-radius:8px;box-shadow:0 2px 4px rgba(0,0,0,0.1)}");
        out.println(".form-box input,.form-box select{width:100%;padding:8px;margin:5px 0;border:1px solid #ddd;border-radius:4px;box-sizing:border-box}");
        out.println(".form-box label{display:block;margin-top:10px;color:#555;font-weight:bold}");
        out.println(".form-box input[type=submit]{background:#4CAF50;color:white;border:none;cursor:pointer;padding:10px;margin-top:20px}");
        out.println(".form-box input[type=submit]:hover{background:#45a049}");
        out.println(".back{display:block;text-align:center;margin-top:20px;color:#2196F3;text-decoration:none}");
        out.println("</style></head><body>");

        out.println("<h1>新增学生信息</h1>");
        out.println("<div class='form-box'>");
        out.println("<form action='InsertStudentservlet.do' method='post'>");
        out.println("<label>学号：</label><input type='text' name='id' required>");
        out.println("<label>姓名：</label><input type='text' name='name' required>");
        out.println("<label>性别：</label><select name='sex'><option value='男'>男</option><option value='女'>女</option></select>");
        out.println("<label>年龄：</label><input type='text' name='age' required>");
        out.println("<label>班级：</label><input type='text' name='grade' required>");
        out.println("<label>成绩：</label><input type='text' name='score' required>");
        out.println("<input type='submit' value='提交'>");
        out.println("</form>");
        out.println("</div>");
        out.println("<a href='ListStudentServlet.do' class='back'>返回列表</a>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
