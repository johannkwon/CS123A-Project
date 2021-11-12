package com.company;

import javax.management.Query;

public class BLASTSubject{
    String Description;
    String ScientificName;
    String MaxScore;
    String TotalScore;
    String QueryCover;
    String EValue;
    String PercentIdentity;
    String AccessionLength;
    String AccessionNumber;
    String Sequence;

    public BLASTSubject() {
    }

    public void giveSequence(String seq) {
        Sequence = seq;
    }

    public void print() {
        System.out.print("Description: " + this.Description + "\n");
        System.out.print("Scientific Name: " + this.ScientificName + "\n");
        System.out.print("Accession Number: " + this.AccessionNumber + "\n");
    }



}
