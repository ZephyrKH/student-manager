package control;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ImportStudentFormServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><title>批量导入学生</title>");
        out.println("<style>body{font-family:Arial;margin:20px;background:#f5f5f5}");
        out.println("h1{text-align:center;color:#333}");
        out.println(".form-box{width:600px;margin:0 auto;background:white;padding:30px;border-radius:8px;box-shadow:0 2px 4px rgba(0,0,0,0.1)}");
        out.println(".form-box input[type=file]{width:100%;padding:8px;margin:10px 0;border:1px solid #ddd;border-radius:4px;box-sizing:border-box}");
        out.println(".form-box input[type=submit]{background:#4CAF50;color:white;border:none;cursor:pointer;padding:10px;margin-top:20px;width:100%}");
        out.println(".form-box input[type=submit]:hover{background:#45a049}");
        out.println(".back{display:block;text-align:center;margin-top:20px;color:#2196F3;text-decoration:none}");
        out.println(".tip{background:#fff3cd;padding:10px;border-radius:4px;margin:10px 0;font-size:14px;color:#856404}");
        out.println("pre{background:#f8f8f8;padding:10px;border-radius:4px;font-size:12px}");
        out.println("</style></head><body>");

        out.println("<h1>批量导入学生信息（CSV文件）</h1>");
        out.println("<div class='form-box'>");
        out.println("<div class='tip'>");
        out.println("<strong>说明：</strong>请上传CSV文件，第一行为表头，格式为：");
        out.println("<pre>id,name,sex,age,grade,score\n1,张三,男,20,计算机1班,95.5\n2,李四,女,21,计算机2班,88.0</pre>");
        out.println("</div>");
        out.println("<form action='ImportStudentServlet.do' method='post' enctype='multipart/form-data'>");
        out.println("<label>选择CSV文件：</label><input type='file' name='excelFile' accept='.csv' required>");
        out.println("<input type='submit' value='上传并导入'>");
        out.println("</form>");
        out.println("</div>");
        out.println("<a href='ListStudentServlet.do' class='back'>返回列表</a>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
