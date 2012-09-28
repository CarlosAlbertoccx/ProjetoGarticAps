package br.ufpb.ccae.dce.aps.projeto.gartic;

import java.io.Serializable;
import java.util.ArrayList;



public class GerenteArquivoGartic implements Serializable{ 

	private static final long serialVersionUID = 1L;
	private ArrayList<Sala> listasalas;
	
	public GerenteArquivoGartic(){
		this.listasalas = new ArrayList<Sala>();	
	}

	public ArrayList<Sala> getSalas() {
		return listasalas;
	}
	public void setSalas(ArrayList<Sala> salas) {
		this.listasalas = salas;
	}
		
}
