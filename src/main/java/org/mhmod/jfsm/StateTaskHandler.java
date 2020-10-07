/*
 * Copyright (c) 2020 By Mahmoud Ibrahim
 * This class is written as a contribution for the open source community under GPL license.
 * Author: Mahmoud Ibrahim
 * upworks: https://www.upwork.com/o/profiles/users/~01c04883943f6add8f/
 * github: https://github.com/mhmod1990.
 */

package org.mhmod.jfsm;

import org.mhmod.logger.JfsmLog;

/**
 *  class which handle the FSM in separare thread
 */
public class StateTaskHandler extends Thread {

    private static final String TAG = "[StateTaskHandler]";
    // flag used to stop the operation of the fsm
    private boolean mStop = false;

    public StateHandler mCurStateHandler;
    private StateMachine mStateMachine;
    private StateMachine.StateEventsHandler mEventsHandler;
    private StateHandler mInitStateHandler;

    public StateTaskHandler(StateMachine statemachine, StateMachine.StateEventsHandler eventsHandler, StateHandler init) {
        mStateMachine = statemachine;
        mCurStateHandler = init;
        mInitStateHandler = init;
        mEventsHandler = eventsHandler;
    }

    /**
     *  override thread run
     */
    @Override
    public void run() {
        while (!mStop) {
            //if the messages is empty lock the loop untill the next notify
            if (mEventsHandler.isEmpty())
                synchronized (mEventsHandler) {
                    try {
                        mEventsHandler.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            //closed signal received which released the lock
            if (mEventsHandler.isEmpty())
                continue;

            JfsmLog.d(TAG, mCurStateHandler.getStateName() + " received message");
            StateHandler.Event event = mEventsHandler.poll();

            if(event == null){
                JfsmLog.e(TAG, "null event");
                continue;
            }

            if(mStateMachine.getNextState() == null)
                mStateMachine.setNextState(mInitStateHandler);

            mCurStateHandler = mStateMachine.getNextState();

            if(mCurStateHandler == null) {
                JfsmLog.e(TAG, "no current State handler ");
                mCurStateHandler = mInitStateHandler;
            }

            if(! mCurStateHandler.accept(event)) {
                JfsmLog.w(TAG, "invalid command "+event.getCommand()+" to this state "+mCurStateHandler.getStateName());
                continue;
            }

            switch (mCurStateHandler.handle(mStateMachine, event)){
                case StateHandler.STATECODE_ONGOING:
                    mEventsHandler.add(event);
                    break;
                case StateHandler.STATECODE_BREAK:
                case StateHandler.STATECODE_WAITDATA:
                    break;

            }

        }
    }
}