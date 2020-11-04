package teste;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.Local;

class TesteHoraData {

	
	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Inicio do processo...");
	}


	@Test
	void testGetDataAtual() {
		for(int i = 0; i < 10;i++) {
			String data = Local.getDataAtual();
			if(data != null) {
				System.out.println("Data atual: " + data);					
			}
			else {
				System.out.println("Data atual: Erro");	
			}
		}
	}

	@Test
	void testGetHoraAtual() {
		for(int i = 0; i < 10;i++) {
			String hora = Local.getDataAtual();
			if(hora != null) {
				System.out.println("Hora atual: " + hora);					
			}
			else {
				System.out.println("Hora atual: Erro");	
			}
		}
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("Final do processo...");
	}
}
