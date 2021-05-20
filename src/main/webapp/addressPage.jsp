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
<title>Address Page</title>
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
  <%
  
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  
  if (session.getAttribute("mailId") == null) {
	    response.sendRedirect("index.jsp");
     }%>
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
    <a href="LoginAuthentication">
    <button>Logout</button></a>
  </div> 
  
  <div class="offset">
    <center>
    <table class="pure-table pure-table-horizontal">
      <thead>
        <tr style="font-size: 20px;">
          <th>Door No</th>
          <th>Street</th>
          <th>Nagar/Area</th>
          <th>City</th>
          <th>District</th>
          <th>State</th>
          <th>Pincode</th>
          <th>Country</th>
          <th colspan="2"><center>Options</center></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${addressList}" var="address">
          <tr style="font-size: 20px;">
            <c:if test="${address.isDeleted == false}">
              <td><c:out value="${address.doorNumber}"/></td>
              <td><c:out value="${address.street}"/></td>
              <td><c:out value="${address.nagar}"/></td>
              <td><c:out value="${address.city}"/></td>
              <td><c:out value="${address.district}"/></td>
              <td><c:out value="${address.state}"/></td>
              <td><c:out value="${address.pinCode}"/></td>
              <td><c:out value="${address.country}"/></td>
              <td><a style="font-size: 20px; color: #595959;" 
                  href="EmployeeController?action=getAddressForEdit&id=<c:out 
                  value="${address.id}"/>&employeeId=<c:out 
                  value="${address.employee.id}"/>"><b>EDIT</b></a></td>
              <td><a style="font-size: 20px; color: #00e600;" 
                  href="EmployeeController?action=deleteIndividualAddress&id=<c:out 
                  value="${address.id}"/>&employeeId=<c:out 
                  value="${address.employee.id}"/>"><b>DELETE</b></a></td>
            </c:if>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    </center>
    <center>
    <a style="font-size: 20px; color: #e600e6;" 
        href="EmployeeController?action=addressForm&employeeId=<c:out 
        value="${addressList.get(0).employee.id}"/>">
    <b>ADD</b></a></center>
  </div>

</body>
</html>