package acme.components;

import java.util.ArrayList;
import java.util.List;

import acme.entities.words.Word;


public class SpamComponent {
	
	
	
	public static boolean containSpam(final String text, final List<Word> spamWords, final Double threshold) {
		final List<String> stringSpamWords= SpamComponent.listWordsToStrings(spamWords);
		final List<List<String>> composedSpamWords = SpamComponent.getComposedSpamWords(stringSpamWords);
		final List<String> wordsInput= SpamComponent.formatWordsInput(text);
		
		
		int spamCounter = 0;
		int totalWordsCounter=0;

		for ( int i=0; i<wordsInput.size(); ) { //Comprobamos si se trata de spam simple
			final String w = wordsInput.get(i);
			if(stringSpamWords.contains(w)) {
				spamCounter++;
				totalWordsCounter++;
				i++;
			}else{ //Comprobamos si se trata de una palabra de spam compuesta por varias palabras
				
				boolean coincide=false;
				 int numIndexAdd=1;
				for(int j=0; j<composedSpamWords.size(); j++) {
					
					final List<String>composedSpamWord = composedSpamWords.get(j);
					if(!coincide && wordsInput.size()-i >= composedSpamWord.size()) {
						 coincide = SpamComponent.containSpamRecursive(wordsInput,composedSpamWord,i,0);
						 if(coincide) {
							 numIndexAdd=composedSpamWord.size();
							 break;
						 	}
						}
					
					}
				if(coincide) {
					 spamCounter++;
					}
				i+=numIndexAdd;
				totalWordsCounter++;
				}
			}
		
		return SpamComponent.exceedsThreshold(threshold, spamCounter, totalWordsCounter);
	
		}
	
	public static boolean containSpamRecursive(final List<String>wordsInput,final List<String>composedSpamWord, final int i, final int c) {
		
		
		final String inputWord= wordsInput.get(i);
		final String composedWord= composedSpamWord.get(c);
		
	if(c<composedSpamWord.size()-1) {
	
		if(inputWord.equals(composedWord)) {
			return SpamComponent.containSpamRecursive(wordsInput,composedSpamWord,i+1,c+1);
		}else {
			return false;
		}
		 
	}else {
		return inputWord.equals(composedWord);
		}
		
	}
	
	public static List<List<String>> getComposedSpamWords(final List<String>spamWords){
		final List<List<String>>res = new ArrayList<List<String>>();
		
		for (final String word : spamWords) {
			final String[]aux = word.split(" ");
			
			final List<String> composedSpamWords = new ArrayList<String>();
			if(aux.length>1) {
				for (final String w : aux) {
					if(w.length()>0) {
						composedSpamWords.add(w);
					}
				}
				res.add(composedSpamWords);
			}
		}
		
		return res;
	}
	
	public static List<String> formatWordsInput(final String text){
		
		final String[]words = text.toLowerCase().split(" ");
		final List<String>wordsInput = new ArrayList<>();
		
		for (final String p : words) {
			if(p.length()>0) {
				wordsInput.add(p);
			}
		}
		return wordsInput;
	}
	
	
	
	
	
	public static final  boolean exceedsThreshold(final Double threshold,  final int spamCounter, final int totalWordsCounter) {

		boolean res=false;

		final Double percentageSpam = ((double)spamCounter/totalWordsCounter)*100;
		
		if(percentageSpam>=threshold) res = true;
		return res;
		
	}
	
	public static List<String> listWordsToStrings(final List<Word> wordList){

		final List<String> stringSpamWords= new ArrayList<String>();
		for(final Word w:wordList) {
			stringSpamWords.add(w.getWord());
		}
		return stringSpamWords;
		
	}
	
	
}
