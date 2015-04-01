<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>




<%@ include file="header.jsp" %>
<%@ include file="view/navbar.jsp" %>

<sql:setDataSource 
	var="database" 
	driver="com.mysql.jdbc.Driver" 
	url="jdbc:mysql://localhost:3306/test" 
	user="root" 
	password=""/>

<section class="login-form wrap">
    <h3 class="signin">Zaloguj się</h3>

    <form name="login" method="post" action="" onsubmit="return formValidate()">
    
        <div class="alert alert-danger" role="alert"></div>
        
        <table class="form-container">
            <tr class="form-field username">
                <td>Login:</td>
                <td><input type="text" name="user_name" /></td>
            </tr>

            <tr class="form-field password">
                <td>Hasło:</td> 
                <td><input type="password" name="user_pass"></td>
            </tr>
            
        </table>
        <input class="login-button" type="submit" value="Logowanie" />
    </form>
</section>

<%@ include file="footer.jsp" %>