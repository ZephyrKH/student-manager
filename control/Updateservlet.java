package control;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.StudentModel;
import entity.Student;

public class Updateservlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><title>修改学生</title>");
        out.println("<style>body{font-family:Arial;margin:20px;background:#f5f5f5}");
        out.println("h1{text-align:center;color:#333}");
        out.println(".form-box{width:500px;margin:0 auto;background:white;padding:30px;border-radius:8px;box-shadow:0 2px 4px rgba(0,0,0,0.1)}");
        out.println(".form-box input,.form-box select{width:100%;padding:8px;margin:5px 0;border:1px solid #ddd;border-radius:4px;box-sizing:border-box}");
        out.println(".form-box label{display:block;margin-top:10px;color:#555;font-weight:bold}");
        out.println(".form-box input[type=submit]{background:#2196F3;color:white;border:none;cursor:pointer;padding:10px;margin-top:20px}");
        out.println(".form-box input[type=submit]:hover{background:#1976D2}");
        out.println(".back{display:block;text-align:center;margin-top:20px;color:#2196F3;text-decoration:none}");
        out.println(".err{text-align:center;color:#a94442;padding:20px}");
        out.println("</style></head><body>");

        try {
            String id = request.getParameter("id");
            if (id == null) {
                out.println("<div class='err'>错误：没有提供学生ID</div>");
                out.println("<a href='ListStudentServlet.do' class='back'>返回列表</a>");
                out.println("</body></html>");
                return;
            }

            Integer studentId = Integer.valueOf(id);
            StudentModel model = new StudentModel();
            Student student = model.load(studentId);

            if (student == null) {
                out.println("<div class='err'>未找到学号为 " + id + " 的学生</div>");
                out.println("<a href='ListStudentServlet.do' class='back'>返回列表</a>");
                out.println("</body></html>");
                return;
            }

            out.println("<h1>修改学生信息</h1>");
            out.println("<div class='form-box'>");
            out.println("<form action='DoUpdatStudenteservlet.do' method='post'>");
            out.println("<input type='hidden' name='id' value='" + student.getId() + "'>");
            out.println("<label>学号：</label><input type='text' value='" + student.getId() + "' disabled>");
            out.println("<label>姓名：</label><input type='text' name='name' value='" + student.getName() + "' required>");
            out.println("<label>性别：</label><select name='sex'>");
            out.println("<option value='男'" + ("男".equals(student.getSex()) ? " selected" : "") + ">男</option>");
            out.println("<option value='女'" + ("女".equals(student.getSex()) ? " selected" : "") + ">女</option>");
            out.println("</select>");
            out.println("<label>年龄：</label><input type='text' name='age' value='" + student.getAge() + "' required>");
            out.println("<label>班级：</label><input type='text' name='grade' value='" + student.getGrade() + "' required>");
            out.println("<label>成绩：</label><input type='text' name='score' value='" + student.getScore() + "' required>");
            out.println("<input type='submit' value='保存修改'>");
            out.println("</form>");
            out.println("</div>");
            out.println("<a href='ListStudentServlet.do' class='back'>返回列表</a>");
        } catch (Exception e) {
            out.println("<div class='err'>错误发生：" + e.getMessage() + "</div>");
            e.printStackTrace();
        }

        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
