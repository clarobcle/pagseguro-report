package br.com.clarobcle.report;

import java.io.InputStream;
import java.util.List;

import br.com.clarobcle.entitity.Pagseguro;

public class Report {
	
	public void generatReport(List <Pagseguro> sales) {
		InputStream file = Report.class.getResourceAsStream("/reports/report.jrxml");
		
		
	}

}
