package util;

import java.math.BigInteger;
import org.springframework.util.DigestUtils;
import com.google.gson.JsonObject;
import model.Url;
import seguranca.CifraRsa;

public class Servico {

	private static CifraRsa cifraRsa;

	/* CODIFICA UMA STRING PARA MD5 E RETORNA */
	public static String toMd5(String dados) {
		return new BigInteger(1, DigestUtils.md5Digest(dados.getBytes())).toString(16);
	}

	/* GERA UMA RESPOSTA JSON E RETORNA */
	public static String toJson(String atributo, String valor) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(atributo, valor);
		return jsonObject.toString();
	}

	/* CIFRA OS VALORES DE UMA URL COM RSA */
	public static String toCifraUrl(Url url) {
		cifraRsa = new CifraRsa();
		StringBuilder urlCifrada = new StringBuilder();
		urlCifrada.append("?id=").append(cifraRsa.criptografa(url.getId())).append("&").append("tipo=")
				.append(cifraRsa.criptografa(url.getTipo())).append("&").append("validade=")
				.append(cifraRsa.criptografa(url.getValidade()));
		return urlCifrada.toString();
	}

	/* DECIFRA OS VALORES DE UMA URL COM RSA */
	public static Url toDecifraUrl(Url url) {
		cifraRsa = new CifraRsa();
		return new Url(cifraRsa.descriptografa(url.getId().replaceAll(" ", "+")), cifraRsa.descriptografa(url.getTipo().replaceAll(" ", "+")), cifraRsa.descriptografa(url.getValidade().replaceAll(" ", "+")));
	}
	
	/* VERIFICA SE O VALOR É UM INTEIRO OU STRING */
	public static boolean isInteger(String value) {
		if (value.length() >= 9) {
			return false;
		}
		return value != null && value.matches("[0-9]*");
	}
}
