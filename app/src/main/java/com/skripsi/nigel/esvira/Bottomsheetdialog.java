package com.skripsi.nigel.esvira;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;

public class Bottomsheetdialog extends BottomSheetDialogFragment {
    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View v = View.inflate(getContext(), R.layout.bottomsheet_layout,null);
        dialog.setContentView(v);
    }
}

