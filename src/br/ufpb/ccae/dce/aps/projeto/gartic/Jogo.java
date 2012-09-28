package br.ufpb.ccae.dce.aps.projeto.gartic;

import java.util.*;

import br.ufpb.ccae.dce.aps.projeto.gartic.teste.ExcecaoGartic;

public class Jogo {
	
     private GerenciadorBancoPalavras bd;
     private List <Sala> salas;
    // private Jogador jogador;
     private Sala s;

    
    public Jogo(){
       
         this.salas = new LinkedList<Sala>();
         this.s = new Sala();
    }
    
    public void iniciar() {
    
    }
    
    public List<String> getNicks() {
        return null;
    }
    
    public List<String> getTemas() {
        return null;
    }       
    
    public boolean criarSala(String tema)throws ExcecaoGartic{
        
            if(existeSala(tema) == true){
                throw new ExcecaoGartic("SALA EXISTE");
            }else
             s = new Sala(tema);        
            salas.add(s);
            return true;
    }
   
    public boolean existeSala(String tema){
       
        for(Sala sala : salas ){
             if(sala.getTema().equals(tema)){
                return true;
            }
        }return false;
        
     }

 public String escolherJogoDavez(String sala) throws ExcecaoGartic{
    	
    	for(Sala s : salas ){
            if(s.getTema().equals(sala)){
              Map lista = s.getListaJogadores();
              Iterator i = lista.keySet().iterator();

          	while(i.hasNext()){
          		String chave = (String)i.next();
          		Jogador valor = (Jogador)lista.get(chave);
          		if(valor.getStatus() == false){
          			valor.setStatus(true);
          			return valor.getNick();
          		}
          	} return null;

           }else throw new ExcecaoGartic("Não existe essa sala");
       }return null;
    	
    }


    public boolean recebePalavra() {
        return false;
    }

    public boolean tempoLimite(Object tempoAcabou) {
 
        return true;
    }

    public boolean jogadorSala(String nick, String sala) {
        Sala s;
        s = this.procuraSala(sala);
        if(s.procuraJogador(nick) != null)
        	return true;
        else
        	return false;
    }


    public Sala procuraSala(String string) {
        ListIterator<Sala> iterador = salas.listIterator(0);
        while(iterador.hasNext()){  
              Sala sala = iterador.next();
              if(sala.equals(string)){
                  return sala;
              }
              return s; 
        }
        return s;
    }

    public boolean tempoTotalDoJogo() {
        return true;
    }

    public boolean sairTempoEspirou() {
        return true;
    }


    public boolean recebeTema() {
        return true;
    }


    public boolean jogadorVez() {
    	return true;
    }

    public static String exibeDica() {
         return null;
    }

    public static String fimRodada() {
        return null;
    }


    public static String addScore() {
         return null;
    }

    public boolean validaNick(String string) {
        return true;
    }
    public boolean invalidaNick(String string) {
        return false;
    }

    public boolean sairSala(){
        return true;
     }


    public void getListaSalas() {        
    }
   
    public boolean proximojogador(){
    	return true;
    }

	public boolean pulo(boolean pular) {
		if(pular == true){
		 return this.proximojogador();
		}else
			
		    return false;
	}
}

