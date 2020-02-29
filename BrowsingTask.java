package com.mandin.antoine.spacemanager.v2;

import android.os.AsyncTask;
import android.util.Log;

import com.mandin.antoine.spacemanager.v2.model.DirectoryElement;
import com.mandin.antoine.spacemanager.v2.model.Element;
import com.mandin.antoine.spacemanager.v2.model.FileElement;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrowsingTask extends AsyncTask<String, BrowsingTask.ProgressValue, Long[]> {
    private static final String TAG = "BrowsingTask";
    private static final int SIZE_INDEX = 0, FILE_COUNT_INDEX = 1, LAST_USE_INDEX = 2;
    private Long[] values = new Long[3];
    private ProgressValue progressValue = new ProgressValue();
    private ProgressHandler progressHandler;
    private List<Element> elements = new ArrayList<>();

    public BrowsingTask setProgressHandler(ProgressHandler progressHandler) {
        this.progressHandler = progressHandler;
        return this;
    }

    private static final boolean LOG_INCREMENTATION = true;

    public BrowsingTask() {
        super();
        if (Utils.debugging)
            Log.i(TAG, "<init>");
    }

    @Override
    protected Long[] doInBackground(String... strings) {
        if (strings == null || strings.length == 0 || strings[0] == null) {
            if(Utils.debugging)
                Log.e(TAG,"doInBackground. Error -> invalid params", new Throwable());
            return values;
        }

        File current = new File(strings[0]);
        if (current.isDirectory()) {
            File[] children = current.listFiles();

            for (File child : children) {
                Arrays.fill(values, 0L);

                Element el;
                if(proceed(child)){
                    //child is a directory
                    el = new DirectoryElement(
                            values[LAST_USE_INDEX],
                            child.getPath(),
                            values[SIZE_INDEX],
                            child.getName(),
                            values[FILE_COUNT_INDEX]

                    );
                } else {
                    //Child is a file
                    el = new FileElement(
                            values[LAST_USE_INDEX],
                            child.getPath(),
                            values[SIZE_INDEX],
                            child.getName()
                    );
                }

                if (Utils.debugging && LOG_INCREMENTATION)
                    Log.i(TAG, "doInBackground -> Incrementation (size = "+values[SIZE_INDEX]+", file scanned = "+values[FILE_COUNT_INDEX]+")");

                progressValue.element = el;
                elements.add(el);
                publishProgress(progressValue);
                progressValue.element = null;
            }
        } else {
            if(Utils.debugging)
                Log.e(TAG,"doInBackground. Error -> given path isn't linked to a directory", new Throwable());
        }

        if(Utils.debugging){
            Log.i(TAG,"Elements scanned : ");
            for(Element element : elements){
                Log.i(TAG,"- "+element.toString());
            }
        }

        return values;
    }

    @Override
    protected void onProgressUpdate(ProgressValue... values) {
        super.onProgressUpdate(values);
        if(progressHandler != null)
            progressHandler.onProgress(values);
    }

    //@return if the file is a directory
    private boolean proceed(File f) {
        progressValue.scannedFiles++;
        publishProgress(progressValue);

        values[LAST_USE_INDEX] = Math.max(values[LAST_USE_INDEX], f.lastModified());

        if (f.isDirectory()) {
            File[] children = f.listFiles();

            for (File child : children)
                proceed(child);

            return true;
        } else {
            values[SIZE_INDEX] += f.length();
            values[FILE_COUNT_INDEX]++;
            return false;
        }
    }

    public static class ProgressValue {
        private Element element;
        private long scannedFiles;

        public ProgressValue() {
            this.element = null;
            this.scannedFiles = 0;
        }

        public Element getElement() {
            return element;
        }

        public long getScannedFiles() {
            return scannedFiles;
        }
    }

    public interface ProgressHandler {
        void onProgress(ProgressValue... progressValues);
    }
}
