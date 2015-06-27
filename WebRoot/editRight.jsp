<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>编辑权限 —— Survey Door 调查系统</title>

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
            <h3>编辑权限</h3>
            
            <s:form action="RightAction_saveOrUpdateRight" method="post">
			<s:hidden name="id" />
              <div class="form-group">
                <label>权限名称</label> 
                <s:textfield name="rightName" cssClass="form-control" placeholder="权限名称" />
              </div>
              <div class="form-group">
                <label>权限URL</label>
                <s:textfield name="rightUrl" cssClass="form-control" placeholder="权限URL"/>
              </div>
              <div class="form-group">
                <label>权限位</label>
                <s:textfield name="rightPos" cssClass="form-control" readonly="true" />
              </div>
              <div class="form-group">
                <label>权限码</label>
                <s:textfield name="rightCode" cssClass="form-control" readonly="true" />
              </div>
              <div class="form-group">
                <label class="checkbox-inline">
                 	<s:checkbox  name="common" />公共资源
                </label>
              </div>
              <div class="form-group">
                <label>权限描述</label>
                <s:textarea name="rightDesc" cssClass="form-control" cols="30" rows="5" placeholder="权限描述"/>
                <span class="help-block">help text here</span>
              </div>
              <s:submit value="Submit" cssClass="btn btn-success pull-right" />
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
