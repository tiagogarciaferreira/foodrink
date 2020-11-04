package implementacao;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import constante.EnumEmail;
import contexto.Contexto;
import model.Email;

public class ServicoEmail implements Runnable {

	private Email email;

	public ServicoEmail(Email email) {
		this.email = email;
	}

	public ServicoEmail() {}

	public void enviar() {
		try {
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");/* Autorização */
			properties.put("mail.smtp.starttls", "true"); /* Autenticação */
			properties.put("mail.smtp.host", "smtp.gmail.com"); /* Sercidor gmail Google */
			properties.put("mail.smtp.port", "465");/* Porta do servidor */
			properties.put("mail.smtp.socketFactory.port", "465");/* Expecifica a porta a ser conectada pelo socket */
			properties.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");/* Classe socket de conexão ao SMTP */

			/* criando sessao de autenticada que permitira enviar o enumEmail */
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(EnumEmail.REMETENTE.value(), EnumEmail.SENHA_REMETENTE.value());
				}
			});
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(EnumEmail.REMETENTE.value(), "FooDrink")); /* Quem está enviano */
			message.setSubject(email.getAssunto());/* Assunto do e-mail */
			MimeMultipart mimeMultipart = new MimeMultipart("related");
			BodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent(email.getMensagem(), "text/html; charset=UTF-8");
			mimeMultipart.addBodyPart(bodyPart);
			bodyPart = new MimeBodyPart();			
			DataSource dataSource = new FileDataSource(Contexto.getContexto("resources/img/logo.png"));
			bodyPart.setDataHandler(new DataHandler(dataSource));
			bodyPart.setHeader("Content-ID", "<image>");
			mimeMultipart.addBodyPart(bodyPart);
			message.setContent(mimeMultipart);

			for (String destinatario : email.getDestinatario()) {
				Address[] addresses = InternetAddress.parse(destinatario);
				message.setRecipients(Message.RecipientType.TO, addresses);/* DadosEmail de destino */
				Transport.send(message);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		enviar();
	}

}
