package com.example.raulbarbosa.ihclogin;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class AddPlan extends Fragment {
    public Button chest;
    private Context context;
    public static AddPlan newInstance() {
        AddPlan fragment = new AddPlan();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=  inflater.inflate(R.layout.addplan, container, false);
        chest = v.findViewById(R.id.chest_button);
        chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getView().getContext(),Plan1.class));
                Fragment selectedFragment = Chest.newInstance();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
                Toast toast = Toast.makeText(getActivity(), "dentro do click plano 1", Toast.LENGTH_LONG);
                toast.show();
            }
        });


        return v;
    }
}

