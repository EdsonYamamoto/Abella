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
    "\11\0\1\34\1\46\1\66\1\66\1\45\22\0\1\34\1\47\1\0"+
    "\1\42\1\32\1\33\1\0\1\44\1\1\1\14\1\6\1\22\1\40"+
    "\1\23\1\5\1\24\1\21\11\21\1\4\1\40\1\26\1\27\1\25"+
    "\1\2\1\31\1\43\1\7\1\43\1\15\1\10\1\43\1\11\1\43"+
    "\1\12\4\43\1\13\3\43\1\17\1\20\1\16\1\43\1\60\4\43"+
    "\1\41\1\0\1\41\1\30\1\43\1\0\1\56\3\43\1\63\1\61"+
    "\1\55\1\62\1\3\2\43\1\64\1\57\1\35\1\36\1\53\1\43"+
    "\1\54\1\65\1\37\6\43\1\51\1\50\1\52\7\0\1\66\44\0"+
    "\1\47\17\0\1\47\5\0\4\47\3\0\3\47\3\0\1\47\1\0"+
    "\1\47\1\0\1\47\1\0\4\47\3\0\1\47\5\0\4\47\3\0"+
    "\4\47\2\0\1\47\1\0\1\47\1\0\1\47\1\0\4\47\3\0"+
    "\1\47\u1f2d\0\1\66\1\66\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udfe6\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\2\4\1\5\1\3\1\6"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\4\1\15"+
    "\1\16\1\17\1\20\1\21\1\3\1\22\1\23\1\24"+
    "\2\25\1\1\3\3\1\0\1\26\1\27\1\7\3\0"+
    "\1\30\1\31\1\3\1\0\1\32\1\0\1\33\3\3"+
    "\4\0\2\34\2\3\1\35\1\3\2\0\1\36\1\37"+
    "\1\3\1\40\1\0\1\3\1\0\1\3\4\0\1\41"+
    "\15\0\1\42\5\0\1\43\3\0\1\44\1\45";

  private static int [] zzUnpackAction() {
    int [] result = new int[98];
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
    "\0\0\0\67\0\156\0\245\0\334\0\u0113\0\67\0\u014a"+
    "\0\67\0\u0181\0\u0181\0\u0181\0\u01b8\0\u01ef\0\u0226\0\67"+
    "\0\67\0\67\0\67\0\67\0\67\0\u025d\0\67\0\67"+
    "\0\u0294\0\u02cb\0\67\0\u0302\0\u0339\0\u0370\0\u03a7\0\u03de"+
    "\0\u014a\0\67\0\u0113\0\u0113\0\u0415\0\u044c\0\67\0\67"+
    "\0\u0483\0\u04ba\0\u04ba\0\u0302\0\67\0\u04f1\0\u0528\0\u055f"+
    "\0\u0596\0\u05cd\0\u0604\0\u063b\0\u0672\0\67\0\u06a9\0\u06e0"+
    "\0\u014a\0\u0717\0\u074e\0\u0785\0\67\0\u014a\0\u07bc\0\u014a"+
    "\0\u07f3\0\u082a\0\u0861\0\u0898\0\u08cf\0\u0906\0\u093d\0\u0974"+
    "\0\u014a\0\u09ab\0\u09e2\0\u0a19\0\u0a50\0\u0a87\0\u0abe\0\u0af5"+
    "\0\u0b2c\0\u0b63\0\u0b9a\0\u0bd1\0\u0c08\0\u0c3f\0\67\0\u0c76"+
    "\0\u0cad\0\u0ce4\0\u0d1b\0\u0d52\0\67\0\u0d89\0\u0dc0\0\u0df7"+
    "\0\67\0\67";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[98];
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
    "\1\2\1\3\1\2\1\4\1\5\1\6\1\7\5\10"+
    "\1\11\4\10\1\12\1\13\1\14\1\15\1\16\1\17"+
    "\1\20\1\21\1\22\1\23\1\24\1\25\2\10\1\26"+
    "\1\20\1\27\1\30\1\10\1\31\1\32\1\33\1\2"+
    "\1\25\1\34\1\2\1\35\4\10\1\36\2\10\1\37"+
    "\2\10\72\0\1\40\67\0\1\10\3\0\5\10\1\0"+
    "\5\10\13\0\3\10\3\0\1\10\7\0\6\10\1\41"+
    "\4\10\30\0\1\42\60\0\1\43\50\0\1\10\3\0"+
    "\5\10\1\0\5\10\13\0\3\10\3\0\1\10\7\0"+
    "\13\10\6\0\1\44\13\0\1\12\53\0\1\45\15\0"+
    "\1\46\71\0\1\47\66\0\1\50\42\0\1\10\3\0"+
    "\5\10\1\0\5\10\13\0\3\10\3\0\1\10\7\0"+
    "\7\10\1\51\3\10\1\0\2\52\1\0\41\52\1\53"+
    "\4\0\16\52\46\0\1\33\20\0\52\54\1\55\14\54"+
    "\3\0\1\10\3\0\5\10\1\0\5\10\13\0\3\10"+
    "\3\0\1\10\7\0\1\10\1\56\11\10\4\0\1\10"+
    "\3\0\5\10\1\0\5\10\13\0\3\10\3\0\1\10"+
    "\7\0\3\10\1\57\7\10\4\0\1\10\3\0\5\10"+
    "\1\0\5\10\13\0\3\10\3\0\1\10\7\0\11\10"+
    "\1\60\1\10\4\0\1\61\63\0\6\62\1\63\60\62"+
    "\2\64\1\0\42\64\1\65\1\66\2\0\16\64\3\0"+
    "\1\10\3\0\5\10\1\0\5\10\13\0\3\10\3\0"+
    "\1\10\7\0\10\10\1\67\2\10\1\0\44\52\1\53"+
    "\2\0\1\52\1\0\16\52\3\0\1\10\3\0\5\10"+
    "\1\0\5\10\13\0\1\10\1\70\1\10\3\0\1\10"+
    "\7\0\13\10\4\0\1\10\3\0\5\10\1\0\5\10"+
    "\13\0\3\10\3\0\1\10\7\0\1\10\1\71\11\10"+
    "\4\0\1\10\3\0\5\10\1\0\5\10\13\0\3\10"+
    "\3\0\1\10\7\0\12\10\1\72\5\0\1\73\62\0"+
    "\6\62\1\74\60\62\6\0\1\63\15\0\1\75\42\0"+
    "\45\64\1\65\1\66\1\64\1\0\16\64\46\0\1\66"+
    "\23\0\1\10\3\0\5\10\1\0\5\10\13\0\1\76"+
    "\2\10\3\0\1\10\7\0\13\10\4\0\1\10\3\0"+
    "\5\10\1\0\5\10\13\0\3\10\3\0\1\10\7\0"+
    "\2\10\1\77\10\10\4\0\1\10\3\0\5\10\1\0"+
    "\5\10\13\0\3\10\3\0\1\10\7\0\10\10\1\100"+
    "\2\10\6\0\1\101\61\0\6\62\1\74\15\62\1\75"+
    "\42\62\3\0\1\10\3\0\5\10\1\0\5\10\13\0"+
    "\3\10\3\0\1\10\7\0\1\10\1\102\11\10\7\0"+
    "\1\103\63\0\1\10\3\0\5\10\1\0\5\10\13\0"+
    "\3\10\3\0\1\10\7\0\3\10\1\104\7\10\10\0"+
    "\1\105\1\106\1\0\1\107\5\0\1\110\51\0\1\10"+
    "\3\0\5\10\1\0\5\10\13\0\3\10\3\0\1\10"+
    "\7\0\4\10\1\111\6\10\11\0\1\112\71\0\1\113"+
    "\66\0\1\114\71\0\1\115\61\0\1\116\72\0\1\117"+
    "\67\0\1\120\67\0\1\121\61\0\1\122\62\0\1\123"+
    "\70\0\1\124\70\0\1\125\67\0\1\126\67\0\1\127"+
    "\63\0\1\130\70\0\1\131\61\0\1\132\70\0\1\133"+
    "\67\0\1\134\71\0\1\135\71\0\1\136\55\0\1\137"+
    "\66\0\1\140\74\0\1\141\66\0\1\142\52\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3630];
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
    "\1\0\1\11\4\1\1\11\1\1\1\11\6\1\6\11"+
    "\1\1\2\11\2\1\1\11\4\1\1\0\1\1\1\11"+
    "\1\1\3\0\2\11\1\1\1\0\1\1\1\0\1\11"+
    "\3\1\4\0\1\1\1\11\4\1\2\0\1\11\3\1"+
    "\1\0\1\1\1\0\1\1\4\0\1\1\15\0\1\11"+
    "\5\0\1\11\3\0\2\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[98];
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
    while (i < 262) {
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
            { throw new RuntimeException("Caractere inv�lido " + yytext() + " na linha " + yyline + ", coluna " +yycolumn);
            }
          case 38: break;
          case 2: 
            { return new PascalToken( "abreParenteses", yytext() );
            }
          case 39: break;
          case 3: 
            { return createToken("identificadores", yytext());
            }
          case 40: break;
          case 4: 
            { return createToken("delimitadores", yytext());
            }
          case 41: break;
          case 5: 
            { return new PascalToken( "multiplicar", yytext() );
            }
          case 42: break;
          case 6: 
            { return new PascalToken( "fechaParenteses", yytext() );
            }
          case 43: break;
          case 7: 
            { return new PascalToken( "numero real", yytext() );
            }
          case 44: break;
          case 8: 
            { return new PascalToken( "soma", yytext() );
            }
          case 45: break;
          case 9: 
            { return new PascalToken( "subtracao", yytext() );
            }
          case 46: break;
          case 10: 
            { return new PascalToken( "dividir", yytext() );
            }
          case 47: break;
          case 11: 
            { return new PascalToken( "maior", yytext() );
            }
          case 48: break;
          case 12: 
            { return new PascalToken( "menor", yytext() );
            }
          case 49: break;
          case 13: 
            { return new PascalToken( "expoente", yytext() );
            }
          case 50: break;
          case 14: 
            { return new PascalToken( "arrouba", yytext() );
            }
          case 51: break;
          case 15: 
            { return new PascalToken( "cifrao", yytext() );
            }
          case 52: break;
          case 16: 
            { return new PascalToken( "porrcentagem", yytext() );
            }
          case 53: break;
          case 17: 
            { /**/
            }
          case 54: break;
          case 18: 
            { return new PascalToken( "vetor", yytext() );
            }
          case 55: break;
          case 19: 
            { return new PascalToken( "number", yytext() );
            }
          case 56: break;
          case 20: 
            { return new PascalToken( "abreImpressao", yytext() );
            }
          case 57: break;
          case 21: 
            { return createToken("fimLinha", yytext());
            }
          case 58: break;
          case 22: 
            { return new PascalToken( "if", yytext() );
            }
          case 59: break;
          case 23: 
            { return new PascalToken( "igual", yytext() );
            }
          case 60: break;
          case 24: 
            { return new PascalToken( "igual ou maior", yytext() );
            }
          case 61: break;
          case 25: 
            { return new PascalToken( "igual ou menor", yytext() );
            }
          case 62: break;
          case 26: 
            { return new PascalToken( "texto", yytext() );
            }
          case 63: break;
          case 27: 
            { return createToken("comentario1", yytext());
            }
          case 64: break;
          case 28: 
            { return createToken("comentario3", yytext());
            }
          case 65: break;
          case 29: 
            { return new PascalToken( "var", yytext() );
            }
          case 66: break;
          case 30: 
            { return createToken("comentario2", yytext());
            }
          case 67: break;
          case 31: 
            { return new PascalToken( "then", yytext() );
            }
          case 68: break;
          case 32: 
            { return new PascalToken( "else", yytext() );
            }
          case 69: break;
          case 33: 
            { return new PascalToken( "program", yytext() );
            }
          case 70: break;
          case 34: 
            { return new PascalToken( "end", yytext() );
            }
          case 71: break;
          case 35: 
            { return new PascalToken( "begin", yytext() );
            }
          case 72: break;
          case 36: 
            { return new PascalToken( "string", yytext() );
            }
          case 73: break;
          case 37: 
            { return new PascalToken( "inteiro", yytext() );
            }
          case 74: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
