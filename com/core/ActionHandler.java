package core;

import java.util.HashMap;
import java.util.LinkedList;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class ActionHandler implements NativeKeyListener {

    private String textValue = "output";

    /* t = String that is added up by pressing Keys
     * multipleWords = lengthy String of multiple t's
     * wordCount = max length of t's in multipleWords; breaks at 3
     */
    private String t = "";
    private String multipleWords = "";
    private short wordCount = 0;


    /*entries in Sheet1.csv*/
    static HashMap<String, LinkedList<String>> entryMap = new EntriesToMap().entriesAsMap();

    public String getTextValue () {
        return textValue;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {


        System.out.println("Key Pressed: " + Character.toString(e.getKeyChar()) + " t = " + t );

        t = t +  Character.toString(e.getKeyChar());



        if (entryMap.containsKey(t)) {
            var list = entryMap.get(t);
            String toDisplay = "";
            for (String s : list) {
                toDisplay = toDisplay + s + " ; ";
            }

            System.out.println(toDisplay);
            AOTMain.text.setText(toDisplay);
//              t = "";

        }

        if (e.getKeyChar() == ' ') {

            if (multipleWords.equals("")) {
                multipleWords = t.trim();
            }
            else {
                multipleWords = multipleWords + " " + t.trim();
            }

            wordCount++;

            if (entryMap.containsKey(multipleWords) && wordCount > 1) {
                var list = entryMap.get(multipleWords);
                String toDisplay = "";
                for (String s : list) {
                    toDisplay = toDisplay + s + " ; ";
                }

                wordCount = 0;
                multipleWords = "";
                AOTMain.text.setText(toDisplay);
            }
            if (wordCount > 3) {
                multipleWords = "";
                wordCount = 0;
            }
            t = "";
        }
//          if (  e.getCharacter().equals("a")) {
//              e.consume();
//              textTarget.clear();
//              textTarget.setText("success");
//          }
//          else if (e.getCharacter().equals("b")) {
//              textTarget.clear();
//              textTarget.setText("success Aswell!");
//          }



    }


}