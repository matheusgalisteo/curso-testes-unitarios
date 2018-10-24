package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroException;

public class CalculadoraTest {
	
	private Calculadora calc;
	
	@Before
	public void setup(){
		calc = new Calculadora();
	}
	@Test
	public void deveSomarDoisValores(){
		//cenario
		int a = 5;
		int b = 3;
		//acao
		int resultado = calc.somar(a, b);
		//verificacao
	    Assert.assertEquals(8, resultado);		
	}
	@Test
	public void deveSubtrairDoisValores(){
		//cenario
		int a = 5;
		int b = 3;
		//acao
		int resultado = calc.subtracao(a, b);
		//verificacao
		Assert.assertEquals(2, resultado);	
	}
	@Test
	public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException{
		//cenario
		int a = 6;
		int b = 3;
		//acao
		int resultado = calc.divisao(a, b);
		//verificacao
		Assert.assertEquals(2, resultado);	
	}
	@Test(expected = NaoPodeDividirPorZeroException.class)
	public void deveLancarExececaoAoDividirPorZero() throws NaoPodeDividirPorZeroException{
		//cenario
		int a = 10;
		int b = 0;
		//acao
		calc.divisao(a, b);	
	}
}
