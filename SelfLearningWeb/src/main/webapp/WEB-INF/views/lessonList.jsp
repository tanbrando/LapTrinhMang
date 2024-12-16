<%@ page import="java.util.ArrayList,
				model.BEAN.User,
				model.BEAN.Lesson" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
    <title>Self Study</title>
</head>
<body>
	<% 
   		User user = (User) session.getAttribute("currentUser");
   	%>
    <div class="container--main">
        <div class="sideBar">
            <h1 class="logo--black">SELF STUDY</h1>
            <ul class="navbar--vertical">
                <li> 
                    <img src="../static/img/book-solid.svg" alt="">
                    <a href="${pageContext.request.contextPath}/Lesson/list">Học tập</a>
                </li>
                <li>
                    <img src="../static/img/folder-solid.svg" alt="">
                    <a href="${pageContext.request.contextPath}/Lesson/edit?author_id=<%=user.getID() %>">Bài học của bạn</a>
                </li>
            </ul>
            <div class="action-group">

            </div>
            <ul class="navbar--vertical">
                <li>
                    <img src="../static/img/right-from-bracket-solid.svg" alt="">
                    <a href="${pageContext.request.contextPath}/Auth/logout">Đăng xuất</a>
                </li>
            </ul>
        </div>
        <div class="content-section--main">
            <div class="content-section__header--main">
                <div class="headerbar">
                    <h1>Học tập</h1>
                    <div class="search-box">
                        <input type="text" name="" id="">
                        <img src="../static/img/magnifying-glass-solid.svg" alt="" class="btn btn--img">
                    </div>
                </div>
                
                <div class="account-box">
                	
                    <p><%=user.getFullName()%></p>
                </div>
            </div>
            <div class="content-section__body--main">
                <div class="content-box">
                    <div class="content-box__body">
                    <% 	ArrayList<Lesson> lessonList = (ArrayList<Lesson>) request.getAttribute("lessonList");
                    	for(int i = 0; i < lessonList.size();i++) {	
                    		Lesson lesson = lessonList.get(i);
                    %>
                        <div class="lesson-box">
                            <img src="${pageContext.request.contextPath}/img/lesson/<%=lesson.getBannerFilePath() %>" alt="">
                            <div class="lesson-box__overview">
                                <h2><%=lesson.getTitle() %></h2>
                                <p><%=lesson.getDescription() %></p>
                                <a href="${pageContext.request.contextPath}/Lesson/view?id=<%=lesson.getID()%>" class="btn btn--a">Học</a>
                            </div>
                        </div>
                    <%	} %>
                    </div>
                </div>
                <div class="info-box">
                    <div class="calendar">
                        <h3>Tháng 6, 2024</h3>
                        <div class="calendar__table">
                            <table>
                                <tr>
                                    <th>CN</th>
                                    <th>T2</th>
                                    <th>T3</th>
                                    <th>T4</th>
                                    <th>T5</th>
                                    <th>T6</th>
                                    <th>T7</th>
                                </tr>
                                <tr>
                                    <td>11</td>
                                    <td>11</td>
                                    <td>11</td>
                                    <td>11</td>
                                    <td>11</td>
                                    <td>11</td>
                                    <td>11</td>
                            </table>
                        </div>
                    </div>
                    <div class="leaderboard">
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
    
</body>
</html>