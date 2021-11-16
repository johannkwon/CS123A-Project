package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BLASTDatabase extends ArrayList<BLASTSubject> {

    public BLASTDatabase() {}

    public void FillDatabase(String filename) throws IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        String[] Values = new String[9];

        

        String current = br.readLine();

        // Fills in the BLASTSubject attributes
        while(true) {
            BLASTSubject CurrentSubject = new BLASTSubject();
            
            current = br.readLine();

            if (current == null) {
                break;
            }

            //System.out.print(current + "\n");

            Values = current.split(",");

            //System.out.print(Arrays.toString(Values) + "\n");


            CurrentSubject.Description = Values[0];
            CurrentSubject.ScientificName = Values[1];
            CurrentSubject.MaxScore = Values[2];
            CurrentSubject.TotalScore = Values[3];
            CurrentSubject.QueryCover = Values[4];
            CurrentSubject.EValue = Values[5];
            CurrentSubject.PercentIdentity = Values[6];
            CurrentSubject.AccessionLength = Values[7];
            CurrentSubject.AccessionNumber = Values[8];


            this.add(CurrentSubject);
        }

        br.close();
    }
}
