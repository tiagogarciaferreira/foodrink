package implementacao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import constante.EnumEmail;
import model.Email;
import model.ItemPedido;
import model.Pedido;
import model.Preparo;
import model.Produto;
import repository.RepositoryGeneric;
import session.HibernateUtil;
import templete.Html;

public class ServicoPedido implements Runnable{

	private ItemPedido itemPedido;
	private Produto produto;
	private Pedido pedido;
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public ServicoPedido() {}

	public ServicoPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	@Override
	public void run() {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			notificaFinalizacao();
			sessionFactory.getCurrentSession().getTransaction().commit();
			sessionFactory.getCurrentSession().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void notificaFinalizacao() throws Exception {
		pedido = new RepositoryGeneric<Pedido>().getObjetoId(Pedido.class, pedido.getIdPedido());
		ArrayList<String> destinatarios = new ArrayList<String>();
		destinatarios.add(pedido.getCliente().getUsuario().getEmail());
		new Thread(new ServicoEmail(new Email(destinatarios, EnumEmail.PEDIDO_FINALIZADO.value(),Html.pedidoFinalizado(pedido)))).start();
	}

	/* CONVERTE LISTA DE ITENS JSON PARA UMA LISTA DE ITENS OBJETO JAVA */
	public List<ItemPedido> getItensPedidoObjeto(String json, Integer IdPedido) {
		List<ItemPedido>  itensPedido = new ArrayList<ItemPedido>();
		json = "{\"produtos\":["+json+"]}";
		json = json.replaceAll("\\\\", "");
		JsonObject jsonObject;
		JsonParser jsonParser = new JsonParser();
		jsonObject = (JsonObject) jsonParser.parse(json);
		JsonArray jsonArray = (JsonArray) jsonObject.getAsJsonArray("produtos");
		 for(int i = 0; i < jsonArray.size();i++) {
			 String id_item = jsonArray.get(i).getAsJsonObject().get("idItemPedido").toString().replaceAll("\"", "");
			 Integer itemID = (!id_item.equals("null")) ? Integer.parseInt(id_item): null;
			 String id_produto = jsonArray.get(i).getAsJsonObject().get("idProduto").toString().replaceAll("\"", ""); 
			 Integer produtoID = (!id_produto.equals("null")) ? Integer.parseInt(id_produto): null;
			 itemPedido = new ItemPedido(itemID, jsonArray.get(i).getAsJsonObject().get("quantidade").getAsInt());
			 produto = new Produto(produtoID, jsonArray.get(i).getAsJsonObject().get("preco").getAsBigDecimal());
			 itemPedido.setProduto(produto);
			 pedido = new Pedido(IdPedido);
			 itemPedido.setPedido(pedido);
			 if(!removerItem(itemPedido)) {
				 itensPedido.add(itemPedido);
			 }	 
		 }
		 return itensPedido;
	}
	
	/* REMOVER ITEM DO PEDIDO */
	private boolean removerItem(ItemPedido item) {
		 try {
			 if(item.getProduto().getIdProduto() == null) {
				 new RepositoryGeneric<Preparo>().deletar("delete from preparo where item_pedido_id ="+item.getIdItemPedido());
				 new RepositoryGeneric<ItemPedido>().delete(new ItemPedido(item.getIdItemPedido()));
				 return true;
			 }
		 }
		 catch (Exception e) {
			e.printStackTrace();
		 }
		 return false;
	}
	

	/* CONVERTE LISTA DE ITENS OBJETO JAVA PARA LISTA DE ITENS JSON */
	public String setItensPedidoJson(List<ItemPedido> itensPedido) {
		String json = "";
		Integer index = 0;
		for (ItemPedido item : itensPedido) {
			json += "{\"idItemPedido\":" + item.getIdItemPedido() + ",\"idProduto\":\""
					+ item.getProduto().getIdProduto() + "\",\"nome\":\"" + item.getProduto().getNome() + " - "
					+ item.getProduto().getTamanho() + "\",\"preco\":\"" + item.getProduto().getPreco()
					+ "\",\"quantidade\":\"" + item.getQuantidade() + "\"}";
			json += (index < itensPedido.size() - 1) ? "," : "";
			index++;
		}
		return json;
	}

	
	

}
