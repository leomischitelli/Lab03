package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dictionary {
	
	private Set<RichWord> listaParole;

	public Dictionary() {
		listaParole = new HashSet<>();
	}

	public void loadDictionary(String language) {
		
		try {
			FileReader fr = new FileReader(language);
			BufferedReader br = new BufferedReader(fr);
			String word;
			
			while((word = br.readLine()) != null) {
				RichWord parola = new RichWord(word);
				if(!listaParole.contains(parola))
					listaParole.add(parola);
				
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		
		List<RichWord> listaOut = new ArrayList<RichWord>();
		for(String s : inputTextList) {
			RichWord parola = new RichWord(s);
			if(!listaParole.contains(parola)) {
				parola.setCorretta(false);
				listaOut.add(parola);
			}
		}
		
		
		return listaOut;
		
	}

}
