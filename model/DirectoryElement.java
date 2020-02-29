package com.mandin.antoine.spacemanager.v2.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.mandin.antoine.spacemanager.v2.Utils;

public class DirectoryElement extends Element {
    private static final String TAG = "model.DirectoryElement";
    private long fileCount;

    public DirectoryElement(long lastUsed, String path, long size, String name, long fileCount) {
        super(lastUsed, path, size, name);
        this.fileCount = fileCount;

        if(Utils.debugging)
            Log.i(TAG,"<init> : fileCount("+fileCount+")");
    }


}
