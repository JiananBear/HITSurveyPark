<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>非矩阵型选择问题设计 —— Survey Door 调查系统</title>

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
            <h3>非矩阵型选择问题设计</h3>
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
                <label>问题选项</label>
                <textarea  name="options" cols="30" rows="5" class="form-control" placeholder="问题选项"><s:property value="options"/></textarea>
                <span class="help-block">help text here</span>
              </div>
              <div class="form-group">
                  <label class="checkbox-inline">
                    <s:checkbox id="collapseSelected" name="other" data-toggle="collapse" data-target="#collapseOthers" />是否含有“其它”选项
                  </label>
              </div>
              <div id="collapseOthers" class="collapse">
                <div class="form-group">
                  <h3 class="control-label">“其它”项类型</h3>
                  <div class="control-container">
                    <label class="radio-inline"><input type="radio" name="otherStyle" value="0">无</label>
                    <label class="radio-inline"><input type="radio" name="otherStyle" value="1">文本框</label>
                    <label class="radio-inline"><input type="radio" name="otherStyle" value="2">下拉列表框</label>
                  </div>
                </div>
                <div class="form-group">
                  <label>“其它”项下拉列表选项</label>
                  <textarea name="otherSelectOptions" cols="30" rows="5" class="form-control" placeholder="下拉列表选项"><s:property value="otherSelectOptions"/></textarea>
                  <span class="help-block">help text here</span>
                </div>
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
  $('#collapseSelected:checked').length ? $('#collapseOthers').collapse(): void(0);
});
</script>
<%@ include file="modalboxfooter.jsp" %>
</body>
</html>