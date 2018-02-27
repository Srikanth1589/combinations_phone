package com.phonenumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public class PhoneNumberCombinations {
    //static lists and maps
    public static List<String> combinations = new ArrayList<>();
    public static List<String> link = new ArrayList<>();
    public static final HashMap<Integer, List<String>> phoneNumberMap = getPhoneNumberMap();
    
    /*
     * Main function which sets the combination
     */
	public void phoneCombinations(String pNumber) {
	    resetters();
	    setCombination(pNumber);
	}

	/*
	 * Get the combination based on page selected
	 */
    public List<String> getCombinationsPageSelected(String digits, int page, int pagesize) {
        phoneCombinations(digits);
	    List<String> comb = combinations;
	    List<List<String>> pages = getPages(comb,pagesize);
	    List<String> pageList = pages.get(page);
	    return pageList;
	}
	
    /*
     * get the combination page wise in the start
     */
	public Page<String> getCombinationsPageWise(String digits, int page, int pagesize) {
	    phoneCombinations(digits);
        List<String> comb = combinations;
        Page<String> pageList = new PageImpl<String>(comb, new PageRequest(page, pagesize), comb.size());
        return pageList;
    }
	
	/**
	 * This returns the paged format of a collection.
	 * This source code is taken from implementation on line.
	 * @param c collection
	 * @param pageSize size of page
	 * @return a list of list of paged collection
	 */
	public <T> List<List<T>> getPages(Collection<T> c, Integer pageSize) {
	    if (c == null)
	        return Collections.emptyList();
	    List<T> list = new ArrayList<T>(c);
	    if (pageSize == null || pageSize <= 0 || pageSize > list.size())
	        pageSize = list.size();
	    int numPages = (int) Math.ceil((double)list.size() / (double)pageSize);
	    List<List<T>> pages = new ArrayList<List<T>>(numPages);
	    for (int pageNum = 0; pageNum < numPages;)
	        pages.add(list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size())));
	    return pages;
	}
	 
	/**
	 * Sets the combinations taken from each number
	 * @param pNumber phone number
	 */
	public void setCombination(String pNumber){
	    if(preCheck(pNumber)) {
	        return;
	    }
	    int curr = NumberUtils.toInt(pNumber.substring(0,1));
	    List<String> letterCombs = phoneNumberMap.get(curr);
	    for (String indivLetter :  letterCombs) {
	        link.add(indivLetter);
	        setCombination(pNumber.substring(1));
	        link.remove(link.size()-1);
	    }
	}

	/**
	 * Checks for the length and collects the combination and returns true
	 * if the length is 0
	 * @param pNumber phone number
	 * @return true/false
	 */
    private boolean preCheck(String pNumber) {
        if (pNumber.length() == 0) {
            StringBuilder builder = new StringBuilder();
            for (String x : link) {
                builder.append(x);
            }
            combinations.add(builder.toString());
            return true;
        }
        return false;
    }
	
    /**
     * get the keypad combination map 
     * @return the combinations
     */
    private static HashMap<Integer, List<String>> getPhoneNumberMap() {
        HashMap<Integer, List<String>> phoneMap = new HashMap<>();
        phoneMap.put(0, Arrays.asList("0"));
        phoneMap.put(1, Arrays.asList("1"));
        phoneMap.put(2, Arrays.asList("2","a","b","c"));
        phoneMap.put(3, Arrays.asList("3","d","e","f"));
        phoneMap.put(4, Arrays.asList("4","g","h","i"));
        phoneMap.put(5, Arrays.asList("5","j","k","l"));
        phoneMap.put(6, Arrays.asList("6","m","n","o"));
        phoneMap.put(7, Arrays.asList("7","p","q","r","s"));
        phoneMap.put(8, Arrays.asList("8","t","u","v"));
        phoneMap.put(9, Arrays.asList("9","w","x","y","z"));
        return phoneMap;
    }
	
    /*
     * Resetting the static lists
     */
	private void resetters() {
        combinations = new ArrayList<>();
        link = new ArrayList<>();
    }
	
}
