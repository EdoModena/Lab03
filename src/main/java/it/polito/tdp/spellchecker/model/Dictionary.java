package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
	
	public List<Richword> spellCheckTextDichotomic(List<String> InputText, String Language){
		List<Richword> rich = new ArrayList<Richword>();
		int a = (int) (dizionari.get(Language).size()/2);
		int min=0;
		int max=dizionari.get(Language).size();
		boolean f;
		for(String s: InputText) {
			f=false;
			while(!f) {
			if((max-min)<=1) {
				if(s.compareTo(dizionari.get(Language).get(a))==0) {
					Richword r=new Richword(s,true);
					rich.add(r);
					f=true;
				}
				else {
					Richword r=new Richword(s,false);
					rich.add(r);
					f=true;
				}
			}
			else if(s.compareTo(dizionari.get(Language).get(a))==0) {
				Richword r=new Richword(s,true);
				rich.add(r);
				f=true;
			}
			else if(s.compareTo(dizionari.get(Language).get(a))>0) {
				min=a;
				a=(int)(max+min)/2;
			}
			else {
				max=a;
				a=(int)(max+min)/2;
			}
			}
		}
		return rich;
	}
	
	public List<Richword> spellCheckTextLinear(List<String> InputText, String Language){
		List<Richword> rich = new ArrayList<Richword>();
		int c=0;
		for(String s: InputText) {
			for(int i=0;i<dizionari.get(Language).size();i++) {
			if(s.compareTo(dizionari.get(Language).get(i))==0)
				c++;
			}
			if(c==1) {
				Richword r=new Richword(s,true);
				rich.add(r);
			}
			else {
				Richword r=new Richword(s,false);
				rich.add(r);
			}
			c=0;
		}
		return rich;
	}
}
