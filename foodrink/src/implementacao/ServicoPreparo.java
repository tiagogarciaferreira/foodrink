package implementacao;

import java.util.HashMap;
import java.util.List;
import model.ItemPedido;
import model.Preparo;
import repository.RepositoryGeneric;

public class ServicoPreparo {
	
	private HashMap<String, String> hashMap;
	private StringBuilder consulta;

	public void remover(Integer pedidoID) throws Exception {
		consulta = new StringBuilder();
		consulta.append("from " + ItemPedido.class.getSimpleName());
		consulta.append(" i where pedido_id=:id_pedido");
		hashMap = new HashMap<String, String>();
		hashMap.put("consulta", consulta.toString());
		hashMap.put("id_pedido", String.valueOf(pedidoID));
		List<ItemPedido> listaItens = new RepositoryGeneric<ItemPedido>().getListaTodosHQL(hashMap);
		new RepositoryGeneric<ItemPedido>().clearSession();
		for (ItemPedido item : listaItens) {
			new RepositoryGeneric<Preparo>().deletar("delete from preparo where item_pedido_id=" + item.getIdItemPedido());
		}
	}
}
