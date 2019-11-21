<%@page import="com.NationalScholarship.model.basicScholarshipRegistration"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>

#applicants {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#applicants td, #applicants th {
  border: 1px solid #ddd;
  padding: 8px;
}

#applicants tr:nth-child(even){background-color: #f2f2f2;}

#applicants tr:hover {background-color: #ddd;}

#applicants th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
}

</style>
</head>
<body>
<table id="applicants">
 <tr>
 <th>  <b>Aadhar number</th>
 	<th>  <b>Scholarship Applied</th>	
 	<th><b>student caste</th>	
 	<th><b><b>Family income</th>
 	<th><b>Father name</th>
 	<th><b>Is disabled</th>
 	<th><b>Martial status</th>
 	<th><b>Student parents profession</th>
 	<th><b>Student type disability</th>
 	
 	<th><b>student Religion</th>
 	<th><b>Student type disability</th>
 	
 	</tr>
 <c:forEach items="${userlist}" var="bsr">
      <div>      
 	 <tr>
    <td><c:out value="${bsr[0]}"/></td>

 	 
    <td><c:out value="${bsr[1]}"/></td>
  
 	 
    <td><c:out value="${bsr[2]}"/></td>
  
    <td><c:out value="${bsr[3]}"/></td>
  
    <td><c:out value="${bsr[4]}"/></td>
  
    <td><c:out value="${bsr[5]}"/></td>
  
    <td><c:out value="${bsr[6]}"/></td>
  
    <td><c:out value="${bsr[7]}"/></td>
  
    <td><c:out value="${bsr[8]}"/></td>
 
    <td><c:out value="${bsr[9]}"/></td>
 
    <td><c:out value="${bsr[10]}"/></td>
  </tr>

</c:forEach>
</body>
</html>