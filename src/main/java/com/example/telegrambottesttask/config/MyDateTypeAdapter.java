package com.example.telegrambottesttask.config;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Date;

public class MyDateTypeAdapter extends TypeAdapter {
    @Override
    public void write(JsonWriter jsonWriter, Object o) throws IOException {

    }

    @Override
    public Date read(JsonReader in) throws IOException {
        String s = in.toString();
        System.out.println(s);
        return null;

    }

}
