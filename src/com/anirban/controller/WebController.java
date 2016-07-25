package com.anirban.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anirban.languagederivation.algo.LanguagePredictor;
import com.anirban.languagederivation.algo.LanguagePredictorImpl;

/**
 * The WebController gets the request from JSP and makes a call to
 * LanguagePredictor to predict the final language.
 *
 * @author Anirban Adak
 * @version 1.0
 * @since 2016-07-21
 */
public class WebController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7523653323796620418L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String input = request.getParameter("inputText");
		LanguagePredictor lp = new LanguagePredictorImpl();
		String responseText = lp.finalLanguage(input);
		PrintWriter out = response.getWriter();
		out.print(responseText);

	}

}
