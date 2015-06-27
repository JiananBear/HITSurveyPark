package com.werun.surveypark.struts2.action;

import java.awt.Font;

import javax.annotation.Resource;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.werun.surveypark.domain.Question;
import com.werun.surveypark.domain.statistics.OptionStatisticsModel;
import com.werun.surveypark.domain.statistics.QuestionStatisticsModel;
import com.werun.surveypark.service.StatisticsService;

@Controller
@Scope("prototype")
public class ChartOutputAction extends BaseAction<Question>{

	private static final long serialVersionUID = 3439842753005456549L;
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
	
	// 对哪个问题进行统计
		private Integer qid;
	
	@Resource
	private StatisticsService ss;
	
	/**
	 * 生成图表并输出到浏览器
	 */
	public String execute() {
		return SUCCESS;
	}
	
	
	public JFreeChart getChart() {
		JFreeChart chart = null ;
		try
		{
			Font font = new Font("宋体", 0, 20);// 字体
			QuestionStatisticsModel qsm = ss.statistics(qid);
			DefaultPieDataset pieds = null;// 饼图的数据集
			DefaultCategoryDataset cateds = null;// 种类数据集
			
			if(chartType<2)
			{
				pieds = new DefaultPieDataset();
				for (OptionStatisticsModel os : qsm.getOsms()) {
					pieds.setValue(os.getOptionLabel(), os.getCount());
				}
			}
			else
			{
				cateds=new DefaultCategoryDataset();
				for (OptionStatisticsModel os : qsm.getOsms()) {
					cateds.setValue(os.getCount(), os.getOptionLabel(), "");
				}
			}
			
			switch (chartType) {
			
				case CHARTTYPE_PIE_2D:
					chart= ChartFactory.createPieChart(qsm.getQuestion().getTitle(), pieds, true, false, false);
					break;
				case CHARTTYPE_PIE_3D:
					chart = ChartFactory.createPieChart3D(qsm.getQuestion().getTitle(), pieds, true, true, true);
					//设置前景色透明度
					chart.getPlot().setForegroundAlpha(0.6f);
					break ;
				case CHARTTYPE_BAR_2D_H:// 平面条形图
					chart = ChartFactory.createBarChart(qsm.getQuestion().getTitle(), "", "", cateds,
							PlotOrientation.HORIZONTAL, true, true, true);
					break ;
				case CHARTTYPE_BAR_2D_V:// 平面条形图
					chart = ChartFactory.createBarChart(qsm.getQuestion().getTitle(), "", "", cateds,
							PlotOrientation.VERTICAL, true, true, true);
				case CHARTTYPE_BAR_3D_H:// 平面条形图
					chart = ChartFactory.createBarChart3D(qsm.getQuestion().getTitle(), "", "", cateds,
							PlotOrientation.HORIZONTAL, true, true, true);
				case CHARTTYPE_BAR_3D_V:// 平面条形图
					chart = ChartFactory.createBarChart3D(qsm.getQuestion().getTitle(), "", "", cateds,
							PlotOrientation.VERTICAL, true, true, true);
					break ;
				//
				case CHARTTYPE_LINE_2D:// 平面条形图
					chart = ChartFactory.createLineChart(qsm.getQuestion().getTitle(), "", "", cateds,
							PlotOrientation.VERTICAL, true, true, true);
					break ;
				case CHARTTYPE_LINE_3D:// 平面条形图
					chart = ChartFactory.createLineChart3D(qsm.getQuestion().getTitle(), "", "", cateds,
							PlotOrientation.HORIZONTAL, true, true, true);
					break ;
			}
			
			chart.getTitle().setFont(font);
			chart.getLegend().setItemFont(font);
			
			
			//设置饼图特效
			if(chart.getPlot() instanceof PiePlot){
				PiePlot pieplot = (PiePlot) chart.getPlot();
				pieplot.setLabelFont(font);
				pieplot.setExplodePercent(0, 0.1);
				pieplot.setStartAngle(-15);
				pieplot.setDirection(Rotation.CLOCKWISE);
				pieplot.setNoDataMessage("No data to display"); 
				//pieplot.setForegroundAlpha(0.5f);
				//pieplot.setBackgroundImageAlpha(0.3f);
			}
			//设置非饼图效果
			else{
				chart.getCategoryPlot().getRangeAxis().setLabelFont(font);
				chart.getCategoryPlot().getRangeAxis().setTickLabelFont(font);
				chart.getCategoryPlot().getDomainAxis().setLabelFont(font);
				chart.getCategoryPlot().getDomainAxis().setTickLabelFont(font);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chart ;
			
			
}
		
	public int getChartType() {
		return chartType;
	}

	public void setChartType(int chartType) {
		this.chartType = chartType;
	}

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}
	
	
}
