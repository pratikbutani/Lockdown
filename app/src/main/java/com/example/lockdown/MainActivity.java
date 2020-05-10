package com.example.lockdown;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String COLLECTION = "lockdownDate";
    EditText editText;
    FirebaseFirestore database;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        recyclerView = findViewById(R.id.recyclerView);

        database = FirebaseFirestore.getInstance();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        database.collection(COLLECTION).orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (queryDocumentSnapshots != null) {
                    List<LockDownModel> lockDownModelList = queryDocumentSnapshots.toObjects(LockDownModel.class);
                    MyAdapter adapter = new MyAdapter(lockDownModelList);
                    recyclerView.setAdapter(adapter);
                }
            }
        });


    }

    public void buttonClick(View view) {
        String text = editText.getText().toString();

        LockDownModel lockDownModel = new LockDownModel();
        lockDownModel.setMyTask(text);

        database.collection(COLLECTION).document().set(lockDownModel);

        editText.setText("");

        Toast.makeText(this, "Added Value : " + text, Toast.LENGTH_SHORT).show();
    }
}
