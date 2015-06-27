<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>调查列表 —— Survey Door 调查系统</title>

  <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">

  <link rel="stylesheet" href="styles/bootstrap.css">
  <link rel="stylesheet" href="styles/mainPage.css">
  <style type="text/css">
.media>.media-left>.thumbnail>img {
	width: 128px;
	height: 128px;
}
  </style>
</head>
<body>
  <%@ include file="header.jsp" %>
  
  <!-- body start -->
  <div class="container">
    <div class="row">
      <div class="col-sm-12">
        <div class="page-header">
          <h1>调查列表</h1>
        </div>
        <ul class="media-list">
        
	        <s:iterator var="s" value="surveys">
		        <s:set var="sId" value="#s.id" />
		          <li class="media">
		            <div class="media-left">
		              <s:a cssClass="thumbnail" action="EngageSurveyAction_entry?sid=%{#sId}"><img src='<s:property value="getImageUrl(logoPhotoPath)"/>' height="128px" width="128px" alt="title"></s:a>
		            </div>
		            <div class="media-body">
		              <h3 class="media-heading"><s:a action="EngageSurveyAction_entry?sid=%{#sId}"><s:property value="title" /></s:a></h3>
		              <h4><small><s:property value="user.nickName" /></small>&nbsp;<small><s:date name="createTime" format="yy-MM-dd HH:mm" /></small></h4>
		              <s:property value="@com.werun.surveypark.util.StringUtil@getDescString(info,300)"/>
		            </div>
		          </li>
	          </s:iterator>
	          <hr/>
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