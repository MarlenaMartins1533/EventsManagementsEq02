<%-- 
    Document   : index
    Created on : Nov 15, 2014, 12:23:05 AM
    Author     : rgcs
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../templates/header.jspf" %>
<div class="page-header">
    <h2>Project Base Actions</h2>
</div>    
<div>
    <h2>User</h2>
    <ol>
        <li><a href="${pageContext.request.contextPath}/User/all">List</a></li> 
        <li><a href="${pageContext.request.contextPath}/User/add">Create</a></li>
        <li><a href="${pageContext.request.contextPath}/User/addClient">Create Company</a></li>
        <li><a href="${pageContext.request.contextPath}/loginForm">Login</a></li>
        <li><a href="${pageContext.request.contextPath}/menu">Menu</a></li>
    </ol>

    <h2>Classes</h2>
    <ol>
        <li><a href="${pageContext.request.contextPath}/Classes/all">List</a></li> 
    </ol>
    <h2>Activity</h2>
    <ol>
        <li><a href="${pageContext.request.contextPath}/Activity/all">List</a></li> 
        <li><a href="${pageContext.request.contextPath}/Activity/add">Create</a></li>
    </ol>
</div>
<%@include file="../templates/footer.jspf" %>
