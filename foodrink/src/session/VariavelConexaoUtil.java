package session;

import java.io.Serializable;

public class VariavelConexaoUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	/* 
	 * VARIAVEL DE CONEXAO COM O DATASOURCE 
	 * DOC: https://docs.oracle.com/cd/E19857-01/820-5704/bhano/index.html
	 */
	public static String JAVA_COMP_ENV_JDBC_DATA_SOURCE = "java:/comp/env/jdbc/datasource";

}
