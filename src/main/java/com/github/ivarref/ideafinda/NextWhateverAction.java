package com.github.ivarref.ideafinda;

public class NextWhateverAction {

    public enum NextAction {
        CHANGE, ERROR
    };

    public static NextAction currentNextAction = NextAction.ERROR;


}
