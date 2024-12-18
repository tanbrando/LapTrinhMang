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
    <div class="container--auth">
        <div class="sideImg">
            <div class="header--auth">
                <h1 class="logo">SELF STUDY</h1>
                <a href="${pageContext.request.contextPath}/Auth/login" class="header__btn--auth">Đăng nhập</a>
            </div>
            
        </div>
    
        <div class="content-section--auth">
            <form action="${pageContext.request.contextPath}/Auth/register" method="POST" class="auth-form">
                <h1 class="auth-form__heading">Xin chào!</h1>
                <div class="auth-form__input-group">
                    <input type="text" id="fullname" name="fullname" required>
                    <label for="fullname">Họ và tên</label>
                </div>
                <div class="auth-form__input-group">
                    <input type="text" id="username" name="username" required>
                    <label for="username">Tên đăng nhập</label>
                </div>
                <div class="auth-form__input-group">
                    <input type="password" id="password" name="password" required>
                    <label for="password">Mật khẩu</label>
                </div>
                <div class="auth-form__input-group">
                    <input type="password" id="confirm-password" required>
                    <label for="confirm-password">Nhập lại mật khẩu</label>
                </div>
                <div class="auth-form__input-group">
                    <button type="submit">Đăng ký</button>
                </div>
                <div class="auth-form__span">
                    <span>Bạn đã có tài khoản ? </span>
                    <a href="${pageContext.request.contextPath}/Auth/login">Đăng nhập</a>
                </div>
            </form>
        </div>
    </div>
    
</body>
</html>