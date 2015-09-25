package com.azovstal.test.other;

import java.util.regex.*;
public class SimpleGroupExample{
    public static void main(String args[]){
        //the original pattern is always group 0
        Pattern p = Pattern.compile("\\w\\d");
        String candidate = "7qWq7S is my favorite";

        //if there is a match, extract that part of
        //the candidate string that matches group(0)
        Matcher matcher = p.matcher(candidate);

        //OUTPUT is 'q7'
        if (matcher.find()){
            String tmp = matcher.group(0);
            System.out.println(tmp);
        }
    }
}

