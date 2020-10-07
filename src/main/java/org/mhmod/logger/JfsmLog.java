package org.mhmod.logger;

public class JfsmLog {

    private static JfsmLogger jfsmLogger = new ConsoleJfsmLogger();
    public final static int DEBUG = 0;
    public final static int RELEASE = 1;
    public final static int FILE_LOG = 0;
    public final static int CONSOLE_LOG = 1;
    public static int DEBUG_LEVEL = DEBUG;


    public static void d(String tag, String message){
        if(DEBUG_LEVEL == DEBUG)
            jfsmLogger.write_debug(tag+": "+message);
    }

    public static void w(String tag, String message){
        jfsmLogger.write_warning(tag+": "+message);
    }


    public static void e(String tag, String message){
        jfsmLogger.write_error(tag+": "+message);
    }



    public static void setJfsmLogger(int loggerId){
        switch (loggerId){
            case CONSOLE_LOG: {
                jfsmLogger = new ConsoleJfsmLogger();
                break;
            }

            case FILE_LOG:{
                jfsmLogger = new FileJfsmLogger();
                break;
            }

        }
    }

}
