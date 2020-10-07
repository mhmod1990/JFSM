/*
 * Copyright (c) 2020 By Mahmoud Ibrahim
 * This class is written as a contribution for the open source community under GPL license.
 * Author: Mahmoud Ibrahim
 * upworks: https://www.upwork.com/o/profiles/users/~01c04883943f6add8f/
 * github: https://github.com/mhmod1990.
 */

package org.mhmod.jfsm;

import java.util.ArrayList;
import java.util.List;

/**
 * StateHandler that all the states must extends to be registered and work inside the FSM
 */
public abstract class StateHandler {

    // class constants
    private static final String TAG = "[CallState]";

    // FSM end of state transition state
    public static final int STATECODE_ONGOING = 0;
    public static final int STATECODE_BREAK = 1;
    public static final int STATECODE_WAITDATA = 2;

    // State informations
    private String mStateName;
    private int mStateCode;

    // the allowed commands for this state
    protected List mReceiveCmdList = new ArrayList();


    /**
     * state constructor which used to set the state information & register the allowed commands
     * @param stateName string state name
     * @param stateCode int state code
     */
    public StateHandler(String stateName, int stateCode){
        this.mStateName = stateName;
        this.mStateCode = stateCode;
        registerReceiveCmd();
    }

    /**
     * register the allowed commands
     */
    protected abstract void registerReceiveCmd();

    protected void addAcceptedCmd(int command){
        mReceiveCmdList.add(command);
    }
    /**
     * this fucntion check if the incoming command is allowed to be received by this state or not
     * @param event
     * @return
     */
    public boolean accept(Event event){

        if (event == null) {
            return false;
        }
        if (mReceiveCmdList.contains(event.getCommand())) {
            return true;
        }

        return false;
    }

    /**
     * abstract function to handle the command which has been triggered
     * @param stateMachine  The Instance of the StateMachine that this state registered on
     * @param event         the event which contains the command
     * @return  int which represent the transitin state, should it be
     */
    protected abstract int handle(StateMachine stateMachine, Event event);

    /**
     * Event Class which contains the command and the data which will be sent to the current state
     */
    public static class Event {

        // Event data & command
        private Object mData;
        private int mCommand;

        /**
         * The Event constructor to set the default values of Event
         * @param mData object which is the requires object
         * @param mCommand int the command type that has been triggered
         */
        public Event(Object mData, int mCommand) {
            this.mData = mData;
            this.mCommand = mCommand;
        }

        /**
         * get the object
         * @return
         */
        public Object getData() {
            return mData;
        }

        /**
         * set the object which will be sent to the current state
         * @param data
         */
        public void setData(Object data) {
            this.mData = data;
        }

        /**
         * get the command of the transition
         * @return
         */
        public int getCommand() {
            return mCommand;
        }

        /**
         * set the command of the transition
         * @param command
         */
        public void setCommand(int command) {
            this.mCommand = command;
        }

        @Override
        public String toString() {
            return "Event{" +
                    "mData=" + mData +
                    ", mCommand=" + mCommand +
                    '}';
        }
    }

    /**
     * get this state name
     * @return
     */
    public String getStateName() {
        return mStateName;
    }

    /**
     * return this state code
     * @return
     */
    public int getStateCode() {
        return mStateCode;
    }

    @Override
    public String toString() {
        return "StateHandler{" +
                "mStateName='" + mStateName + '\'' +
                ", mStateCode=" + mStateCode +
                '}';
    }
}
