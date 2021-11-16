package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //Path to CSV containing BLAST hits
        String filename = "";

        //Path to empty CSV for outputting results
        String outputFile = "";

        //Create BlastDatabase for storing subjects from initial BLAST search
        BLASTDatabase myDatabase = new BLASTDatabase();
        Sequences seq = new Sequences();

        //Fill BlastDatabase from CSV
        myDatabase.FillDatabase(filename);

        //Insert all subjects into HashMap to exclude dpulicates
        for (int i = 0; i < myDatabase.size(); i++) {
			System.out.println(i);
            seq.insertInto(myDatabase.get(i));
        }

        //Create instance of DatabaseOutput to store all Subjects with duplicates removed
        DatabaseOutput output = new DatabaseOutput(outputFile);

        //Print all non-dpulicate results to console and write to output CSV
		for(BLASTSubject value : seq.getSequences().values()) {
			String name = value.Description;
			System.out.println(name);

			if (value.isChloroplast()) {
                output.writeSubject(value);
            }
		}

        output.close();

		//Print number of non-duplicate entries to console
		System.out.println(seq.getSequences().size());
    }
}