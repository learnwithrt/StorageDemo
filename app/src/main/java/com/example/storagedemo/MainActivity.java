package com.example.storagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    //Multiline text - EditText
    EditText content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content=findViewById(R.id.content);
        //Actions for the buttons
        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clear the edit text
                content.setText("");
            }
        });
        findViewById(R.id.read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Read from the file
                FileInputStream fis= null;
                try {
                    fis = openFileInput("Data.txt");
                    //use fis object for inputstreamreader
                    InputStreamReader isr=new InputStreamReader(fis);
                    BufferedReader br=new BufferedReader(isr);
                    String info="";//info will have many lines
                    String line="";//one line read from the file
                    while((line=br.readLine())!=null) {
                        info += line;//append line to the info
                        info += "\n";
                    }

                    //Write on the editext
                    content.setText(info);
                    br.close();
                    fis.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        findViewById(R.id.write).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //You want to read information from content
                String info=content.getText().toString();
                //Write information to a file
                try {
                    FileOutputStream fos=openFileOutput("Data.txt",MODE_PRIVATE);//write information
                    //use file output stream to create a outputstreamwriter
                    OutputStreamWriter osw=new OutputStreamWriter(fos);
                    //use osw object to create a BufferedWriter object
                    //BufferedReader
                    BufferedWriter bw=new BufferedWriter(osw);
                    info+="\n";//add new line after last character
                    bw.write(info);
                    //send information from buffer to the file
                    bw.flush();

                    //close all streams and files
                    bw.close();
                    fos.close();
                    //Display a message to tell information is successfully writen to file
                    Toast.makeText(getBaseContext(),"File Created Successfully",Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        findViewById(R.id.append).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //You want to read information from content
                String info=content.getText().toString();
                //Write information to a file
                //I do not want to overwrite information, i want to add information to the file
                try {
                    FileOutputStream fos=openFileOutput("Data.txt",MODE_APPEND);//Append information
                    //use file output stream to create a outputstreamwriter
                    OutputStreamWriter osw=new OutputStreamWriter(fos);
                    //use osw object to create a BufferedWriter object
                    //BufferedReader
                    BufferedWriter bw=new BufferedWriter(osw);
                    info+="\n";//add new line after last character
                    bw.write(info);
                    //send information from buffer to the file
                    bw.flush();

                    //close all streams and files
                    bw.close();
                    fos.close();
                    //Display a message to tell information is successfully written to file
                    Toast.makeText(getBaseContext(),"File Appended Successfully",Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
