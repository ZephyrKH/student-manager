<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="entity.Student" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>学生信息列表</title>
</head>
<body>
    <center>
        <table align="center" width="360" border="0">
            <tr>
                <td align="center">
                    <h1>学生信息列表</h1>
                </td>
                <td align="center">
                    <a href="jsp/studentinsert.jsp">新增</a>
                </td>
                <td align="center">
                    <a href="jsp/studentimport.jsp">批量导入</a>
                </td>
            </tr>
        </table>
        <table align="center" width="660" border="1" cellspacing="0" cellpadding="5" bordercolor="#000">
            <tr>
                <th width="50px">学号</th>
                <th width="100px">姓名</th>
                <th width="50px">性别</th>
                <th width="150px">班级</th>
                <th width="50px">成绩</th>
                <th width="50px">修改</th>
                <th width="50px">删除</th>
            </tr>
            <%
                List<Student> studentlist = (List<Student>) request.getAttribute("studentlist");
                if (studentlist != null && !studentlist.isEmpty()) {
                    for (Student studentitem : studentlist) {
            %>
            <tr>
                <td><%= studentitem.getId() %></td>
                <td><%= studentitem.getName() %></td>
                <td><%= studentitem.getSex() %></td>
                <td><%= studentitem.getGrade() %></td>
                <td><%= studentitem.getScore() %></td>
                <td><a href="UpdateStudentservlet.to?id=<%= studentitem.getId() %>">修改</a></td>
                <td><a href="showStudent.do?id=<%= studentitem.getId() %>">删除</a></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </center>
</body>
</html>