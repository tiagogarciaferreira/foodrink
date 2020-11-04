package implementacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import constante.EnumStatus;
import model.Email;
import model.Reserva;
import repository.RepositoryGeneric;
import session.HibernateUtil;
import templete.Html;
import util.Local;

public class ServicoReserva implements Runnable {

	private StringBuilder consulta;
	private HashMap<String, String> hashMap;
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Integer token = -1;
	private Reserva atualReserva;
	/* 0 atualizar reservas 1 email de status da reserva */

	public ServicoReserva() {
	}

	public ServicoReserva(Integer token, Reserva atualReserva) {
		this.token = token;
		this.atualReserva = atualReserva;
	}

	@Override
	public void run() {
		switch (this.token) {
		case 0:
			sessionFactory.getCurrentSession().beginTransaction();
			controleReservas();
			sessionFactory.getCurrentSession().getTransaction().commit();
			sessionFactory.getCurrentSession().close();
			break;
		case 1:
			try {
				sessionFactory.getCurrentSession().beginTransaction();
				avisoStatus();
				sessionFactory.getCurrentSession().getTransaction().commit();
				sessionFactory.getCurrentSession().close();
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}

	private void controleReservas() {
		List<Reserva> allReservas = new ArrayList<Reserva>();
		try {
			allReservas = getReservasAtivas();
			for (Reserva reserva : allReservas) {
				if (reserva.getData().equals(Local.getDataAtual())) {
					if (horaPassado(reserva.getHora())) {
						reserva.setStatus(EnumStatus.CANCELADA.value());
						new RepositoryGeneric<Reserva>().update(reserva);
					}
				} else if (!dataPassado(reserva.getData())) {
					reserva.setStatus(EnumStatus.CANCELADA.value());
					new RepositoryGeneric<Reserva>().update(reserva);
				}
			}
			new RepositoryGeneric<Reserva>().clearSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* LISTA TODAS AS RESERVAS PENDENTES E CONFIRMADAS PARA VALIDAÇÃO */
	private List<Reserva> getReservasAtivas() throws Exception {
		consulta = new StringBuilder();
		consulta.append("from " + Reserva.class.getSimpleName());
		consulta.append(" r where status in(:status_a,:status_b)");
		hashMap = new HashMap<String, String>();
		hashMap.put("consulta", consulta.toString());
		hashMap.put("status_a", EnumStatus.PENDENTE.value());
		hashMap.put("status_b", EnumStatus.CONFIRMADA.value());
		return new RepositoryGeneric<Reserva>().getListaTodosHQL(hashMap);
	}

	/* RETURN FALSE DATA MENOR OU IGUAL QUE A ATUAL */
	private boolean dataPassado(String data) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");
		DateTime dateTime = DateTime.parse(data, dateTimeFormatter);
		return dateTime.isAfterNow();
	}

	/* RETURN TRUE SE A HORA E SUPERIOR A 20 MINUTOS */
	private boolean horaPassado(String hora) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("HH:mm:ss");
		DateTime dateTimeReserva = DateTime.parse(hora, dateTimeFormatter);
		DateTime dateTimeAtual = DateTime.parse(Local.getHoraAtual(), dateTimeFormatter);
		BigDecimal minutos = new BigDecimal(Minutes.minutesBetween(dateTimeReserva, dateTimeAtual).getMinutes());
		return (minutos.intValueExact() >= 20) ? true : false;
	}

	/* LISTA RESERVAS PENDENTES E CONFIRMADAS */
	public List<Reserva> getReservas(Integer clienteID, boolean historico) {
		List<Reserva> listaReservas = new ArrayList<Reserva>();
		try {
			consulta = new StringBuilder();
			consulta.append(
					"select new model.Reserva(r.idReserva, r.data, r.hora, r.status, r.mesa.numero, r.mesa.lugares, r.empresa.usuario.nome) ");
			consulta.append("from model.Reserva r ");
			consulta.append("where r.cliente.idCliente=:id_cliente and r.status in(:status_a,:status_b)");
			hashMap = new HashMap<String, String>();
			hashMap.put("consulta", consulta.toString());
			hashMap.put("id_cliente", String.valueOf(clienteID));
			if (!historico) {
				hashMap.put("status_a", EnumStatus.PENDENTE.value());
				hashMap.put("status_b", EnumStatus.CONFIRMADA.value());
			} else {
				hashMap.put("status_a", EnumStatus.CANCELADA.value());
				hashMap.put("status_b", EnumStatus.FINALIZADA.value());
			}
			listaReservas = new RepositoryGeneric<Reserva>().getListaHQLConstrutor(hashMap);
		} catch (Exception e) {
			e.printStackTrace();
			return listaReservas;
		}
		return listaReservas;
	}

	public void avisoStatus() throws Exception {
		Reserva reserva = new RepositoryGeneric<Reserva>().getObjetoId(Reserva.class, this.atualReserva.getIdReserva());
		if (reserva.getStatus().equals(EnumStatus.CANCELADA.value()) || reserva.getStatus().equals(EnumStatus.CONFIRMADA.value())) {
			String assunto = (reserva.getStatus().equals(EnumStatus.CANCELADA.value())) ? EnumStatus.CANCELADA.value() : EnumStatus.CONFIRMADA.value();
			assunto = "RESERVA "+assunto;
			List<String> destinatarios = new ArrayList<String>();
			destinatarios.add(reserva.getCliente().getUsuario().getEmail());
			new ServicoEmail(new Email(destinatarios, assunto, Html.statusReserva(reserva))).enviar();
		}
	}
}