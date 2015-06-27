<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>


<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
      <div class="navbar-header">
        <a href="#" class="navbar-brand">
          <img src="images/keyboard_S.png" alt="SurveyDoor">
        </a>
        <a href="#" class="navbar-brand">Survey Door 调查系统</a>
        <button class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
      </div>
      <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
          <li><s:a action="LoginAction_toIndex" namespace="/">首页</s:a></li>
		  <li><s:a action="SurveyAction_newSurvey" namespace="/" href="javascript:void(0)" data-toggle="modal" data-target=".modal-confirm" >新建调查</s:a></li>
		  <li><s:a action="SurveyAction_mySurveys" namespace="/">我的调查</s:a></li>
		  <li><s:a action="EngageSurveyAction_findAllAvailableSurveys" namespace="/">参与调查</s:a></li>
		  <li><s:a action="UserAuthorizeAction_findAllUsers" namespace="/">用户授权管理</s:a></li>
		  <li><s:a action="RoleAction_findAllRoles" namespace="/">角色管理</s:a></li>
		  <li><s:a action="RightAction_findAllRights" namespace="/">权限管理</s:a></li>
		  <li><s:a action="LogAction_findAllLogs" namespace="/">日志管理</s:a></li>
		  <li><s:a action="RegAction_toRegPage" namespace="/">用户注册</s:a></li>
		  <li><s:a action="LoginAction_loginOut" namespace="/">登入/登出</s:a></li>
        </ul>
      </div>
    </div>
  </nav>
  
<%@include file="modalbox.jsp" %>
