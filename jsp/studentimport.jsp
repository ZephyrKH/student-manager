<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>批量导入学生信息</title>
</head>
<body>
    <center>
        <h1>批量导入学生信息</h1>
        
        <% if (request.getAttribute("importCount") != null) { %>
            <div style="color: green; font-weight: bold;">
                成功导入 <%= request.getAttribute("importCount") %> 条学生记录！
            </div>
        <% } %>
        
        <% if (request.getAttribute("error") != null) { %>
            <div style="color: red; font-weight: bold;">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>
        
        <form action="../ImportStudentServlet.do" method="post" enctype="multipart/form-data">
            <p>选择CSV文件：</p>
            <p><input type="file" name="excelFile" accept=".csv" /></p>
            <p>
                <input type="submit" value="上传导入" />
                <input type="button" value="返回列表" onclick="window.location.href='../ListStudentServlet.do'" />
            </p>
        </form>
        
        <p style="font-size: 12px; color: #666;">
            CSV文件格式要求：第一行为表头（学号,姓名,性别,年龄,班级,成绩），从第二行开始为数据，使用英文逗号分隔
        </p>
    </center>
</body>
</html>