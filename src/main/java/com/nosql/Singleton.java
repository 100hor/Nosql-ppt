package com.nosql;

import com.nosql.Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Singleton implements Observer {
    private static Singleton instance;
    private List<String> logs = new ArrayList<String>();

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void addLog(String log) {
        logs.add(log);
    }

    public String getLog() {
        return logs.toString();
    }

    public Integer getCountLog(String used) {
        int count=0;
        for(String s:logs){
            if(s.equals(used)){
                count++;
            }
        }
        return count;
    }

    @Override
    public void update(String log) {
        Singleton.getInstance().addLog(log);
    }

}
