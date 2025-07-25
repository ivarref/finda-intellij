package com.github.ivarref.ideafinda;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ex.ActionUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

public class NextWhateverAction extends AnAction {

    public enum NextAction {
        CHANGE, ERROR
    }

    public static NextAction currentAction = NextAction.ERROR;

    @Override
    public final void update(@NotNull AnActionEvent event) {
        final Project project = event.getProject();
        event.getPresentation().setEnabledAndVisible(null != project);
    }

    @Override
    public final void actionPerformed(@NotNull AnActionEvent e) {
        Messages.showInfoMessage("::" + currentAction, "Next action is ...");
//        e.getActionManager().getAction("janei").get
    }

    public final @NotNull ActionUpdateThread getActionUpdateThread() {
        return super.getActionUpdateThread();
    }

    public static final void run(@NotNull AnActionEvent e, NextAction action) {
        currentAction = action;
        AnAction selfAction = e.getActionManager().getAction("com.github.ivarref.ideafinda.NextWhateverAction");
        ActionUtil.performActionDumbAwareWithCallbacks(selfAction, e);
    }



}
