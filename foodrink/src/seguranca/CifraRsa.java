package seguranca;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import constante.EnumCifra;

public class CifraRsa {

	private KeyFactory keyFactory;
	private static Cipher cipher;

	@SuppressWarnings("unused")
	private void novaChave() {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(EnumCifra.ALGORITHM.value());
			keyPairGenerator.initialize(Integer.parseInt(EnumCifra.TAMANHO_CHAVE.value()));
			KeyPair keypair = keyPairGenerator.generateKeyPair();
			System.out.println("Chave Publica: " + getStringPublicKey(keypair));
			System.out.println("Chave Privada: " + getStringPrivateKey(keypair));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	private String getStringPublicKey(KeyPair keypair) {
		return Base64.getEncoder().encodeToString(keypair.getPublic().getEncoded());
	}

	private String getStringPrivateKey(KeyPair keypair) {
		return Base64.getEncoder().encodeToString(keypair.getPrivate().getEncoded());
	}

	private PublicKey getPublicKey(String stringChave) {

		try {
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(stringChave));
			keyFactory = KeyFactory.getInstance(EnumCifra.ALGORITHM.value());
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			return publicKey;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	private PrivateKey getPrivateKey(String stringChave) {

		try {
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(stringChave));
			keyFactory = KeyFactory.getInstance(EnumCifra.ALGORITHM.value());
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			return privateKey;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String criptografa(String dados) {
		try {
			cipher = Cipher.getInstance(EnumCifra.ALGORITHM.value());
			cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(EnumCifra.CHAVE_PUBLICA.value()));
			byte[] textoCriptografado = cipher.doFinal(dados.getBytes("UTF-8"));
			return Base64.getEncoder().encodeToString(textoCriptografado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String descriptografa(String dados) {
		try {
			cipher = Cipher.getInstance(EnumCifra.ALGORITHM.value());
			cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(EnumCifra.CHAVE_PRIVADA.value()));
			byte[] textoDescriptografado = cipher.doFinal(Base64.getDecoder().decode(dados));
			return new String(textoDescriptografado);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}