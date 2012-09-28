package br.ufpb.ccae.dce.aps.projeto.gartic.teste;


import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.ufpb.ccae.dce.aps.projeto.gartic.FachadaGartic;
import br.ufpb.ccae.dce.aps.projeto.gartic.GerenciadorBancoPalavras;
import br.ufpb.ccae.dce.aps.projeto.gartic.GerenciadorConexaoRede;
import br.ufpb.ccae.dce.aps.projeto.gartic.Jogador;
import br.ufpb.ccae.dce.aps.projeto.gartic.Jogo;
import br.ufpb.ccae.dce.aps.projeto.gartic.PersistenciaArquivo;
import br.ufpb.ccae.dce.aps.projeto.gartic.Sala;
import br.ufpb.ccae.dce.aps.projeto.gartic.GUI.FuncoesGUI;

public class TestesFachadaGartic {

   private Jogo novoJogo;
   private Jogador jogador;
   private FachadaGartic fachada;
   private FachadaGartic f2;
   private GerenciadorBancoPalavras tema;
   private FuncoesGUI fGUI;
   private GerenciadorConexaoRede conectar;
   private Sala sala;
   
   @Before
   public  void setUp(){
       novoJogo = new  Jogo();
       jogador = new Jogador();
       fachada = new FachadaGartic();
       f2 = new FachadaGartic();
       tema = new GerenciadorBancoPalavras();
       fGUI = new FuncoesGUI();
       conectar = new  GerenciadorConexaoRede();
       sala = new Sala();
    }
   
   @Before
   public void init() throws IOException{
	   PersistenciaArquivo.apagarArquivo();
   }
   
   // testa a conexão
   @Test 
   public void testInitConexaoRede() {
	   fachada.verificarRede();
	   Assert.assertTrue(fachada.redeConectar());
    } 
         
    @Test 
    public void testNick()  {
    	fachada.verificarRede();
 	    fachada.redeConectar();
    	fachada.iniciarJogo();
    	fachada.conexaoBD();
    	Assert.assertTrue(fachada.insereNick("David"));
    }
    // NICK
    @Test 
    public void testNickNaoExistente()  {
    	fachada.verificarRede();
 	    fachada.redeConectar();
    	fachada.iniciarJogo();
    	fachada.conexaoBD();
    	Assert.assertTrue(fachada.insereNick("Carlos"));    
    }
    @Test 
    public void testNickValido(){
    	fachada.verificarRede();
 	    fachada.redeConectar();
    	fachada.iniciarJogo();
    	fachada.conexaoBD();
    	Assert.assertTrue(fachada.validar("Carlos"));
    	fachada.insereNick("Carlos");
    }
    
    @Test
    public void testSala() {
    	fachada.verificarRede();
    	fachada.redeConectar();
    	fachada.iniciarJogo();
    	fachada.conexaoBD();
    	Assert.assertTrue(fachada.validar("Carlos"));
    	Assert.assertTrue(fachada.insereNick("Carlos"));
    	Assert.assertTrue(fachada.insereSala(" Animal"));
    }
    @Test // Pesistencia Na Sala
    public void testSalaFachada2(){
    	f2.verificarRede();
    	f2.redeConectar();
    	f2.iniciarJogo();
    	f2.conexaoBD();
    	Assert.assertTrue(f2.validar("Carlos")); // persisitencia
    	Assert.assertTrue(f2.insereNick("Carlos")); // persistencia
    	Assert.assertTrue(f2.insereSala(" Animal"));
    	
    }
    @Test
    public void testEntrouNaSala(){
    	fachada.verificarRede();
    	fachada.redeConectar();
    	fachada.iniciarJogo();
    	fachada.conexaoBD();
    	fachada.insereNick("Carlos");
    	fachada.initPartida();
    	fachada.salaExistente("Animal");
    	fGUI.initEditor();
  	    conectar.intibatePapo();
  	    tema.gerarPalavra();
  	    tema.palavraAdvinhar();
    	Assert.assertTrue(fachada.existeJogadorS("Carlos", "Animal"));
    }
    @Test
    public void testSalaCheia(){
    	fachada.verificarRede();
    	fachada.redeConectar();
    	fachada.iniciarJogo();
    	fachada.conexaoBD();
        fachada.validar("Carlos");
    	fachada.insereNick("Carlos");
    	Assert.assertTrue(f2.validar("Carlos"));
    	Assert.assertTrue(f2.insereNick("Carlos")); 
    	fachada.initPartida();
    	fachada.salaExistente("Animal");
    	fGUI.initEditor();
  	    conectar.intibatePapo();
  	    tema.gerarPalavra();
  	    tema.palavraAdvinhar();
    	Assert.assertEquals(jogador.existeJogador(),true);	
    }
    @Test // persistencia
    public void testSalaCheiaF2(){
    	f2.verificarRede();
    	f2.redeConectar();
    	f2.iniciarJogo();
    	f2.conexaoBD();
        f2.validar("Carlos");
    	f2.insereNick("Carlos");
    	Assert.assertTrue(f2.validar("Carlos")); 
    	Assert.assertTrue(f2.insereNick("Carlos")); 
    	f2.initPartida();
    	fGUI.initEditor();
  	    conectar.intibatePapo();
  	    tema.gerarPalavra();
  	    tema.palavraAdvinhar();
    	f2.salaExistente("Animal");
    	Assert.assertEquals(jogador.existeJogador(),true);	
    }
    @Test
    public void testListarSalas(){	
    	fachada.verificarRede();
    	fachada.redeConectar();
    	fachada.iniciarJogo();
    	fachada.conexaoBD();
    	fachada.insereNick("Carlos");
        Assert.assertTrue(fachada.listarSalas("Animal"));
    }    
    @Test // persistencia
    public void testListarSalasF2(){
        f2.verificarRede();
    	f2.redeConectar();
       	f2.iniciarJogo();
       	f2.conexaoBD();
       	f2.insereNick("Carlos");
       	Assert.assertTrue(f2.insereNick("Carlos")); 
       	Assert.assertTrue(f2.listarSalas("Animal"));
        }    

    @Test 
    public void testBuscarSalas(){
    	fachada.verificarRede();
    	fachada.redeConectar();
    	fachada.iniciarJogo();
    	fachada.conexaoBD();
    	fachada.validar("Carlos");
    	fachada.insereNick("Carlos");
        Assert.assertTrue(fachada.pesquisaSala("Animais"));
    }
  
    @Test // persistencia
    public void testBuscarSalasf2(){
    	f2.verificarRede();
    	f2.redeConectar();
    	f2.iniciarJogo();
    	f2.conexaoBD();
    	f2.validar("Carlos");
    	f2.insereNick("Carlos");
    	Assert.assertTrue(f2.validar("Carlos"));
    	Assert.assertTrue(f2.insereNick("Carlos")); 
        Assert.assertTrue(f2.pesquisaSala("Animais"));
    }
    @Test 
    public void testSairDaSala(){
    	fachada.verificarRede();
    	fachada.redeConectar();
    	fachada.iniciarJogo();
    	fachada.conexaoBD();
    	fachada.validar("Carlos");
    	fachada.insereNick("Carlos");
    	Assert.assertTrue(f2.validar("Carlos"));
    	Assert.assertTrue(f2.insereNick("Carlos"));
    	fachada.initPartida();
    	fachada.salaExistente("Animal");
    	fGUI.initEditor();
  	    conectar.intibatePapo();
  	    tema.gerarPalavra();
  	    tema.palavraAdvinhar();
    	fachada.existeJogadorS("Carlos","Animais"); 
    	fachada.saindoDaSala();
    	Assert.assertTrue(fachada.existeJogadorS("Carlos","Animais") );
    }
    @Test  // persisitencia
    public void testSairDaSalaF2(){
    	f2.verificarRede();
    	f2.redeConectar();
    	f2.iniciarJogo();
    	f2.conexaoBD();
    	f2.validar("Carlos");
    	f2.insereNick("Carlos");
    	Assert.assertTrue(f2.validar("Carlos")); 
    	Assert.assertTrue(f2.insereNick("Carlos"));
    	f2.initPartida();
    	f2.salaExistente("Animal");
    	fGUI.initEditor();
  	    conectar.intibatePapo();
  	    tema.gerarPalavra();
  	    tema.palavraAdvinhar();
    	f2.existeJogadorS("Carlos","Animais"); 
    	f2.saindoDaSala();
    	Assert.assertTrue(f2.existeJogadorS("Carlos","Animais") );
    }
     //INICIAR O JOGO
    @Test
    public void testInitJogo() {
    	fachada.verificarRede();
    	fachada.redeConectar();
    	fachada.conexaoBD();
    	Assert.assertTrue(fachada.iniciarJogo());
    }
    
   @Test
   public void testInitPartida()  {
	   fachada.verificarRede();
	   fachada.redeConectar();
   	   fachada.conexaoBD();
	   fachada.iniciarJogo();
	   fachada.validar("David");
	   fachada.validar("Carlos");
	   Assert.assertTrue(f2.validar("Carlos")); // persisitencia
       Assert.assertTrue(f2.insereNick("Carlos")); // persistencia
       Assert.assertTrue(f2.validar("David")); // persisitencia
   	   Assert.assertTrue(f2.insereNick("David")); // persistencia
	   fachada.insereNick("David");
	   fachada.insereNick("Carlos");
	   Assert.assertTrue(fachada.initPartida());
	   fachada.salaExistente("Animal"); fGUI.initEditor();
	   conectar.intibatePapo();
	   tema.gerarPalavra();
	   tema.palavraAdvinhar();
	   fachada.escolherAvezDeJogar("Animal");
   }
   @Test 
   public void testMandarDica(){
	   fachada.verificarRede();
	   fachada.redeConectar();
   	   fachada.conexaoBD();
	   fachada.iniciarJogo();
	   fachada.insereNick("Carlos");
   	   Assert.assertTrue(f2.insereNick("Carlos")); // persistencia
	   Assert.assertTrue(fachada.initPartida());
	   fGUI.initEditor();
	   conectar.intibatePapo();
	   tema.gerarPalavra();
	   tema.palavraAdvinhar();
	   Assert.assertTrue(fachada.escolheJogadorDaVez());
	   Assert.assertTrue(fachada.dica()); 
       Assert.assertTrue(Jogo.exibeDica(),true);
   }
   @Test
   public void testProximoADesenhar(){
	   fachada.verificarRede();
	   fachada.redeConectar();
   	   fachada.conexaoBD();
	   fachada.iniciarJogo();
	   fachada.insereNick("Carlos");
   	   Assert.assertTrue(f2.insereNick("Carlos")); // persistencia
	   Assert.assertTrue(fachada.initPartida());
	   Assert.assertTrue(fachada.escolheJogadorDaVez());
	   Assert.assertTrue(novoJogo.jogadorVez());
	   Assert.assertTrue(fGUI.initEditor());
	   conectar.intibatePapo();
	   tema.gerarPalavra();
	   tema.palavraAdvinhar();
	   tema.procuraPalavra("merda"); 
   }
   @Test
   public void testPular(){
	   fachada.verificarRede();
	   fachada.redeConectar();
   	   fachada.conexaoBD();
	   fachada.iniciarJogo();
	   fachada.validar("David");
	   fachada.validar("Carlos");
	   fachada.insereNick("David");
	   fachada.insereNick("Carlos");
	   Assert.assertTrue(f2.validar("Carlos")); // persisitencia
   	   Assert.assertTrue(f2.insereNick("Carlos")); // persistencia
	   fachada.initPartida();
	   fachada.salaExistente("Animal");
	   fGUI.initEditor();
	   conectar.intibatePapo();
	   tema.gerarPalavra();
	   tema.palavraAdvinhar();
	   fachada.escolherAvezDeJogar("Animal");
	  Assert.assertTrue(fachada.pular());
	   
   }
   // PALAVRAS
   @Test 
   public void testInitConexaoBD(){
	   fachada.verificarRede();
	   fachada.redeConectar();
	   Assert.assertTrue(tema.gerarPalavra());
	   fachada.iniciarJogo();
   }
   @Test
   public void testTentativaCorreta(){
	   fachada.redeConectar();
   	   fachada.conexaoBD();
	   fachada.iniciarJogo();
	   fachada.insereNick("Carlos");
   	   Assert.assertTrue(f2.insereNick("Carlos")); // persistencia
	   fachada.initPartida();
	   Assert.assertTrue(fachada.initPartida());
	   fGUI.initEditor();
	   conectar.intibatePapo();
	   tema.gerarPalavra();
	   tema.palavraAdvinhar();
	   fachada.escolherAvezDeJogar("Animal");
	   fachada.palavraRecebida("Animal");
   }
   @Test 
   public void testJogadorDaVezRecebePalavra(){
	   fachada.redeConectar();
   	   fachada.conexaoBD();
	   fachada.iniciarJogo();
	   fachada.insereNick("Carlos");
	   fachada.insereNick("David");
	   Assert.assertTrue(f2.insereNick("Carlos")); // persistencia
   	   Assert.assertTrue(f2.insereNick("David"));// persistencia
   	   fGUI.initEditor();
	   conectar.intibatePapo();
	   tema.gerarPalavra();
	   tema.palavraAdvinhar();
	   fachada.escolherAvezDeJogar("Animal");
	   Assert.assertTrue(fachada.palavraRecebida("Animal"));
	   Assert.assertTrue(fachada.initPartida());
   }
   @Test
   public void testTentativa(){
	   fachada.redeConectar();
   	   fachada.conexaoBD();
	   fachada.iniciarJogo();
	   fachada.insereNick("Carlos");
	   fachada.insereNick("David");
	   Assert.assertTrue(f2.insereNick("Carlos")); // persistencia
   	   Assert.assertTrue(f2.insereNick("David")); // persistencia
   	   fGUI.initEditor();
	   conectar.intibatePapo();
	   tema.gerarPalavra();
	   tema.palavraAdvinhar();
	   fachada.escolherAvezDeJogar("Animal");
	   fachada.palavraRecebida("Animal");
	   fachada.insereResposta("casa");
	   fachada.insereResposta("Moto");
	   fachada.insereResposta("carro");
	   fachada.insereResposta("bola");
	   Assert.assertTrue(fachada.tempoDeLimitacao());
   }
   @Test 
   public void testEnviarMsgChat(){
	   fachada.verificarRede();
	   fachada.redeConectar();
   	   fachada.conexaoBD();
	   fachada.iniciarJogo();
	   fachada.validar("David");
	   fachada.validar("Carlos");
	   fachada.insereNick("David");
	   fachada.insereNick("Carlos");
	   Assert.assertTrue(f2.insereNick("Carlos")); // persistencia
   	   Assert.assertTrue(f2.insereNick("David")); // persistencia
	   fachada.initPartida();
	   fachada.salaExistente("Animal");
	   fGUI.initEditor();
	   conectar.intibatePapo();
	   tema.gerarPalavra();
	   tema.palavraAdvinhar();
	   fachada.escolherAvezDeJogar("Animal");
	   fachada.iniciarBatePapo();
       Assert.assertTrue(fachada.insereMensagem());
   }
   @Test 
   public void testResposta(){
	   fachada.redeConectar(); 
   	   fachada.conexaoBD();
	   fachada.iniciarJogo();
	   fachada.insereNick("Carlos");
	   fachada.validar("Carlos");
	   Assert.assertTrue(f2.insereNick("Carlos")); // persistencia
	   Assert.assertTrue(f2.validar("Carlos")); // persisitencia
       fachada.salaExistente("Animal");
       fGUI.initEditor();
	   conectar.intibatePapo();
	   tema.gerarPalavra();
	   tema.palavraAdvinhar();
	   Assert.assertTrue(fachada.existeJogadorS("Carlos","Animais"));
	   Assert.assertTrue(fachada.insereResposta("Cavalo"));
   }
   //RespostaProxima
   @Test //DenunciarSopro
    public void testDenunciarSopro(){   
    
   }
   // palavras inapropriada
   @Test
   public void testPalavraImproprias() {
	   fachada.redeConectar(); 
   	   fachada.conexaoBD();
	   fachada.iniciarJogo();
	   fachada.insereNick("Carlos");
	   fachada.validar("Carlos");
	   Assert.assertTrue(f2.insereNick("Carlos")); // persistencia
	   Assert.assertTrue(f2.validar("Carlos")); // persisitencia
       fachada.salaExistente("Animal");
	   fGUI.initEditor();
	   conectar.intibatePapo();
	   tema.gerarPalavra();
	   tema.palavraAdvinhar();
	   tema.procuraPalavra("merda");
   }
   @Test (expected=ExcecaoGartic.class)
   public void testPalavrabloqueada() throws ExcecaoGartic{
	   fachada.redeConectar(); 
   	   fachada.conexaoBD();
	   fachada.iniciarJogo();
	   fachada.insereNick("Carlos");
	   fachada.validar("Carlos");
	   Assert.assertTrue(f2.insereNick("Carlos")); // persistencia
	   Assert.assertTrue(f2.validar("Carlos")); // persisitencia
       fachada.salaExistente("Animal");
       fGUI.initEditor();
	   conectar.intibatePapo();
	   tema.gerarPalavra();
	   tema.palavraAdvinhar();
	   Assert.assertTrue(fachada.existeJogadorS("Carlos","Animais"));
	   fachada.iniciarJogo();
	   Assert.assertTrue(fachada.insereResposta("merda"));
	   fachada.palavraAadivinha(tema);
	  
   }
   @Test
   public void testScore(){
	   fachada.iniciarJogo();
	   fachada.validar("David");
	   fachada.validar("Carlos");
	   fachada.insereNick("David");
	   fachada.insereNick("Carlos");
	   Assert.assertTrue(f2.insereNick("Carlos")); // persistencia
   	   Assert.assertTrue(f2.insereNick("David")); // persistencia
	   fachada.initPartida();
	   fachada.salaExistente("Animal");
	   fGUI.initEditor();
	   conectar.intibatePapo();
	   tema.gerarPalavra();
	   tema.palavraAdvinhar();
	   fachada.escolherAvezDeJogar("Animal");
	   Assert.assertTrue(Jogo.addScore(),true);
   }
   @Test
   public void testproximaRodada(){
	   fachada.verificarRede();
	   fachada.redeConectar();
   	   fachada.conexaoBD();
	   fachada.iniciarJogo();
	   fachada.validar("David");
	   fachada.validar("Carlos");
	   fachada.insereNick("David");
	   fachada.insereNick("Carlos");
	   Assert.assertTrue(f2.insereNick("Carlos")); // persistencia
	   Assert.assertTrue(f2.validar("Carlos")); // persisitencia
	   Assert.assertTrue(f2.insereNick("David")); // persistencia
	   Assert.assertTrue(f2.validar("David")); // persisitencia
	   Assert.assertTrue(fachada.initPartida());
	   fachada.salaExistente("Animal");
	   fGUI.initEditor();
	   conectar.intibatePapo();
	   tema.gerarPalavra();
	   tema.palavraAdvinhar();
	   fachada.escolherAvezDeJogar("Animal");
	   Assert.assertTrue(fachada.palavraRecebida("Animal"));
	   Assert.assertTrue(fachada.initPartida());
	   Assert.assertTrue(fachada.proximaRodada());
   }
   @Test  //DetectarVencedor
   public void testVencedor(){
	   fachada.verificarRede();
	   fachada.redeConectar();
   	   fachada.conexaoBD();
	   fachada.iniciarJogo();
	   fachada.validar("David");
	   fachada.validar("Carlos");
	   fachada.insereNick("David");
	   fachada.insereNick("Carlos");
	   Assert.assertTrue(f2.insereNick("Carlos")); // persistencia
	   Assert.assertTrue(f2.validar("Carlos")); // persisitencia
	   Assert.assertTrue(f2.insereNick("David")); // persistencia
	   Assert.assertTrue(f2.validar("David")); // persisitencia
	   Assert.assertTrue(fachada.initPartida());
	   fachada.salaExistente("Animal");
	   fGUI.initEditor();
	   conectar.intibatePapo();
	   tema.gerarPalavra();
	   tema.palavraAdvinhar();
	   fachada.escolherAvezDeJogar("Animal");
	   Assert.assertTrue(fachada.palavraRecebida("Animal"));
	   Assert.assertTrue(fachada.initPartida());
	   Assert.assertTrue(fachada.proximaRodada());
	   Assert.assertTrue(Jogo.fimRodada(),true);
	}  
   // tempo q o jogador tem q digitar as palavras.
   @Test
   public void testTempo(){
	   fachada.verificarRede();
	   fachada.redeConectar();
   	   fachada.conexaoBD();
	   fachada.iniciarJogo();
	   fachada.validar("David");
	   fachada.validar("Carlos");
	   fachada.insereNick("David");
	   fachada.insereNick("Carlos");
	   Assert.assertTrue(f2.insereNick("Carlos")); // persistencia
	   Assert.assertTrue(f2.validar("Carlos")); // persisitencia
	   Assert.assertTrue(f2.insereNick("David")); // persistencia
	   Assert.assertTrue(f2.validar("David")); // persisitencia
	   Assert.assertTrue(fachada.initPartida());
	   fachada.salaExistente("Animal");
	   fGUI.initEditor();
	   conectar.intibatePapo();
	   tema.gerarPalavra();
	   tema.palavraAdvinhar();
	   fachada.escolherAvezDeJogar("Animal");
	   Assert.assertTrue(fachada.palavraRecebida("Animal"));
	   Assert.assertTrue(fachada.initPartida());
       Assert.assertTrue(novoJogo.tempoLimite(tema.tempoAcabou()));
   }
   @Test
   public void testInatividadeJogodor(){
	   fachada.verificarRede();
	   fachada.redeConectar();
   	   fachada.conexaoBD();
	   fachada.iniciarJogo();
	   fachada.validar("David");
	   fachada.validar("Carlos");
	   fachada.insereNick("David");
	   fachada.insereNick("Carlos");
	   Assert.assertTrue(f2.insereNick("Carlos")); // persistencia
	   Assert.assertTrue(f2.validar("Carlos")); // persisitencia
	   Assert.assertTrue(f2.insereNick("David")); // persistencia
	   Assert.assertTrue(f2.validar("David")); // persisitencia
	   Assert.assertTrue(fachada.initPartida());
	   fachada.salaExistente("Animal");
	   fGUI.initEditor();
	   conectar.intibatePapo();
	   tema.gerarPalavra();
	   tema.palavraAdvinhar();
	   fachada.escolherAvezDeJogar("Animal");
	   Assert.assertTrue(fachada.palavraRecebida("Animal"));
	   Assert.assertTrue(fachada.initPartida());
       Assert.assertTrue( fachada.tempoDoJogo());
       Assert.assertTrue(fachada.tempoExpirou());
  }
   //DESENHO
   @Test
    public void testInitEditor() {
      Assert.assertTrue(fGUI.initEditor());
    }
  
  @Test
  public void testFerramentaEditor() {
    Assert.assertFalse(fGUI.funfaFerramenta());
  }
  
  @Test
  public void testCoresEditor() {
    Assert.assertFalse(fGUI.funfaCores());
  }
       
}