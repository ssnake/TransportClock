package com.snake.transportclockapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by snake on 14.01.14.
 */
public class AsyncClientTask extends AsyncTask<ClientTask, ClientTask, ClientTask > {


    public interface TaskUpdateListener {
        public void onTaskUpdate(ClientTask task);
        public Context getContext();
    }

    TaskUpdateListener mTaskListener;
    ProgressDialog mDialog;
    Boolean mShowDialog;

    public AsyncClientTask(TaskUpdateListener listener) {
        this(listener, true);
    }
    public AsyncClientTask(TaskUpdateListener listener, Boolean showDialog) {
        mTaskListener = listener;
        mShowDialog = showDialog;

    }

    @Override
    protected ClientTask doInBackground(ClientTask... params) {

        ClientTask t = params[0];


        this.publishProgress(t);
        t.run();
        return t;
    }

    @Override
    protected void onPreExecute() {
        if (!mShowDialog)
            return;
        Context c = mTaskListener.getContext();
        mDialog = ProgressDialog.show(c, "", c.getResources().getString(R.string.loading), true);
    }

    @Override
    protected void onPostExecute(ClientTask clientTask) {
        mTaskListener.onTaskUpdate(clientTask);
        if (mShowDialog)
            mDialog.dismiss();

    }

    @Override
    protected void onProgressUpdate(ClientTask... values) {
        if (mShowDialog)
            mDialog.setMessage(AppHelper.getTaskName(mTaskListener.getContext(), values[0]));


    }
}
