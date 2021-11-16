package com.company;

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


    //BlastSubject ctor
    public BLASTSubject() {
    }

    //Returns nucleotide sequence of BlastSUbject
    public void giveSequence(String seq) {
        Sequence = seq;
    }


    //Prints description, scientific name, and acccession number of a BlastSubject
    public void print() {
        System.out.print("Description: " + this.Description + "\n");
        System.out.print("Scientific Name: " + this.ScientificName + "\n");
        System.out.print("Accession Number: " + this.AccessionNumber + "\n");
    }



    //Filtering function to check is subject is of the desired genus
    public boolean genusIs(String genus) {
        String fullname = this.ScientificName;
        String[] parsed = fullname.split(" ");
        if (parsed[0].equals(genus)) {
            return true;
        }
        else {
            return false;
        }
    }


    //Filtering function to check if subject is chloroplast genome
    public boolean isChloroplast() {
        if (this.Description.contains("chloroplast")) {
            return true;
        }

        else {
            return false;
        }

    }


}