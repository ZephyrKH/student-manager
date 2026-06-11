package control;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.StudentModel;
import entity.Student;

public class ShowStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><title>查看学生</title>");
        out.println("<style>body{font-family:Arial;margin:20px;background:#f5f5f5}");
        out.println("h1{text-align:center;color:#333}");
        out.println(".info-box{width:500px;margin:0 auto;background:white;padding:30px;border-radius:8px;box-shadow:0 2px 4px rgba(0,0,0,0.1)}");
        out.println(".info-box p{padding:8px;border-bottom:1px solid #eee}");
        out.println(".info-box .label{display:inline-block;width:80px;font-weight:bold;color:#555}");
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

            out.println("<h1>学生详细信息</h1>");
            out.println("<div class='info-box'>");
            out.println("<p><span class='label'>学号：</span>" + student.getId() + "</p>");
            out.println("<p><span class='label'>姓名：</span>" + student.getName() + "</p>");
            out.println("<p><span class='label'>性别：</span>" + student.getSex() + "</p>");
            out.println("<p><span class='label'>年龄：</span>" + student.getAge() + "</p>");
            out.println("<p><span class='label'>班级：</span>" + student.getGrade() + "</p>");
            out.println("<p><span class='label'>成绩：</span>" + student.getScore() + "</p>");
            out.println("</div>");
            out.println("<a href='ListStudentServlet.do' class='back'>返回列表</a>");
        } catch (Exception e) {
            out.println("<div class='err'>错误发生：" + e.getMessage() + "</div>");
            e.printStackTrace();
        }

        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
