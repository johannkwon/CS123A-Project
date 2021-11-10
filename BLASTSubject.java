package com.company;

public class BLASTSubject{
    String AccessionNumber;
    int MaxScore;
    double PercentIdentity;
    int AccessionLength;
    float QueryCover;
    double EValue;

    public BLASTSubject(String AccessionNumber, int MaxScore, double PercentIdentity,
                        int AccessionLength, float QueryCover, double EValue) {

        this.AccessionLength = AccessionLength;
        this.AccessionNumber = AccessionNumber;
        this.QueryCover = QueryCover;
        this.EValue = EValue;
        this.PercentIdentity = PercentIdentity;
    }



}
