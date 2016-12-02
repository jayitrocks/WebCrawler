package com.crawler;

/**
 * Created by Jayendrakumar_M on 12/1/2016.
 */
public class Initiator {
    public static void main(String[] args)
    {
        Commander cmd = new Commander();
        cmd.search("http://www.python.org");
    }
}
