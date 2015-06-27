package com.werun.surveypark.struts2.action;

import java.text.DecimalFormat;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.werun.surveypark.domain.Question;
import com.werun.surveypark.domain.statistics.OptionStatisticsModel;
import com.werun.surveypark.domain.statistics.QuestionStatisticsModel;
import com.werun.surveypark.service.StatisticsService;


@Controller
@Scope("prototype")
public class MatrixStatisticsAction extends BaseAction<Question>{


	private static final long serialVersionUID = -4731907693006321046L;
	
	/* 平面饼图 */
	private static final int CHARTTYPE_PIE_2D = 0;
	/* 立体饼图 */
	private static final int CHARTTYPE_PIE_3D = 1;
	/* 水平平面柱状图 */
	private static final int CHARTTYPE_BAR_2D_H = 2;
	/* 竖直平面柱状图 */
	private static final int CHARTTYPE_BAR_2D_V = 3;
	/* 水平立体柱状图 */
	private static final int CHARTTYPE_BAR_3D_H = 4;
	/* 竖直立体柱状图 */
	private static final int CHARTTYPE_BAR_3D_V = 5;
	/* 平面折线图 */
	private static final int CHARTTYPE_LINE_2D = 6;
	/* 立体折线图 */
	private static final int CHARTTYPE_LINE_3D = 7;
	
	private Integer chartType;
	
	private Integer qid;
	
	private QuestionStatisticsModel qsm;
	private String qsmjson; 
	
	public Integer getChartType() {
		return chartType;
	}

	public void setChartType(Integer chartType) {
		this.chartType = chartType;
	}

	public String getQsmjson() {
		return qsmjson;
	}

	public void setQsmjson(String qsmjson) {
		this.qsmjson = qsmjson;
	}

	@Resource
	private StatisticsService ss;
	
	
	private String[] colors={
			"#ff0000",
			"#00ff00",
			"#0000ff",
			"#ffff00",
			"#ff00ff",
			"#000fff",
		};
	
	

	public String[] getColors() {
		return colors;
	}

	public void setColors(String[] colors) {
		this.colors = colors;
	}

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
	
	@Override
	public String execute() throws Exception {
		this.qsm=ss.statistics(qid);
		this.qsm.getQuestion().setPage(null);
		this.qsmjson=JSONArray.fromObject(qsm).toString();
		return ""+qsm.getQuestion().getQuestionType();
	}
	
	public String getScale(int rindex , int cindex)
	{
		int qcount=0;
		int ocount=0;
		
		for(OptionStatisticsModel osm:  qsm.getOsms())
		{
			if(osm.getMatrixRowIndex()==rindex)
			{
				qcount+=osm.getCount();
				if(osm.getMatrixColIndex()==cindex&&osm.getMatrixRowIndex()==rindex)
				{
					ocount=osm.getCount();
				}
			}
		}
		
		float scale = 0 ;
		if(qcount != 0){
			scale = (float)ocount / (float)qcount ;
		}
		scale = scale * 100 ;
		
		DecimalFormat df=new DecimalFormat();
		df.applyPattern("#,###.00");
		return "" + ocount + "(" + df.format(scale) + ")";
		
	}
	
	public int getPercent(int rindex , int cindex,int oindex)
	{
		int qcount=qsm.getCount();
		int ocount=0;
		for(OptionStatisticsModel osm:  qsm.getOsms())
		{
			if(osm.getMatrixColIndex()==cindex&&osm.getMatrixRowIndex()==rindex&&osm.getMatrixSelectIndex()==oindex)
			{
				ocount=osm.getCount();
				break;
			}
		}
		float scale = 0 ;
		if(qcount != 0){
			scale = (float)ocount / (float)qcount ;
		}
		scale = scale * 100 ;
		return (int)scale ;
	}
	
	public String getScale(int rindex , int cindex,int oindex)
	{
		int qcount=qsm.getCount();
		int ocount=0;
		for(OptionStatisticsModel osm:  qsm.getOsms())
		{
			if(osm.getMatrixColIndex()==cindex&&osm.getMatrixRowIndex()==rindex&&osm.getMatrixSelectIndex()==oindex)
			{
				ocount=osm.getCount();
				break;
			}
		}
		float scale = 0 ;
		if(qcount != 0){
			scale = (float)ocount / (float)qcount ;
		}
		scale = scale * 100 ;
		DecimalFormat format = new DecimalFormat();
		format.applyPattern("#,###.00");
		return "" + ocount + "(" + format.format(scale) + ")";
		
	}
	
	
	
	
	

}
