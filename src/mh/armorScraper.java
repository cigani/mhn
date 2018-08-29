package mh;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

class armorScraper {
    private String mURL;
    private List<String> defaultSkills = new skillMatrix().getDefaultSkills();
    private HashMap<String, Integer> armorHash = new HashMap<>();
    private ArrayList<String> attributeList = new ArrayList<>();
    private ArrayList<Integer> armorValues = new ArrayList<>();
    private HashMap<String, HashMap<String, Integer>> singleArmorSetHash = new HashMap<>();
    private HashMap<String, HashMap<String, HashMap<String, Integer>>> multiArmorHashSet = new HashMap<>();
    private ArrayList<String> nullGear = new ArrayList<>();

    void scrape() {
        Document doc = null;
        try {
            String wikiURL = "https://monsterhunterworld.wiki.fextralife.com/Armor+Sets";
            doc = Jsoup.connect(wikiURL).userAgent("Mozilla/56.0").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements armorSetList = doc.getElementsByClass("col-sm-3").select("a");


        nullGear.add("No Mail");
        nullGear.add("No Helm");
        nullGear.add("No Vambraces");
        nullGear.add("No Coil");
        nullGear.add("No Greaves");

        for (Element ele : armorSetList) {
            String mURL = ele.attr("href");
            try {
                String baseURL = "https://monsterhunterworld.wiki.fextralife.com";
                doc = Jsoup.connect(baseURL + mURL).userAgent("Mozilla/56.0").get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Elements infobox = doc.getElementsByClass("infobox");
            Elements armorTable = doc.getElementsByClass("col-sm-7 col-lg-6");

            extractSkills(infobox);
            ArrayList<String> armorNames = extractArmorList(armorTable);
            extractArmorValues(armorTable, armorNames);
            extractAttributeNames(armorTable);
            generateFinalMap(armorNames);
            String key = mURL.replaceAll(Pattern.quote("+"), "_");
            key = key.replaceAll(Pattern.quote("/"), "");
            multiArmorHashSet.put(key, singleArmorSetHash);

            attributeList.clear();
            armorValues.clear();
            singleArmorSetHash.clear();
        }

    }

    private void generateFinalMap(ArrayList<String> armorNames) {
        // Attribute names and HashMap formation <Attribute> <Value>
        int count = 0;
        attributeList.subList((armorValues.size() / 5), attributeList.size()).clear();
        for (String name : armorNames) {
            armorHash.clear();
            for (String e : attributeList) {
                armorHash.put(e, armorValues.get(count));
                count++;
            }
            singleArmorSetHash.put(name, armorHash);
        }
    }

    private void extractAttributeNames(Elements armorTable) {
        // Attribute names
        Elements attributeNames = armorTable.select("h4").select("img");
        for (Element list : attributeNames) {
            attributeList.add(list.attr("alt"));
        }
    }

    private void extractArmorValues(Elements armorTable, ArrayList<String> armorNames) {
        // Armor Values

        for (Element e : armorTable.select("td"))
            if (e.attr("style").contains("center")) {
                if (e.select("img").attr("alt").contains("gem_level")) {
                    if (e.select("img").size() > 1) {
                        StringBuilder tempArmor;
                        tempArmor = new StringBuilder();
                        for (Element img : e.select("img")) {
                            String value = img.attr("alt");
                            tempArmor.append(value.substring(value.length() - 1));
                        }
                        armorValues.add(Integer.valueOf(String.valueOf(tempArmor)));
                    }
                    else {
                        String tempArmor = e.select("img").attr("alt");

                        tempArmor = tempArmor.substring(tempArmor.length() - 1);
                        armorValues.add(Integer.valueOf(tempArmor));
                    }
                } else {
                    if (!armorNames.contains(e.text()) && !nullGear.contains(e.text())) {
                        String tempArmor = e.text();
                        if (e.text().equals("-")) {
                            tempArmor = "0";

                        }
                        armorValues.add(Integer.valueOf(tempArmor));
                    }
                }
            }
    }

    private ArrayList<String> extractArmorList(Elements armorTable) {
        //Armor listing
        ArrayList<String> armorNames = new ArrayList<>();
        for (Element e : armorTable.select("td")) {
            armorNames.add(e.select("h4").select("a").text());
        }
        armorNames.removeAll(Arrays.asList("", null));
        return armorNames;
    }

    private void extractSkills(Elements infobox) {
        // Skills
        for (Element e : infobox.select("td")) {
            String m = e.text().replaceAll("x.+$", "").trim();
            if (defaultSkills.contains(m)) {
                String l = e.text();
                String[] armorSkills = l.split("x");
            }
        }
    }
}