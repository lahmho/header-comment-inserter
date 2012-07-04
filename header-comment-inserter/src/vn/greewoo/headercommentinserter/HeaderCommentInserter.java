/**
 *   (C) Smart Physics
 *  www.smartphysics.net
 *  
 * @author: Lam Ho
 * @since: Jul 4, 2012
 */
package vn.greewoo.headercommentinserter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * 
 */
public class HeaderCommentInserter {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final String BASE_DIR = "D:\\git_home\\chinese-chess\\chinese-chess-android\\src";

	enum InputType {
		ADD_HEADER, DELETE_HEADER
	}

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String comment = getHeaderComment();
		browseFilesRecursively(new File(BASE_DIR), new InputTypeBuilder()
				.setType(InputType.ADD_HEADER).setComment(comment));
	}

	/**
	 * 
	 * @param comment
	 * @param file
	 */
	private static void browseFilesRecursively(File file, InputTypeBuilder input) {
		final File[] children = file.listFiles();
		if (children != null) {
			for (File child : children) {
				if (child.isFile()) {
					System.out.println(child.getAbsolutePath());
					if (input.getType() == InputType.ADD_HEADER) {
						insertHearderComment(child, input.getComment());
					} else if (input.getType() == InputType.DELETE_HEADER) {
						deleteHeaderComment(child);
					}
				}
				browseFilesRecursively(child, input);
			}
		}
	}

	/**
	 * @param child
	 * @param numberOfDeleteLine
	 */
	private static void deleteHeaderComment(File child) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream(child), "UTF8"));
			StringBuilder builder = new StringBuilder();

			String str;
			boolean startCopy = true;
			while ((str = in.readLine()) != null) {
				if (str.contains("/**"))
					startCopy = false;
				if (startCopy)
					builder.append(str).append("\n");
				if (str.contains("*/"))
					startCopy = true;
			}
			in.close();

			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(child), "UTF8"));

			out.write(builder.toString());

			out.flush();
			out.close();

		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @param file
	 * @param baseDir
	 */
	private static void insertHearderComment(File file, String comment) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF8"));
			StringBuilder builder = new StringBuilder();

			String str;
			while ((str = in.readLine()) != null) {
				builder.append(str).append("\n");
			}
			in.close();

			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "UTF8"));
			out.write(comment + builder.toString());
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 
	 */
	private static String getHeaderComment() {
		BufferedReader br = null;
		StringBuilder stringBuilder = new StringBuilder(1024);
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("data/headercomment.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				stringBuilder.append(sCurrentLine).append("\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return stringBuilder.toString();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

}
