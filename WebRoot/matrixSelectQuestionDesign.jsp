<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>矩阵型下拉问题设计 —— Survey Door 调查系统</title>

  <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">

  <link rel="stylesheet" href="styles/bootstrap.css">
  <link rel="stylesheet" href="styles/login.css">
>
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
            <h3>矩阵型下拉问题设计</h3>
            <s:form action="QuestionAction_saveOrUpdateQuestion.action" method="post">
				<s:hidden name="id" />
				<s:hidden name="questionType" />
				<s:hidden name="pid" />
				<s:hidden name="sid" />
				<s:hidden name="orderno"/>
              <div class="form-group">
                <label>问题标题</label>
                <input type="text" name="title" value='<s:property value="title"/>' class="form-control" placeholder="问题标题">
              </div>
              <div class="form-group">
                <label>行标题标签组</label>
                <textarea name="matrixRowTitles" cols="30" rows="5" class="form-control" placeholder="行标题标签组"><s:property value="matrixRowTitles"/></textarea>
                <span class="help-block">help text here</span>
              </div>
              <div class="form-group">
                <label>列标题标签组</label>
                <textarea name="matrixColTitles" cols="30" rows="5" class="form-control" placeholder="列标题标签组"><s:property value="matrixColTitles"/></textarea>
                <span class="help-block">help text here</span>
              </div>
              <div class="form-group">
                <label>下拉列表选项集合</label>
                <textarea name="matrixSelectOptions" cols="30" rows="5" class="form-control" placeholder="下拉列表选项集合"><s:property value="matrixSelectOptions"/></textarea>
                <span class="help-block">help text here</span>
              </div>
              <input type="submit" name="ok" value="Submit" class="btn btn-success pull-right">
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