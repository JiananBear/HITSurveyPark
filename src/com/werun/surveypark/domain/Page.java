package com.werun.surveypark.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Page
 */
public class Page extends BaseEntry{
	
	private static final long serialVersionUID = 2561785748036553603L;
	private Integer id;
	private String title = "未命名";
	private String description;
	
	//调查
	private transient Survey survey ;
	
	//问题集合
	private Set<Question> questions = new HashSet<Question>();
	
	private List<Question> queList=null;
	
	private float orderno;
	
	public List<Question> getQueList() {
		if (questions!=null)
		{
			queList=new LinkedList<Question>(questions);
			Collections.sort(queList, new Comparator<Question>() {
	
				@Override
				public int compare(Question o1, Question o2) {
					// TODO Auto-generated method stub
					if (o1.getOrderno()!=null&&o2.getOrderno()!=null)
						return (int) (o1.getOrderno()-o2.getOrderno());
					else
						return 0;
				}
			});
		}
		return queList;
	}

	
	public float getOrderno() {
		return orderno;
	}

	public void setOrderno(float orderno) {
		this.orderno = orderno;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Integer getId() {
		
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		if(id!=null)
		{
			if(orderno==0)
				this.orderno=id;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
