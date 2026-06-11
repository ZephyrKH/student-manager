package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.StudentModel;
import entity.Student;

public class ListStudentServlet  extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>学生信息管理系统</title>");
        out.println("<style>");
        out.println("body{font-family:Arial;margin:20px;background:#f5f5f5}");
        out.println("h1{text-align:center;color:#333}");
        out.println(".nav{text-align:center;margin-bottom:20px}");
        out.println(".nav a{display:inline-block;padding:8px 16px;margin:0 5px;background:#4CAF50;color:white;text-decoration:none;border-radius:4px}");
        out.println(".nav a:hover{background:#45a049}");
        out.println("table{border-collapse:collapse;width:90%;margin:0 auto;background:white;box-shadow:0 2px 4px rgba(0,0,0,0.1)}");
        out.println("th,td{border:1px solid #ddd;padding:10px;text-align:center}");
        out.println("th{background:#4CAF50;color:white}");
        out.println("tr:nth-child(even){background:#f9f9f9}");
        out.println(".action a{color:#2196F3;text-decoration:none;margin:0 5px}");
        out.println(".action a:hover{text-decoration:underline}");
        out.println(".delete{color:#f44336 !important}");
        out.println(".delete:hover{color:#d32f2f !important}");
        out.println(".form-box{width:500px;margin:0 auto;background:white;padding:30px;border-radius:8px;box-shadow:0 2px 4px rgba(0,0,0,0.1)}");
        out.println(".form-box input,.form-box select{width:100%;padding:8px;margin:5px 0;border:1px solid #ddd;border-radius:4px;box-sizing:border-box}");
        out.println(".form-box input[type=submit]{background:#4CAF50;color:white;border:none;cursor:pointer;padding:10px}");
        out.println(".form-box input[type=submit]:hover{background:#45a049}");
        out.println(".btn{display:inline-block;padding:8px 16px;background:#4CAF50;color:white;text-decoration:none;border-radius:4px;border:none;cursor:pointer}");
        out.println(".msg{text-align:center;padding:15px;background:#dff0d8;color:#3c763d;border-radius:4px;margin:10px auto;width:80%}");
        out.println(".err{text-align:center;padding:15px;background:#f2dede;color:#a94442;border-radius:4px;margin:10px auto;width:80%}");
        out.println("</style></head>");
        out.println("<body>");

        try {
            StudentModel model = new StudentModel();
            List studentlist = model.search();

            out.println("<h1>学生信息管理系统</h1>");

            String msg = request.getParameter("msg");
            if (msg != null) {
                if ("added".equals(msg)) out.println("<div class='msg'>添加学生成功！</div>");
                else if ("updated".equals(msg)) out.println("<div class='msg'>修改学生成功！</div>");
                else if ("deleted".equals(msg)) out.println("<div class='msg'>删除学生成功！</div>");
                else if ("imported".equals(msg)) out.println("<div class='msg'>批量导入成功！</div>");
            }

            out.println("<div class='nav'>");
            out.println("<a href='InsertStudentForm.do'>新增学生</a>");
            out.println("<a href='ImportStudentForm.do'>批量导入</a>");
            out.println("<a href='ListStudentServlet.do'>刷新列表</a>");
            out.println("</div>");

            if (studentlist != null && !studentlist.isEmpty()) {
                out.println("<table>");
                out.println("<tr><th>学号</th><th>姓名</th><th>性别</th><th>年龄</th><th>班级</th><th>成绩</th><th>操作</th></tr>");

                for (int i=0; i<studentlist.size(); i++) {
                    Student s = (Student) studentlist.get(i);
                    out.println("<tr>");
                    out.println("<td>" + s.getId() + "</td>");
                    out.println("<td>" + s.getName() + "</td>");
                    out.println("<td>" + s.getSex() + "</td>");
                    out.println("<td>" + s.getAge() + "</td>");
                    out.println("<td>" + s.getGrade() + "</td>");
                    out.println("<td>" + s.getScore() + "</td>");
                    out.println("<td class='action'>");
                    out.println("<a href='showStudent.do?id=" + s.getId() + "'>查看</a>");
                    out.println("<a href='UpdateStudentservlet.to?id=" + s.getId() + "'>修改</a>");
                    out.println("<a href='DeleteStudentservlet.do?id=" + s.getId() + "' class='delete' onclick=\"return confirm('确定删除学号" + s.getId() + "的学生吗？')\">删除</a>");
                    out.println("</td>");
                    out.println("</tr>");
                }

                out.println("</table>");
                out.println("<p style='text-align:center;margin-top:20px'>共 " + studentlist.size() + " 条记录</p>");
            } else {
                out.println("<div class='err'>没有找到学生记录</div>");
            }

        } catch (Exception e) {
            out.println("<div class='err'>错误发生: " + e.getMessage() + "</div>");
            e.printStackTrace();
        }

        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
