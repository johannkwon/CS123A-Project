package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        BLASTDatabase myDatabase = new BLASTDatabase();

        myDatabase.FillDatabase("/Users/Rajiv/IdeaProjects/RedwoodProject/src/com/company/SSBH6ZAK016-Alignment-Descriptions.csv");

        for (int i = 0; i < myDatabase.size(); i++) {
            myDatabase.get(i).print();
            System.out.print("\n");
        }
    }
}
