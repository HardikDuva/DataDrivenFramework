package org.configuration;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utilities {

	public static String getTimeStamp() {
		return (new SimpleDateFormat("yyyyMMdd_HHmmss")).format(new Date());
	}

	/**
	 * Create directory at specific path
	 */
	public static void createDir(String directoryPath) {
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			directory.mkdirs();
		}
	}

	/**
	 * Delete directory from specific path
	 */
	public static void deleteDir(String path) {
		Path directory = Paths.get(path);
		try {
			Files.walkFileTree(directory, new SimpleFileVisitor<>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
					Files.delete(file); // this will work because it's always a File
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					Files.delete(dir); //this will work because Files in the directory are already deleted
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			System.out.println("-");
		}
	}

	/**
	 * Get current Date
	 */
	public static String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * Append text to file
	 */
	public static void appendTextToFile(String filePath, String text) {
		try {
			File fileObj = new File(filePath);
			fileObj.getParentFile().mkdirs();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try (PrintWriter out = new PrintWriter(new BufferedWriter(
				new FileWriter(filePath, true)))) {
			out.println(text);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Get String from Array
	 */
	public static String getStringFromArray(String[] arr) {
		StringBuilder str = new StringBuilder();
		for (String s : arr) {
			str.append(s).append(",");
		}
		if (str.toString().endsWith(",")) {
			str = new StringBuilder(str.substring(0, str.length() - 1));
		}
		return str.toString();
	}
	
	}
