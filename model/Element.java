package com.mandin.antoine.spacemanager.v2.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.mandin.antoine.spacemanager.v2.Utils;

import java.text.DateFormat;
import java.util.Calendar;

public abstract class Element {
    protected final long lastUsed;
    protected final String path;
    protected final String name;
    protected final long size;
    private static final String TAG = "model.Element";

    public Element(long lastUsed, String path, long size, String name) {
        this.lastUsed = lastUsed;
        this.path = path;
        this.size = size;
        this.name = name;
        if(Utils.debugging)
            Log.i(TAG,"<init> : lastUsed("+lastUsed+"), path("+path+"), size("+size+")");
    }

    @NonNull
    @Override
    public String toString() {
        return name+" - "+Utils.formatSize(size);
    }
}
