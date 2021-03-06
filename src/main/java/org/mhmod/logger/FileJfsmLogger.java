package org.mhmod.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileJfsmLogger implements JfsmLogger {

    private final static String LOG_FOLDER = "";
    private final static String LOG_FILE = "";
    private  File file;


    public FileJfsmLogger(){
         File folder = new File((LOG_FOLDER));
         if(!folder.exists())
             folder.mkdir();

         file = new File(LOG_FILE);
    }

    public void write_error(String message) {
        try {
            FileWriter writer = new FileWriter(file.getAbsoluteFile(),true);
            writer.append("E / : " + message + "\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void write_debug(String message) {
        try {
            FileWriter writer = new FileWriter(file.getAbsoluteFile(),true);
            writer.append("D / :" + message + "\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write_warning(String message) {

        try {
            FileWriter writer = new FileWriter(file.getAbsoluteFile(),true);
            writer.append("WAR / :" + message + "\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
