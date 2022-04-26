/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;



import org.apache.commons.io.FileUtils;
import org.apache.commons.fileupload.FileItem;
import patentanalysis.Preprocessing;
import patentanalysis.TfIdfHelper;
import sun.applet.Main;

public class ConnectionManager extends DBUtils {

    public static HashMap hm = null;
    public static ArrayList al = null;
    public static FileWriter writer = null;
    static Random r = new Random();

    public static Connection getDBConnection() {
        Connection conn = null;
        try {
            Class.forName(ServerConstants.db_driver);
            conn = DriverManager.getConnection(ServerConstants.db_url,
                    ServerConstants.db_user, ServerConstants.db_pwd);
            System.out.println("Got Connection" + ServerConstants.db_url);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Please start the mysql Service from XAMPP Console.\n"
                    + ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static String insertUser(HashMap parameters) {
        String success = "";
        String name = StringHelper.n2s(parameters.get("name"));
        String phone = StringHelper.n2s(parameters.get("phone"));

        String Address = StringHelper.n2s(parameters.get("address"));
        String uname = StringHelper.n2s(parameters.get("uname"));
        String pwd = StringHelper.n2s(parameters.get("pwd"));

        // uid, uname, pwd, name, age, gender, phone
        String query = "insert into useraccount (uname, pwd, name, address,phone) values(?,?,?,?,?)";
        int a = DBUtils.executeUpdate(query, uname, pwd, name, Address,
                phone);

        if (a > 0) {
            success = "1";
        } else {
            success = "N";
        }
        return success;
    }

    public static UserModel checkLogin(HashMap parameters) {

        String uname = StringHelper.n2s(parameters.get("uid"));
        String password = StringHelper.n2s(parameters.get("pwd"));
        return checkLoginDB(uname, password);
    }

    public static List getAllPatent() {
        return DBUtils.getBeanList(PatentModel.class, "SELECT * FROM `dataset`;");
    }

    public static HashMap getTFIDF(String args) {
        HashMap map = new HashMap();


        String data = args;
        data = Preprocessing.removeStopWords(args);
        data = Preprocessing.removeAtHash(args);
        data = data.trim();
        String[] textArray = data.split(" ");
        List<String> doc1 = Arrays.asList(textArray);

        List<List<String>> documents = Arrays.asList(doc1);
        for (int j = 0; j < textArray.length; j++) {
            String term = textArray[j];
            TfIdfHelper calculator = new TfIdfHelper();
            double tfidf = calculator.getTF(doc1, term);
            map.put(term, tfidf);

        }

        System.out.println("map " + map);
        return map;
    }

    public static void getTFIDF(List list) {
        HashMap map = new HashMap();
        PatentModel pm = null;
        double si = StringHelper.n2d(list.size());
        for (int i = 0; i < list.size(); i++) {
            pm = (PatentModel) list.get(i);

            String data = pm.getAbs();
            data = Preprocessing.removeStopWords(data);
            data = Preprocessing.removeAtHash(data);
            data = data.trim();
            String[] textArray = data.split(" ");
            List<String> doc1 = Arrays.asList(textArray);

            List<List<String>> documents = Arrays.asList(doc1);
            for (int j = 0; j < textArray.length; j++) {
                String term = textArray[j];
                TfIdfHelper calculator = new TfIdfHelper();
                double tfidf = calculator.getTF(doc1, term);
                map.put(term, tfidf);

            }

        }
        System.out.println("map " + map);
    }

    public static void main(String[] args) {
        getTFIDF(getAllPatent());
    }

    public static HashMap getAllTFIDF() {
        String query = "SELECT * FROM wordtf";
        List list = DBUtils.getBeanList(WordModel.class, query);
        HashMap map = new HashMap();
        if (list.size() > 0) {
            for (Iterator it = list.iterator(); it.hasNext();) {
                WordModel wordModel = (WordModel) it.next();
                map.put(wordModel.getWord(), wordModel.getTfidf());
            }
        }
        return map;
    }

    public static HashMap findWordFrequency(HashMap userEnteredMap) {
        HashMap dataMap = getAllTFIDF();
        HashMap map = new HashMap();
        Set<String> keyset = userEnteredMap.keySet();
        for (String string : keyset) {
            if (dataMap.get(string) != null) {
                map.put(string, StringHelper.n2i(dataMap.get(string)));

                System.out.println("found Match for " + string + " Matching Frequency is " + dataMap.get(string));
            }
        }

        return map;
    }

    public static HashMap getMatchedPatentDetails(HashMap map) {
        Set<String> keyset = map.keySet();
        HashMap list = new HashMap();

        for (String keyWord : keyset) {
            String query = "SELECT * FROM `dataset` where abs like '%" + keyWord + "%' or domain like '%" + keyWord + "%' or title like '%" + keyWord + "%';";
            List l = DBUtils.getBeanList(PatentModel.class, query);
            if (l.size() > 0) {
                for (int j = 0; j < l.size(); j++) {
                    PatentModel pm = (PatentModel) l.get(j);
                    list.put(pm.getDid(), pm);
                }

            }
        }
        return list;

    }

    public static UserModel checkLoginDB(String uname, String password) {
        String query = "SELECT * FROM useraccount where uname = ? and pwd = ?";
        UserModel um = null;
        List list = DBUtils.getBeanList(UserModel.class, query, uname, password);

        if (list.size()
                > 0) {
            System.out.println(
                    "list " + list.get(0));
            um = (UserModel) list.get(0);
            // System.out.println("um "+um.getFlatid());
        }
        return um;
    }

    public static List getLog() {
        String query = "SELECT  ";

        List list = DBUtils.getBeanList(UserModel.class, query);
        System.out.println(
                "list " + list.size());

        return list;
    }

    public static void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
