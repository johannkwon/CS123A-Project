package com.company;

import java.util.HashMap;

public class Sequences {

    private HashMap<String, BLASTSubject> sequenceHashMap = new HashMap<String, BLASTSubject>();

    public Sequences() {
    }

    // Inserts into sequenceHashMap using the sequence as a key and the BLASTSubject as the value
    public void insertInto(BLASTSubject nucleotide) {
        try
        {
            String page = NucleotidePageFetch.getNucleotideGPPage(nucleotide.AccessionNumber);
            nucleotide.Sequence = parseSequence(page);
            sequenceHashMap.put(nucleotide.Sequence, nucleotide);
        }
        catch (Exception x)
        {
            NucleotidePageFetch.sop("STRESS: " + x.getMessage());
            x.printStackTrace();
        }
    }

    // Isolates the sequence (key) by removing "ORIGIN," 0-9, white spaces and returns the sequence as a string
    public String parseSequence(String page) {
        int origin = page.indexOf("ORIGIN");
        int end = page.indexOf("//");
        String sequence = page.substring(origin, end).replaceAll("[(ORIGIN)0-9\\s]", "");

        return sequence;
    }

    public HashMap<String, BLASTSubject> getSequences() {
        return this.sequenceHashMap;
    }

    
}
