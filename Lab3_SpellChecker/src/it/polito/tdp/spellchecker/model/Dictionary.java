package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	List<String> dizionarioItaliano = new ArrayList<String>();	
	List<String> dizionarioInglese = new ArrayList<String>();
	List<String> dizionarioCorrente;
	List<RichWord> rw = new LinkedList<RichWord>();
	List<String> werr = new LinkedList<String>();
	int errori = 0;
	long time = 0;
	final static boolean dicotomica = false;
	int contatore = 0;
	
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
				dic.add(word.toLowerCase()); // Aggiungere parola alla struttura dati
			}
			Collections.sort(dizionarioCorrente);
			br.close();
			} catch (IOException e){
			System.out.println("Errore nella lettura del file");
			}	
	}
	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList) {
		RichWord r;
		for (String s : inputTextList) {			
			if (dicotomica) {
				if (binarySearch(s.toLowerCase())) 
					r = new RichWord(s, true);
				else {
					r = new RichWord(s, false);
					werr.add(s);
					errori++;
				}
					
				rw.add(r);
				
			} else {
				if (dizionarioCorrente.contains(s.toLowerCase())) 
					r = new RichWord(s, true);
				else {
					r = new RichWord(s, false);
					werr.add(s);
					errori++;
				}
				rw.add(r);
			}
		}
		time = System.nanoTime();
		return rw;
	}
	
	/*
	 *  Metodo che implementa la ricerca dicotomica
	 */
	private boolean binarySearch(String stemp) {
		int inizio = 0;
	    int fine = dizionarioCorrente.size();
	
	    while (inizio!=fine) {
		    int medio = inizio + (fine - inizio)/2;
		    if (stemp.compareToIgnoreCase(dizionarioCorrente.get(medio))==0) {
		        return true;
		    }
		    else if (stemp.compareToIgnoreCase(dizionarioCorrente.get(medio))>0) {
		    	inizio=medio +1;
		    }
		    else {
		 	fine=medio;
		    }
	    }
		return false;
		
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

	public long getTime() {
		return time;
	}

	public List<RichWord> getRw() {
		return rw;
	}
	
}
