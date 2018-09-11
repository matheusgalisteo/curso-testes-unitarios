package br.ce.wcaquino.servicos;


import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.Date;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;



public class LocacaoServiceTest {
	
	//Regras de execu��o de testes
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testeLocacao() throws Exception {
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 2, 5.0);
		
		//acao
		Locacao locacao = service.alugarFilme(usuario, filme);
		
		//verificacao //new Date passa a data atual
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		//assertThat(locacao.getValor(), is((6.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
		
	}
	@Test(expected=Exception.class)
	public void testLocacao_filmeSemEstoque() throws Exception{
		//cenario
				LocacaoService service = new LocacaoService();
				Usuario usuario = new Usuario("Usuario 1");
				Filme filme = new Filme("Filme 1", 0, 5.0);
				
				//acao
				Locacao locacao = service.alugarFilme(usuario, filme);
	}

	@Test
	public void testLocacao_filmeSemEstoque2(){
		//cenario
				LocacaoService service = new LocacaoService();
				Usuario usuario = new Usuario("Usuario 1");
				Filme filme = new Filme("Filme 1", 0, 5.0);
				
				//acao
				try {
					Locacao locacao = service.alugarFilme(usuario, filme);
					Assert.fail("Deveria lan�ar exce��o");
				} catch (Exception e) {
					Assert.assertThat(e.getMessage(), is("Filme sem estoque!"));
				}
	}
	
	@Test
	public void testLocacao_filmeSemEstoque3() throws Exception{
		//cenario
				LocacaoService service = new LocacaoService();
				Usuario usuario = new Usuario("Usuario 1");
				Filme filme = new Filme("Filme 1", 0, 5.0);
				
				exception.expect(Exception.class);
				exception.expectMessage("Filme sem estoque");
				//acao				
				Locacao locacao = service.alugarFilme(usuario, filme);
				
				
	}
}
