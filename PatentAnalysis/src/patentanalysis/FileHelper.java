package patentanalysis;

import com.util.StringHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileHelper {

    public static File[] getFileList(String dirPath) {
        File f = new File(dirPath);
        try {
            System.out.println("Canonical Path " + f.getCanonicalPath());
        } catch (IOException ex) {
            Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        FilenameFilter textFilter = new FilenameFilter() {

            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (lowercaseName.endsWith(".txt") ) {
                    return true;
                } else {
                    return false;
                }
            }
        };


        File[] a = f.listFiles(textFilter);
        if(a!=null)
        System.out.println(" Got Files "+a.length);
        return a;
    }

    public static ArrayList<ArrayList<String[]>> getFilesCombo(String DIR_PATH) {
        File[] files = getFileList(DIR_PATH);
        String combo = "";
        ArrayList<ArrayList<String[]>> all = new ArrayList<ArrayList<String[]>>();
        for (int i = 0; i < files.length; i++) {
//            if (files[i].getName().indexOf("comments") != -1) {
                ArrayList<String[]> data = parseFile(files[i].getAbsolutePath());
                all.add(data);
                System.out.println("Got Data "+data);
//            }
        }
        return all;
    }

    public static void main(String[] args) {
//    parseFile("D:\\work\\tweets\\modi.txt");
 
    }

    public static HashMap loadFileMap(String fileName) {
        File f = new File(fileName);
        System.out.println(f.getAbsolutePath());
        StringBuffer arr = readFileContent(f.getAbsolutePath());
        System.out.println(" arr " + arr.toString());
        String[] tokens = arr.toString().split("\n");
        HashMap map = new HashMap();
        for (int i = 0; i < tokens.length; i++) {
            String string = tokens[i];
            map.put(string.trim().toLowerCase(), 1);
        }
        System.out.println("map " + map);
        return map;
    }

    public static HashMap loadAffineMap(String fileName) {
        File f = new File(fileName);
        System.out.println(f.getAbsolutePath());
        StringBuffer arr = readFileContent(f.getAbsolutePath());
        System.out.println(" arr " + arr.toString());
        String[] tokens = arr.toString().split("\n");
        HashMap map = new HashMap();
        for (int i = 0; i < tokens.length; i++) {
            String[] string = tokens[i].split("\\s+");
            if (string.length >= 2) {
                int weiatage = 0;
                String key = string[0];

                weiatage = StringHelper.n2i(string[1]);

                map.put(key.trim().toLowerCase(), weiatage);
            }
        }
        System.out.println("map " + map);
        return map;
    }

    public static ArrayList<String[]> parseFile(String fileName) {
        ArrayList<String[]> arr = new ArrayList<String[]>();
        StringBuffer sb = readFileContent(fileName);
        String[] tokens = sb.toString().split("\\|1234\\|");
        for (int i = 0; i < tokens.length; i++) {
            String string = tokens[i];
            String[] keyTweet = string.split("\\|\\|");
            if (keyTweet.length == 2) {
                String keyword = keyTweet[0];
                String tweet = keyTweet[1];
                arr.add(new String[]{keyword, tweet});
//                System.out.println("keyword " + keyword);
//                System.out.println("tweet " + tweet);
            }
                        if (keyTweet.length == 3) {
                String keyword = keyTweet[0];
                String tweet = keyTweet[1];
                arr.add(new String[]{keyword, tweet,keyTweet[2]});
//                System.out.println("keyword " + keyword);
//                System.out.println("tweet " + tweet);
            }

        }

        return arr;
    }

    public static StringBuffer readFileContent(String filepath) {

        InputStream is = null;
        int i;
        char c;
        StringBuffer sb = new StringBuffer();
        try {
            // new input stream created
            is = new FileInputStream(filepath);

            System.out.println("Characters printed:");
            byte[] b = new byte[1024];
            // reads till the end of the stream
            while ((i = is.read(b)) != -1) {
                // converts integer to character

                String s = new String(b);
                sb.append(s.trim());
            }
        } catch (Exception e) {

            // if any I/O error occurs
            e.printStackTrace();
        } finally {

            // releases system resources associated with this stream
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return sb;
    }
}
