package com.example.dny.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> Todo = null;
    ArrayAdapter<String> adapter = null;
    ListView lv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toast t1=Toast.makeText(getApplicationContext(), "Press \"+\" to add a Task",
                Toast.LENGTH_LONG);
        t1.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        t1.show();
        Toast t2=Toast.makeText(getApplicationContext(), "Long click on a task to mark it as Done",
                Toast.LENGTH_LONG);
        t2.show();


        Todo = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Todo);
        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);
        lv.setBackgroundColor(Color.parseColor("#F2FBFB"));


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (int i = 0; i < parent.getChildCount(); i++) {
                    parent.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }

                view.setBackgroundColor(Color.parseColor("#ADFDEB"));

            }
        });



        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder delAlert = new AlertDialog.Builder(MainActivity.this);
                delAlert.setTitle("Task Completed");
                delAlert.setIcon(R.drawable.ic_warning_black_24dp);
                delAlert.setMessage("Mark this task as Done");
                delAlert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Todo.remove(position);
                        lv.setAdapter(adapter);
                        Toast.makeText(getApplicationContext(), "Congratulations!! You did it!!",
                                Toast.LENGTH_LONG).show();
                        return;

                    }
                });

                delAlert.setNegativeButton("To Do", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e("Not deleted", "yet");
                        return;
                    }
                });
                delAlert.show();
                return true;

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id==R.id.add_Item)
        {
            Log.e("add_item", "New Item");
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setIcon(R.drawable.ic_control_point_black_24dp);


            final EditText tv= new EditText(this);


            alert.setView(tv);


            alert.setTitle("Add New");
            alert.setMessage("What do you want to do next?");

            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Log.e("Seletcted None", "None");
                    return;
                }
            });
            alert.setPositiveButton("Do it", new DialogInterface.OnClickListener() {
                @Override

                public void onClick(DialogInterface dialog, int which) {
                    final String str= String.valueOf(tv.getText());
                    Todo.add(str);
                    lv.setAdapter(adapter);
                    Log.e("New Item added:", "Added");
                    Toast.makeText(getApplicationContext(), "+ve I love you", Toast.LENGTH_LONG);
                    return;
                }
            });
            alert.show();

            return true;
        }
        if(id==R.id.del_Item)
        {
            Log.e("Entered Del", "item");
            AlertDialog.Builder del= new AlertDialog.Builder(this);
            del.setTitle("Delete All");
            del.setIcon(R.drawable.ic_delete_forever_black_24dp);
            del.setMessage("Are you sure you want to empty your Todo List?");
            del.setPositiveButton("Empty", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Todo.clear();
                    lv.setAdapter(adapter);

                }
            });

            del.show();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    }

