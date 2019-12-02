package br.com.clarobcle.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Url {

	public String getUrl(String email, String token, String dateini, String datefim) throws IOException {

		
		OkHttpClient client = new OkHttpClient();

		HttpUrl.Builder urlBuilder = HttpUrl.parse("https://ws.pagseguro.uol.com.br/v3/transactions/").newBuilder();
		urlBuilder.addQueryParameter("email", email);
		urlBuilder.addQueryParameter("token", token);
		urlBuilder.addQueryParameter("initialDate", dateini);
		urlBuilder.addQueryParameter("finalDate", datefim);

		//corrige caracteres especiais na url como o @ que fica com %40
		String url = URLDecoder.decode(urlBuilder.build().toString(), StandardCharsets.UTF_8.name());
		
		//mostrar a url carregada
		//System.out.println(url);
		
		Request request = new Request.Builder()
				.url(url)
				.build();

		Response response = client.newCall(request).execute();
		//guardar o response.body em uma variavel, pois o response.body limpa o body
		String result = response.body().string();
		//mostrar o body do html
		//System.out.println(result);
		//somente com o JOption pane ele mostra a saida qd ela e grande
		//JOptionPane.showMessageDialog(null, result);
		return result;

	}
	
}
