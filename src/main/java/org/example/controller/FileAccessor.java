package org.example.controller;

import org.example.bank.BankAccount;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FileAccessor {
    public static String fileName;
    private static Properties properties;
    private static ObjectOutputStream objectOutputStream;
    private static AppendableObjectOutputStream appendableObjectOutputStream;
    private static ObjectInputStream objectInputStream;

    public static void initializer(){
        properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get("./src/main/resources/fileSetting.txt"));) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException("File setting file could not be loaded");
        }
        fileName = properties.getProperty("fileName");
        String directoryName = properties.getProperty("directoryName");
        File file = new File(directoryName);
        if (!file.exists()) {
            file.mkdir();
        }
        else {
            deserialize();
        }
    }

    private static void openReadConnection() {
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
        } catch (IOException e) {
            System.err.println("could not open the connection because file could not be founded: "+e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
    }
    private static void openWriteConnection() {
        try {
            if (new File(fileName).exists()) {
                appendableObjectOutputStream = new AppendableObjectOutputStream((new FileOutputStream(fileName, true)));
                objectOutputStream=null;
            } else {
                objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName, true));
            }
        } catch (IOException e) {
            System.err.println("could not open the connection because file could not be founded: "+e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
    }
    public static List<BankAccount> deserialize() {
        openReadConnection();
        System.out.println("deserializing");
        List<BankAccount> bankAccounts = new ArrayList<>();
        while (true) {
            try {
                BankAccount obj = (BankAccount) objectInputStream.readObject();
                bankAccounts.add(obj);
            } catch (ClassNotFoundException e) {
                System.err.println("Unable to cast object. Class not found during deserialization: "+e.getMessage());
                e.printStackTrace();
            } catch (EOFException e) {
                break;
            } catch (IOException e) {
                System.err.println("Unable to read object from file: "+e.getMessage());
                e.printStackTrace();
            }
        }
        closeConnection();
        return bankAccounts;
    }

    public static void serialize(BankAccount bankAccount) {
        openWriteConnection();
        System.out.println("serializing");
        try {
            if (objectOutputStream != null) {
                objectOutputStream.writeObject(bankAccount);
                objectOutputStream.flush();
            } else {
                appendableObjectOutputStream.writeObject(bankAccount);
                appendableObjectOutputStream.flush();
            }
        } catch (IOException e){
            System.err.println("Could not write object: "+bankAccount+" because: "+e.getMessage());
            e.printStackTrace();
        }
        closeConnection();
    }

    public static void closeConnection(){
        try {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            } else if (appendableObjectOutputStream != null){
                appendableObjectOutputStream.close();
            }
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }catch (IOException e){
            System.err.println("could not close the resources");
            e.printStackTrace();
            System.exit(-1);
        }
    }

}
