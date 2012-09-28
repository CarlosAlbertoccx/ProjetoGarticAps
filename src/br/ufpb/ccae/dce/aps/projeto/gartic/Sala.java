package br.ufpb.ccae.dce.aps.projeto.gartic;

import br.ufpb.ccae.dce.aps.projeto.gartic.teste.ExcecaoGartic;
import java.io.Serializable;
import java.util.*;


public class Sala implements Serializable {

 
	private static final long serialVersionUID = 1L;
	private String tema;
    private Map <String,Jogador> listaJogadores;
    
    public Sala(String tema){
        listaJogadores = new HashMap<String,Jogador>();
    }
    
    public Sala(){
    	 listaJogadores = new HashMap<String,Jogador>();
    }
        
    public Map<String, Jogador> getListaJogadores() {
        return listaJogadores;
    }
 
    public void setListaJogadores(Map<String, Jogador> listaJogadores) {
        this.listaJogadores = listaJogadores;
    }

    public String getTema(){
        return tema;
    }
    
    public void setTema(String tema){
        this.tema = tema;
    }
    
    public void adicionarJogadores(String nick) throws ExcecaoGartic{
    	Jogador jogador = new Jogador();
    	if(VerificarNickIgual(nick)== true){ 		
    		jogador.setNick(nick);
    		listaJogadores.put(nick, jogador);
    		PersistenciaArquivo.getInstance().getSalas();
    		
    	}
    }

    public Jogador procuraJogador(String nick) {
    	Jogador j = new Jogador();
        if(listaJogadores.containsKey(nick))
            return listaJogadores.get(nick);
        else
        	return j;
    }
    
    public boolean VerificarNickIgual(String novoNick){
           if(procuraJogador(novoNick) != null){
               return false;
           } 
           
           return true;
        }
        
    
}