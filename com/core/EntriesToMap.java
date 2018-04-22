package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class EntriesToMap {

    public HashMap<String, LinkedList<String>> entriesAsMap () {
        // csv = comma seperated value
        String fileName = "C:\\Users\\FME\\Desktop\\Sheet1.csv";

        String line = "";
        HashMap<String, String> map = new HashMap<String, String> ();


        try {

            BufferedReader br = new BufferedReader(new FileReader(fileName));

            while ((line = br.readLine()) != null) {

                //split by ; -> e.g. "k : können"
                String[] contentArray = line.split(";");

                map.put(contentArray[0].toLowerCase(), contentArray[1].toLowerCase());
//                  System.out.println(contentArray[0] + " : " + contentArray[1]);
            }
            br.close();

            /* add multiple entries
             * re-copies: "Unterschied" is saved under "uts1" aswell as "uti"
             * create map that has all variants of "Unterschied" as a LinkedList
             */
            HashMap<String, LinkedList<String>> newMap = new HashMap<String, LinkedList<String>>();

            HashMap<String, LinkedList<String>> toReturn = new HashMap<String, LinkedList<String>>();

            var l = new LinkedList<String>();
            l.add("t");
            toReturn.put("test", l);

            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (newMap.containsKey(entry.getValue())) {
                    LinkedList<String> list = newMap.get(entry.getValue());
                    list.add(entry.getKey());
                    newMap.put(entry.getValue(), list);
                }
                else {
                    LinkedList<String> list = new LinkedList<String>();
                    list.add(entry.getKey());
                    newMap.put(entry.getValue(), list);
                }

            }

//              for (Map.Entry<String, LinkedList<String>> entry : newMap.entrySet()) {
////                    if (entry.getValue().size() > 1) {
//                      LinkedList<String> list = entry.getValue();
//                      String tempString = "";
//                      for (String string : list) {
//                          tempString += string + " - ";
//                      }
//                      count++;
//                      System.out.println(entry.getKey() + " ## " + tempString);
////                    }
//
//              }
//              System.out.println();
//              System.out.println();
//              System.out.println(count);

            return toReturn;


        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
}