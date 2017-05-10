package com.decker.javaProgramming.exam.fin.pre.y2015.q4;


public class Model {

    // thePhrase is the target string to match
    private final String thePhrase = "huff and puff!";
    public final int theLength = thePhrase.length();
    private StringBuffer buffer;

    public Model() {
        this.buffer = new StringBuffer();
    }

    public void add(String s) {
        if (!isMatched())
            this.buffer.append(s);
    }

    public void deleteLastChar() {
        if (this.buffer.length() > 0)
            this.buffer.deleteCharAt(this.buffer.length() - 1);
    }

    public boolean isEmpty() {
        return this.buffer.length() == 0;
    }

    public String getPhrase() {
        String s = buffer.toString();
        String[] words = s.split("\\s+");
        return String.join(" ", words).toLowerCase();
    }

    /**
     * returns the number of characters in the buffer
     * which match (case incensitively) the string thePhrase
     * (counting left to right); the bufferes chars can be
     * read from the string returned by getPhrase() (it's all
     * low case, only single white spaces between the words)
     */
    public int matchingLength() {
        // TODO provide the implementation as solution of Q4.1a

        return this.buffer.toString().toLowerCase().replaceAll(thePhrase, "").length();
    }

    public boolean isMatched() {
        return thePhrase.length() == matchingLength();
    }

    @Override
    public String toString() {
        return getPhrase();
    }
}
