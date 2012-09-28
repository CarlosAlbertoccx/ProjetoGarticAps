package br.ufpb.ccae.dce.aps.projeto.gartic;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

	public class FachadaGartic {
		
			private  Jogo gartic;
			private  Jogador jogadores;
			private  GerenciadorConexaoRede conectar;
			private  GerenciadorBancoPalavras bd;
			private  Editor paint;
			private  String tema;
		//	private  FachadaGartic fachada;
			private  Sala salas;
			
			// construdor
			public FachadaGartic(){
				this.bd = new GerenciadorBancoPalavras();
				this.conectar = new GerenciadorConexaoRede();
				this.jogadores = new Jogador();
				this.paint = new Editor();
				this.salas = new Sala();
				this.gartic = new Jogo();
			}
			

			public  boolean iniciarJogo(){ 
				gartic.iniciar();
				conectar.start();
				return true;
			}
			// verificar se os dois jogadores na sala
			public boolean initPartida(){
				paint.editor();
				bd.tema(tema);
				bd.palavraAdvinhar();
				return true;
			}
			
			public  boolean insereNick(String nome){
				salas.adicionarJogadores(nome);
				return true;
			}
			
			public boolean validar(String nome){
				gartic.validaNick(nome);
				return true;
			}
			
			public boolean existeJogadorS(String nome, String sala){
				gartic.jogadorSala(nome, sala);
				return true;
			}
			
			//SALA
			public void salaExistente(String sala){
				gartic.existeSala(sala);
			}
			public boolean saindoDaSala(){
				gartic.sairSala();
				return true;
			}
			
  		    public boolean insereSala(String tema) {
				// TODO Auto-generated method stub
				gartic.criarSala(tema);
				return true;
			}
  		    
  		    public boolean listarSalas(String tema){
  		    	salas.adicionarJogadores(tema);
  		    	return true;
  		    }
  		    public boolean pesquisaSala(String string) {
  		    	return true;
  		    }
  		   
  		    //nick
 
  		    public String escolherAvezDeJogar(String sala){
  		      return gartic.escolherJogoDavez(sala);		  
  		    }
  		    
  		    public boolean escolheJogadorDaVez(){
  		    	gartic.jogadorVez();
  		    	return true;
  		    }
  		    
  		    // Palavras
  		    
  		    public boolean palavraRecebida(String sala){
  		    	gartic.escolherJogoDavez(sala);
  		    	gartic.recebePalavra();
  		    	return true;
  		     }
  		    
  		    public boolean insereResposta(String palavra) {
  		        jogadores.inserePalavra(palavra);
  		    	return true;
  		    }
  		    
  		    public boolean insereMensagem() {
  		    	// TODO Auto-generated method stub
  		    	return true;
  		    }
  		   
  		    public boolean dica(){
  		    	 jogadores.enviaDica();
  		    	 return true;
  		     }
  		    
  		    
			// Redes
			
			// Verificar se esta Online
			
			public void verificarRede(){
				this.conectar.pesquisarIp();
			}
			
			public boolean conexaoBD(){
				this.conectar.servidor();
				this.conectar.pesquisarIp();
				return true;
			}
			
			public boolean redeConectar(){
				this.conectar.start();
				return true;		
			}
		
			public boolean tempoDoJogo(){
				gartic.tempoTotalDoJogo();
				return true;
			}

			public boolean tempoExpirou(){
				gartic.sairTempoEspirou();
				return true;
			}
			public boolean tempoDeLimitacao(){
				gartic.tempoLimite(bd.tempoAcabou());
				return true;
			}

			public boolean pular() {
			  gartic.pulo(false);
				return true;
			}
			
			public boolean proximaRodada() {
				// TODO Auto-generated method stub
				 jogadores.setPontuacao(125);
			       jogadores.proximaRodada();
			       return true;
			}

			public boolean palavraAadivinha(GerenciadorBancoPalavras temas) {
				// TODO Auto-generated method stub
				 jogadores.InseriPalavraAdivinhar(temas);
				 return true;
			}
			
			public boolean iniciarBatePapo() {
				jogadores.insereMsgBP();
				return true;
			}

}

		
		

	

