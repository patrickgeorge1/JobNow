package com.example.jobnow.activity.mainActivity.chatPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.jobnow.R;
import com.example.jobnow.SingletonDatabase;

import java.util.List;

public class ChatFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.close();
        inflater.inflate(R.menu.search, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_main, container, false);
        ListView listView = view.findViewById(R.id.listView_chats);

        final List<String> chatList = SingletonDatabase.getInstance().getChats();
        final ArrayAdapter<String> chatListAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, chatList);
        listView.setAdapter(chatListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "List Item " + position, Toast.LENGTH_SHORT).show();
                chatListAdapter.remove(chatList.get(position));
            }
        });



        return view;
    }
}