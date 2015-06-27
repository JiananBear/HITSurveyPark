<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>选择问题类型 —— Survey Door 调查系统</title>

  <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">

  <link rel="stylesheet" href="styles/bootstrap.css">
  <link rel="stylesheet" href="styles/login.css">
</head>
<body>
  <!-- nav start -->
  <%@ include file="header1.jsp" %>
  <!-- nav end -->

  <!-- body start -->
  <div class="container-fluid login-body">
    <div class="row">
      <div class="col-sm-8 col-md-6 col-lg-4 login-box">
        <div class="panel panel-default">
          <div class="panel-body">
            <h3>选择问题类型</h3>
            <s:form action="QuestionAction_toDesignQuestionPage.action" method="post">
				<s:hidden name="pid" />
				<s:hidden name="sid" />
	              <select name="questionType" class="form-control" onchange="this.form.submit();">
	               		<option selected="selected">--请选择问题类型--</option>
						<option value="0">非矩阵式横向单选按钮</option>
						<option value="1">非矩阵式纵向单选按钮</option>
						<option value="2">非矩阵式横向复选按钮</option>
						<option value="3">非矩阵式纵向复选按钮</option>
						<option value="4">非矩阵式下拉列表</option>
						<option value="5">非矩阵式文本框</option>
						<option value="6">矩阵式单选按钮</option>
						<option value="7">矩阵式复选按钮</option>
						<option value="8">矩阵式下拉列表</option>
	              </select>
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