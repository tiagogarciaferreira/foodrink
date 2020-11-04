package implementacao;

import java.util.HashMap;
import model.Cliente;
import repository.RepositoryGeneric;

public class ServicoCliente {

	private StringBuilder consulta;
	private HashMap<String, String> hashMap;

	public Integer getId(Integer usuarioID) {
		try {
			consulta = new StringBuilder();
			consulta.append("select c.id_cliente from " + Cliente.class.getSimpleName());
			consulta.append(" c where c.usuario_id=:id_usuario");
			hashMap = new HashMap<String, String>();
			hashMap.put("consulta", consulta.toString());
			hashMap.put("id_usuario", String.valueOf(usuarioID));
			return new RepositoryGeneric<Cliente>().getInteger(hashMap);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}
