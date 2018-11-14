package com.example.linuxlite.myapplication.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linuxlite.myapplication.R;
import com.example.linuxlite.myapplication.adapter.DashboardAdapter;
import com.example.linuxlite.myapplication.model.KotaModel;
import com.example.linuxlite.myapplication.model.ListKota;
import com.example.linuxlite.myapplication.player.RadioManager;
import com.example.linuxlite.myapplication.service.MyService;
import com.example.linuxlite.myapplication.utils.Server;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Dashboard.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Dashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dashboard extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private SharedPreferences sharedPreferences;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressDialog progressDialog;

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    @BindView(R.id.playTrigger)
    ImageButton trigger;

    @BindView(R.id.my_recycleView)
    ListView listView;

    @BindView(R.id.name)
    TextView textView;

    @BindView(R.id.sub_player)
    View subPlayer;


    public Dashboard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Dashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static Dashboard newInstance(String param1, String param2) {
        Dashboard fragment = new Dashboard();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycleView);

        progressDialog = new ProgressDialog(getActivity());

        getKota();
        return view;

    }

    private void getKota() {
        progressDialog.setTitle("Memuat data");
        progressDialog.setMessage("Tunggu Sebentar ...");
        progressDialog.show();

        MyService apiService = Server.getClient().create(MyService.class);
        Call<ListKota> call = apiService.getKota();
        call.enqueue(new Callback<ListKota>() {
            @Override
            public void onResponse(Call<ListKota> call, Response<ListKota> response) {
                ListKota listKota = response.body();

                try {
                    if(listKota.getSuccess() == true){
                        List<KotaModel> list = listKota.getData();

                        DashboardAdapter dashboardAdapter = new DashboardAdapter(list, getContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setHasFixedSize(true);
                        layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(dashboardAdapter);
                        progressDialog.dismiss();
                    }else{
//                        Toast.makeText(getActivity(), listJadwal.getMsg(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ListKota> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                if (progressDialog != null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

//    @OnClick(R.id.playTrigger)
//    public void onClicked(){
//
//        if(TextUtils.isEmpty(streamURL)) return;
//
//        radioManager.playOrPause(streamURL);
//    }
//
//    @OnItemClick(R.id.my_recycleView)
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
//
//        KotaModel shoutcast = (KotaModel) parent.getItemAtPosition(position);
//        if(shoutcast == null){
//
//            return;
//
//        }
//
//        textView.setText(shoutcast.getKota());
//
//        subPlayer.setVisibility(View.VISIBLE);
//
//
//    }
}
