<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>个性化名称/按钮 —— Survey Door 调查系统</title>

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
            <h3>个性化名称/按钮</h3>
            <s:form action="SurveyAction_updateSurvey" namespace="/" method="post">
			  <s:hidden name="id" />
              <div class="form-group">
                <label>调查标题</label>
                <input type="text" name="title" value='<s:property value="title"/>'  class="form-control" value="调查标题" placeholder="调查标题">
              </div>
              <div class="form-group">
                <label>“下一步”提示文本</label>
                <input type="text" name="nextText" value='<s:property value="nextText"/>' class="form-control" value="下一步" placeholder="下一步">
              </div>
              <div class="form-group">
                <label>“上一步”提示文本</label>
                <input type="text" name="preText" value='<s:property value="preText"/>' class="form-control" value="上一步" placeholder="上一步">
              </div>
              <div class="form-group">
                <label>“完成”提示文本</label>
                <input type="text" name="doneText" value='<s:property value="doneText"/>' class="form-control" value="完成" placeholder="完成">
              </div>
              <div class="form-group">
                <label>“退出”提示文本</label>
                <input type="text" name="exitText" value='<s:property value="exitText"/>' class="form-control" value="退出" placeholder="退出">
              </div>
              <div class="form-group">
                <label>简介</label>
                <textarea  name="info"  class="form-control" cols="30" rows="5"><s:property value="info"/></textarea>
                <span class="help-block">help text here</span>
              </div>
              <input type="submit" value="Submit" class="btn btn-success pull-right">
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