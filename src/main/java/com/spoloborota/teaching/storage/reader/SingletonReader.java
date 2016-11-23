package com.spoloborota.teaching.storage.reader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SingletonReader extends BufferedReader {

    private static SingletonReader rdr;

    private SingletonReader(InputStreamReader inputStreamReader) {
        super(inputStreamReader);
    }

    public static SingletonReader getInstance() {
        if (rdr == null) {
            rdr = new SingletonReader(new InputStreamReader(System.in));
        }
        return rdr;
    }
}
