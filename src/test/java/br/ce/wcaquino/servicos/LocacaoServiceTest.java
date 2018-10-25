package br.ce.wcaquino.servicos;



import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;

public class LocacaoServiceTest {

	private LocacaoService service;
	//definicao do contador
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup(){
		service = new LocacaoService();
	}
	
	@Test
	public void deveAlugarFilme() throws Exception {
		//cenario		
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));
		
		//acao
		Locacao locacao = service.alugarFilme(usuario, filmes);
			
		//verificacao
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
	}
	
	@Test(expected = FilmeSemEstoqueException.class) //Forma elegante Serve quando o que nos importa é a exceção 
	public void deveLancarExcecaoAoAlugarFilmeSemEstoque() throws Exception{
		//should shouldNot (nome dos testes em ingles)
		//cenario
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 4.0));
		
		//acao
		service.alugarFilme(usuario, filmes);
	}
	
	@Test //Forma Robusta Serve para maior controle da execução
	public void deveLancarExececaoAoAlugarSemUsuario() throws FilmeSemEstoqueException{
		//cenario
		List<Filme> filmes = Arrays.asList(new Filme("Filme 2", 1, 4.0));
		
		//acao
		try {
			service.alugarFilme(null, filmes);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}
	

	@Test
	public void deveLancarExececaoAoAlugarSemFilme() throws FilmeSemEstoqueException, LocadoraException{
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		//acao
		service.alugarFilme(usuario, null);
	}
	@Test 
	public void devePagar75PcNoFilme3() throws FilmeSemEstoqueException, LocadoraException{
		//cenario
		Usuario usuario = new Usuario();
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0));
		//acao
		Locacao resultado = service.alugarFilme(usuario, filmes);
		//verificacao
		//4+4+3
		assertThat(resultado.getValor(), is(11.0));
	}
	@Test 
	public void devePagar50PcNoFilme4() throws FilmeSemEstoqueException, LocadoraException{
		//cenario
		Usuario usuario = new Usuario();
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0),new Filme("Filme 4", 2, 4.0));
		//acao
		Locacao resultado = service.alugarFilme(usuario, filmes);
		//verificacao
		//4+4+3+2
		assertThat(resultado.getValor(), is(13.0));
	}
	@Test 
	public void devePagar25PcNoFilme5() throws FilmeSemEstoqueException, LocadoraException{
		//cenario
		Usuario usuario = new Usuario();
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0),new Filme("Filme 4", 2, 4.0), new Filme("Filme 5", 2, 4.0));
		//acao
		Locacao resultado = service.alugarFilme(usuario, filmes);
		//verificacao
		//4+4+3+2+1
		assertThat(resultado.getValor(), is(14.0));
	}
	@Test 
	public void devePagar0PcNoFilme6() throws FilmeSemEstoqueException, LocadoraException{
		//cenario
		Usuario usuario = new Usuario();
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0), 
										   new Filme("Filme 3", 2, 4.0),new Filme("Filme 4", 2, 4.0), 
										   new Filme("Filme 5", 2, 4.0),new Filme("Filme 5", 2, 4.0));
		//acao
		Locacao resultado = service.alugarFilme(usuario, filmes);
		//verificacao
		//4+4+3+2+1
		assertThat(resultado.getValor(), is(14.0));
	}
}
