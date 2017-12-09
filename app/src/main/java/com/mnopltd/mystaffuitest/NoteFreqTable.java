package com.mnopltd.mystaffuitest;

import android.util.Log;

/**
 * Created by neal on 11/8/2017.
 */


public class NoteFreqTable
{


    static public NoteFreqEntry[] BaseNoteFreq = new NoteFreqEntry[120];  /* How to make size intelligent? */
	/*HUH? or at lease define a literal constant at the top of the class like a C language #DEFINE */

    NoteFreqTable() /* Actual constructor for fixed table - I don't seem to know how to invoke this from the
                           main class */
    {
    }

    static public void BuildNoteFreqTableInternally() /* NOT constructor for fixed table; this is explicitly
                                                             called from the main method */
    {
        BaseNoteFreq[0]  = new NoteFreqEntry( 'C', ' ', ' ', ' ', 	0, 16.351,  0);
        BaseNoteFreq[1]  = new NoteFreqEntry( 'C', '#', 'D', 'b', 	0, 17.324,  0);
        BaseNoteFreq[2]  = new NoteFreqEntry( 'D', ' ', ' ', ' ', 	0, 18.354,  0);
        BaseNoteFreq[3]  = new NoteFreqEntry( 'D', '#', 'E', 'b', 	0, 19.445,  0);
        BaseNoteFreq[4]  = new NoteFreqEntry( 'E', ' ', ' ', ' ', 	0, 20.601,  0);
        BaseNoteFreq[5]  = new NoteFreqEntry( 'F', ' ', ' ', ' ', 	0, 21.827,  0);
        BaseNoteFreq[6]  = new NoteFreqEntry( 'F', '#', 'G', 'b', 	0, 23.124,  0);
        BaseNoteFreq[7]  = new NoteFreqEntry( 'G', ' ', ' ', ' ', 	0, 24.499,  0);
        BaseNoteFreq[8]  = new NoteFreqEntry( 'G', '#', 'A', 'b', 	0, 25.956,  0);
        BaseNoteFreq[9]  = new NoteFreqEntry( 'A', ' ', ' ', ' ', 	0, 27.5,    0);
        BaseNoteFreq[10]  = new NoteFreqEntry( 'A', '#', 'B', 'b', 	0, 29.135,  0);
        BaseNoteFreq[11]  = new NoteFreqEntry( 'B', ' ', ' ', ' ', 	0, 30.868,  0);
        BaseNoteFreq[12]  = new NoteFreqEntry( 'C', ' ', ' ', ' ', 	1, 32.703,  0);
        BaseNoteFreq[13]  = new NoteFreqEntry( 'C', '#', 'D', 'b', 	1, 34.648,  1);
        BaseNoteFreq[14]  = new NoteFreqEntry( 'D', ' ', ' ', ' ', 	1, 36.708,  2);
        BaseNoteFreq[15]  = new NoteFreqEntry( 'D', '#', 'E', 'b', 	1, 38.891,  3);
        BaseNoteFreq[16]  = new NoteFreqEntry( 'E', ' ', ' ', ' ', 	1, 41.203,  4);
        BaseNoteFreq[17]  = new NoteFreqEntry( 'F', ' ', ' ', ' ', 	1, 43.654,  5);
        BaseNoteFreq[18]  = new NoteFreqEntry( 'F', '#', 'G', 'b', 	1, 46.249,  6);
        BaseNoteFreq[19]  = new NoteFreqEntry( 'G', ' ', ' ', ' ', 	1, 48.999,  7);
        BaseNoteFreq[20]  = new NoteFreqEntry( 'G', '#', 'A', 'b', 	1, 51.913,  8);
        BaseNoteFreq[21]  = new NoteFreqEntry( 'A', ' ', ' ', ' ', 	1, 55,      9);
        BaseNoteFreq[22]  = new NoteFreqEntry( 'A', '#', 'B', 'b', 	1, 58.27,  10);
        BaseNoteFreq[23]  = new NoteFreqEntry( 'B', ' ', 'C', 'B', 	1, 61.735, 11);
        BaseNoteFreq[24]  = new NoteFreqEntry( 'C', ' ', 'B', '#', 	2, 65.406, 12);
        BaseNoteFreq[25]  = new NoteFreqEntry( 'C', '#', 'D', 'b', 	2, 69.296, 13);
        BaseNoteFreq[26]  = new NoteFreqEntry( 'D', ' ', ' ', ' ', 	2, 73.416, 14);
        BaseNoteFreq[27]  = new NoteFreqEntry( 'D', '#', 'E', 'b', 	2, 77.782, 15);
        BaseNoteFreq[28]  = new NoteFreqEntry( 'E', ' ', 'F', 'b', 	2, 82.407, 16);
        BaseNoteFreq[29]  = new NoteFreqEntry( 'F', ' ', 'E', '#', 	2, 87.307, 17);
        BaseNoteFreq[30]  = new NoteFreqEntry( 'F', '#', 'G', 'b', 	2, 92.499, 18);
        BaseNoteFreq[31]  = new NoteFreqEntry( 'G', ' ', ' ', ' ', 	2, 97.999, 19);
        BaseNoteFreq[32]  = new NoteFreqEntry( 'G', '#', 'A', 'b', 	2, 103.826, 20);
        BaseNoteFreq[33]  = new NoteFreqEntry( 'A', ' ', ' ', ' ', 	2, 110,     21);
        BaseNoteFreq[34]  = new NoteFreqEntry( 'A', '#', 'B', 'b', 	2, 116.541, 22);
        BaseNoteFreq[35]  = new NoteFreqEntry( 'B', ' ', 'C', 'B', 	2, 123.471, 23);
        BaseNoteFreq[36]  = new NoteFreqEntry( 'C', ' ', 'B', '#', 	3, 130.813, 24);
        BaseNoteFreq[37]  = new NoteFreqEntry( 'C', '#', 'D', 'b', 	3, 138.591, 25);
        BaseNoteFreq[38]  = new NoteFreqEntry( 'D', ' ', ' ', ' ', 	3, 146.832, 26);
        BaseNoteFreq[39]  = new NoteFreqEntry( 'D', '#', 'E', 'b', 	3, 155.563, 27);
        BaseNoteFreq[40]  = new NoteFreqEntry( 'E', ' ', 'F', 'b', 	3, 164.814, 28);
        BaseNoteFreq[41]  = new NoteFreqEntry( 'F', ' ', 'E', '#', 	3, 174.614, 29);
        BaseNoteFreq[42]  = new NoteFreqEntry( 'F', '#', 'G', 'b', 	3, 184.997, 30);
        BaseNoteFreq[43]  = new NoteFreqEntry( 'G', ' ', ' ', ' ', 	3, 195.998, 31);
        BaseNoteFreq[44]  = new NoteFreqEntry( 'G', '#', 'A', 'b', 	3, 207.652, 32);
        BaseNoteFreq[45]  = new NoteFreqEntry( 'A', ' ', ' ', ' ', 	3, 220,     33);
        BaseNoteFreq[46]  = new NoteFreqEntry( 'A', '#', 'B', 'b', 	3, 233.082, 34);
        BaseNoteFreq[47]  = new NoteFreqEntry( 'B', ' ', 'C', 'b', 	3, 246.942, 35);
        BaseNoteFreq[48]  = new NoteFreqEntry( 'C', ' ', 'B', '#', 	4, 261.626, 36);
        BaseNoteFreq[49]  = new NoteFreqEntry( 'C', '#', 'D', 'b', 	4, 277.183, 37);
        BaseNoteFreq[50]  = new NoteFreqEntry( 'D', ' ', ' ', ' ', 	4, 293.665, 38);
        BaseNoteFreq[51]  = new NoteFreqEntry( 'D', '#', 'E', 'b', 	4, 311.127, 39);
        BaseNoteFreq[52]  = new NoteFreqEntry( 'E', ' ', 'F', 'b', 	4, 329.628, 40);
        BaseNoteFreq[53]  = new NoteFreqEntry( 'F', ' ', 'E', '#', 	4, 349.228, 41);
        BaseNoteFreq[54]  = new NoteFreqEntry( 'F', '#', 'G', 'b', 	4, 369.994, 42);
        BaseNoteFreq[55]  = new NoteFreqEntry( 'G', ' ', ' ', ' ', 	4, 391.995, 43);
        BaseNoteFreq[56]  = new NoteFreqEntry( 'G', '#', 'A', 'b', 	4, 415.305, 44);
        BaseNoteFreq[57]  = new NoteFreqEntry( 'A', ' ', ' ', ' ', 	4, 440,     45);
        BaseNoteFreq[58]  = new NoteFreqEntry( 'A', '#', 'B', 'b', 	4, 466.164, 46);
        BaseNoteFreq[59]  = new NoteFreqEntry( 'B', ' ', 'C', 'b', 	4, 493.883, 47);
        BaseNoteFreq[60]  = new NoteFreqEntry( 'C', ' ', 'B', '#', 	5, 523.251, 48);
        BaseNoteFreq[61]  = new NoteFreqEntry( 'C', '#', 'D', 'b', 	5, 554.365, 49);
        BaseNoteFreq[62]  = new NoteFreqEntry( 'D', ' ', ' ', ' ', 	5, 587.33,  50);
        BaseNoteFreq[63]  = new NoteFreqEntry( 'D', '#', 'E', 'b', 	5, 622.254, 51);
        BaseNoteFreq[64]  = new NoteFreqEntry( 'E', ' ', 'F', 'b', 	5, 659.255, 52);
        BaseNoteFreq[65]  = new NoteFreqEntry( 'F', ' ', 'E', '#', 	5, 698.456, 53);
        BaseNoteFreq[66]  = new NoteFreqEntry( 'F', '#', 'G', 'b', 	5, 739.989, 54);
        BaseNoteFreq[67]  = new NoteFreqEntry( 'G', ' ', ' ', ' ', 	5, 783.991, 55);
        BaseNoteFreq[68]  = new NoteFreqEntry( 'G', '#', 'A', 'b', 	5, 830.609, 56);
        BaseNoteFreq[69]  = new NoteFreqEntry( 'A', ' ', ' ', ' ', 	5, 880,     57);
        BaseNoteFreq[70]  = new NoteFreqEntry( 'A', '#', 'B', 'b', 	5, 932.328, 58);
        BaseNoteFreq[71]  = new NoteFreqEntry( 'B', ' ', 'C', 'b', 	5, 987.767, 59);
        BaseNoteFreq[72]  = new NoteFreqEntry( 'C', ' ', 'B', '#', 	6, 1046.502, 60);
        BaseNoteFreq[73]  = new NoteFreqEntry( 'C', '#', 'D', 'b', 	6, 1108.731, 0);
        BaseNoteFreq[74]  = new NoteFreqEntry( 'D', ' ', ' ', ' ', 	6, 1174.659, 0);
        BaseNoteFreq[75]  = new NoteFreqEntry( 'D', '#', 'E', 'b', 	6, 1244.508, 0);
        BaseNoteFreq[76]  = new NoteFreqEntry( 'E', ' ', 'F', 'b', 	6, 1318.51,  0);
        BaseNoteFreq[77]  = new NoteFreqEntry( 'F', ' ', 'E', '#', 	6, 1396.913, 0);
        BaseNoteFreq[78]  = new NoteFreqEntry( 'F', '#', 'G', 'b', 	6, 1479.978, 0);
        BaseNoteFreq[79]  = new NoteFreqEntry( 'G', ' ', ' ', ' ', 	6, 1567.982, 0);
        BaseNoteFreq[80]  = new NoteFreqEntry( 'G', '#', 'A', 'b', 	6, 1661.219, 0);
        BaseNoteFreq[81]  = new NoteFreqEntry( 'A', ' ', ' ', ' ', 	6, 1760,     0);
        BaseNoteFreq[82]  = new NoteFreqEntry( 'A', '#', 'B', 'b', 	6, 1864.655, 0);
        BaseNoteFreq[83]  = new NoteFreqEntry( 'B', ' ', 'C', 'b', 	6, 1975.533, 0);
        BaseNoteFreq[84]  = new NoteFreqEntry( 'C', ' ', 'B', '#', 	7, 2093.005, 0);
        BaseNoteFreq[85]  = new NoteFreqEntry( 'C', '#', 'D', 'b', 	7, 2217.461, 0);
        BaseNoteFreq[86]  = new NoteFreqEntry( 'D', ' ', ' ', ' ', 	7, 2349.318, 0);
        BaseNoteFreq[87]  = new NoteFreqEntry( 'D', '#', 'E', 'b', 	7, 2489.016, 0);
        BaseNoteFreq[88]  = new NoteFreqEntry( 'E', ' ', 'F', 'b', 	7, 2637.021, 0);
        BaseNoteFreq[89]  = new NoteFreqEntry( 'F', ' ', 'E', '#', 	7, 2793.826, 0);
        BaseNoteFreq[90]  = new NoteFreqEntry( 'F', '#', 'G', 'b', 	7, 2959.955, 0);
        BaseNoteFreq[91]  = new NoteFreqEntry( 'G', ' ', ' ', ' ', 	7, 3135.964, 0);
        BaseNoteFreq[92]  = new NoteFreqEntry( 'G', '#', 'A', 'b', 	7, 3322.438, 0);
        BaseNoteFreq[93]  = new NoteFreqEntry( 'A', ' ', ' ', ' ', 	7, 3520, 0);
        BaseNoteFreq[94]  = new NoteFreqEntry( 'A', '#', 'B', 'b', 	7, 3729.31, 0);
        BaseNoteFreq[95]  = new NoteFreqEntry( 'B', ' ', ' ', ' ', 	7, 3951.066, 0);
        BaseNoteFreq[96]  = new NoteFreqEntry( 'C', ' ', 'B', '#', 	8, 4186.009, 0);
        BaseNoteFreq[97]  = new NoteFreqEntry( 'C', '#', 'D', 'b', 	8, 4434.922, 0);
        BaseNoteFreq[98]  = new NoteFreqEntry( 'D', ' ', ' ', ' ', 	8, 4698.636, 0);
        BaseNoteFreq[99]  = new NoteFreqEntry( 'D', '#', 'E', 'b', 	8, 4978.032, 0);
        BaseNoteFreq[100]  = new NoteFreqEntry( 'E', ' ', 'F', 'b', 	8, 5274.042, 0);
        BaseNoteFreq[101]  = new NoteFreqEntry( 'F', ' ', 'E', '#', 	8, 5587.652, 0);
        BaseNoteFreq[102]  = new NoteFreqEntry( 'F', '#', 'G', 'b', 	8, 5919.91, 0);
        BaseNoteFreq[103]  = new NoteFreqEntry( 'G', ' ', ' ', ' ', 	8, 6271.928, 0);
        BaseNoteFreq[104]  = new NoteFreqEntry( 'G', '#', 'A', 'b', 	8, 6644.876, 0);
        BaseNoteFreq[105]  = new NoteFreqEntry( 'A', ' ', ' ', ' ', 	8, 7040, 0);
        BaseNoteFreq[106]  = new NoteFreqEntry( 'A', '#', 'B', 'b', 	8, 7458.62, 0);
        BaseNoteFreq[107]  = new NoteFreqEntry( 'B', ' ', ' ', ' ', 	8, 7902.132, 0);
        BaseNoteFreq[108]  = new NoteFreqEntry( 'C', ' ', ' ', ' ', 	9, 8372.018, 0);
        BaseNoteFreq[109]  = new NoteFreqEntry( 'C', '#', 'D', 'b', 	9, 8869.844, 0);
        BaseNoteFreq[110]  = new NoteFreqEntry( 'D', ' ', ' ', ' ', 	9, 9397.272, 0);
        BaseNoteFreq[111]  = new NoteFreqEntry( 'D', '#', 'E', 'b', 	9, 9956.064, 0);
        BaseNoteFreq[112]  = new NoteFreqEntry( 'E', ' ', 'F', 'b', 	9, 10548.084, 0);
        BaseNoteFreq[113]  = new NoteFreqEntry( 'F', ' ', 'E', '#', 	9, 11175.304, 0);
        BaseNoteFreq[114]  = new NoteFreqEntry( 'F', '#', 'G', 'b', 	9, 11839.82, 0);
        BaseNoteFreq[115]  = new NoteFreqEntry( 'G', ' ', ' ', ' ', 	9, 12543.856, 0);
        BaseNoteFreq[116]  = new NoteFreqEntry( 'G', '#', 'A', 'b', 	9, 13289.752, 0);
        BaseNoteFreq[117]  = new NoteFreqEntry( 'A', ' ', ' ', ' ', 	9, 14080, 0);
        BaseNoteFreq[118]  = new NoteFreqEntry( 'A', '#', 'B', 'b', 	9, 14917.24, 0);
        BaseNoteFreq[119]  = new NoteFreqEntry( 'B', ' ', ' ', ' ', 	9, 15804.264, 0);
    }

    static public void printNoteFreqTable()
    {
        int idx;
        Log.i("MyDebug", "BaseNoteFreq Table Contents...");
        for(idx=0; idx < 120; idx++)
            BaseNoteFreq[idx].printNoteFreqEntry(idx);
    }

    static public double SearchNoteFreqTableForFreq(char name, char sharpflat, int octave, int transpose)
    {
        int idx;
        int useIdx;
	/* System.out.println("\nSearching for: " + name + " sharpflat: " + sharpflat + " octave: " + octave
				+ " transpose: " + transpose); */
        for(idx=0; idx < 120; idx++)
        {
		/* System.out.println
			("  Searching Row: " + idx  + " Name1:" + BaseNoteFreq[idx].getName1()
				+ " Sharp/Flat:"   + BaseNoteFreq[idx].getSharpFlat1()
				+ " Name2:"        + BaseNoteFreq[idx].getName2()
				+ " Sharp/Flat:"   + BaseNoteFreq[idx].getSharpFlat2()
				+ " Octave:"       + BaseNoteFreq[idx].getOctave() );  */
            if (((BaseNoteFreq[idx].getName1() == name && BaseNoteFreq[idx].getSharpFlat1() == sharpflat)
                    ||  (BaseNoteFreq[idx].getName2() == name && BaseNoteFreq[idx].getSharpFlat2() == sharpflat))
                    && BaseNoteFreq[idx].getOctave() == octave)
            {
			/* Then we have a match */
			/* System.out.print("  Got Match on row " + idx + "... "); */
                useIdx = idx;
                if ( transpose != 0 && transpose > -13 && transpose < 13 )
                {
                    useIdx = idx + transpose;
				/* Could do bounds checking on transpose being reasonable. */
                    if ( useIdx < 0 ) useIdx = 0;
                    if ( useIdx > 119 ) useIdx = 119;
                }
			/* System.out.print("  Using row " + useIdx + "... " ); */
                return BaseNoteFreq[useIdx].getFreq();
            }
        }
        return 0;	/* Failure */
    }

    static public int SearchNoteFreqTableForPianoKey(char name, char sharpflat, int octave, int transpose)
    {
        int idx;
        int useIdx;
	/* System.out.println("\nSearching for: " + name + " sharpflat: " + sharpflat + " octave: " + octave
				+ " transpose: " + transpose); */
        for(idx=0; idx < 120; idx++)
        {
		/* System.out.println
			("  Searching Row: " + idx  + " Name1:" + BaseNoteFreq[idx].getName1()
				+ " Sharp/Flat:"   + BaseNoteFreq[idx].getSharpFlat1()
				+ " Name2:"        + BaseNoteFreq[idx].getName2()
				+ " Sharp/Flat:"   + BaseNoteFreq[idx].getSharpFlat2()
				+ " Octave:"       + BaseNoteFreq[idx].getOctave() );  */
            if (((BaseNoteFreq[idx].getName1() == name && BaseNoteFreq[idx].getSharpFlat1() == sharpflat)
                    ||  (BaseNoteFreq[idx].getName2() == name && BaseNoteFreq[idx].getSharpFlat2() == sharpflat))
                    && BaseNoteFreq[idx].getOctave() == octave)
            {
			/* Then we have a match */
			/* System.out.print("  Got Match on row " + idx + "... "); */
                useIdx = idx;
                if ( transpose != 0 && transpose > -13 && transpose < 13 )
                {
                    useIdx = idx + transpose;
				/* Could do bounds checking on transpose being reasonable. */
                    if ( useIdx < 0 ) useIdx = 0;
                    if ( useIdx > 119 ) useIdx = 119;
                }
			/* System.out.print("  Using row " + useIdx + "... " ); */
                return BaseNoteFreq[useIdx].getPianoKey();
            }
        }
        return 0;	/* Failure */
    }
}
