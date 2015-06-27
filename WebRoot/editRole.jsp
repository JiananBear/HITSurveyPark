<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>编辑角色 —— Survey Door 调查系统</title>

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
      <div class="col-sm-10 col-lg-8 login-box">
        <div class="panel panel-default">
          <div class="panel-body">
            <h3>编辑角色</h3>
            
            <s:form id="form1" action="RoleAction_saveOrUpdateRole" cssClass="form-horizontal" method="post">
				<s:hidden name="id" />
              <div class="form-group">
                <label class="col-sm-2 control-label">角色名称</label>
                <div class="col-sm-10">
                  <s:textfield name="roleName" cssClass="form-control" placeholder="角色名称" />
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">角色值</label>
                <div class="col-sm-10">
                  <s:textfield name="roleValue" cssClass="form-control" placeholder="角色值" />
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">角色描述</label>
                <div class="col-sm-10">
                  <s:textarea name="roleDesc" cssClass="form-control" cols="30" rows="5"/>
                  <span class="help-block">help text here</span>
                </div>
              </div>
              <div class="form-group">
                <div class="col-xs-5">
                  
                  <s:select id="left" 
						name="ownRightIds" 
						list="rights" 
						listKey="id" 
						listValue="rightName"
						multiple="true"
						cssClass="form-control">
					</s:select>
                </div>
                <div class="col-xs-2">
                  <input type="button" id="one1" class="btn btn-default btn-block" value="&gt;">
                  <input type="button" id="one2" class="btn btn-default btn-block" value="&lt;">
                  <input type="button" id="all1" class="btn btn-default btn-block" value="&gt;&gt;">
                  <input type="button" id="all2" class="btn btn-default btn-block" value="&lt;&lt;">
                </div>
                <div class="col-xs-5">
                  <s:select id="right" 
						list="noOwnRights" 
						name="noOwnRightIds"
						listKey="id" 
						listValue="rightName"
						multiple="true"
						cssClass="form-control">
					</s:select>
                </div>
              </div>
              <input type="submit" value="Submit" class="btn btn-success pull-right" onclick="return submitForm1()">
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

<script >

$(function(){
	$('#one1').click(function(){
		var size = $('#left>option:selected').size();
		if(size != 0){
			$('#left > option:selected').appendTo($('#right'));
		}
		else{
			$('#left>option:first-child').appendTo($('#right'));
		}
	});	
	$('#all1').click(function(){
		$('#left > option').appendTo($('#right'));
	});	
	$('#one2').click(function(){
		var size = $('#right>option:selected').size();
		if(size != 0){
			$('#right > option:selected').appendTo($('#left'));
		}
		else{
			$('#right>option:first-child').appendTo($('#left'));
		}
	});	
	$('#all2').click(function(){
		$('#right > option').appendTo($('#left'));
	});	
});

function submitForm1(){
	$('#left > option').attr('selected','selected');
	return true ;
}

</script>

<%@ include file="modalboxfooter.jsp" %>
</body>
</html>