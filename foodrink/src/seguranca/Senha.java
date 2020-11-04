package seguranca;

import java.util.Random;

import util.Servico;

public class Senha {

	private static Integer numeroCaracteres;
	private static StringBuilder novaSenha;
	private static final String[] caracteres = { "a", "1", "b", "2", "]", "7", "8", "N", "O", "P", "9", "a", "b", "c", "d",
			"e", "f", "g", "{", "]", "h", "i", "j", "k", "[", "J", "K", "L", "M", "4", "5", "@", "#", "6", "Q", "R",
			"S", "T", "U", "}", "l", "m", "n", "o", "%", "&", "p", "A", "B", "C", "D", "E", "q", "r", "x", "{", "y",
			"z", "F", "G", "H", "I", "s", "t", "u", "v", "w", "V", "W", "X", "Y", "Z", "!", "$", "*" };

	/* GERA E RETORNA UMA NOVA SENHA */
	public static String gerarNova() {
		numeroCaracteres = new Random().nextInt((10 - 8) + 1) + 8;
		novaSenha = new StringBuilder();
		for (int i = 0; i < numeroCaracteres; i++) {
			int posicao = (int) (Math.random() * caracteres.length);
			novaSenha.append(caracteres[posicao]);
		}
		System.out.println("Nova senha gerada: "+ novaSenha.toString());
		return novaSenha.toString();
	}

	/* RETORNA A SENHA EM MD5 */
	public static String senhaToMD5(String senha) {
		return Servico.toMd5(senha);
	}

}
