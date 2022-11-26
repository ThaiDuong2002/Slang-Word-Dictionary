package Dictionary;

import java.io.*;
import java.util.*;

/**
 * Dictionary
 * Created by Thai Duong
 * Date 11/15/2022 - 9:32 PM
 * Description: ...
 */
public class SlangDictionary {
	private static final Hashtable<String, List<String>> dictionary = new Hashtable<>();
	private static final List<String> historySearch = new ArrayList<>();
	Scanner input;
	
	public void getData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/Data/slang.txt"));
			String data;
			while ((data = br.readLine()) != null) {
				if (data.contains("`")) {
					String[] item = data.split("`");
					List<String> temp = new ArrayList<>(Arrays.asList(item[1].split("\\|")));
					temp.replaceAll(s -> s.replaceAll(" ", ""));
					dictionary.put(item[0], temp);
				}
			}
			br.close();
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}
	
	public List<String> findBySlangWord(String word) {
		historySearch.add(word);
		word = word.toUpperCase();
		return dictionary.get(word);
	}
	
	public List<String> findByDefinition(String word) {
		historySearch.add(word);
		List<String> slangList = new ArrayList<>();
		for (String s : dictionary.keySet()) {
			if (dictionary.get(s).contains(word)) {
				slangList.add(s);
			}
		}
		return slangList;
	}
	
	public List<String> getHistorySearch() {
		return historySearch;
	}
	
	public void addSlangWord(String slang, List<String> definitions, String option) {
		slang = slang.toUpperCase();
		if (Objects.equals(option, "overwrite")) {
			dictionary.put(slang, definitions);
		} else if (Objects.equals(option, "duplicate")) {
			List<String> temp = dictionary.get(slang);
			definitions.addAll(temp);
			dictionary.put(slang, definitions);
		} else {
			dictionary.put(slang, definitions);
		}
	}
	
	public boolean deleteSlang(String slang) {
		if (dictionary.containsKey(slang)) {
			dictionary.remove(slang);
			return true;
		} else {
			return false;
		}
	}
	
	public void resetDictionary() {
		dictionary.clear();
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/Data/default.txt"));
			String data;
			while ((data = br.readLine()) != null) {
				if (data.contains("`")) {
					String[] item = data.split("`");
					List<String> temp = new ArrayList<>(Arrays.asList(item[1].split("\\|")));
					temp.replaceAll(s -> s.replaceAll(" ", ""));
					dictionary.put(item[0], temp);
				}
			}
			br.close();
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}
	
	public String randomSlang() {
		Random rd = new Random();
		int randomNumber = rd.nextInt(dictionary.size());
		int i = 0;
		String a = null;
		for (String s : dictionary.keySet()) {
			if (i == randomNumber) {
				a = s;
			}
			i++;
		}
		return a;
	}
}
