package com.lifemichael.samples.mvvm.model;

import java.io.*;

public class Model implements IModel{

    @Override
    public void saveData(String file, String content) throws ModelException {
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try {
            fos = new FileOutputStream(file);
            dos = new DataOutputStream(fos);
            dos.writeUTF(content);
        } catch (IOException e) {
            throw new ModelException("problem reading data",e);
        } finally {
            if(dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String loadData(String file) throws ModelException {
        FileInputStream fis = null;
        DataInputStream dis = null;
        try {
            fis = new FileInputStream(file);
            dis = new DataInputStream(fis);
            return dis.readUTF();
        } catch (IOException e) {
            throw new ModelException("problem reading data",e);
        } finally {
            if(dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
