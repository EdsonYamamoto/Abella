/* The following code was generated by JFlex 1.6.1 */

package compiladorPascal;

import java_cup.runtime.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>C:/Users/edson.kazumi/git/Abella/ProjetoAbella/src/compiladorPascal/pascal.lex</tt>
 */
public class LexicalAnalyzer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\41\1\57\1\61\1\61\1\56\22\0\1\41\2\60\1\46"+
    "\1\37\1\40\1\60\1\55\1\53\1\54\1\30\1\25\1\44\1\26"+
    "\1\27\1\31\1\24\11\24\1\42\1\43\1\33\1\34\1\32\1\60"+
    "\1\36\1\21\1\7\1\17\1\12\1\10\1\13\1\11\1\14\1\1"+
    "\2\51\1\15\1\51\1\2\1\4\1\16\1\23\1\6\1\5\1\3"+
    "\1\20\2\51\1\22\2\51\1\45\1\60\1\45\1\35\1\52\1\60"+
    "\1\21\1\7\1\17\1\12\1\10\1\13\1\11\1\14\1\1\2\51"+
    "\1\15\1\51\1\2\1\4\1\16\1\23\1\6\1\5\1\3\1\20"+
    "\2\51\1\22\2\51\1\47\1\57\1\50\1\60\6\0\1\61\32\0"+
    "\1\60\1\0\2\60\3\0\2\60\1\0\1\60\1\0\1\60\3\0"+
    "\1\60\1\0\3\60\4\0\2\60\5\0\4\60\3\0\4\60\2\0"+
    "\1\60\1\0\1\60\1\0\1\60\1\0\4\60\3\0\3\60\3\0"+
    "\4\60\3\0\4\60\2\0\1\60\1\0\1\60\1\0\1\60\1\0"+
    "\4\60\3\0\3\60\u1583\0\1\60\u097f\0\13\60\35\0\1\61\1\61"+
    "\5\0\1\60\57\0\1\60\u0fa0\0\1\60\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\ud00f\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\1\1\2\11\3\1\4\1\5\1\6\1\7\1\10"+
    "\1\11\1\12\1\13\1\7\1\14\1\15\1\16\1\17"+
    "\1\20\1\7\1\21\1\22\1\2\1\23\1\24\1\25"+
    "\1\26\1\3\1\27\11\3\1\0\1\4\2\0\1\30"+
    "\1\31\1\32\1\33\1\0\1\34\3\0\1\1\1\0"+
    "\1\35\6\3\1\36\3\3\2\0\1\37\2\0\2\3"+
    "\1\40\2\3\2\0\2\3\1\41\2\3\1\0\1\42"+
    "\1\0\1\43\4\3\1\0\1\3\1\44\4\3\1\45"+
    "\1\3\1\0\1\3\1\0\3\3\1\46\1\3\1\47"+
    "\1\3\1\0\2\3\1\50\1\51\1\3\1\0\3\3"+
    "\1\0\1\3\1\0\1\3\4\0\1\3\1\0\1\52"+
    "\1\3\1\0\1\52\1\3\1\0\1\3\10\0\1\53";

  private static int [] zzUnpackAction() {
    int [] result = new int[150];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\62\0\144\0\226\0\310\0\372\0\u012c\0\u015e"+
    "\0\u0190\0\u01c2\0\u01f4\0\u0226\0\u0226\0\u0226\0\u0258\0\62"+
    "\0\u028a\0\u02bc\0\u02ee\0\62\0\62\0\62\0\62\0\62"+
    "\0\62\0\u0320\0\62\0\62\0\u0352\0\u0384\0\62\0\u03b6"+
    "\0\62\0\u03e8\0\226\0\u041a\0\u044c\0\u047e\0\u04b0\0\u04e2"+
    "\0\u0514\0\u0546\0\u0578\0\u05aa\0\u0258\0\u0258\0\u05dc\0\u060e"+
    "\0\62\0\62\0\62\0\62\0\u0352\0\62\0\u0640\0\u0672"+
    "\0\u06a4\0\u06d6\0\u03b6\0\62\0\u0708\0\u073a\0\u076c\0\u079e"+
    "\0\u07d0\0\u0802\0\226\0\u0834\0\u0866\0\u0898\0\u08ca\0\u08fc"+
    "\0\62\0\u092e\0\u0960\0\u0992\0\u09c4\0\226\0\u09f6\0\u0a28"+
    "\0\u0a5a\0\u0a8c\0\u0abe\0\u0af0\0\226\0\u0b22\0\u0b54\0\u0b86"+
    "\0\62\0\u0bb8\0\62\0\u0bea\0\u0c1c\0\u0c4e\0\u0c80\0\u0cb2"+
    "\0\u0ce4\0\226\0\u0d16\0\u0d48\0\u0d7a\0\u0dac\0\226\0\u0dde"+
    "\0\u0e10\0\u0e42\0\u0e74\0\u0ea6\0\u0ed8\0\u0f0a\0\226\0\u0f3c"+
    "\0\62\0\u0f6e\0\u0fa0\0\u0fd2\0\u1004\0\226\0\226\0\u1036"+
    "\0\u1068\0\u109a\0\u10cc\0\u10fe\0\u1130\0\u1162\0\u1194\0\u11c6"+
    "\0\u11f8\0\u122a\0\u125c\0\u128e\0\u12c0\0\u12f2\0\u1324\0\u1356"+
    "\0\u1388\0\u13ba\0\u13ec\0\u141e\0\u1450\0\u1482\0\u14b4\0\u14e6"+
    "\0\u1518\0\u154a\0\u157c\0\u15ae\0\u15e0\0\62";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[150];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\4\1\6\1\7\1\10"+
    "\1\11\2\4\1\12\2\4\1\13\5\4\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25"+
    "\1\26\1\27\1\30\1\31\1\32\2\24\1\33\1\34"+
    "\1\35\1\2\1\4\1\2\1\36\1\37\1\40\2\41"+
    "\1\2\64\0\1\4\1\42\10\4\1\43\11\4\24\0"+
    "\2\4\10\0\24\4\24\0\2\4\10\0\13\4\1\44"+
    "\10\4\24\0\2\4\10\0\2\4\1\45\17\4\1\46"+
    "\1\4\24\0\2\4\10\0\20\4\1\47\3\4\24\0"+
    "\2\4\10\0\7\4\1\50\14\4\24\0\2\4\10\0"+
    "\1\4\1\51\12\4\1\52\7\4\24\0\2\4\10\0"+
    "\17\4\1\53\4\4\24\0\2\4\10\0\5\4\1\54"+
    "\16\4\24\0\2\4\33\0\1\14\2\0\1\55\56\0"+
    "\1\56\65\0\1\57\1\60\64\0\1\61\57\0\1\62"+
    "\1\0\1\63\61\0\1\64\25\0\50\65\1\66\11\65"+
    "\1\0\23\67\4\0\1\70\10\0\2\71\1\0\1\71"+
    "\4\0\1\67\2\0\1\72\2\0\1\71\3\0\36\73"+
    "\2\0\14\73\1\74\4\73\1\0\2\4\1\75\21\4"+
    "\24\0\2\4\10\0\7\4\1\76\14\4\24\0\2\4"+
    "\10\0\5\4\1\77\16\4\24\0\2\4\10\0\14\4"+
    "\1\100\7\4\24\0\2\4\10\0\1\101\23\4\24\0"+
    "\2\4\10\0\10\4\1\102\13\4\24\0\2\4\10\0"+
    "\11\4\1\103\12\4\24\0\2\4\10\0\4\4\1\104"+
    "\17\4\24\0\2\4\10\0\1\4\1\105\22\4\24\0"+
    "\2\4\10\0\3\4\1\106\20\4\24\0\2\4\7\0"+
    "\30\107\1\110\31\107\56\60\2\111\2\60\1\0\24\67"+
    "\14\0\2\71\1\0\1\71\4\0\2\67\1\0\1\72"+
    "\2\0\1\71\2\0\30\112\1\113\31\112\1\0\23\67"+
    "\15\0\2\71\1\0\1\71\4\0\1\67\2\0\1\72"+
    "\2\0\1\71\55\0\1\71\7\0\2\4\1\114\4\4"+
    "\1\115\14\4\24\0\2\4\10\0\1\4\1\116\22\4"+
    "\24\0\2\4\10\0\1\117\1\4\1\120\21\4\24\0"+
    "\2\4\10\0\24\4\2\0\1\121\11\0\1\122\7\0"+
    "\2\4\4\0\1\122\3\0\4\4\1\123\17\4\24\0"+
    "\2\4\10\0\1\124\23\4\24\0\2\4\10\0\7\4"+
    "\1\125\14\4\24\0\2\4\10\0\16\4\1\126\5\4"+
    "\24\0\2\4\10\0\16\4\1\127\5\4\24\0\2\4"+
    "\7\0\30\107\1\130\31\107\30\0\1\110\1\131\30\0"+
    "\30\112\1\132\31\112\30\0\1\113\23\0\1\133\6\0"+
    "\3\4\1\134\20\4\24\0\2\4\10\0\10\4\1\135"+
    "\13\4\24\0\2\4\10\0\1\4\1\136\22\4\24\0"+
    "\2\4\10\0\3\4\1\137\20\4\24\0\2\4\30\0"+
    "\1\140\17\0\1\121\15\0\1\121\31\0\1\121\11\0"+
    "\1\122\15\0\1\122\3\0\7\4\1\141\14\4\24\0"+
    "\2\4\10\0\1\4\1\142\22\4\24\0\2\4\10\0"+
    "\2\4\1\143\21\4\24\0\2\4\10\0\7\4\1\144"+
    "\14\4\24\0\2\4\7\0\30\107\1\130\1\131\30\107"+
    "\30\112\1\132\23\112\1\133\5\112\1\0\4\4\1\145"+
    "\17\4\24\0\2\4\10\0\7\4\1\146\14\4\24\0"+
    "\2\4\10\0\10\4\1\147\13\4\24\0\2\4\10\0"+
    "\1\150\23\4\24\0\2\4\21\0\1\151\50\0\7\4"+
    "\1\152\14\4\14\0\1\153\7\0\2\4\4\0\1\153"+
    "\3\0\1\154\23\4\24\0\2\4\10\0\11\4\1\155"+
    "\12\4\24\0\2\4\10\0\2\4\1\156\21\4\24\0"+
    "\2\4\10\0\5\4\1\157\16\4\24\0\2\4\10\0"+
    "\1\4\1\160\22\4\24\0\2\4\21\0\1\161\50\0"+
    "\21\4\1\162\2\4\24\0\2\4\17\0\1\163\30\0"+
    "\1\153\15\0\1\153\3\0\3\4\1\164\20\4\24\0"+
    "\2\4\10\0\17\4\1\165\4\4\24\0\2\4\10\0"+
    "\5\4\1\166\16\4\24\0\2\4\10\0\2\4\1\167"+
    "\21\4\24\0\2\4\10\0\16\4\1\170\5\4\24\0"+
    "\2\4\31\0\1\171\40\0\1\4\1\172\22\4\24\0"+
    "\2\4\10\0\5\4\1\173\16\4\24\0\2\4\10\0"+
    "\7\4\1\174\14\4\24\0\2\4\26\0\1\175\43\0"+
    "\23\176\1\4\14\0\1\177\7\0\1\176\1\4\4\0"+
    "\1\177\3\0\7\4\1\172\14\4\24\0\2\4\10\0"+
    "\15\4\1\200\6\4\24\0\2\4\17\0\1\201\52\0"+
    "\24\176\2\0\1\202\11\0\1\203\7\0\2\176\4\0"+
    "\1\203\3\0\23\204\15\0\1\177\7\0\1\204\5\0"+
    "\1\177\3\0\2\4\1\205\21\4\24\0\2\4\25\0"+
    "\1\206\44\0\23\207\15\0\1\202\7\0\1\207\5\0"+
    "\1\202\31\0\1\202\11\0\1\203\15\0\1\203\3\0"+
    "\24\204\2\0\1\202\11\0\1\203\7\0\2\204\4\0"+
    "\1\203\3\0\1\210\23\4\24\0\2\4\12\0\1\211"+
    "\57\0\24\207\14\0\1\212\7\0\2\207\4\0\1\212"+
    "\3\0\3\4\1\213\20\4\24\0\2\4\10\0\1\214"+
    "\121\0\1\212\15\0\1\212\3\0\1\4\1\215\22\4"+
    "\24\0\2\4\13\0\1\216\56\0\24\4\2\0\1\217"+
    "\11\0\1\220\7\0\2\4\4\0\1\220\4\0\1\220"+
    "\76\0\1\221\21\0\1\217\15\0\1\217\31\0\1\217"+
    "\11\0\1\220\15\0\1\220\10\0\1\222\63\0\1\223"+
    "\72\0\1\224\43\0\1\225\66\0\1\226\51\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[5650];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\1\1\11\15\1\1\11\3\1\6\11\1\1\2\11"+
    "\2\1\1\11\1\1\1\11\13\1\1\0\1\1\2\0"+
    "\4\11\1\0\1\11\3\0\1\1\1\0\1\11\12\1"+
    "\2\0\1\11\2\0\5\1\2\0\5\1\1\0\1\11"+
    "\1\0\1\11\4\1\1\0\10\1\1\0\1\1\1\0"+
    "\5\1\1\11\1\1\1\0\5\1\1\0\3\1\1\0"+
    "\1\1\1\0\1\1\4\0\1\1\1\0\2\1\1\0"+
    "\2\1\1\0\1\1\10\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[150];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */


private PascalToken createToken(String name, String value) {
	return new PascalToken( name, value, yyline, yycolumn);
}



  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public LexicalAnalyzer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 326) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public PascalToken yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { return new PascalToken( "variaveisMetodo", yytext() );
            }
          case 44: break;
          case 2: 
            { throw new RuntimeException("Caractere inv�lido " + yytext() + " na linha " + yyline + ", coluna " +yycolumn);
            }
          case 45: break;
          case 3: 
            { return createToken("ID", yytext());
            }
          case 46: break;
          case 4: 
            { return new PascalToken( "numero real", yytext() );
            }
          case 47: break;
          case 5: 
            { return new PascalToken( "soma", yytext() );
            }
          case 48: break;
          case 6: 
            { return new PascalToken( "subtracao", yytext() );
            }
          case 49: break;
          case 7: 
            { return createToken("delimitadores", yytext());
            }
          case 50: break;
          case 8: 
            { return new PascalToken( "multiplicar", yytext() );
            }
          case 51: break;
          case 9: 
            { return new PascalToken( "dividir", yytext() );
            }
          case 52: break;
          case 10: 
            { return new PascalToken( "maior", yytext() );
            }
          case 53: break;
          case 11: 
            { return new PascalToken( "menor", yytext() );
            }
          case 54: break;
          case 12: 
            { return new PascalToken( "expoente", yytext() );
            }
          case 55: break;
          case 13: 
            { return new PascalToken( "arrouba", yytext() );
            }
          case 56: break;
          case 14: 
            { return new PascalToken( "cifrao", yytext() );
            }
          case 57: break;
          case 15: 
            { return new PascalToken( "porcentagem", yytext() );
            }
          case 58: break;
          case 16: 
            { /**/
            }
          case 59: break;
          case 17: 
            { return new PascalToken( "vetor", yytext() );
            }
          case 60: break;
          case 18: 
            { return new PascalToken( "number", yytext() );
            }
          case 61: break;
          case 19: 
            { return new PascalToken( "abreParenteses", yytext() );
            }
          case 62: break;
          case 20: 
            { return new PascalToken( "fechaParenteses", yytext() );
            }
          case 63: break;
          case 21: 
            { return new PascalToken( "abreImpressao", yytext() );
            }
          case 64: break;
          case 22: 
            { return createToken("fimLinha", yytext());
            }
          case 65: break;
          case 23: 
            { return new PascalToken( "if", yytext() );
            }
          case 66: break;
          case 24: 
            { return new PascalToken( "igual ou maior", yytext() );
            }
          case 67: break;
          case 25: 
            { return new PascalToken( "diferente", yytext() );
            }
          case 68: break;
          case 26: 
            { return new PascalToken( "igual ou menor", yytext() );
            }
          case 69: break;
          case 27: 
            { return new PascalToken( "igual", yytext() );
            }
          case 70: break;
          case 28: 
            { return createToken("comentario1", yytext());
            }
          case 71: break;
          case 29: 
            { return new PascalToken( "texto", yytext() );
            }
          case 72: break;
          case 30: 
            { return new PascalToken( "end", yytext() );
            }
          case 73: break;
          case 31: 
            { return createToken("comentario3", yytext());
            }
          case 74: break;
          case 32: 
            { return new PascalToken( "then", yytext() );
            }
          case 75: break;
          case 33: 
            { return new PascalToken( "else", yytext() );
            }
          case 76: break;
          case 34: 
            { return createToken("comentario2", yytext());
            }
          case 77: break;
          case 35: 
            { return createToken("comentario4", yytext());
            }
          case 78: break;
          case 36: 
            { return new PascalToken( "begin", yytext() );
            }
          case 79: break;
          case 37: 
            { return new PascalToken( "string", yytext() );
            }
          case 80: break;
          case 38: 
            { return new PascalToken( "inteiro", yytext() );
            }
          case 81: break;
          case 39: 
            { return createToken("consulta", yytext());
            }
          case 82: break;
          case 40: 
            { return new PascalToken( "IntToStr", yytext() );
            }
          case 83: break;
          case 41: 
            { return new PascalToken( "StrToInt", yytext() );
            }
          case 84: break;
          case 42: 
            { return new PascalToken( "metodo", yytext() );
            }
          case 85: break;
          case 43: 
            { return createToken("raise error", yytext());
            }
          case 86: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
