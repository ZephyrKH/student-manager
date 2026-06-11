package control;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import model.ExcelImportModel;
import entity.Student;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1MB 阈值，超过则写入临时文件
    maxFileSize = 10 * 1024 * 1024,  // 最大文件大小 10MB
    maxRequestSize = 15 * 1024 * 1024 // 最大请求大小 15MB
    // location 不指定，使用 ServletContext 的临时目录（由 Tomcat 自动管理）
)
public class ImportStudentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            Part filePart = request.getPart("excelFile");
            String fileName = getFileName(filePart);

            if (fileName != null && fileName.toLowerCase().endsWith(".csv")) {
                InputStream inputStream = filePart.getInputStream();

                ExcelImportModel model = new ExcelImportModel();
                List<Student> studentList = model.parseExcel(inputStream);
                int count = model.importStudents(studentList);

                response.sendRedirect("ListStudentServlet.do?msg=imported&count=" + count);
                return;
            } else {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().println("<h1>请上传CSV文件(.csv)</h1><a href='ImportStudentForm.do'>返回</a>");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html;charset=UTF-8");
            String errorMsg = "<h1>文件上传失败：" + e.getClass().getName() + "</h1>";
            errorMsg += "<p>消息：" + e.getMessage() + "</p>";
            errorMsg += "<p>临时目录：" + getServletContext().getAttribute("jakarta.servlet.context.tempdir") + "</p>";
            errorMsg += "<pre>";
            for (StackTraceElement element : e.getStackTrace()) {
                errorMsg += element.toString() + "<br>";
            }
            errorMsg += "</pre>";
            errorMsg += "<a href='ImportStudentForm.do'>返回</a>";
            response.getWriter().println(errorMsg);
        }
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                String name = token.substring(token.indexOf("=") + 2, token.length() - 1);
                // 处理中文文件名
                try {
                    return new String(name.getBytes("ISO-8859-1"), "UTF-8");
                } catch (Exception e) {
                    return name;
                }
            }
        }
        return null;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("ImportStudentForm.do");
    }
}
