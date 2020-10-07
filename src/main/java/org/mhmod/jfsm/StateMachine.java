/*
 * Copyright (c) 2020 By Mahmoud Ibrahim
 * This class is written as a contribution for the open source community under GPL license.
 * Author: Mahmoud Ibrahim
 * upworks: https://www.upwork.com/o/profiles/users/~01c04883943f6add8f/
 * github: https://github.com/mhmod1990.
 */

package org.mhmod.jfsm;

import org.mhmod.logger.JfsmLog;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * the stateMchine class which use generic object as the data or object which will need to have certain states or modes.
 * @param <T>
 */
public class StateMachine <T>{

    // define fsm constants
    private static final String TAG = "[StateMachine]";
    public static final int CMD_START = 0;

    // define the variables which store the defaults of the fsm
    private  String mStateMachineName;
    private  StateHandler mInitStateHandler;
    private  StateHandler mStateHandlerBefore;
    private  StateHandler mStateHandlerAfter;
    private StateHandler mStatehandlerNext;
    private StateTaskHandler mTaskHandler;

    // Store the object which this fsm will use or the data it will store
    private final T mData;

    // the emiiter where the FSM will use to emit an events
    private StateMachineEmitter mEmitter;

    private int mCurrentStateCode;
    protected StateEventsHandler mEventsHandler;


    /**
     * construct the statemachine and the handlerthread with the default values
     * @param stateMachineName  the name of the statemachine
     * @param initStateHandler  the initial state that the fsm will use to start the FSM
     * @param stateHandlerBefore  it is a hook to be executed before any state
     * @param stateHandlerAfter   it is a hook to be executed after any state
     * @param data  it is the Object where the FSM will operate or data to be stored
     */
    public StateMachine(String stateMachineName, StateHandler initStateHandler, StateHandler stateHandlerBefore,
                        StateHandler stateHandlerAfter, T data) {
        mStateMachineName = stateMachineName;
        mInitStateHandler = initStateHandler;
        mStateHandlerBefore = stateHandlerBefore;
        mStateHandlerAfter = stateHandlerAfter;
        mData = data;
        mEventsHandler = new StateEventsHandler();

        mTaskHandler = new StateTaskHandler(this, mEventsHandler, mInitStateHandler);
        mTaskHandler.start();
    }


    /**
     * start the operation of the FSM
     */
    public void start(){

            mEventsHandler.add(new StateHandler.Event(this, CMD_START));
        synchronized (mEventsHandler) {
            mEventsHandler.notifyAll();
        }
    }

    /**
     * trigger a transition of the FSM which certain command and send data with this command
     * @param command   the command id which trigger the FSM
     * @param data      the data which will be sent with this command
     */
    public void trigger(int command,Object data){

            mEventsHandler.add(new StateHandler.Event(data, command));
        synchronized (mEventsHandler) {
            mEventsHandler.notifyAll();
        }
    }

    /**
     * get the current state code
     * @return  int the code of the current state
     */
    public int getCurrentStateCode() {
        return mTaskHandler.mCurStateHandler.getStateCode();
    }

    /**
     * get the next state of the fsm that will be executed in the next trigger
     * @return
     */
    public StateHandler getNextState() {
        return mStatehandlerNext;
    }

    /**
     * set the next state of the fsm that will be executed in the next trigger
     * @param mStatehandlerNext
     */
    public void setNextState(StateHandler mStatehandlerNext) {
        this.mStatehandlerNext = mStatehandlerNext;
    }

    /**
     * get the emitter of the fsm
     * @return stateMachineEmitter which the fsm will use to send notification
     */
    public StateMachineEmitter getEmitter() {
        return mEmitter;
    }

    /**
     * set the emitter of the fsm
     * @param mEmitter stateMachineEmitter which the fsm will use to send notification
     */
    public void setEmitter(StateMachineEmitter mEmitter) {
        this.mEmitter = mEmitter;
    }

    /**
     *
     * @return
     */
    public T getData() {
        return mData;
    }


    /**
     * StateEventHandler which Queue the events to be triggered one by one
     */
    public static class StateEventsHandler  extends LinkedBlockingQueue<StateHandler.Event>{

    }

}
