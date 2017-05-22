package com.lucgu.sigviewer.helper;

/**
 * Created by 1414 on 5/22/2017.
 */

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by dwipr_000 on 6/7/2015.
 */
public class MyJsonReader {
    Context context;
    int jsonResourceId;
    public MyJsonReader(Context context, int jsonResourceId) {
        this.context = context;
        this.jsonResourceId = jsonResourceId;
    }
    public String readJson() throws IOException {
        InputStream is = this.context.getResources().openRawResource(this.jsonResourceId);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        Reader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
        int n;
        try {
            while((n=reader.read(buffer))!=-1){
                writer.write(buffer,0,n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
        String jsonstring = writer.toString();
        return jsonstring;
    }
}