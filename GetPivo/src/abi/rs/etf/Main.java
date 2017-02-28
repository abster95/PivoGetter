package abi.rs.etf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;
import java.util.StringTokenizer;

public class Main {
	public static final int num = 6;
	private static final String filePath = new File("GetPivo\\data.txt").getAbsolutePath();
	public static Person[] everybody = new Person[num];

	public static void readFile() {
		System.out.println(filePath);
		BufferedReader br = null;
		String fromFile = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			fromFile = sb.toString();

		} catch (FileNotFoundException e) {
			System.err.println("Can't find file to read");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringTokenizer tokenizer = new StringTokenizer(fromFile);
		int i = 0;
		while (tokenizer.hasMoreTokens()) {
			String s = tokenizer.nextToken();
			int w = Integer.parseInt(tokenizer.nextToken());
			int d = Integer.parseInt(tokenizer.nextToken());
			@SuppressWarnings("unused")
			double p = Double.parseDouble(tokenizer.nextToken());
			everybody[i++] = new Person(s, w, d);
			// everybody[i - 1].setDrank();
		}
	}

	public static void writeFile() {
		Formatter writter = null;
		try {
			writter = new Formatter(new File(filePath));
			for (int j = 0; j < num; j++) {
				writter.format("%s\n", everybody[j].toString());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writter.close();
		}
	}

	public static void main(String[] args) {
		readFile();
		new GUI();
	}

}
