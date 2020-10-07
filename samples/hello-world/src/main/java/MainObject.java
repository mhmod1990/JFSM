/*
 * Copyright (c) 2020 By Mahmoud Ibrahim
 * This class is written as a contribution for the open source community under GPL license.
 * Author: Mahmoud Ibrahim
 * upworks: https://www.upwork.com/o/profiles/users/~01c04883943f6add8f/
 * github: https://github.com/mhmod1990.
 */

import org.mhmod.logger.JfsmLog;

public class MainObject {


    private static final String TAG = "[MainObject]";

    public void doSomthingInFisrtState(){
        JfsmLog.d(TAG, "doing something in first state");
    }

    public void doSomthingInLastState(){
        JfsmLog.d(TAG, "doing something in Last state");
    }

}
