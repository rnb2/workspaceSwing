package com.test.delete;

import java.io.File;

public class Delete
{

    public Delete()
    {
    }

    public static void main(String args[])
    {
        
    	String path = (System.getProperties().get("user.home") + (String)System.getProperties().get("file.separator") + ".ids_ugdt"+(String)System.getProperties().get("file.separator"));
    	String args1[] = {new String(path)};
        
        int i = args1.length;
        for(int j = 0; j < i; j++)
        {
            String s = args1[j];
            deleteFile(new File(s));
        }

        if(args.length < 1)
            System.out.println("Please provide one or more directories to clean.");
    }

    public static void deleteFile(File file)
    {
        if(file.isDirectory())
        {
            File afile[] = file.listFiles();
            int i = afile.length;
            for(int j = 0; j < i; j++)
            {
                File file1 = afile[j];
                deleteFile(file1);
            }

        }
        file.delete();
    }
    
}
