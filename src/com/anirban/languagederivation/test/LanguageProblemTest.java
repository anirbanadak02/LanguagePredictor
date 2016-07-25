package com.anirban.languagederivation.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.anirban.languagederivation.algo.LanguagePredictor;
import com.anirban.languagederivation.algo.LanguagePredictorImpl;

public class LanguageProblemTest {
	
	@Test
	public void testpredictEngLishLanguage() throws IOException
	{
			LanguagePredictor lp = new LanguagePredictorImpl();			
			assertEquals(lp.finalLanguage("Let us go quickly"), "Final language is:: ENGLISH");	
	}
	
	@Test
	public void testpredictSpanishLanguage() throws IOException
	{
			LanguagePredictor lp = new LanguagePredictorImpl();			
			assertEquals(lp.finalLanguage("Y en el ascenso a Finhaut Emosson"), "Final language is:: SPANISH");	
	}
	
	@Test
	public void testpredictFrenchLanguage() throws IOException
	{
			LanguagePredictor lp = new LanguagePredictorImpl();			
			assertEquals(lp.finalLanguage("pas a poser une question pour etre aide dans vos premiers pas"), "Final language is:: FRENCH");	
	}
	
	@Test
	public void testpredictGermanLanguage() throws IOException
	{
			LanguagePredictor lp = new LanguagePredictorImpl();			
			assertEquals(lp.finalLanguage("polizeilichen Ermittlungen war der Mast auf"), "Final language is:: GERMAN");	
	}
	
	@Test
	public void testpredictItalianLanguage() throws IOException
	{
			LanguagePredictor lp = new LanguagePredictorImpl();			
			assertEquals(lp.finalLanguage("Matteo Renzi e non"), "Final language is:: ITALIAN");	
	}
	
	@Test
	public void testpredictIndonesianLanguage() throws IOException
	{
			LanguagePredictor lp = new LanguagePredictorImpl();			
			assertEquals(lp.finalLanguage("CONCACAF di Miami ikut digeledah"), "Final language is:: INDONESIAN");	
	}

}
