package com.werun.surveypark.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.werun.surveypark.dao.BaseDao;
import com.werun.surveypark.domain.Answer;
import com.werun.surveypark.domain.Page;
import com.werun.surveypark.domain.Question;
import com.werun.surveypark.domain.Survey;
import com.werun.surveypark.domain.User;
import com.werun.surveypark.service.SurveyService;
import com.werun.surveypark.util.DataUtil;
import com.werun.surveypark.util.ParserSurveyTemplate;
import com.werun.surveypark.util.ValidateUtil;

@Service("surveyService")
public class SurveyServiceImpl implements SurveyService{
	
	@Resource(name="surveyDao")
	private BaseDao<Survey> surveyDao;
	
	@Resource(name="pageDao")
	private BaseDao<Page> pageDao;

	@Resource(name="questionDao")
	private BaseDao<Question> quesDao;
	
	@Resource(name="answerDao")
	private BaseDao<Answer> answerDao;

	@Override
	public Survey newSurvey(User u) {
		Survey sur=new Survey();
		
		Page p=new Page();
		p.setSurvey(sur);
		sur.setUser(u);
		sur.getPages().add(p);
		sur.setClosed(true);
		sur.setChecked(Survey.CHECKING);
		surveyDao.saveEntity(sur);
		pageDao.saveEntity(p);
		return sur;
	}

	@Override
	public List<Survey> findMySurveys(User u) {
		String hql="from Survey s where s.user.id=?";
		return surveyDao.findEntityByHQL(hql, u.getId());
	}
	@Override
	public List<Survey> findRandomNSurveys(int n)
	{
		List<Survey> re=new ArrayList<Survey>();
		List<Survey> list=findAllAvailableSurveys();
		Collections.shuffle(list);
		for(int i=0;i<n;i++)
		{
			re.add(list.get(i));
		}
		return  re;
	}

	@Override
	public Survey getSurveyWithChildren(Integer sid) {
		Survey s=surveyDao.getEntity(sid);
		Set<Page> pages=s.getPages();
		for (Page p : pages) {
			p.getQuestions().size();
		}
		return s;
	}

	@Override
	public Survey getSurvey(Integer sid) {
		return surveyDao.getEntity(sid);
	}

	@Override
	public void updateSurvey(Survey model) {
		surveyDao.updateEntity(model);
	}

	@Override
	public void saveOrUpdatePage(Page temp) {
		pageDao.saveOrUpdateEntity(temp);
	}

	@Override
	public Page getPage(Integer pid) {
		
		return pageDao.getEntity(pid);
	}
	

	@Override
	public void saveOrUpdateQuestion(Question model) {
		quesDao.saveOrUpdateEntity(model);
	}

	@Override
	public Question getQuestion(Integer qid) {
		return quesDao.getEntity(qid);
	}

	@Override
	public void deleteQueston(Integer qid) {
		String hql="delete from Answer a where a.questionId = ?";
		answerDao.batchEntityByHQL(hql, qid);
		hql="delete from Question q where q.id = ?";
		quesDao.batchEntityByHQL(hql, qid);
	}

	@Override
	public void deletePage(Integer pid) {
		String hql="delete from Answer a where a.questionId in (select q.id from Question q where q.page.id=?)";
		answerDao.batchEntityByHQL(hql, pid);
		hql="delete from Question q where q.page.id=?";
		quesDao.batchEntityByHQL(hql, pid);
		hql = "delete from Page p where p.id = ?" ;
		pageDao.batchEntityByHQL(hql,pid);
	}

	@Override
	public void deleteSurvey(Integer sid) {
		String hql="delete from Answer a where a.surveyId=?";
		answerDao.batchEntityByHQL(hql, sid);
		hql="delete from Question q where q.page.id in (select p.id from Page p where p.survey.id = ?)";
		quesDao.batchEntityByHQL(hql,sid);
		hql="delete from Page p where p.survey.id=?";
		pageDao.batchEntityByHQL(hql,sid);
		hql = "delete from Survey s where s.id = ?" ;
		surveyDao.batchEntityByHQL(hql,sid);
	}

	@Override
	public void clearAnswers(Integer sid) {
		String hql="delete from Answer a where a.surveyId=?";
		answerDao.batchEntityByHQL(hql, sid);
	}

	@Override
	public void toggleStatus(Integer sid) {
		Survey s=surveyDao.getEntity(sid);
		String hql="update Survey s set s.closed = ? where s.id = ?";
		surveyDao.batchEntityByHQL(hql,!s.isClosed(),sid);
	}

	@Override
	public void updateLogoPhotoPath(Integer sid, String path) {
		String hql="update Survey s set s.logoPhotoPath = ? where s.id = ?";
		surveyDao.batchEntityByHQL(hql,path,sid);
	}

	@Override
	public List<Survey> findSurveysWithPage(User user) {
		String hql="from Survey s where s.user.id = ?";
		List<Survey> surveys= surveyDao.findEntityByHQL(hql, user.getId());
		for (Survey s : surveys) {
			s.getPages().size();
		}
		return surveys;
	}

	@Override
	public void moveOrCopyPage(Integer srcPid, Integer targPid, int pos) {
		Page srcPage=this.getPage(srcPid);
		Survey srcSurvey=srcPage.getSurvey();
		Page targPage=this.getPage(targPid);
		Survey targSurvey=targPage.getSurvey();
		
		if(srcSurvey.getId().equals(targSurvey.getId()))
		{
			setOrderno(srcPage,targPage,pos);
		}
		else 
		{
			//对源页面进行深度复制
			srcPage.getQuestions().size();
			Page copy = (Page) DataUtil.deeplyCopy(srcPage) ; //对srcPage进行深度复制
			//设置新的关联关系
			copy.setSurvey(targSurvey);
			
			//分别保存新的页面和问题
			pageDao.saveEntity(copy);
			for(Question q : copy.getQuestions()){
				quesDao.saveEntity(q);
			}
			setOrderno(copy,targPage,pos);
		}
		
	}

	private void setOrderno(Page srcPage, Page targPage, int pos) {
		if(pos==0)
		{
			if(isFirstPage(targPage))
			{
				srcPage.setOrderno(targPage.getOrderno()-0.01f);
			}
			else 
			{
				Page prePage=getPrePage(targPage);
				srcPage.setOrderno((prePage.getOrderno()+targPage.getOrderno())/2);
			}
		}
		else 
		{
			if(isLastPage(targPage))
			{
				srcPage.setOrderno(targPage.getOrderno()+0.01f);
			}
			else
			{
				Page nextPage=getNextPage(targPage);
				srcPage.setOrderno((nextPage.getOrderno()+targPage.getOrderno())/2);
			}
		}
		
	}

	private Page getNextPage(Page targPage) {
		String hql="from Page p where p.orderno > ? and p.survey.id = ? order by p.orderno asc";
		List<Page> list = pageDao.findEntityByHQL(hql, targPage.getOrderno(),targPage.getSurvey().getId());
		return list.get(0);
	}

	private boolean isLastPage(Page targPage) {
		String hql="from Page p where p.orderno > ? and p.survey.id = ?";
		List<Page> list = pageDao.findEntityByHQL(hql, targPage.getOrderno(),targPage.getSurvey().getId());
		return !ValidateUtil.isValid(list);
	}

	private Page getPrePage(Page targPage) {
		String hql="from Page p where p.orderno < ? and p.survey.id = ? order by p.orderno desc";
		List<Page> list = pageDao.findEntityByHQL(hql, targPage.getOrderno(),targPage.getSurvey().getId());
		return list.get(0);
	}

	private boolean isFirstPage(Page targPage) {
		String hql="from Page p where p.orderno < ? and p.survey.id = ?";
		List<Page> list = pageDao.findEntityByHQL(hql, targPage.getOrderno(),targPage.getSurvey().getId());
		return !ValidateUtil.isValid(list);
	}

	@Override
	public List<Survey> findAllAvailableSurveys() {
		String hql = "from Survey s where s.closed = ?" ;
		List<Survey> s= surveyDao.findEntityByHQL(hql,false);
		for (Survey survey : s) {
			if (survey.getUser()!=null)
				survey.getUser().toString();
		}
		return s;
	}

	@Override
	public Page getFirstPage(Integer sid) {
		String hql = "from Page p where p.survey.id = ? order by p.orderno asc" ;
		Page p = pageDao.findEntityByHQL(hql,sid).get(0);
		p.getSurvey().getTitle();
		p.getQuestions().size();
		return p;
	}

	/**
	 * 查询指定页面的上一页
	 */
	public Page getPrePage(Integer currPid){
		Page p = this.getPage(currPid);
		p =  this.getPrePage(p);
		p.getQuestions().size();
		return p ;
	}
	
	/**
	 * 查询指定页面的下一页
	 */
	public Page getNextPage(Integer currPid){
		Page p = this.getPage(currPid);
		p =  this.getNextPage(p);
		p.getQuestions().size();
		return p ;
	}

	@Override
	public void saveAnswers(List<Answer> processAnswers) {
		String uuid = UUID.randomUUID().toString();
		Date date = new Date();
		for(Answer a : processAnswers){
			System.out.println(a.getAnswerIds());
			a.setUuid(uuid);
			a.setAnswerTime(date);
			answerDao.saveEntity(a);
		}
	}

	@Override
	public List<Question> getQuestions(Integer sid) {
		String hql = "from Question q where q.page.survey.id = ?" ;
		return quesDao.findEntityByHQL(hql,sid);
	}

	@Override
	public List<Answer> findAnswers(Integer sid) {
		String hql="from Answer a where a.surveyId = ? order by a.uuid";
		return answerDao.findEntityByHQL(hql,sid);
	}
	
	
	public Survey saveSurveyTemplate(User u,String path,Integer tmpIndex)
	{
		Survey s=ParserSurveyTemplate.parserXml2SurveyTemplate(path, tmpIndex);
		s.setUser(u);
		s.setClosed(true);
		s.setChecked(Survey.CHECKING);
		for(Page p: s.getPages())
		{
			for(Question q : p.getQuestions())
			{
				quesDao.saveEntity(q);
			}
			pageDao.saveEntity(p);
		}
		surveyDao.saveEntity(s);
		return getSurveyWithChildren(s.getId());
	}
	
	public Survey getBlockSurvey(User u)
	{
		Survey sur=new Survey();
		Page p=new Page();
		p.setSurvey(sur);
		sur.setUser(u);
		sur.getPages().add(p);
		return sur;
	}
	
	public Survey getSurveyTemplate(User u,String path,Integer tmpIndex)
	{
		Survey s=ParserSurveyTemplate.parserXml2SurveyTemplate(path, tmpIndex);
		s.setUser(u);
		return s;
	}
	
	public void saveSurvey(Survey s)
	{
		surveyDao.saveEntity(s);
		for (Page p : s.getPages()) {
			pageDao.saveEntity(p);
			
			for(Question q : p.getQuestions())
			{
				quesDao.saveEntity(q);
			}
		}
	}

	@Override
	public List<Survey> getALLCheckingSurveys() {
		
		List<Survey> list= surveyDao.findEntityByHQL("from Survey s where s.checked=?",Survey.CHECKING);
		for (Survey survey : list) {
			survey.getUser().getNickName();
		}
		return list;
	}

	@Override
	public void checkSurvey(Integer sid, Integer check) {
		Survey s=surveyDao.getEntity(sid);
		String hql="update Survey s set s.checked = ? where s.id = ?";
		surveyDao.batchEntityByHQL(hql,check,s.getId());
	}
	

}
