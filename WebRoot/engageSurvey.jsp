<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>参与调查 —— Survey Door 调查系统</title>

  <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">

  <link rel="stylesheet" href="styles/bootstrap.css">
  <link rel="stylesheet" href="styles/mainPage.css">
</head>
<body>
  <%@ include file="header.jsp" %>
  
  <!-- body start -->
  <div class="container">
    <div class="row">
      <div class="col-sm-12">
        <div class="page-header">
          <h1>参与调查</h1>
        </div>
        <div class="row">
          <div class="col-sm-10 col-md-8 col-sm-offset-1 col-md-offset-2">
            <h2><s:property value="currPage.title"/></h2>
            <s:form action="EngageSurveyAction_doEngageSurvey" method="post" cssClass="form-survey" >
				<s:hidden name="currPid" value="%{currPage.id}" />
			
				<!-- 遍历问题集合 -->
				<s:iterator var="q" value="currPage.questions" status="qst">
					<!-- 设置变量,对问题的id进行保持 -->
					<s:set var="qId" value="#q.id" />
					<!-- 设置变量,保持问题的题型 -->
					<s:set var="qt" value="#q.questionType" />
					
					<div class="form-group">
	                	<h3 class="control-label"><s:property value="#qst.count+'.' + #q.title" /></h3>
			            
			            
			            <!-- 判断当前题型是否属于前四种 -->
						<s:if test='#qt lt 4'>
							<div class="control-container">
				            <!-- 输出普通选项 -->
				             <s:iterator var="option" value="#q.optionArr" status="st">
								<s:if test="#qt==0">
						                 
				                  	<label class="radio-inline"><input type="radio" name='q<s:property value="#qId" />' 
				                  			value='<s:property value="#st.index" />'
				                  			<s:property value="setTag('q' + #qId,#st.index,'checked')" />>
				                  			<s:property />
				                  			</label>	
											
					            </s:if>
					            
					            <s:elseif test="#qt == 1">
						  			<div class="radio"><label><input type="radio" name='q<s:property value="#qId" />' 
				                  			value='<s:property value="#st.index" />'
				                  			<s:property value="setTag('q' + #qId,#st.index,'checked')" />>
				                  			<s:property /></label></div>
						  		</s:elseif>
						  		<s:elseif test="#qt == 2">
						  			<label class="checkbox-inline"><input type="checkbox"  name='q<s:property value="#qId" />' 
					                  			value='<s:property value="#st.index" />'
					                  			<s:property value="setTag('q' + #qId,#st.index,'checked')" />>
					                  			<s:property />
					                  			</label>
						  		</s:elseif>
						  		<s:elseif test="#qt == 3">
						  			<div class="checkbox"><label><input type="checkbox" name='q<s:property value="#qId" />' 
					                  			value='<s:property value="#st.index" />'
					                  			<s:property value="setTag('q' + #qId,#st.index,'checked')" />><s:property /></label></div>
						  		</s:elseif>
							  </s:iterator>
							  <!-- 输出普通选项结束 -->
							  
							  <!-- 输出其他选项 -->
							  <!-- 处理'其它'项内容,判断是否含有其他选项 -->
								<s:if test="#q.other">
									<div class="form-inline">
			                    		<div class="form-group">
											<s:if test="#qt == 0">
						  						<label class="radio-inline"><input type='radio' 
						  								name='q<s:property value="#qId"/>' 
														value="other"
														<s:property value="setTag('q' + #qId,'other','checked')" />>
														其他</label>
						  					</s:if>
						  					<s:elseif test="#qt == 1">
						  						<div class="radio"><label><input type='radio'
						  								name='q<s:property value="#qId"/>' 
														value="other"
														<s:property value="setTag('q' + #qId,'other','checked')" />>其他</label></div>
						  					</s:elseif>
						  					<s:elseif test="#qt == 2">
						  						<label class="checkbox-inline"><input type="checkbox" name='q<s:property value="#qId"/>' 
														value="other"
														<s:property value="setTag('q' + #qId,'other','checked')" />>
														其他</label>
						  					</s:elseif>
						  					<s:elseif test="#qt == 3">
						  						<div class="checkbox"><label><input type="checkbox" name='q<s:property value="#qId"/>' 
														value="other"
														<s:property value="setTag('q' + #qId,'other','checked')" />>
														其他</label></div>
						  					</s:elseif>
											<!-- 文本框 -->
											<s:if test="#q.otherStyle == 1">
												<input type="text"  class="form-control" name='q<s:property value="#qId"/>other'
															<s:property value="setText('q' + #qId + 'other')" />
															>
											</s:if>
											<!--  下拉列表 -->
											<s:elseif test="#q.otherStyle == 2">
												<select class="form-control" name='q<s:property value="#qId"/>other'>
														<s:iterator var="option" value="#q.otherSelectOptionArr" status="optst">
															<option value='<s:property value="#optst.index" />'
																	<s:property value="setTag('q' + #qId+'other',#optst.index,'selected')" />
																	><s:property /></option>
														</s:iterator>
													</select>
											</s:elseif>
										</div>
		                  			</div>
								</s:if>
									  <!-- 输出其他选项结束 -->
								</div>
							</s:if>
							<!-- 判断当前题型是否属于前四种 结束-->
							
							
							<!-- 非矩阵是下拉列表 -->
							<s:if test="#qt == 4">
								<div class="control-container">
									<select name='q<s:property value="#qId" />' class="form-control">
										<s:iterator var="option" value="#q.optionArr" status="optst">
											<option value='<s:property value="#optst.index" />'
													<s:property value="setTag('q' + #qId,#optst.index,'selected')" />
													><s:property /></option>
										</s:iterator>
									</select>
								</div>
							</s:if>
							
							<!-- 非矩阵式文本框 -->
							<s:if test="#qt == 5">
							<div class="control-container">
								<textarea cols="30" rows="5" class="form-control" 
									name='q<s:property value="#qId" />'>
									<s:property value="setText('q' + #qId)" /> 
								</textarea>
							</div>
							</s:if>
							
						<!-- 矩阵问题(6,7,8) -->
						<s:if test="#qt > 5">
							<div class="control-container">
							<table class="table">
								<!-- 列头 -->
								<thead>
		                      		<th></th>
		                      		<!-- 循环输出列表标题数组 -->
									<s:iterator var="col" value="#q.matrixColTitleArr">
										<td><s:property value="#col" /></td>
									</s:iterator>
		                    	</thead>
								
								<tbody>
									<!-- 输出n多行 -->
									<s:iterator var="row" value="#q.matrixRowTitleArr" status="rowst">
										<tr>
											<td><s:property value="#row" /></td>
											<!-- 套打控件 -->
											<s:iterator var="col" value="#q.matrixColTitleArr" status="colst">
												<td>
													<!-- radio -->
													<s:if test="#qt == 6">
														<label class="radio-inline">
															<input type="radio" 
															name='q<s:property value="#qId+'_' + #rowst.index" />' 
															value='<s:property value="#rowst.index + '_' + #colst.index"/>'
															<s:property value="setTag('q' + #qId+'_' + #rowst.index,#rowst.index + '_' + #colst.index,'checked')" />>
														</label>
													</s:if>
													<s:if test="#qt == 7">
														<label class="checkbox-inline">
															<input type="checkbox" name='q<s:property value="#qId" />' 
																		value='<s:property value="#rowst.index+'_' +#colst.index"/>'
																		<s:property value="setTag('q' + #qId,#rowst.index + '_' + #colst.index,'checked')" />>
														</label>
													</s:if>
													<s:if test="#qt == 8">
														<select name='q<s:property value="#qId"/>' class="form-control">
															<s:iterator var="option" value="#q.matrixSelectOptionArr" status="optst">
																<option value='<s:property value="#rowst.index+'_'+#colst.index+'_'+#optst.index"/>'
																		<s:property value="setTag('q' + #qId,#rowst.index + '_' + #colst.index+'_' +#optst.index,'selected')" />
																		><s:property value="#option"/></option>
															</s:iterator>
														</select>
													</s:if>
												</td>
											</s:iterator>
										</tr>
									</s:iterator>
									
								</tbody>
							</table>
							</div>
						</s:if>
						<!-- 矩阵问题(6,7,8)结束 -->	
						   
				   		
				   	</div>
				   	<hr />
				   </s:iterator>
		         <!-- 遍历问题集合结束 -->   
		            
              
             <!-- 构造上一步按钮 -->
             <div class="btn-group">
				<s:if test="currPage.orderno != #session.current_survey.minOrderno">
					<input type="submit" name='submit_pre' value='&larr;<s:property value="#session.current_survey.preText" />' class="btn btn-default">
				</s:if>
				<!-- 构造下一步按钮 -->
				<s:if test="currPage.orderno != #session.current_survey.maxOrderno">
					<input type="submit" name='submit_next' value='<s:property value="#session.current_survey.nextText"/>&rarr;' class="btn btn-default">
				</s:if>
				
			</div>
			<!-- 构造完成按钮 -->
			<s:if test="currPage.orderno == #session.current_survey.maxOrderno">
				<button type="submit" name="submit_done" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>&nbsp;完成</button>
				<!--<div class="btn btn-primary pull-right"><input type="submit" name="submit_done" value='<s:property value="#session.current_survey.doneText"/>' class="btn btn-default"></div>-->
			</s:if>
			<button type="submit" name="submit_exit" class="btn btn-danger pull-right"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;退出</button>
			<!-- <div class="btn btn-primary pull-right"><input type="submit" name="submit_exit" value='<s:property value="#session.current_survey.exitText"/>' class="btn btn-default"> </div> -->
              
              
            </s:form>
          </div>
        </div>
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