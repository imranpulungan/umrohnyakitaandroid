package com.ikhwanul.ikhlas.iiwandroid.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.widget.DatePicker;

import com.ikhwanul.ikhlas.iiwandroid.utils.DateUtils;
import java.util.Calendar;

/**
 * Created by januar on 1/18/18.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private OnCompleteDatePickerLister mListener;
    private Calendar mCalender;
    private long mMinDate;
    private long mMaxDate;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PatientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DatePickerFragment newInstance(FragmentManager fragmentManager, Calendar calendar,
                                                 OnCompleteDatePickerLister lister) {
        DatePickerFragment fragment = newInstance(fragmentManager, calendar, lister, 0, 0);
        return fragment;
    }

    public static DatePickerFragment newInstance(FragmentManager fragmentManager, Calendar calendar,
                                                 OnCompleteDatePickerLister lister, long minDate, long maxDate) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.mCalender = calendar;
        fragment.mListener = lister;
        fragment.mMinDate = minDate;
        fragment.mMaxDate = maxDate;
        fragment.show(fragmentManager, "datePicker");
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        int year = mCalender.get(Calendar.YEAR);
        int month = mCalender.get(Calendar.MONTH);
        int day = mCalender.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
        if (mMinDate > 0 ) {
            dialog.getDatePicker().setMinDate(mMinDate);
        }
        if (mMaxDate > 0){
            dialog.getDatePicker().setMaxDate(mMaxDate);
        }

        return dialog;
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        mListener.onComplete(DateUtils.format(year + "-" + (month + 1) + "-" + day));
    }

    public interface OnCompleteDatePickerLister{
        void onComplete(String date);
    }
}
