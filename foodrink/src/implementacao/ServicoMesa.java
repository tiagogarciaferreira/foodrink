package implementacao;

import java.util.HashMap;
import java.util.List;
import model.Empresa;
import constante.EnumStatus;
import model.Mesa;
import model.Pedido;
import model.Reserva;
import repository.RepositoryGeneric;

public class ServicoMesa {
	
	private StringBuilder consulta;
	private HashMap<String, String> hashMap;
	
	public List<Mesa> getMesasDisponiveis(Integer empresaID, String data, String mesaSelecionada) throws Exception{
		List<Mesa> mesasOcupadas = getMesasComReservas(empresaID, data);
		mesasOcupadas.addAll(getMesasComPedidos(empresaID, data));
		List<Mesa> mesas = getMesas(empresaID);
		Integer ocupada = (mesaSelecionada.isEmpty()) ? -1 : Integer.parseInt(mesaSelecionada);
		for(int x = 0; x < mesas.size(); x++) {
			for(int y = 0; y < mesasOcupadas.size(); y++) {
				if(mesas.get(x).getIdMesa().intValue() == mesasOcupadas.get(y).getIdMesa().intValue() && mesas.get(x).getIdMesa() != ocupada.intValue()) {
					mesas.remove(x);
				}
				
			}
		}
		return mesas;
	}
	
	/* LISTA DE MESAS */
	private List<Mesa> getMesas(Integer empresaID){
		Empresa empresa = new RepositoryGeneric<Empresa>().getObjetoId(Empresa.class, empresaID);
		List<Mesa> listaMesas = empresa.getMesas();
		return listaMesas;
	}
	
	/* MESAS COM RESERVAS PENDENTES OU CONFIRMADAS */
	private List<Mesa> getMesasComReservas(Integer empresaID, String data) throws Exception{
		consulta = new StringBuilder();
		consulta.append("select new model.Mesa(r.mesa.idMesa) from "+ Reserva.class.getSimpleName());
		consulta.append(" r where r.empresa.idEmpresa=:id_empresa and r.status in (:status_a,:status_b) and r.data=:data");
		hashMap = new HashMap<String, String>();
		hashMap.put("consulta", consulta.toString());
		hashMap.put("id_empresa", String.valueOf(empresaID));
		hashMap.put("status_a", EnumStatus.PENDENTE.value());
		hashMap.put("status_b", EnumStatus.CONFIRMADA.value());
		hashMap.put("data", data);
		return new RepositoryGeneric<Mesa>().getListaHQLConstrutor(hashMap);
	}
	
	/* MESAS COM PEDIDOS */
	private List<Mesa> getMesasComPedidos(Integer empresaID, String data) throws Exception{
		consulta = new StringBuilder();
		consulta.append("select new model.Mesa(p.mesa.idMesa) from "+ Pedido.class.getSimpleName());
		consulta.append(" p where p.empresa.idEmpresa=:id_empresa and p.status=:status and p.data=:data");
		hashMap = new HashMap<String, String>();
		hashMap.put("consulta", consulta.toString());
		hashMap.put("id_empresa", String.valueOf(empresaID));
		hashMap.put("status", EnumStatus.EM_ANDAMENTO.value());
		hashMap.put("data", data);
		return new RepositoryGeneric<Mesa>().getListaHQLConstrutor(hashMap);
	}
	
	
}
