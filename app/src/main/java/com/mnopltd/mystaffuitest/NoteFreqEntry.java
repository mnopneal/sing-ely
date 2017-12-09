package com.mnopltd.mystaffuitest;

import android.util.Log;

/**
 * Created by neal on 11/8/2017.
 */


public class NoteFreqEntry
{
    private char Name1;
    private char SharpFlat1;
    private char Name2;
    private char SharpFlat2;
    private int  Octave;
    private double  Freq;
    private int PianoKey;

    NoteFreqEntry(char n1, char s1, char n2, char s2, int oct, double fr, int pKey)
    {
        Name1 = n1;  SharpFlat1 = s1; Name2 = n2;  SharpFlat2 = s2; Octave = oct; Freq = fr; PianoKey = pKey;
    }


    public char getName1() 	{ return Name1; }
    public char getSharpFlat1() 	{ return SharpFlat1; }
    public char getName2() 	{ return Name2; }
    public char getSharpFlat2() 	{ return SharpFlat2; }
    public int getOctave() 	{ return Octave; }
    public double getFreq()	{ return Freq; }
    public int getPianoKey()	{ return PianoKey; }

    public void printNoteFreqEntry(int i)
    {
        Log.i("MyDebug", "Row: " + i  	+ " Name1:"  + this.Name1  + " Sharp/Flat:"   + this.SharpFlat1
                        + " Name2:"  + this.Name2  + " Sharp/Flat:"   + this.SharpFlat2
                        + " Octave:" + this.Octave + " Freq:"         + this.Freq + " Piano Key: " + PianoKey);
    }
}
