package com.werun.surveypark.datasourse;



import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.werun.surveypark.domain.Survey;

public class SurveyparkDataSourceRouter extends AbstractRoutingDataSource{


	@Override
	protected Object determineCurrentLookupKey() {
		SurveyToken t=SurveyToken.getCurrentToken();
		if(t!=null)
		{
			Survey s=t.getCurrentSurvey();
			SurveyToken.unbindToken();
			if(s!=null)
			{
				return s.getId()%2==0?"even":"odd" ;
			}
		}
		
		return null;
	}

}
