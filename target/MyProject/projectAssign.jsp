<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List, java.util.ArrayList, 
    com.ideas2it.project.employeemanagement.model.Employee"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"   
href="https://unpkg.com/purecss@1.0.0/build/pure-min.css"   
integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w"   
crossorigin="anonymous">
<title>Project Assign Page</title>
<style>
  body {
    margin: 0;
    padding: 0;
    font-family: Calibri, Helvetica, sans-serif;
    background-color: #e6e6ff;
  }
  
  .offset {
    margin-top: 0;
    padding-top: 130px;
    text-align: center;
    transition: .3s;
    font-size: 30px;
  }
  
  .header {
    position: fixed;
    width: 100%;
    height: 70px;
    font-weight: bold;
    transition: 0.3s;
    background-color: white;
    padding: 10px;
  }
  
  h1 {
    margin-top: 15px;
    margin-left: 15px;
  }
  
  button {
    text-decoration: none;
    color: black;
    font-size: 20px;
    position: relative;
    top: -82%;
    left: 36%;
    border: none;
    background-color: white;
  }
  
  button:hover {
    color: blue;
  }
   
  a {
    text-decoration: none;
    color: black;
    font-size: 25px;
  }
  
  a:hover {
    color: blue;
  }
  
  #options {
    text-decoration: none;
    color: black;
    font-size: 20px;
    position: relative;
    top: -76%;
    left: 42%;
    border: none;
    background-color: white;
  }
    
  input[type=submit] {
    background-color: fuchsia;
    width: 10%;
    color: black;
    padding: 8px;
    margin: 10px 0px;
    border: none;
    cursor: pointer;
    font-size: 20px;
  }
  
  input[type=text] {   
    width: 100%;   
    margin: 4px 0;  
    padding: 8px 20px;   
    display: inline-block;   
    border: 2px solid green;   
    box-sizing: border-box;  
    font-size: 20px; 
  }

  #assign {
    width: 200px;
    position: fixed;
    top: 40%;
    left: 10%;
  }
</style>
</head>
<body>
  <%
  
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  
  if (session.getAttribute("mailId") == null) {
	    response.sendRedirect("index.jsp");
    }
    %>
    
  <div class="header">
    <h1>Ideas2IT Technologies</h1>
    <a href="EmployeeController?action=indexPage">
    <button>Home</button></a>&nbsp;&nbsp;&nbsp;
    <a href="EmployeeController?action=employeeMainPage">
    <button>Employee</button></a>&nbsp;&nbsp;&nbsp;
    <a href="ProjectController?action=projectMainPage">
    <button>Project</button></a>&nbsp;&nbsp;&nbsp;
    <a href="EmployeeController?action=employeeForm">
    <button>Create Employee</button></a>&nbsp;&nbsp;&nbsp;
    <a href="ProjectController?action=projectForm">
    <button>Create Project</button></a>&nbsp;&nbsp;&nbsp;
    <a href="LoginAuthentication?action=changeAdminPassword">
    <button>Change Password</button></a>&nbsp;&nbsp;&nbsp;
    <a href="LoginAuthentication">
    <button>Logout</button></a>
  </div>
    
  <div class="offset">
    <center>
    <table class="pure-table pure-table-horizontal">
      <thead>
        <tr style="font-size: 20px;">
          <th>ID</th>
          <th>Name</th>
          <th>Mobile Number</th>
          <th>Mail Id</th>
        </tr>
      </thead>
      <tbody>
      <%List<String> employeeDatas = 
                (List<String>)request.getAttribute("employee");
        String employee = employeeDatas.get(0);
        String[] employeeList = employee.split(",");%>
        <tr style="font-size: 20px;">
          <td><%=employeeList[0]%></td>
          <td><%=employeeList[1]%></td>
          <td><%=employeeList[6]%></td>
          <td><%=employeeList[7]%></td>
        </tr>
      </tbody>
    </table>
    </center><br>
    <center>
    <table class="pure-table pure-table-horizontal">
      <thead>
        <tr style="font-size: 20px;">
          <th>ID</th>
          <th>Name</th>
          <th>Start Date</th>
          <th>End Date</th>
          <th>Status</th>
          <th>CheckBox</th>
        </tr>
       </thead>
       <tbody>
       <%for (int index = 1; index < employeeDatas.size(); index++) {
             String project = employeeDatas.get(index);
             String[] projectList = project.split(",");%>
         <form action="EmployeeController" method="post">
           <tr style="font-size: 20px;">
             <td><%=projectList[0]%></td>
             <td><%=projectList[1]%></td>
             <td><%=projectList[2]%></td>
             <td><%=projectList[3]%></td>
             <td><%=projectList[4]%></td>
             <td><center><input type="checkbox" name="names" 
                 value="<%=projectList[0]%>"></center></td>
           </tr>
         <%}%>
         </tbody>
       </table>
   
       <input type="hidden" name="action" value="assignProjectsToEmployee">
       <input type="hidden" name="employeeId" value="<%=employeeList[0]%>">
       <input type="submit" value="Assign">
     </form>
     </center>
   </div> 
</body>
</html>