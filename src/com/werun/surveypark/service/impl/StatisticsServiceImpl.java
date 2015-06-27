package com.werun.surveypark.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.werun.surveypark.dao.BaseDao;
import com.werun.surveypark.domain.Answer;
import com.werun.surveypark.domain.Question;
import com.werun.surveypark.domain.statistics.OptionStatisticsModel;
import com.werun.surveypark.domain.statistics.QuestionStatisticsModel;
import com.werun.surveypark.service.StatisticsService;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService{
	
	@Resource(name="questionDao")
	private BaseDao<Question> questionDao;
	@Resource(name="answerDao")
	private BaseDao<Answer> answerDao;
	
	@Override
	public QuestionStatisticsModel statistics(Integer qid) {
		QuestionStatisticsModel qsm=new QuestionStatisticsModel();
		Question q=questionDao.getEntity(qid);
		qsm.setQuestion(q);
		
		
		String hql="select count(*) from Answer a where a.questionId=?";
		int qcount = ((Long)answerDao.uniqueResult(hql,qid)).intValue();
		qsm.setCount(qcount);
		
		int qt =q.getQuestionType();
		String ohql = "select count(*) from Answer a where a.questionId = ? and concat(',',a.answerIds,',') like ?" ;
		switch (qt) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				String[] optArr = q.getOptionArr();
				OptionStatisticsModel osm=null;
				for(int i =0;i<optArr.length;i++)
				{
					osm=new OptionStatisticsModel();
					osm.setOptionLabel(optArr[i]);
					osm.setOptionIndex(i+"");
					int ocount = ((Long)answerDao.uniqueResult(ohql,qid,"%,"+i+",%")).intValue();
					osm.setCount(ocount);
					qsm.getOsms().add(osm);
				}
				break;
			case 6:
			case 7:
			case 8:
				String[] rows = q.getMatrixRowTitleArr();
				String[] cols = q.getMatrixColTitleArr();
				String[] opts = q.getMatrixSelectOptionArr();
				
				for(int i=0;i<rows.length;i++)
				{
					for(int j=0;j<cols.length;j++)
					{
						if(qt!=8)
						{
							osm=new OptionStatisticsModel();
							osm.setMatrixColLabel(cols[j]);
							osm.setMatrixColIndex(j);
							osm.setMatrixRowLabel(rows[i]);
							osm.setMatrixRowIndex(i);
							int ocount = ((Long)answerDao.uniqueResult(ohql, qid,"%," + i + "_" + j + ",%")).intValue();
							osm.setCount(ocount);
							qsm.getOsms().add(osm);
						}
						else 
						{
							for(int k=0;k<opts.length;k++)
							{
								osm=new OptionStatisticsModel();
								osm.setMatrixColLabel(cols[j]);
								osm.setMatrixColIndex(j);
								osm.setMatrixRowLabel(rows[i]);
								osm.setMatrixRowIndex(i);
								osm.setMatrixSelectLabel(opts[k]);
								osm.setMatrixSelectIndex(k);
								int ocount = ((Long)answerDao.uniqueResult(ohql, qid,"%," + i + "_" + j + "_" + k + ",%")).intValue();
								osm.setCount(ocount);
								qsm.getOsms().add(osm);
							}
						}
					}
				}
				break;

		
			}
		
		return qsm;
	}

}
