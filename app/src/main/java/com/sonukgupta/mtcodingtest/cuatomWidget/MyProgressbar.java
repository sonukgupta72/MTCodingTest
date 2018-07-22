package com.sonukgupta.mtcodingtest.cuatomWidget;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sonukgupta.mtcodingtest.R;

/**
 * Created by incredsonu on 22/7/18.
 */

public class MyProgressbar {

    public static AlertDialog getSimpleProgressDialogue(Context context, boolean cancellable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View view = inflater.inflate(R.layout.layout_progress_dialogue, null);

        builder.setView(view)
                .setCancelable(cancellable);

        return builder.create();
    }
}
