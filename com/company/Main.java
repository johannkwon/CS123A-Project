package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        BLASTDatabase myDatabase = new BLASTDatabase();
        Sequences seq = new Sequences();

        myDatabase.FillDatabase("D:/VSCODE WORKSPACES/RedwoodProject/com/company/SSBH6ZAK016-Alignment-Descriptions.csv");

        for (int i = 0; i < myDatabase.size(); i++) {
			System.out.println(i);
            seq.insertInto(myDatabase.get(i));
        }

        // prints out sequences (should be 90, last time i checked)
		for(BLASTSubject value : seq.getSequences().values()) {
			String name = value.Description;
			System.out.println(name);
		}

		System.out.println(seq.getSequences().size());
    }
}
