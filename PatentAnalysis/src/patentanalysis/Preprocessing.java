/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package patentanalysis;

import com.util.ServerConstants;
import java.util.HashMap;

/**
 *
 * @author technowings-pc
 */
public class Preprocessing {

    public static void main(String[] args) {
        String a = "Why I support Narendra Modi” by @AmritHallan ...so #very true... @ndtv watch & learn how to write op-eds... https://t.co/kd9HOpxJ9l";
        System.out.println(a);
        String s = removeAtHash(a);
        System.out.println("s");
        String s1 = removeURL(s);
        System.out.println(" " + s1);
         String s2 = removeDoubleQuote(s1);
        System.out.println(" " + s2);
    }
    public static HashMap stopWordsMap=new HashMap();
    static{
        stopWordsMap=FileHelper.loadFileMap(ServerConstants.STOPWORDS_FILE);
        
    }
  public static String removeStopWords(String data) {
        String[] sp = data.split(" ");
        StringBuffer finalString = new StringBuffer();
        for (int i = 0; i < sp.length; i++) {
            String string = sp[i].toLowerCase();
            if(stopWordsMap.get(string)==null){
                finalString.append(sp[i]+" ");
            }
        }
        return finalString.toString();
    }
    public static String removeAtHash(String data) {
        String[] sp = data.split(" ");
        StringBuffer finalString = new StringBuffer();
        for (int i = 0; i < sp.length; i++) {
            String string = sp[i];

            if (!(string.contains("@")||string.contains("#"))) {
                finalString.append(string + " ");
            }
        }
        return finalString.toString();
    }

    public static String removeURL(String data) {
        String[] sp = data.split(" ");
        StringBuffer finalString = new StringBuffer();
        for (int i = 0; i < sp.length; i++) {
            String string = sp[i];

            if (!(string.contains("http")||string.contains("https"))) {
                finalString.append(string + " ");
            }
        }
        return finalString.toString();
    }

    public static String removeDoubleQuote(String data) {
        data = data.replaceAll("\"", "");
        data = data.replaceAll("”", "");
          data = data.replaceAll("\\.\\.\\.", "\\. ");
        return data;
    }
      public static String removeSpecial(String data) {
        String[] sp = data.split(" ");
        StringBuffer finalString = new StringBuffer();
        for (int i = 0; i < sp.length; i++) {
            String string = sp[i];

            if (!(string.contains("@")||string.contains("#"))) {
                finalString.append(string + " ");
            }
        }
        return finalString.toString();
    }
   
}
