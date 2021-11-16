package com.company;

import java.io.FileWriter;
import java.io.IOException;

public class DatabaseOutput extends FileWriter {
    public DatabaseOutput(String fileName) throws IOException {
        super(fileName);
    }

    // Writes subject into a .csv file in the same format as BLAST's .csv format
    public void writeSubject(BLASTSubject subject) throws IOException {
        this.append(subject.Description + ",");
        this.append(subject.ScientificName + ",");
        this.append(subject.MaxScore + ",");
        this.append(subject.TotalScore + ",");
        this.append(subject.QueryCover + ",");
        this.append(subject.EValue + ",");
        this.append(subject.PercentIdentity + ",");
        this.append(subject.AccessionLength + ",");
        this.append(subject.AccessionNumber + ",");
        this.append("\n");
    }

    // Writes subject sequence into a .fasta file in FASTA format
    public void writeFasta(BLASTSubject subject) throws IOException {
        this.append(">" + subject.AccessionNumber + /*" " + subject.Description + */"\n");
        this.append(subject.Sequence + "\n");
    }
}