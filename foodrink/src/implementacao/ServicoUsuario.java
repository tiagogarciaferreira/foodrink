package implementacao;

import java.util.HashMap;
import model.Usuario;
import repository.RepositoryGeneric;

public class ServicoUsuario {

	private StringBuilder consulta;
	private HashMap<String, String> hashMap;
	
	public String senhaAtual(Integer usuarioID) {
		try {
			consulta = new StringBuilder();
			consulta.append("select u.senha as senha from " + Usuario.class.getSimpleName());
			consulta.append(" u where id_usuario=:id_usuario");
			hashMap = new HashMap<String, String>();
			hashMap.put("tipo_consulta", "SQL");
			hashMap.put("consulta", consulta.toString());
			hashMap.put("id_usuario", String.valueOf(usuarioID));
			Usuario usuario = new RepositoryGeneric<Usuario>().getObjetoSQL_HQL(Usuario.class, hashMap);
			return usuario.getSenha();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
}
