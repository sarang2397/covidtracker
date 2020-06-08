package com.example.covid19tracker.ui.country;

public class CovidCountry
{

    String mCovidCountry,mCases;

    public CovidCountry(String mCovidCountry, String mCases)
    {
        this.mCovidCountry = mCovidCountry;
        this.mCases = mCases;
    }

    public String getmCovidCountry()
    {
        return mCovidCountry;
    }

    public String getmCases() {
        return mCases;
    }
}
