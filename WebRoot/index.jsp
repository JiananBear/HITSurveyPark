<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>日志管理 —— Survey Door 调查系统</title>
  <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
  <link rel="stylesheet" href="styles/bootstrap.css">
  <link rel="stylesheet" href="styles/mainPage.css">
</head>
<body>
  <!-- nav start -->
  <%@ include file="header.jsp" %>
  <!-- nav end -->

  <!-- body start -->
  <div class="container">
    <div class="row">
      <div class="col-sm-4 col-lg-3 hidden-xs">
        <p class="personal-info">
          <a href="#" class="thumbnail">
            <img src="http://placehold.it/360x360" alt="Logo">
          </a>
        </p>
        <div class="form-horizontal">
          <div class="form-group">
            <label class="control-label col-md-4">昵称</label>
            <div class="col-md-8">
              <p class="form-control-static"><s:property value="#session['user'].nickName"/></p>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-4">性别</label>
            <div class="col-md-8">
              <p class="form-control-static">男</p>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-4">邮箱</label>
            <div class="col-md-8">
              <p class="form-control-static">暂无</p>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-4">个人简介</label>
            <div class="col-md-8">
              <p class="form-control-static">暂无</p>
            </div>
          </div>
        </div>
      </div>
      <div class="col-sm-8 col-lg-9 table-responsive">
        <div class="page-header">
          <h1>
            <span>个人主页</span>
          </h1>
        </div>
        <ul class="media-list">
        
        <s:iterator value="randomSurveys" var="s">
        <s:set var="sId" value="#s.id" />
          <li class="media">
            <div class="media-left">
              <s:a cssClass="thumbnail" action="EngageSurveyAction_entry?sid=%{#sId}"><img src='<s:property value="getImageUrl(logoPhotoPath)"/>' height="128px" width="128px" alt="title"></s:a>
            </div>
            <div class="media-body">
              <h3 class="media-heading"><s:a action="EngageSurveyAction_entry?sid=%{#sId}"><s:property value="title" /></s:a></h3>
              <h4><small><s:property value="user.nickName" /></small>&nbsp;<small><s:date name="createTime" format="yy-MM-dd HH:mm" /></small></h4>
              <s:property value="@com.werun.surveypark.util.StringUtil@getDescString(info,200)"/>
            </div>
          </li>
          <hr/>
         </s:iterator>
        </ul>
      </div>
    </div>
  </div>
  <!-- body end -->
  
 

  <!-- footer start -->
  <%@ include file="footer.jsp" %>
  <!-- footer end -->

  <script type="text/javascript" src="scripts/jquery.js"></script>
  <script type="text/javascript" src="scripts/bootstrap.js"></script>
  
  <%@ include file="modalboxfooter.jsp" %>
  
</body>
</html>