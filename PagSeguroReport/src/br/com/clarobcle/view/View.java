package br.com.clarobcle.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.github.lgooddatepicker.datepicker.DatePicker;
import com.github.lgooddatepicker.datepicker.DatePickerSettings;

import br.com.clarobcle.controller.Url;
import br.com.clarobcle.secret.Credentials;

public class View extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//method author:Rafael
	private static String format(LocalDate ld) {
		Date d = java.sql.Date.valueOf(ld);
		TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		df.setTimeZone(tz);
		return df.format(d);
	}
	
	//method author:Claudio
	private static String formatNow() {
	LocalDateTime agora = LocalDateTime.now();

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

	String agoraFormatado = agora.format(formatter);
	
	return agoraFormatado;
	
	//System.out.println("LocalDateTime formatado: " + agoraFormatado);
	}

	/**
	 * Create the frame.
	 */
	public View() {
		setTitle("PagSeguro - Vendas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPagseguroVendas = new JLabel("PagSeguro Relatórios:");
		lblPagseguroVendas.setBounds(217, 10, 136, 16);
		contentPane.add(lblPagseguroVendas);

		JLabel lb_email = new JLabel("1. Selecione o E-mail:");
		lb_email.setBounds(30, 50, 142, 30);
		contentPane.add(lb_email);

		JLabel lb_dtini = new JLabel("2. Data Inicial:");
		lb_dtini.setBounds(30, 100, 136, 30);
		contentPane.add(lb_dtini);

		//dataini
		Locale locale = new Locale("pt", "BR");
		DatePickerSettings dateSettings = new DatePickerSettings(locale);
		dateSettings.setInitialDateToToday();
		DatePicker datePicker1 = new DatePicker(dateSettings);
		datePicker1.setBounds(190, 100, 300, 30);
		contentPane.add(datePicker1);

		JLabel lb_dtfim = new JLabel("3. Data Final:");
		lb_dtfim.setBounds(30, 150, 136, 30);
		contentPane.add(lb_dtfim);

		//datafim
		Locale locale2 = new Locale("pt", "BR");
		DatePickerSettings dateSettings2 = new DatePickerSettings(locale2);
		dateSettings2.setInitialDateToToday();
		DatePicker datePicker2 = new DatePicker(dateSettings2);
		datePicker2.setBounds(190, 150, 300, 30);
		contentPane.add(datePicker2);

		Credentials c = new Credentials();

		JComboBox <String> comboBox = new JComboBox <String>();
		for(String item : c.getAccounts()) {
			comboBox.addItem(item);
		}
		comboBox.setBounds(184, 51, 300, 30);
		comboBox.setSelectedIndex(-1);
		contentPane.add(comboBox);

		JButton bt_submit = new JButton("Visualizar Relatório");
		bt_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//&initialDate=2019-11-07T00:00:00.000-03:00&finalDate=2019-11-07T23:59:59.000-03:00

				//validar combobox se nulo
				if (comboBox.getSelectedItem() != null) {
				
					String email = (String) comboBox.getSelectedItem();

					//String url = "https://ws.pagseguro.uol.com.br/v3/transactions/?email="+email+"\n"+"&token="+c.getTokens(email)+"\n"+"&initialDate="+format(datePicker1.getDate())+"&finalDate="+(format(datePicker2.getDate()));
					//JOptionPane.showMessageDialog(null, url);
					
					Url url = new Url();
					try {
												
						if (datePicker1.getDate().toString() != null) {
							System.out.println(
									url.getUrl(email, c.getTokens(email), format(datePicker1.getDate()), format(datePicker2.getDate()))	);
						}else {
							System.out.println(
									url.getUrl(email, c.getTokens(email), format(datePicker1.getDate()), format(datePicker2.getDate())) );
						}
						
						//System.out.println(format(datePicker1.getDate()));
						//String test = ".000-03:00";
						System.out.println((datePicker2.getDate().toString()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
				    JOptionPane.showMessageDialog(null,
				    		"Por favor, selecione o E-mail desejado!", //mensagem
				            "Erro", // titulo da janela 
				            JOptionPane.ERROR_MESSAGE);//decoracao
				}
				
							}
		});
		bt_submit.setBounds(210, 207, 150, 50);
		contentPane.add(bt_submit);
	}

}
