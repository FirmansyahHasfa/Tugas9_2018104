package com.example.tugas4;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.tugas4.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    RecyclerView recylerView;
    private FragmentHomeBinding binding;
    String s1[], s2[],s3[];
    int images[] =
            {R.drawable.bolafutsal,R.drawable.jerseybasket,R.drawable.jerseyfutsal};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        final OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        binding.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance().enqueueUniqueWork(
                        "SUKSES", ExistingWorkPolicy.REPLACE, request);
            }
        });
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recylerView = getActivity().findViewById(R.id.recyclerViewToko);
        s1 = getResources().getStringArray(R.array.star);
        s2 = getResources().getStringArray(R.array.barang);
        s3 = getResources().getStringArray(R.array.star);
        HomeAdapter appAdapter = new HomeAdapter(getActivity(), s1, s2, images);
        recylerView.setAdapter(appAdapter);
        recylerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL,false));
    }
}
