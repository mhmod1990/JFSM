/*
 * Copyright (c) 2020 By Mahmoud Ibrahim
 * This class is written as a contribution for the open source community under GPL license.
 * Author: Mahmoud Ibrahim
 * upworks: https://www.upwork.com/o/profiles/users/~01c04883943f6add8f/
 * github: https://github.com/mhmod1990.
 */

package org.mhmod.jfsm;

/**
 * StateMachineEmiter
 */
public interface StateMachineEmitter {

    /**
     * notify State Change
     * @param newStateCode
     */
    void notifyStateChange(int newStateCode);

}
