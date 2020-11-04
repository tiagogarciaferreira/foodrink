package implementacao;

import java.util.HashMap;
import constante.EnumTipoUsuario;
import model.Empresa;
import model.Funcionario;
import repository.RepositoryGeneric;
import session.UserLogado;

public class ServicoEmpresa {

	private StringBuilder consulta;
	private HashMap<String, String> hashMap;

	public Integer getId(Integer usuarioID) {
		try {
			if(UserLogado.getPermissaoUserLogado().contains(EnumTipoUsuario.EMPRESA.name())) {
				return getIdentificador(usuarioID);
			}
			else if(UserLogado.getPermissaoUserLogado().contains(EnumTipoUsuario.GARCOM.name()) || UserLogado.getPermissaoUserLogado().contains(EnumTipoUsuario.COZINHA.name())) {
				consulta = new StringBuilder();
				consulta.append("select f.empresa_id from " + Funcionario.class.getSimpleName());
				consulta.append(" f where f.usuario_id=:id_usuario");
				hashMap = new HashMap<String, String>();
				hashMap.put("consulta", consulta.toString());
				hashMap.put("id_usuario", String.valueOf(usuarioID));
				return new RepositoryGeneric<Funcionario>().getInteger(hashMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public Integer getIdentificador(Integer usuarioID) {
		try {
			consulta = new StringBuilder();
			consulta.append("select e.id_empresa from " + Empresa.class.getSimpleName());
			consulta.append(" e where e.usuario_id=:id_usuario");
			hashMap = new HashMap<String, String>();
			hashMap.put("consulta", consulta.toString());
			hashMap.put("id_usuario", String.valueOf(usuarioID));
			return new RepositoryGeneric<Empresa>().getInteger(hashMap);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public String funcionamento(Integer empresaID) {
		Empresa empresa = new Empresa();
		try {
			consulta = new StringBuilder();
			consulta.append("select e.funcionamento as funcionamento from " + Empresa.class.getSimpleName());
			consulta.append(" e where e.id_empresa=:id_empresa");
			hashMap = new HashMap<String, String>();
			hashMap.put("tipo_consulta", "SQL");
			hashMap.put("consulta", consulta.toString());
			hashMap.put("id_empresa", String.valueOf(empresaID));
			empresa = new RepositoryGeneric<Empresa>().getObjetoSQL_HQL(Empresa.class, hashMap);
			return empresa.getFuncionamento();
		} catch (Exception e) {
			e.printStackTrace();
			return empresa.getFuncionamento();
		}
	}
	
	
	
}
