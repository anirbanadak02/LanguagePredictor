package com.anirban.languagederivation.algo;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import com.anirban.languagederivation.dto.ResponseDTO;

/**
 * The LanguagePredictor interface builds an algorithm that builds the data
 * dictionary based on the available files in different languages and then
 * compares the input text to derive the language.
 *
 * @author Anirban Adak
 * @version 1.0
 * @since 2016-07-21
 */
public interface LanguagePredictor {
	/**
	 * This interface is used to accept the text message from the user and
	 * compare it with the data dictionary. Based on the matches it gives score
	 * to each language and puts it into map. Then the map is again set into
	 * ResponseDTO.
	 * 
	 * @param textMessage
	 *            This is the input from the user to test which language it
	 *            belongs to.
	 * @return ResponseDTO This returns the scores of different languages based
	 *         on the input. Also holds exception message if it exists.
	 */
	public ResponseDTO predictLanguage(String textMessage) throws IOException;

	/**
	 * This interface is used to build data dictionary from the files available
	 * from a particular path eg.ENGLISH.1,ENGLISH.2,FRENCH.1 etc.
	 * 
	 * @return Map This returns the each language along
	 *         with the unique set of words.
	 */
	public Map<String, Set<String>> buildDictionary() throws IOException;

	/**
	 * This interface is used to fetch the language from the file name.
	 * 
	 * @return String This returns the language.
	 */
	public String getLanguage(String fileName);

	/**
	 * This interface is used to validate input text. It should adhere to A-Z
	 * and these characters .,;:
	 * 
	 * @return boolean This returns true or false based on the match with the
	 *         pattern.
	 */
	public boolean validateText(String text);

	/**
	 * This interface is used to accept the text message from the user and
	 * return the final language or exception if occurred.
	 * 
	 * @param textMessage
	 *            This is the input from the user to test which language it
	 *            belongs to.
	 * @return String This returns the language or the exception message.
	 */
	public String finalLanguage(String textMessage) throws IOException;

}
