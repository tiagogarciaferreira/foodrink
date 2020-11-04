package interfac;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public interface InterfaceGeneric<T> extends Serializable {

	void save(T objeto) throws Exception;
	void persist(T objeto) throws Exception;
	void saveOrUpdate(T objeto) throws Exception;
	void update(T objeto) throws Exception;
    void delete(T objeto) throws Exception;
    void deletar(String sql) throws Exception;
	T merge(T objeto) throws Exception;
	void clearSession() throws Exception;
	List<T> getListaTodosHQL(HashMap<String, String> filtro) throws Exception;
	List<T> getListaSQL_HQL(Class<T> entidade, HashMap<String, String> filtro) throws Exception;
	T getObjetoId(Class<T> entidade, Integer id) throws Exception;
    T getObjetoSQL_HQL(Class<T> entidade, HashMap<String, String> filtro) throws Exception;
    T getObjetoHQL(HashMap<String, String> filtro) throws Exception;
    List<T> getListaHQLConstrutor(HashMap<String, String> filtro) throws Exception;
    T getObjetoHQLConstrutor(HashMap<String, String> filtro) throws Exception;
    Integer getInteger(HashMap<String, String> filtro) throws Exception;
	
}
