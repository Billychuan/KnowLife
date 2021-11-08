<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.List" %>
<link rel="stylesheet" href="css/content.css">
<% 
session = request.getSession();
List<String> User=(List<String>)session.getAttribute("User_textarea");
List<String> Robot=(List<String>)session.getAttribute("Robot_textarea");

if (User!=null){
	for(int i=0; i<User.size();i++){
		out.print("<div id='user'>"+"<span id='us2'>"+User.get(i)+"</span>"+"</div>");
		out.print("<div id='remote'>"+"<span id='re2'>"+Robot.get(i)+"</span>"+"</div>");
	}
}
else{
	out.print("<div> </div>");
}

%>

