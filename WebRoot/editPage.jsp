<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>编辑页面内容 —— Survey Door 调查系统</title>

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
            <h3>编辑页面内容</h3>
            <s:form action="PageAction_saveOrUpdatePage" namespace="/" method="post">
            <s:hidden name="id" />
			<s:hidden name="sid" />
			<s:hidden name="orderno" />
              <div class="form-group">
                <label>页面标题</label>
                <input type="text" name="title" class="form-control" value='<s:property value="title"/>' placeholder="页面标题">
              </div>
              <div class="form-group">
                <label>页面描述</label>
                <textarea  name="description"  cols="30" rows="5" class="form-control" placeholder="页面描述"> <s:property value="description"/></textarea>
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