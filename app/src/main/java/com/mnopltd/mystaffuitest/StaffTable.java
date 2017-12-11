package com.mnopltd.mystaffuitest;

import android.util.Log;

/**
 * Created by neal on 11/8/2017.
 */
public class StaffTable
{
    static int yChunk;		/* Size of one note */
    static int halfChunk;		/* Size of half of one note */
    static int currentYPos;

    static int lastPianoNote;  /* saved when either search method is called for later recall */
    static String lastNote;
    static int lastFreq;
    static int lastFudgedY;
    static int lastLinePos;
    static public StaffEntry[] BaseStaff = new StaffEntry[17];  /* How to make size intelligent? */
	/*HUH? or at lease define a literal constant at the top of the class like a C language #DEFINE */

    StaffTable() /* Actual constructor for fixed table - I don't seem to know how to invoke this from the
                           main class */
    {
    }

    static public void BuildStaffTableInternally(char whichStaff, int transpose, int ySize, String sharpFlatList )
    {



        yChunk = ySize / 18;		/* So, if the screen is 2000 pixels high, we figure 1/18th of that; (111) */
        halfChunk = yChunk / 2; 	/*      Then we take half of that.   (55) 				  */
        currentYPos = 0 + halfChunk + (halfChunk / 2); /* Top-most half-line begins at half of its size plus a fudge factor */
        if (whichStaff == 'b')
        {
		/* Bass Staff */
            currentYPos = AssembleStaff( 0, 'E', 4, '-',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff( 1, 'D', 4, ' ',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff( 2, 'C', 4, '-',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff( 3, 'B', 3, ' ',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff( 4, 'A', 3, '_',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff( 5, 'G', 3, ' ',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff( 6, 'F', 3, '_',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff( 7, 'E', 3, ' ',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff( 8, 'D', 3, '_',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff( 9, 'C', 3, ' ',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff(10, 'B', 2, '_',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff(11, 'A', 2, ' ',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff(12, 'G', 2, '_',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff(13, 'F', 2, ' ',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff(14, 'E', 2, '-',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff(15, 'D', 2, ' ',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff(16, 'C', 2, '-',  transpose, sharpFlatList, currentYPos, yChunk );
        }
        else
        {
		/* Treble Staff */
            currentYPos = AssembleStaff( 0, 'C', 6, '-',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff( 1, 'B', 5, ' ',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff( 2, 'A', 5, '-',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff( 3, 'G', 5, ' ',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff( 4, 'F', 5, '_',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff( 5, 'E', 5, ' ',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff( 6, 'D', 5, '_',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff( 7, 'C', 5, ' ',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff( 8, 'B', 4, '_',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff( 9, 'A', 4, ' ',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff(10, 'G', 4, '_',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff(11, 'F', 4, ' ',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff(12, 'E', 4, '_',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff(13, 'D', 4, ' ',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff(14, 'C', 4, '-',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff(15, 'B', 3, ' ',  transpose, sharpFlatList, currentYPos, yChunk );
            currentYPos = AssembleStaff(16, 'A', 3, '-',  transpose, sharpFlatList, currentYPos, yChunk );
        }
    }
    static public int AssembleStaff(int row, char note, int oct, char vtype,
                                    int transpose, String sharpFlatList, int currentYPos, int yChunk )
    {
        char sharpFlat; double freq; int vpos; int pKey;
        int halfChunk;		/* Size of half of one note */

	/* Figure out if sharp or flat */
        sharpFlat = FigureOutSharpFlat(note, sharpFlatList);

	/* Look up Frequency */
        freq = NoteFreqTable.SearchNoteFreqTableForFreq( note, sharpFlat, oct, transpose);
        pKey = NoteFreqTable.SearchNoteFreqTableForPianoKey( note, sharpFlat, oct, transpose);
        if (note == 'B' && sharpFlat == '#' ) pKey = pKey + 12;  /* B# is really C natural in next octave. */
        if (note == 'C' && sharpFlat == 'b' && pKey > 12 ) pKey = pKey - 12;  /* Cb# is really B natural in prior octave. */

	/* Figure out Vertical Position */
        vpos = row * yChunk;
        halfChunk = yChunk / 2 - 5; 	/*      Then we take half of that.   (55) Minus a fudge factor	  */
        BaseStaff[row]  = new StaffEntry( note, sharpFlat, oct, freq, vtype,
                currentYPos, currentYPos - halfChunk, currentYPos + halfChunk, pKey);

        return (currentYPos + yChunk);	/* update for the next one. */
    }

    static public void printStaffTable()
    {
        int idx;
        System.out.println("BaseStaff Table Contents...");
        for(idx=0; idx < 17; idx++)
            BaseStaff[idx].printStaffEntry(idx);
    }

    static public void displayStaffTable() /* Actually put the staff on the screen */
    {
        int idx;
        String toShow;
        System.out.println("Display BaseStaff Table ...");
        for(idx=0; idx < 17; idx++) {
            toShow = "";
            if (MainActivity.getShowNotes()) toShow = toShow + String.valueOf(BaseStaff[idx].getNote()) + String.valueOf(BaseStaff[idx].getOctave())
                    + String.valueOf(BaseStaff[idx].getSharpFlat()) ;
            if (MainActivity.getShowPianoKeys()) toShow = toShow + "-" + String.valueOf(BaseStaff[idx].getPianoKey());
            if (MainActivity.getShowFrequencies()) toShow = toShow + "-" + String.valueOf((int)BaseStaff[idx].getNormalFreq()) + "hz";
            MainActivity.DisplayStaffLine(BaseStaff[idx].getLinePos(), BaseStaff[idx].getViewType(),
                    BaseStaff[idx].getSharpFlat(), halfChunk, toShow, idx);
        }
    }

    static public void playStaff() /* Actually put the staff on the screen */
    {
        int idx;
        String toShow;
        System.out.println("Display BaseStaff Table ...");
        for(idx=14; idx >= 0; idx--) {
            MainActivity.playSound(BaseStaff[idx].getPianoKey());
            MainActivity.mySleep(1);

        }
    }

    static public char FigureOutSharpFlat(char note, String sharpFlatList)
    {
	/* So, sharpFlatList looks like "C#,F#", or it could be empty. There is no protection against mangled
 * 	lists, so "CF" will blow this up */
        int pos;
        pos = sharpFlatList.indexOf(note);
        if (pos < 0 )
            return ' ';
        else
            return sharpFlatList.charAt(pos + 1);
    }

    static public double SearchStaffTableForFreq(int vpos )
    {
        int idx;
        int useIdx;
        Log.i("MyDebug", "Searching for: " + vpos );
        for(idx=0; idx < 17; idx++)
        {
            if (vpos > BaseStaff[idx].getTopPos() && vpos < BaseStaff[idx].getBottomPos() )
            {
			/* Then we have a match */
                Log.i("MyDebug", "  Got Match on row " + idx + "... ");
                lastPianoNote = BaseStaff[idx].getPianoKey();
                lastFreq = (int) BaseStaff[idx].getNormalFreq();
                return BaseStaff[idx].getNormalFreq();
            }
        }
        return 0;	/* Failure */
    }

    static public int SearchStaffTableForPianoKey(int vpos )
    {
        int idx;
        int useIdx;
        int myPos;
        /* myPos = vpos + (vpos / 10);  Original fudge appears to work on Android 4 and 5.  Is off for 7 */
        /* myPos = MainActivity.pxToDp(vpos)  / 2;  Well, that didn't really work either. */
        myPos = (int)(  (float) vpos * MainActivity.getfudgeFactor() );
        lastFudgedY = myPos;
        Log.e("MyDebug", "Searching for: " + vpos + "; myPos = " + myPos );
        for(idx=0; idx < 17; idx++)
        {
            if (myPos > BaseStaff[idx].getTopPos() && myPos < BaseStaff[idx].getBottomPos() )
            {
			/* Then we have a match */
                Log.e("MyDebug", "  Got Match on row " + idx + "... ");
                lastPianoNote = BaseStaff[idx].getPianoKey();
                lastNote = String.valueOf(BaseStaff[idx].getNote()) + String.valueOf(BaseStaff[idx].getOctave())
                        + String.valueOf(BaseStaff[idx].getSharpFlat()) ;
                lastFreq = (int) BaseStaff[idx].getNormalFreq();
                lastLinePos = BaseStaff[idx].getLinePos();
                return BaseStaff[idx].getPianoKey();
            }
        }
        return 0;	/* Failure */
    }
    static public String getLastNote() {
        return lastNote;
    }
    static public int getLastPianoNote() {
        return lastPianoNote;
    }
    static public int getLastFreq() {
        return lastFreq;
    }
    static public int getLastFudgedY() {
        return lastFudgedY;
    }
    static public int gethalfChunk() {
        return halfChunk;
    }
    static public int getLastLinePos() {
        return lastLinePos;
    }
}

