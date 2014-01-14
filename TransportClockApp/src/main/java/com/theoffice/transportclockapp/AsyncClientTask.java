package com.theoffice.transportclockapp;

import android.os.AsyncTask;

/**
 * Created by snake on 14.01.14.
 */
public class AsyncClientTask extends AsyncTask<ClientTask, Void, ClientTask > {


    public interface TaskUpdateListener {
        public void onTaskUpdate(ClientTask task);
    }

    TaskUpdateListener mTaskListener;


    public AsyncClientTask(TaskUpdateListener listener) {
        mTaskListener = listener;
    }

    @Override
    protected ClientTask doInBackground(ClientTask... params) {
        ClientTask t = params[0];
        t.run();
        return t;
    }
    @Override
    protected void onPostExecute(ClientTask clientTask) {
        mTaskListener.onTaskUpdate(clientTask);

    }
}
