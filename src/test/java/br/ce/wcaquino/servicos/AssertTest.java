package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;

public class AssertTest {
	
	@Test
	public void AsserTest(){
		Assert.assertFalse(false);
		Assert.assertTrue(true);
		
		Assert.assertEquals(1, 1);
		Assert.assertEquals(0.59, 0.51, 0.1);
		int i = 5;
		Integer i2 = 5;
		Assert.assertEquals(Integer.valueOf(i), i2);
		Assert.assertEquals(i, i2.intValue());
		
		Assert.assertEquals("Bala", "Bala");
		Assert.assertNotEquals("Bala", "casa");
		Assert.assertTrue("Bala". equalsIgnoreCase("bala"));
		Assert.assertTrue("bola".startsWith("bol"));
		
		Usuario u1 = new Usuario("Usuario 1");
		Usuario u2 = new Usuario("Usuario 1");
		Usuario u3 = null;
		
		//Comparação do nome do usuario
		Assert.assertEquals(u1, u2);
		
		//Comparação da instancia do objeto
		Assert.assertSame(u2, u2);
		
		Assert.assertNull(u3);
	}


}
