package com.demo.schoolmanagement;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeAdapter extends TypeAdapter<LocalTime> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public void write(JsonWriter out, LocalTime value) throws IOException {
        out.value(value != null ? value.format(FORMATTER) : null);
    }

    @Override
    public LocalTime read(JsonReader in) throws IOException {
        String time = in.nextString();
        return time != null ? LocalTime.parse(time, FORMATTER) : null;
    }
}