package com.github.ivarref.ideafinda;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorModificationUtilEx;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;

// https://intellij-support.jetbrains.com/hc/en-us/community/posts/206804965-problem-inserting-text-from-menu-action
public class InsertCommaSpaceAction extends EditorAction {
    public InsertCommaSpaceAction() {
        super(new Handler());
    }

    private static class Handler extends EditorWriteActionHandler {
        public final void executeWriteAction(Editor editor, Caret caret, DataContext dataContext) {
            EditorModificationUtilEx.insertStringAtCaret(editor, ", ");
        }
    }
}
