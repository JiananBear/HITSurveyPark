package com.werun.surveypark.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.sf.json.JSONArray;

import com.werun.surveypark.domain.Question;
import com.werun.surveypark.domain.statistics.QuestionStatisticsModel;
import com.werun.surveypark.service.StatisticsService;

@Controller
@Scope("prototype")
public class JsonAction extends BaseAction<Question>{
	
	private QuestionStatisticsModel qsm;
	private Integer qid;
	
	@Resource
	private StatisticsService ss;
	
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	public QuestionStatisticsModel getQsm() {
		return qsm;
	}
	public void setQsm(QuestionStatisticsModel qsm) {
		this.qsm = qsm;
	}

	public String getQsmJson()
	{
		this.qsm=ss.statistics(qid);
		this.qsm.getQuestion().setPage(null);
		return SUCCESS;
	}
	

}
