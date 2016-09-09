package com.xingtam.tamutils;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

/**
 * For debug.
 * 
 * */
public class DebugUtil{
	public static final String TAG = "Debug";
	private static boolean DEBUG_MODE = true;
    private DebugUtil( ){

    }

    public static void println( String printInfo ){
        if(DEBUG_MODE && null != printInfo ){
            System.out.println( printInfo );
        }
    }

    public static void print( String printInfo ){
        if(DEBUG_MODE && null != printInfo ){
            System.out.print( printInfo );
        }
    }

    public static void printLogI( String logInfo ){
        printLogI( TAG, logInfo );
    }

    public static void printLogI( String tag, String logInfo ){
        if(DEBUG_MODE && null != tag && null != logInfo ){
            Log.i( tag, logInfo );
        }
    }

    public static void printLogE( String logInfo ){
        printLogE( TAG, logInfo );
    }

    public static void printLogE( String tag, String logInfo ){
        if(DEBUG_MODE && null != tag && null != logInfo ){
            Log.e( tag, logInfo );
        }
    }

    public static void printLogW( String logInfo ){
        printLogW( TAG, logInfo );
    }

    public static void printLogW( String tag, String logInfo ){
        if(DEBUG_MODE && null != tag && null != logInfo ){
            Log.w( tag, logInfo );
        }
    }

    public static void printLogD( String logInfo ){
        printLogD( TAG, logInfo );
    }

    public static void printLogD( String tag, String logInfo ){
        if(DEBUG_MODE && null != tag && null != logInfo ){
            Log.d( tag, logInfo );
        }
    }

    public static void printLogV( String logInfo ){
        printLogV( TAG, logInfo );
    }

    public static void printLogV( String tag, String logInfo ){
        if(DEBUG_MODE && null != tag || null != logInfo ){
            Log.v( tag, logInfo );
        }
    }

    public static void printLogWtf( String logInfo ){
        printLogWtf( TAG, logInfo );
    }

    public static void printLogWtf( String tag, String logInfo ){
        if(DEBUG_MODE && null != tag && null != logInfo ){
            Log.wtf( tag, logInfo );
        }
    }
    public static void printBaseInfo( ){
        if(DEBUG_MODE ){
            StringBuffer strBuffer = new StringBuffer( );
            StackTraceElement[ ] stackTrace = new Throwable( ).getStackTrace( );

            strBuffer.append( "; class:" ).append( stackTrace[ 1 ].getClassName( ) )
                    .append( "; method:" ).append( stackTrace[ 1 ].getMethodName( ) )
                    .append( "; number:" ).append( stackTrace[ 1 ].getLineNumber( ) )
                    .append( "; fileName:" ).append( stackTrace[ 1 ].getFileName( ) );

            println( strBuffer.toString( ) );
        }
    }

    public static void printFileNameAndLinerNumber( ){
        if(DEBUG_MODE ){
            StringBuffer strBuffer = new StringBuffer( );
            StackTraceElement[ ] stackTrace = new Throwable( ).getStackTrace( );

            strBuffer.append( "; fileName:" ).append( stackTrace[ 1 ].getFileName( ) )
                    .append( "; number:" ).append( stackTrace[ 1 ].getLineNumber( ) );

            println( strBuffer.toString( ) );
        }
    }

    public static int printLineNumber( ){
        if(DEBUG_MODE ){
            StringBuffer strBuffer = new StringBuffer( );
            StackTraceElement[ ] stackTrace = new Throwable( ).getStackTrace( );

            strBuffer.append( "; number:" ).append( stackTrace[ 1 ].getLineNumber( ) );

            println( strBuffer.toString( ) );
            return stackTrace[ 1 ].getLineNumber( );
        }else{
            return 0;
        }
    }

    public static void printMethod( ){
        if(DEBUG_MODE ){
            StringBuffer strBuffer = new StringBuffer( );
            StackTraceElement[ ] stackTrace = new Throwable( ).getStackTrace( );

            strBuffer.append( "; number:" ).append( stackTrace[ 1 ].getMethodName( ) );

            println( strBuffer.toString( ) );
        }
    }

    public static void printFileNameAndLinerNumber( String printInfo ){
        if( null == printInfo || !DEBUG_MODE ){
            return;
        }
        StringBuffer strBuffer = new StringBuffer( );
        StackTraceElement[ ] stackTrace = new Throwable( ).getStackTrace( );

        strBuffer.append( "; fileName:" ).append( stackTrace[ 1 ].getFileName( ) )
                .append( "; number:" ).append( stackTrace[ 1 ].getLineNumber( ) ).append( "/n" )
                .append( ( null != printInfo ) ? printInfo : "" );

        println( strBuffer.toString( ) );
    }

    @SuppressLint("NewApi")
	public static void showStrictMode( ) {
        if (DEBUG_MODE) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        }
    }

    public static void d(String tag, String msg){
        if(DEBUG_MODE){
            Log.d(tag, msg);
        }
    }
}