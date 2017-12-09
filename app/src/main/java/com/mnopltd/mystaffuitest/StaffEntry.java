package com.mnopltd.mystaffuitest;

import android.util.Log;

/**
 * Created by neal on 11/8/2017.
 */

public class StaffEntry
{
    private char Note;
    private char SharpFlat;
    private int  Octave;
    private double  NormalFreq;
    private char  ViewType;
    private int  LinePos;
    private int  TopPos;
    private int  BottomPos;
    private int  PianoKey;
    StaffEntry(char n, char s, int oct, double fr, char viewtype, int lpos, int tpos, int bpos, int pKey)
    {
        Note = n; SharpFlat = s; Octave = oct; NormalFreq = fr; ViewType = viewtype;
        LinePos = lpos; TopPos = tpos; BottomPos = bpos; PianoKey = pKey;
    }


    public char getNote() 		{ return Note; 	}
    public char getSharpFlat() 	{ return SharpFlat; }
    public int getOctave() 	{ return Octave; }
    public double getNormalFreq()	{ return NormalFreq; }
    public char getViewType()	{ return ViewType; }
    public int getLinePos() 	{ return LinePos; }
    public int getTopPos() 	{ return TopPos; }
    public int getBottomPos() 	{ return BottomPos; }
    public int getPianoKey() 	{ return PianoKey; }

    public void printStaffEntry(int i)
    {
        Log.i("MyDebug", "Row: " + i  	+ " Note:"  	+ this.Note  	+ this.SharpFlat
                        + " Octave:" 	+ this.Octave 	+ " Freq:"         + this.NormalFreq
                        + " ViewType: " + this.ViewType + " LinePos: " 	   + this.LinePos
                        + " TopPos: " 	+ this.TopPos 	+ " BottomPos: "   + this.BottomPos
                        + " PianoKey: " + this.PianoKey);
    }
}
