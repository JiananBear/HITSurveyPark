<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
  	<title>用户登陆 —— Survey Door 调查系统</title>
  	<link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
  	<link rel="stylesheet" href="styles/bootstrap.css">
  	<link rel="stylesheet" href="styles/login.css">

  </head>
  
  <body>
  	<%-- <s:include value="header.jsp"></s:include> --%>
  	<%@ include file="header1.jsp" %>
  	<div class="container-fluid login-body">
    <div class="row">
      <div class="col-sm-8 col-md-6 col-lg-4 login-box">
        <div class="panel panel-default">
          <div class="panel-body">
            <h3>用户登陆</h3>
            <s:form action="LoginAction_doLogin" namespace="/" method="post">
              <div class="form-group">
                <label>Username</label>
                <input type="text" name="email" class="form-control" placeholder="Username/Email">
              </div>
              <div class="alert alert-danger"><s:actionerror></s:actionerror></div>
              <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" class="form-control" placeholder="Password">
              </div>
              <s:submit type="submit" cssClass="btn btn-success pull-right" value="login" />
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
