package com.github.ivarref.ideafinda;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;


public class PopupDialogAction extends AnAction {

    @Override
    public void update(@NotNull AnActionEvent event) {
        final Project project = event.getProject();
        event.getPresentation().setEnabledAndVisible(project != null);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        // Using the event, implement an action.
        // For example, create and show a dialog.
        final Project currentProject = event.getProject();

        final StringBuilder message =
                new StringBuilder(event.getPresentation().getText() + " janei\n\nSelected!");

        // Project
        // ProjectManager.getInstance().

        for (Project p : ProjectManager.getInstance().getOpenProjects()) {
            message.append("\nproject: " + p.getName());
        }

        // If an element is selected in the editor, add info about it.
        final Navigatable selectedElement = event.getData(CommonDataKeys.NAVIGATABLE);
        if (selectedElement != null) {
            message.append("\njanei janei\nSelected Element: ").append(selectedElement);
        }
        final String title = event.getPresentation().getDescription();
        Messages.showMessageDialog(
                currentProject,
                message.toString(),
                title,
                Messages.getInformationIcon());

        Messages.showInfoMessage("Hello World", "my title");
    }

    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return super.getActionUpdateThread();
    }

    // Override getActionUpdateThread() when you target 2022.3 or later!

}