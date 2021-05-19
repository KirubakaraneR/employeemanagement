<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>Employee Page</title>
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
</style>
</head>
<body>

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
    <a href="EmployeeController?action=changeAdminPassword">
    <button>Change Password</button></a>&nbsp;&nbsp;&nbsp;
    <a href="EmployeeController?action=logout">
    <button>Logout</button></a>
  </div>
  
  <div class="offset">
    <center>
    <table class="pure-table pure-table-horizontal">
      <thead>
        <tr style="font-size: 20px;">
          <th>Name</th>
          <th>Contact</th>
          <th>Date Of Birth</th>
          <th>Date Of Join</th>
          <th>Salary</th>
          <th><center>Options</center></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${employeeList}" var="employee">
          <tr style="font-size: 20px;">
            <td><c:out value="${employee.name}"/><br>
            <b>ID: </b><c:out value="${employee.id}"/></td>
            <td><c:out value="${employee.mailId}"/><br>
            <c:out value="${employee.mobileNumber}"/></td>
            <td><fmt:formatDate pattern="dd MMM,yyyy" 
                value="${employee.dateOfBirth}"/></td>
            <td><fmt:formatDate pattern="dd MMM,yyyy" 
                value="${employee.dateOfJoin}"/></td>
            <td><c:out value="${employee.salary}"/></td>
            <td><a style="font-size: 20px; color: #e600e6;" 
                href="EmployeeController?action=viewAddress&employeeId=<c:out 
                value="${employee.id}"/>">
            <b>VIEW ADDRESS</b></a><br>
            <a style="font-size: 20px; color: olive;" 
                href="EmployeeController?action=assignedProjects&employeeId=<c:out 
                value="${employee.id}"/>">
            <b>DISPLAY PROJECTS</b></a><br>
            <a style="font-size: 20px; color: #ff0000;" 
                href="EmployeeController?action=editEmployee&employeeId=<c:out 
                value="${employee.id}"/>">
            <b>EDIT</b></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a style="font-size: 20px; color: #000099;" 
                href="EmployeeController?action=deleteEmployee&employeeId=<c:out 
                value="${employee.id}"/>">
            <b>DELETE</b></a></center>
            <center>
            <a style="font-size: 20px; color: #595959;" 
                href="EmployeeController?action=projectAssignPage&employeeId=<c:out 
                value="${employee.id}"/>">
            <b>ASSIGN</b></a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a style="font-size: 20px; color: #00e600;" 
                href="EmployeeController?action=assignedProjects&employeeId=<c:out 
                value="${employee.id}"/>">
            <b>UNASSIGN</b></a></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    </center>
    <center>
    <a style="font-size: 20px; color: #595959;" 
        href="EmployeeController?action=employeeForm"><b>RESTORE</b></a>
    </center><br>
  </div>
  
</body>
</html>