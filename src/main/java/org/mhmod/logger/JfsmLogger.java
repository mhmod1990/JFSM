package org.mhmod.logger;

public interface JfsmLogger {

    void write_error(String message);

    void write_debug(String message);

    void write_warning(String message);

}
