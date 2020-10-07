/*
 * Copyright (c) 2020 By Mahmoud Ibrahim
 * This class is written as a contribution for the open source community under GPL license.
 * Author: Mahmoud Ibrahim
 * upworks: https://www.upwork.com/o/profiles/users/~01c04883943f6add8f/
 * github: https://github.com/mhmod1990.
 */

import org.mhmod.jfsm.StateHandler;
import org.mhmod.jfsm.StateMachine;
import org.mhmod.logger.JfsmLog;

public class FirstState extends StateHandler {

    private static final String TAG = "[FirstState]";

    public FirstState() {
        super("First State", Constants.FIRST_STATE);
    }

    protected void registerReceiveCmd() {
        addAcceptedCmd(Constants.CMD_START);
    }

    protected int handle(StateMachine stateMachine, Event event) {

        switch (event.getCommand()){
            case Constants.CMD_START:
                JfsmLog.d(TAG, "start Command with data "+event.getData());

                ((MainObject) stateMachine.getData()).doSomthingInFisrtState();
                // move to the next state
                stateMachine.setNextState(new LastState());

                // break and wait for another event
                return StateHandler.STATECODE_BREAK;
        }

        // move to the next state
        return StateHandler.STATECODE_ONGOING;
    }
}
