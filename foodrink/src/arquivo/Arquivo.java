package arquivo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Arquivo {

	/* RETORNA OS BYTES DE UM ARQUIVO PARA UMA SOLICITAÇÃO DE DOWNLOAD */
	public static byte[] getByte(String url) {
		byte[] aplicativo = null;
		try {
			aplicativo = Files.readAllBytes(Paths.get(url));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return aplicativo;
	}
	
}
