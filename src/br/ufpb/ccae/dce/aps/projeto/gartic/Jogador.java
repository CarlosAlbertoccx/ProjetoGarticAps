package br.ufpb.ccae.dce.aps.projeto.gartic;

import br.ufpb.ccae.dce.aps.projeto.gartic.teste.ExcecaoGartic;

public class Jogador {
	
	private String nick;
	private int pontuacao;
	private boolean status;
	private GerenciadorBancoPalavras palavras;
	
	
	public Jogador(){
		this.nick= "";
		this.palavras= new GerenciadorBancoPalavras();
	}
	
	
	public String getNick() {
		return nick;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public int getPontuacao() {
		return pontuacao;
	}
	
	public void setPontuacao(int potuacao) {
		this.pontuacao = potuacao;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}

	public String inserePalavra(String palavra) {
		return"";
		
	}

	public boolean existeJogador() {
		return true;
	}


	public void enviaDica() {
			
	}
	
	public void insereMsgBP() {

	}
	public void proximaRodada() {
		// TODO Auto-generated method stub
		 if(getPontuacao() == 125){
			 Jogo novoJogo = new Jogo();
			 setPontuacao(0);
			 novoJogo.iniciar();
		} 
	}
	public void InseriPalavraAdivinhar(GerenciadorBancoPalavras palavras) throws ExcecaoGartic {
		if (palavras.palavraAdvinhar()){
			throw new ExcecaoGartic("BLOQUEADA MSG");
		} else {
			System.out.println(palavras);
		}
	}


	public boolean getStatus() {
		// TODO Auto-generated method stub
		return status;
	}



	


	
	
	
	

}
