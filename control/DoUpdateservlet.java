package control;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.StudentModel;

public class DoUpdateservlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String sex = request.getParameter("sex");
            int age = Integer.parseInt(request.getParameter("age"));
            String grade = request.getParameter("grade");
            float score = Float.parseFloat(request.getParameter("score"));

            StudentModel model = new StudentModel();
            model.update(id, name, sex, age, grade, score);
            response.sendRedirect("ListStudentServlet.do?msg=updated");
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h1>修改失败：" + e.getMessage() + "</h1><a href='ListStudentServlet.do'>返回</a>");
        }
    }
}
