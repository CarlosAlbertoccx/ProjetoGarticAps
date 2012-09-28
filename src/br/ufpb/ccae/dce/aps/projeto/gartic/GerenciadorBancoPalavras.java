package br.ufpb.ccae.dce.aps.projeto.gartic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.ufpb.ccae.dce.aps.projeto.gartic.teste.ExcecaoGartic;

public class GerenciadorBancoPalavras {
	
	
	private List <String> palavra ;
	private PersistenciaArquivo arquivo;
	 
	public GerenciadorBancoPalavras (){
		this.palavra = new ArrayList<String>();
	}
	
	public void tema(String tema){  
    }
	public boolean gerarPalavra() {
		return true;
	}
	public boolean palavraAdvinhar() {
		return true;
	}
	public boolean tempoAcabou() {
		return true;
	}
	public void procuraPalavra(String string) throws ExcecaoGartic{
		for(String imp: palavra){
			   if(imp.equals(palavra)){
				   throw new ExcecaoGartic("Palavra impropria");
			   } else {
				   System.out.println(palavra);
		       }
			}	
	}
}
