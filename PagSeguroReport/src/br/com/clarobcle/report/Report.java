package br.com.clarobcle.report;

import java.io.InputStream;
import java.util.List;

import br.com.clarobcle.entity.Pagseguro;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class Report {
	
	public void generatReport(List <Pagseguro> url) throws JRException {
		
		InputStream file = Report.class.getResourceAsStream("/reports/report.jrxml");
		
		JasperReport report = JasperCompileManager.compileReport(file);
		
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(url));
		
		JasperViewer.viewReport(print, false);
	}

}
