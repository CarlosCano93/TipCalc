package edx.ccano.android.tipcalc.fragments;

import edx.ccano.android.tipcalc.Models.TipRecord;

/**
 * Created by Carlos on 12/06/2016.
 */
public interface TipHistoryListFragmentListener {
    void addList (TipRecord record);
    void clearList();
}
