package edx.ccano.android.tipcalc.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import edx.ccano.android.tipcalc.Adapters.OnItemClickListener;
import edx.ccano.android.tipcalc.Adapters.TipAdapter;
import edx.ccano.android.tipcalc.Models.TipRecord;
import edx.ccano.android.tipcalc.R;
import edx.ccano.android.tipcalc.activities.TipDetailActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener, OnItemClickListener {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private TipAdapter adapter;

    public TipHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tip_history_list, container, false);
        ButterKnife.bind(this, view);
        initAdapter();
        initRecyclerView();
        return view;
    }

    private void initAdapter() {
        //solo sera null la primera vez. Asi los datos se mantienen frente a un cambio de configuracion
        if (adapter == null){
            adapter = new TipAdapter(getActivity().getApplicationContext(), this);
        }
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void addList(TipRecord record) {
        adapter.add(record);
    }

    @Override
    public void clearList() {
        adapter.clear();
    }

    @Override
    public void onItemClick(TipRecord tipRecord) {
        Intent intent = new Intent(getActivity(), TipDetailActivity.class);

        intent.putExtra(TipDetailActivity.TIP_KEY, tipRecord.getTip());
        intent.putExtra(TipDetailActivity.BILL_TOTAL_KEY, tipRecord.getBill());
        intent.putExtra(TipDetailActivity.DATE_KEY, tipRecord.getDateFormated());

        startActivity(intent);
    }
}
