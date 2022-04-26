/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

/**
 *
 * @author technowings
 */
public class WordModel {
    private String tid;
    private String word;
    private String tfidf;

    /**
     * @return the tid
     */
    public String getTid() {
        return tid;
    }

    /**
     * @param tid the tid to set
     */
    public void setTid(String tid) {
        this.tid = tid;
    }

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * @return the tfidf
     */
    public String getTfidf() {
        return tfidf;
    }

    /**
     * @param tfidf the tfidf to set
     */
    public void setTfidf(String tfidf) {
        this.tfidf = tfidf;
    }
}
