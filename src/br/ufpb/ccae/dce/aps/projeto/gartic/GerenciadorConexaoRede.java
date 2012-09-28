package br.ufpb.ccae.dce.aps.projeto.gartic;

import br.ufpb.ccae.dce.aps.projeto.gartic.teste.ExcecaoGartic;

public class GerenciadorConexaoRede {

	private boolean conexao = true;
	
	public GerenciadorConexaoRede(){
		
	}
	public boolean isConexao() {
		return conexao;
	}
	public void setConexao(boolean conexao) {
		this.conexao = conexao;
	}
	public void servidor() {
	}

     public boolean pesquisarIp() {
		return true;
	}
	public void intibatePapo() {
			
	}
	public void start() throws ExcecaoGartic {
		if(conexao == false){
			throw new ExcecaoGartic("Conexão Não Estabelecida");
		} else 
			System.out.print("Conexão estabelecida");  
	}

}
