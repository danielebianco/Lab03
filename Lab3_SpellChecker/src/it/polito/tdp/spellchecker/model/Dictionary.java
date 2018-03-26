package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	List<String> dizionarioItaliano = new LinkedList<String>();	
	List<String> dizionarioInglese = new LinkedList<String>();
	List<String> dizionarioCorrente;
	List<RichWord> rw = new LinkedList<RichWord>();
	List<String> werr = new LinkedList<String>();
	int errori = 0;
	
	public void loadAll() {
		loadDictionary(dizionarioItaliano, "Italian");
		loadDictionary(dizionarioInglese, "English");
	}
	
	public void loadDictionary(List<String> dic, String language) {
		
		try {
			FileReader fr = new FileReader("rsc/"+language+".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				dic.add(word); // Aggiungere parola alla struttura dati
			}
			br.close();
			} catch (IOException e){
			System.out.println("Errore nella lettura del file");
			}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList) {
		for(String s : inputTextList) {
			if(!dizionarioCorrente.contains(s)) {
				rw.add(new RichWord(s, false));
				werr.add(s);
				errori++;
			}
			else
				rw.add(new RichWord(s, true));
		}
		return rw;
	}
	
	public String stampaString(List<String> werr) {
		String s = "";
		for(String ss : werr) {
			s += ss+"\n";
		}
		return s;
	}
	
	public void resetList(List<String> inputTextList) {
		inputTextList.clear();
	}
	
	public void setErrori(int errori) {
		this.errori = errori;
	}
	
	public void setDizionarioCorrente(List<String> dizionarioCorrente) {
		this.dizionarioCorrente = dizionarioCorrente;
	}
	
	public List<String> getDizionarioItaliano() {
		return dizionarioItaliano;
	}

	public List<String> getDizionarioInglese() {
		return dizionarioInglese;
	}

	public List<String> getDizionarioCorrente() {
		return dizionarioCorrente;
	}
	
	public List<String> getWerr() {
		return werr;
	}
	
	public int getErrori() {
		return errori;
	}

	public List<RichWord> getRw() {
		return rw;
	}
	
}
