<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>设计调查 —— Survey Door 调查系统</title>
  <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
  <link rel="stylesheet" href="styles/bootstrap.css">
  <link rel="stylesheet" href="styles/mainPage.css">
</head>
<body>
  <%@ include file="header.jsp" %>
  <s:set var="sId" value="id" />
  <!-- body start -->
  <div class="container">
    <div class="row">
      <div class="col-sm-12">
        <div class="page-header">
          <h1>
            <span>设计调查</span>
            <small><s:property value="title"/></small>
            <s:if test="lookup==1">
	            
            </s:if>
            <s:else>
            	<div class="btn-group btn-group-lg pull-right">
	              <s:a action="SurveyAction_toAddLogoPage?sid=%{#sId}" cssClass="btn btn-default">设置Logo</s:a>
				  <s:a action="SurveyAction_editSurvey?sid=%{#sId}" cssClass="btn btn-default">编辑调查</s:a>
				  <s:a action="PageAction_toAddPage?sid=%{#sId}" cssClass="btn btn-primary">添加新页</s:a>
	            </div>
            </s:else>
          </h1>
        </div>

        <!-- 迭代页面集合 -->
		<s:iterator var="p" value="pageList">
		<s:set var="pId" value="#p.id" />
		 	<!-- page one start -->
	        <div class="row">
	          <div class="col-sm-10 col-md-8 col-sm-offset-1 col-md-offset-2">
	            <h2>
	              <span><s:property value="#p.title" /></span>
	              <s:if test="lookup!=1">
		              <div class="btn-group pull-right">
		                <s:a action="PageAction_deletePage?sid=%{#sId}&pid=%{#pId}" namespace="/" cssClass="btn btn-default">删除页</s:a>
		                <s:a action="PageAction_editPage?sid=%{#sId}&pid=%{#pId}" namespace="/" cssClass="btn btn-default">编辑页标题</s:a>
						<s:a action="MoveOrCopyPageAction_toSelectTargetPage?srcPid=%{#pId}&srcSid=%{sId}" namespace="/" cssClass="btn btn-default">移动/复制页</s:a>
						<s:a action="QuestionAction_toSelectQuestionType?sid=%{#sId}&pid=%{#pId}" namespace="/" cssClass="btn btn-primary">增加问题</s:a>
		              </div>
	              </s:if>
	            </h2>
	            <form action="#" class="form-survey">
	              <s:iterator var="q" value="#p.queList">
		              <s:set var="qId" value="#q.id" />
		              <!-- simple inline radio start -->
		              <div class="form-group">
		                <h3 class="control-label">
		                  <span><s:property value="#q.title" /></span>
		                  <s:if test="lookup!=1">
			                  <div class="btn-group btn-group-sm pull-right">
			                    <s:a action="QuestionAction_deleteQueston?pid=%{#pId}&qid=%{#qId}&sid=%{#sId}" cssClass="btn btn-default"> 删除问题</s:a>
			                    <s:a action="QuestionAction_editQuestion?pid=%{#pId}&qid=%{#qId}&sid=%{#sId}" cssClass="btn btn-default"> 编辑问题</s:a>
			                  </div>
		                  </s:if>
		                </h3>
							
		                  <!-- 定义变量,设置第一大类的题型 -->
						  <s:set var="qt" value="#q.questionType" />
						  <!-- 判断当前题型是否属于第一大类(0,1,2,3) -->
						  <s:if test='#qt lt 4'>
						  	<div class='control-container'>
						  	<!-- 题目 -->
						  	<s:iterator value="#q.optionArr">
						  		<s:if test="#qt == 0">
						  			<label class="radio-inline"><input type='radio'><s:property /></label>
						  		</s:if>
						  		<s:elseif test="#qt == 1">
						  			<div class="radio"><label><input type='radio'><s:property /></label></div>
						  		</s:elseif>
						  		<s:elseif test="#qt == 2">
						  			<label class="checkbox-inline"><input type="checkbox"  ><s:property /></label>
						  		</s:elseif>
						  		<s:elseif test="#qt == 3">
						  			<div class="checkbox"><label><input type="checkbox"><s:property /></label></div>
						  		</s:elseif>
						  	</s:iterator>
						  	<!-- 处理other问题 -->
							<s:if test="#q.other">
								<div class="form-inline">
		                    		<div class="form-group">
										<s:if test="#qt == 0">
					  						<label class="radio-inline"><input type='radio'>其他</label>
					  					</s:if>
					  					<s:elseif test="#qt == 1">
					  						<div class="radio"><label><input type='radio'>其他</label></div>
					  					</s:elseif>
					  					<s:elseif test="#qt == 2">
					  						<label class="checkbox-inline"><input type="checkbox"  >其他</label>
					  					</s:elseif>
					  					<s:elseif test="#qt == 3">
					  						<div class="checkbox"><label><input type="checkbox">其他</label></div>
					  					</s:elseif>
										<!-- 文本框 -->
										<s:if test="#q.otherStyle == 1">
											<input type="text"  class="form-control">
										</s:if>
										<!--  下拉列表 -->
										<s:elseif test="#q.otherStyle == 2">
											<select class="form-control">
												<s:iterator value="#q.otherSelectOptionArr" >
													<option><s:property /></option>
												</s:iterator>
											</select>
										</s:elseif>
									</div>
		                  		</div>
							</s:if>
							</div>
						</s:if>
							
							
						<!-- 下拉列表 -->
						<s:if test="#qt == 4">
							<div class="control-container">
								<select  class="form-control">
				                    <s:iterator value="#q.optionArr" >
										<option><s:property /></option>
									</s:iterator>
				                </select>
				            </div>
						</s:if>
						
						<s:if test="#qt == 5">
							<div class="control-container">
								<textarea cols="30" rows="5" class="form-control"></textarea>
							</div>
						</s:if>
						
						<!-- 矩阵问题(6,7,8) -->
						<s:if test="#qt > 5">
							<div class="control-container table-responsive">
							<table class="table">
								<!-- 列头 -->
								<thead>
		                      		<th></th>
		                      		<s:iterator value="#q.matrixColTitleArr">
										<td><s:property /></td>
							 		</s:iterator>
		                    	</thead>
								
								<tbody>
									<!-- 输出n多行 -->
									<s:iterator value="#q.matrixRowTitleArr">
										<tr>
											<td><s:property /></td>
											<!-- 套打控件 -->
											<s:iterator value="#q.matrixColTitleArr">
												<td>
													<!-- radio -->
													<s:if test="#qt == 6"><label class="radio-inline"><input type="radio" ></label></s:if>
													<s:if test="#qt == 7"><label class="checkbox-inline"><input type="checkbox"></label></s:if>
													<s:if test="#qt == 8">
														<select class="form-control">
															<s:iterator value="#q.matrixSelectOptionArr">
																<option><s:property /></option>
															</s:iterator>
														</select>
													</s:if>
												</td>
											</s:iterator>
										</tr>
									</s:iterator>
								</tbody>
							</table>
							
						</s:if>
						<!-- 矩阵问题(6,7,8)结束 -->	
						  	
		                
		              </div>
		              <!-- 问题结束 -->	
		              <hr />
	              </s:iterator>

	            </form>
	          </div>
	        </div>
	        <!-- page one end -->
		</s:iterator>
		 <!-- 迭代页面集合结束 -->
  <s:if test="lookup==1">
	      <a href="javascript:history.go(-1);" style="width:10em"  class="btn btn-default pull-right">返回</a>       
  </s:if>
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
