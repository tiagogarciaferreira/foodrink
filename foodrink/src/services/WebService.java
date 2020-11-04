package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import constante.EnumStatus;

public class WebService {

	private String resultado;

	public String buscaCep(String cep) {
		try {
			URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");
			URLConnection urlConnection = url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			while ((resultado = bufferedReader.readLine()) != null) {
				if (resultado.contains("\"erro\": true")) {
					return EnumStatus.INVALIDO.value();
				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
			return EnumStatus.INVALIDO.value();
		} catch (IOException e) {
			e.printStackTrace();
			return EnumStatus.INVALIDO.value();
		}
		return "";
	}

	public String buscaCnpj(String cnpj) {
		try {
			URL url = new URL("https://www.receitaws.com.br/v1/cnpj/" + cnpj);
			URLConnection urlConnection = url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			while ((resultado = bufferedReader.readLine()) != null) {
				if (resultado.contains("\"status\": \"ERROR\"")) {
					return EnumStatus.INVALIDO.value();
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return EnumStatus.INVALIDO.value();
		} catch (IOException e) {
			e.printStackTrace();
			return EnumStatus.INVALIDO.value();
		}
		return "";
	}

}
