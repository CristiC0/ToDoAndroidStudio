package com.example.whattodo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EditTodo extends AppCompatActivity {
    private Button change;
    private EditText text;
    private RadioGroup type;

    private RadioButton selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        change=(Button) findViewById(R.id.add);
        text=(EditText) findViewById(R.id.text);
        type=(RadioGroup) findViewById(R.id.type);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            text.setText(extras.getString("currentText"));
            String t=extras.getString("currentType");
            if(t.equals("Important")) ((RadioButton) findViewById(R.id.i)).toggle();
            if(t.equals("Less Important")) ((RadioButton) findViewById(R.id.li)).toggle();
            if(t.equals("Not Important")) ((RadioButton) findViewById(R.id.ni)).toggle();

            change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Database db=new Database(getApplicationContext());
                    db.updateTodo(extras.getInt("currentId"),text.getText().toString(),selected.getText().toString());

                    Intent i = new Intent(EditTodo.this, MainActivity.class);
                    startActivity(i);
                }
            });

            type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    selected=(RadioButton) findViewById(checkedId);
                }
            });

        }






    }
}