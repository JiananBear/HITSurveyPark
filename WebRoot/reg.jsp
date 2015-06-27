<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
  <title>用户注册 —— Survey Door 调查系统</title>
  <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
  <link rel="stylesheet" href="styles/bootstrap.css">
  <link rel="stylesheet" href="styles/login.css">

  </head>
  
  <body>
  	<%@ include file="header1.jsp" %>
  	
  	<!-- body start -->
  <div class="container-fluid login-body">
    <div class="row">
      <div class="col-sm-8 col-md-6 col-lg-4 login-box">
        <div class="panel panel-default">
          <div class="panel-body">
            <h3>用户注册</h3>
            <s:form action="RegAction_doReg" namespace="/" method="post">
              <div class="form-group">
                <label>Username</label>
                <input type="text" name="email" class="form-control" placeholder="Username">
              </div>
               <div class="alert alert-danger"><s:fielderror><s:param>email</s:param></s:fielderror><s:fielderror fieldName="email"></s:fielderror></div>
              <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" class="form-control" placeholder="Password">
              </div>
               <div class="alert alert-danger"><s:fielderror><s:param>password</s:param></s:fielderror></div>
              <div class="form-group">
                <label>Confirm</label>
                <input type="password" name="confirmPassword" class="form-control" placeholder="Confirm">
              </div>
               <div class="alert alert-danger"></div>
              <div class="form-group">
                <label>Nickname</label>
                <input type="text" name="nickName" class="form-control" placeholder="Nickname">
              </div>
               <div class="alert alert-danger"><s:fielderror><s:param>nickName</s:param></s:fielderror></div>
              <s:submit cssClass="btn btn-success pull-right" value="确定"/>
            </s:form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- body end -->

  <script type="text/javascript" src="scripts/jquery.js"></script>
  <script type="text/javascript" src="scripts/bootstrap.js"></script>
  
  <script type="text/javascript">
	$(function () {
  		$('.alert:empty').css('display', 'none');
	})
</script>
<%@ include file="modalboxfooter.jsp" %>
  </body>
</html>
