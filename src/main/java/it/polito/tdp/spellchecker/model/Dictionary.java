package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {
	
	Map<String,ArrayList<String>> dizionari = new HashMap<String,ArrayList<String>>();
	public void loadDictionary(String language) {
		if(language.compareTo("english")==0) {
			try {
				FileReader fr = new FileReader("src/main/resources/English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				dizionari.put(language, new ArrayList<String>());
				while((word=br.readLine())!=null) {
					dizionari.get(language).add(word);
				}
				br.close();
			} catch(IOException e) {
				System.out.println("Errore nella lettura del file");
			}
		}
		if(language.compareTo("italian")==0) {
			try {
				FileReader fr = new FileReader("src/main/resources/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				dizionari.put(language, new ArrayList<String>());
				while((word=br.readLine())!=null) {
					dizionari.get(language).add(word);
				}
				br.close();
			} catch(IOException e) {
				System.out.println("Errore nella lettura del file");
			}
		}
	}
	
	public List<Richword> spellCheckText(List<String> InputText, String Language){
		List<Richword> rich = new ArrayList<Richword>();
		for(String s: InputText) {
			if(dizionari.get(Language).contains(s)) {
				Richword r=new Richword(s,true);
				rich.add(r);
			}
			else {
				Richword r=new Richword(s,false);
				rich.add(r);
			}
		}
		return rich;
	}
}
