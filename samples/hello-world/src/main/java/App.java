/*
 * Copyright (c) 2020 By Mahmoud Ibrahim
 * This class is written as a contribution for the open source community under GPL license.
 * Author: Mahmoud Ibrahim
 * upworks: https://www.upwork.com/o/profiles/users/~01c04883943f6add8f/
 * github: https://github.com/mhmod1990.
 */

import org.mhmod.jfsm.StateMachine;

public class App {

    public static void main(String[] args){

        MainObject object = new MainObject();

        StateMachine<MainObject> stateMachine = new StateMachine<MainObject>("Test State machine",
                new FirstState(), null, null, object);

        stateMachine.trigger(Constants.CMD_START, "Hello World");

        stateMachine.trigger(Constants.CMD_END, "Hello World");

    }


}
