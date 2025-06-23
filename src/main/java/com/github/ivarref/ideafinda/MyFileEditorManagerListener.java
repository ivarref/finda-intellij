package com.github.ivarref.ideafinda;

import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class MyFileEditorManagerListener implements FileEditorManagerListener {
    private final Project project;

    public MyFileEditorManagerListener(Project project) {
        this.project = project;
    }

    public static void doLog(String msg) {
        String fileName = System.getProperty("user.home") + "/.finda/ideaplugin.log";
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, StandardCharsets.UTF_8, true))) {
            pw.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
        doLog("***************************************************");
        String basePath = source.getProject().getBasePath();
        String enc = URLEncoder.encode(basePath, StandardCharsets.UTF_8);

        String jsonFileName = System.getProperty("user.home") + "/.finda/external_data/idea_project_" + enc + ".json";
//        String meh = basePath.split(Pattern.quote("/"));

        doLog("Project base path is: " + enc);

        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(jsonFileName, StandardCharsets.UTF_8, false)))) {
            pw.println("[");

            for (VirtualFile vf : source.getOpenFiles()) {
                if (vf != source.getOpenFiles()[0]) {
                    pw.println(",");
                }
                pw.println("{");
                String label = "janei: " + vf.getName();
                String command = "python3 " + System.getProperty("user.home") + "/code/ideaopen/ideaopen.py " + vf.getCanonicalPath();
                pw.println("\"label\": \"" +label + "\",");
                pw.println("\"command\": \"" + command + "\"");
                pw.println("}");
                doLog("Open file: " + vf + " " + vf.getCanonicalPath());
            }

            pw.println("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
        doLog("Wrote file: " + jsonFileName);
    }

    @Override
    public void fileClosed(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
        fileOpened(source, file);
    }
}
