package com.bolyartech.forgeexamples.basic.dialogs;

import android.app.DialogFragment;
import android.app.FragmentManager;


public final class MyDialogs {
    // Non-instantiable utility class
    private MyDialogs() {
        throw new AssertionError("No instances allowed");
    }


    public static void showCommProblemDialog(FragmentManager fm) {
        if (fm.findFragmentByTag(DfCommProblem.DIALOG_TAG) == null) {
            DfCommProblem fra = new DfCommProblem();
            fra.show(fm, DfCommProblem.DIALOG_TAG);
            fm.executePendingTransactions();
        }
    }


    public static void showCommWaitDialog(FragmentManager fm) {
        if (fm.findFragmentByTag(DfCommWait.DIALOG_TAG) == null) {
            DfCommWait fra = new DfCommWait();
            fra.show(fm, DfCommWait.DIALOG_TAG);
            fm.executePendingTransactions();
        }
    }


    public static boolean hideCommWaitDialog(FragmentManager fm) {
        DialogFragment df = (DialogFragment) fm.findFragmentByTag(DfCommWait.DIALOG_TAG);
        if (df != null) {
            df.dismiss();
            return true;
        } else {
            return false;
        }
    }
}
