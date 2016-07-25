package com.anirban.languagederivation.algo;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import com.anirban.languagederivation.dto.ResponseDTO;

/**
* The LanguagePredictorImpl program builds an algorithm that
* builds the data dictionary based on the available files in different
* languages and then compares the input text to derive the language.
*
* @author  Anirban Adak
* @version 1.0
* @since   2016-07-21 
*/
public class LanguagePredictorImpl implements LanguagePredictor {

	public static final String NON_ALPHABET_AND_SPACE_REGEX = "[^a-zA-Z ]";
	public static final String LEGAL_CHARACTERS_REGEX = "^[a-zA-Z \\.\\,\\;\\:]+$";
	public static final String INVALID_INPUT = "Invalid input.. Please try again with alphabet A to Z";
	public static final String DATADICTIONARY = "DataDictionary\\";
	Set<String> completeWords = null;
	Map<String, Integer> languageScore = new ConcurrentHashMap<>();
	int countMatch = 0;
	int totalCount = 0;
	String finalLanguage = null;

	/**
	 * This method is used to accept the text message from the user and compare
	 * it with the data dictionary. Based on the matches it gives score to each
	 * language and puts it into map. Then the map is again set into
	 * ResponseDTO.
	 * 
	 * @param textMessage
	 *            This is the input from the user to test which language it
	 *            belongs to.
	 * @return ResponseDTO This returns the scores of different languages based
	 *         on the input. Also holds exception message if it exists.
	 */
	@Override
	public ResponseDTO predictLanguage(String textMessage) throws IOException {
		String errorResponse = null;
		ResponseDTO responseDTO = new ResponseDTO();
		if (validateText(textMessage)) {
			// Comparing textMessage with Dictionary
			// Split the text on basis on space and get it on array
			String[] wordArray = StringUtils.split(textMessage);
			// Put the array into Set to remove the duplicate words
			Set<String> uniqueTextWords = new HashSet<>(Arrays.asList(wordArray));
			totalCount = uniqueTextWords.size();
			Map<String, Set<String>> dataDictionary = buildDictionary();
			uniqueTextWords.forEach(uniqueKey -> {
				dataDictionary.forEach((language, words) -> {
					Set<String> wordSet = words;
					wordSet.forEach(word -> {
						if (uniqueKey.equalsIgnoreCase(word)) {
							if (null != languageScore && languageScore.containsKey(language)) {
								countMatch = languageScore.get(language);
								countMatch = countMatch + 1;
							} else {
								countMatch = 1;
							}

							languageScore.put(language, countMatch);
						}
					});

				});
			});

			responseDTO.setTotalCount(totalCount);
			responseDTO.setLanguageScore(languageScore);
		} else {
			errorResponse = INVALID_INPUT;
			responseDTO.setExceptionMessage(errorResponse);
		}
		return responseDTO;
	}

	/**
	 * This method is used to build data dictionary from the files available
	 * from a particular path eg.ENGLISH.1,ENGLISH.2,FRENCH.1 etc.
	 * 
	 * @return Map This returns the each language along
	 *         with the unique set of words.
	 */
	@Override
	public Map<String, Set<String>> buildDictionary() throws IOException {
		Map<String, Set<String>> dataDictionary = new ConcurrentHashMap<>();
		//Put the DataDictionary folder on C: drive and then test the application or make necessary path change.
		Files.walk(Paths.get("c://DataDictionary")).forEach(filePath -> {
			if (Files.isRegularFile(filePath)) {
				String language = getLanguage(filePath.toString());
				try {
					List<String> lines = Files.readAllLines(filePath, Charset.defaultCharset());
					// Check if the language already exists in the dictionary
					if (!dataDictionary.containsKey(language)) {
						completeWords = new HashSet<>();
					}

					lines.forEach(line -> {
						if (StringUtils.isNotBlank(line)) {
							// Remove all the non alphabets and space from data
							// dictionary
							line = StringUtils.lowerCase(line.replaceAll(NON_ALPHABET_AND_SPACE_REGEX, ""));
							// Split the lines on basis on space and get it on
							// array
							String[] wordArray = StringUtils.split(line);
							// Put the array into Set to remove the duplicate
							// words
							Set<String> newWords = new HashSet<>(Arrays.asList(wordArray));
							completeWords.addAll(newWords);
							dataDictionary.put(language, completeWords);
						}
					});

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return dataDictionary;
	}

	/**
	 * This method is used to fetch the language from the file name.
	 * 
	 * @return String This returns the language.
	 */
	@Override
	public String getLanguage(String fileName) {
		return StringUtils.upperCase(StringUtils.substringBetween(fileName, DATADICTIONARY, "."));
	}

	/**
	 * This method is used to validate input text. It should adhere to A-Z and
	 * these characters .,;:
	 * 
	 * @return boolean This returns true or false based on the match with the
	 *         pattern.
	 */
	@Override
	public boolean validateText(String text) {
		text = StringUtils.lowerCase(text.replaceAll(NON_ALPHABET_AND_SPACE_REGEX, ""));
		Pattern pattern = Pattern.compile(LEGAL_CHARACTERS_REGEX);
		Matcher matcher = pattern.matcher(text);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * This method is used to accept the text message from the user and return
	 * the final language or exception if occurred.
	 * 
	 * @param text
	 *            This is the input from the user to test which language it
	 *            belongs to.
	 * @return String This returns the language or the exception message.
	 */
	@Override
	public String finalLanguage(String text) throws IOException {
		ResponseDTO res = predictLanguage(text);
		String exception = res.getExceptionMessage();
		Map<String, Integer> languageScore = null;
		if (null == exception) {			
			languageScore = res.getLanguageScore();
			if(null!=languageScore && !languageScore.isEmpty())
			{
			int maxValueInMap = (Collections.max(languageScore.values()));
			languageScore.forEach((k, v) -> {
				if (v.equals(maxValueInMap)) {
					finalLanguage = "Final language is:: " + k;
				}
			});
		}
			else
			{
				finalLanguage = "No match.. Try some other text";
			}
		} else {
			finalLanguage = exception;
		}
		return finalLanguage;
	}

}
