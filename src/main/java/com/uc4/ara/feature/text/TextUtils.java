/**---------------------------------------
 *  textUtils.java
 *
 *  Author: George Cui 
 *
 * Copyright (C) 2011 UC4 Software, Inc.  All Rights Reserved.
 *
 * Last check in $Date$
 * by $Author$
 * $Rev$
 */
package com.uc4.ara.feature.text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/** @author George Cui
 * @version $Rev$ $Date$ */
public class TextUtils {
	public static boolean wildCardMatch(String text, String pattern) {
		// Create the cards by splitting using a RegEx. If more speed 
		// is desired, a simpler character based splitting can be done.
		String[] cards = pattern.split("\\*");
		for (int i = 0; i < cards.length; i++) {
			System.out.println(cards[i]);
		}
		// Iterate over the cards.
		for (String card : cards) {
			int idx = text.indexOf(card);
			// Card not detected in the text.
			if (idx == -1) {
				return false;
			}
			// Move ahead, towards the right of the text.
			text = text.substring(idx + card.length());
		}
		return true;
	}

	public static String regExCompare(String string, String regex) {
		Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(string);
		if (matcher.find()) {
			return matcher.group();
		}
		else {
			return "";
		}
	}

	public static boolean textExactCompare(String string, String searchLine) {
		if (string.equals(searchLine)) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean textIndexOfCompare(String string, String searchLine) {
		if (string.indexOf(searchLine) != -1) {
			return true;
		}
		else {
			return false;
		}
	}

	/** @param file
	 *            file to scan
	 * @param searchLine
	 *            pattern to match
	 * @param isRegex
	 *            if the search is regex
	 * @return last postion of the matched line count, -120 when error happens */
	public static int scanLastMatchPos(File file, String searchLine, boolean isRegex) {
		Scanner input;
		int lastPos = 0;
		try {
			input = new Scanner(file, System.getProperty("file.encoding"));
			int lineCount = 0;
			String line = "";
			while (input.hasNext()) {
				lineCount++;
				line = input.nextLine();
				if (isRegex) {
					Pattern pattern = Pattern.compile(searchLine, Pattern.MULTILINE);
					Matcher matcher = pattern.matcher(line);
					if (matcher.find()) {
						if (line.equals(matcher.group())) {
							lastPos = lineCount;
						}
					}
				}
				else {
					if (textExactCompare(line, searchLine)) {
						lastPos = lineCount;
					}
				}
			}
			input.close();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -120;
		}
		return lastPos; // return the last
	}
	
	/** @param file
	 *            file to scan
	 * @param searchLine
	 *            pattern to match
	 * @param isRegex
	 *            if the search is regex
	 * @return last postion of the matched line count, -120 when error happens */
	public static int scanLastMatchPos(FileChannel channel, String searchLine, boolean isRegex) {
		Scanner input;
		int lastPos = 0;
		input = new Scanner(channel, System.getProperty("file.encoding"));
		int lineCount = 0;
		String line = "";
		while (input.hasNext()) {
			lineCount++;
			line = input.nextLine();
			if (isRegex) {
				Pattern pattern = Pattern.compile(searchLine, Pattern.MULTILINE);
				Matcher matcher = pattern.matcher(line);
				if (matcher.find()) {
					if (line.equals(matcher.group())) {
						lastPos = lineCount;
					}
				}
			}
			else {
				if (textExactCompare(line, searchLine)) {
					lastPos = lineCount;
				}
			}
		}
		input.close();
		return lastPos; // return the last
	}

	/** @param file
	 *            file to scan
	 * @param searchLine
	 *            search pattern to match
	 * @param ignoreSpace
	 *            if true, ignore space
	 * @return int array of last empty blocks begin and end, -120 if error */
	public static int[] scanLastEmptyBlock(File file, boolean ignoreSpace) {
		int[] poses = new int[] { 0, 0 };
		Scanner input;
		int posBegin = 0;
		int posEnd = 0;
		try {
			//input = new Scanner(file);
			input = new Scanner(file, System.getProperty("file.encoding"));
			int lineCount = 0;
			String processedLine = "";
			boolean foundBegin = false;
			String line = "";
			while (input.hasNext()) {
				lineCount++;
				line = input.nextLine();
				processedLine = (ignoreSpace) ? StringUtils.trimToEmpty(line) : line;
				//  only search the begin and end within the black block
				if (processedLine.length() == 0) { // last line (first encounter)is
					// qualified as empty line.
					if (!foundBegin) {
						foundBegin = true;
						posBegin = lineCount; // first encounter for the block   
						//posEnd = posBegin;     // initialize to 1 blank space
					}
					posEnd = lineCount; // update pos end within the block
				}
				else {
					foundBegin = false; // ready to search for the next block
				}
			}
			input.close();
			poses[0] = posBegin;
			poses[1] = posEnd;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			poses[1] = -120;
			poses[2] = -120;// errors
		}
		return poses;
	}
	
	/** @param channel
	 *            file channel to scan
	 * @param searchLine
	 *            search pattern to match
	 * @param ignoreSpace
	 *            if true, ignore space
	 * @return int array of last empty blocks begin and end, -120 if error */
	public static int[] scanLastEmptyBlock(FileChannel channel, boolean ignoreSpace) {
		int[] poses = new int[] { 0, 0 };
		Scanner input;
		int posBegin = 0;
		int posEnd = 0;
		//input = new Scanner(file);
		input = new Scanner(channel, System.getProperty("file.encoding"));
		int lineCount = 0;
		String processedLine = "";
		boolean foundBegin = false;
		String line = "";
		while (input.hasNext()) {
			lineCount++;
			line = input.nextLine();
			processedLine = (ignoreSpace) ? StringUtils.trimToEmpty(line) : line;
			//  only search the begin and end within the black block
			if (processedLine.length() == 0) { // last line (first encounter)is
				// qualified as empty line.
				if (!foundBegin) {
					foundBegin = true;
					posBegin = lineCount; // first encounter for the block   
					//posEnd = posBegin;     // initialize to 1 blank space
				}
				posEnd = lineCount; // update pos end within the block
			}
			else {
				foundBegin = false; // ready to search for the next block
			}
		}
		input.close();
		poses[0] = posBegin;
		poses[1] = posEnd;
		return poses;
	}
	

	/**
	 * Count the number of occurrences of findStr in str and return the count
	 * @param str
	 * @param findStr
	 * @return Integer
	 */
	public static Integer countOccurrencesOfString(String str, String findStr) {
		return str.split(Pattern.quote(findStr), -1).length-1;
	}

}
