package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


/*Pode-se atrav�s do Metodo @FixMethodOrder ordenar por ordem alfabetica
@FixMethodOrder(MethodSorters.NAME_ASCENDING)*/
public class OrdemTest {
	
	public static int contador = 0;
	//@Test
	public void inicia(){
		contador = 1;
	}	
	//@Test
	public void verifica(){
		Assert.assertEquals(1, contador);
	}
	@Test
	public void testeGeral(){
		inicia();
		verifica();
	}

}
