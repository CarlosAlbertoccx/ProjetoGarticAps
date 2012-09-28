package br.ufpb.ccae.dce.aps.projeto.gartic;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;




public class PersistenciaArquivo implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static GerenteArquivoGartic instance= null;
	private GerenteArquivoGartic arG = new GerenteArquivoGartic();

	
	public static GerenteArquivoGartic getInstance(){
		if(instance == null) {
	        instance = new GerenteArquivoGartic();
	      }
	      return instance;
	 }

	
	public void grava(){
		FileOutputStream f = null;
		ObjectOutputStream stream = null;

		try {
			f = new FileOutputStream("arquivo.bin");
			stream = new ObjectOutputStream(f);
			stream.writeObject(instance);

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
	} 
		

	public void leArquivo(){
	
		FileInputStream fis = null; 
		ObjectInputStream stream = null; 
		try { 
			fis = new FileInputStream("arquivo.bin"); 
			stream = new ObjectInputStream(fis); 
			instance = (GerenteArquivoGartic) stream.readObject(); 
			}catch (Exception e) { 

					e.printStackTrace(); 
			} finally { 

				if (fis != null) { 
					try { 
						fis.close(); 
					} catch (IOException e) { 

						e.printStackTrace(); 
					} 
				} 

			if (stream != null) { 
				try { 
					stream.close(); 
				} catch (IOException e) { 
					e.printStackTrace(); 
				} 
			} 
		} 
	}
	public static void apagarArquivo() throws IOException {
		File file = new File("arquivo.bin");
		if (file.exists()) {
			file.delete();
		}
	}
	public void reset() throws IOException{
		PersistenciaArquivo.apagarArquivo();
	}
	
	
	public GerenteArquivoGartic getArG() {
		return arG;
	}
	
	public void setArG(GerenteArquivoGartic arG) {
		this.arG = arG;
	}
}
	
