package com.mandin.antoine.spacemanager.v2.model;

import android.util.Log;

import com.mandin.antoine.spacemanager.v2.Utils;

public class FileElement extends Element {
    private static final String TAG = "model.FileElement";
    //TODO type (extension)

    public FileElement(long lastUsed, String path, long size, String name) {
        super(lastUsed, path, size, name);

        if(Utils.debugging)
            Log.i(TAG,"<init>");
    }
}
