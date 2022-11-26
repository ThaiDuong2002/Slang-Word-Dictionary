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
	
	//Get Data
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
	
	// 1. Find Definition of Slang words
	public List<String> findBySlangWord(String word) {
		historySearch.add(word);
		word = word.toUpperCase();
		return dictionary.get(word);
	}
	
	// 2. Find all Slang words which have same definition
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
	
	// 3. Get search history
	public List<String> getHistorySearch() {
		return historySearch;
	}
	
	// 4. Add or overwrite or duplicate a slang
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
	
	// 5. Edit a slang
	public void editSlang(String slang) {
	
	}
	
	// 6. Delete a slang
	public boolean deleteSlang(String slang) {
		if (dictionary.containsKey(slang)) {
			dictionary.remove(slang);
			return true;
		} else {
			return false;
		}
	}
	
	// 7. Reset a dictionary
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
	
	// 8. Random a slang in dictionary
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
	
	// 9. "Choose a correct definition of the slang" Game
	public String slangGameA() {
		Random random = new Random();
		String data;
		String word1 = randomSlang();
		data = word1 + "`";
		List<String> def1 = dictionary.get(word1);
		word1 = def1.get(random.nextInt(def1.size()));
		data += word1 + "`";
		data += word1 + "|";
		
		String word2 = randomSlang();
		List<String> def2 = dictionary.get(word2);
		word2 = def2.get(random.nextInt(def2.size()));
		data += word2 + "|";
		
		String word3 = randomSlang();
		List<String> def3 = dictionary.get(word3);
		word3 = def3.get(random.nextInt(def3.size()));
		data += word3 + "|";
		
		String word4 = randomSlang();
		List<String> def4 = dictionary.get(word4);
		word4 = def4.get(random.nextInt(def4.size()));
		data += word4;
		return data;
	}
	
	// 10. "Choose a correct slang of the definition" Game
	public String slangGameB() {
		String data;
		Random random = new Random();
		String word1 = randomSlang();
		List<String> def = dictionary.get(word1);
		data = def.get(random.nextInt(def.size())) + "`";
		data += word1 + "`";
		data += word1 + "|";
		
		String word2 = randomSlang();
		data += word2 + "|";
		
		String word3 = randomSlang();
		data += word3 + "|";
		
		String word4 = randomSlang();
		data += word4;
		return data;
	}
}
