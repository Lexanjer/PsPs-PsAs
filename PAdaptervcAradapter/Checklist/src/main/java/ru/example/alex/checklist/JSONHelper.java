package ru.example.alex.checklist;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;



public class JSONHelper {

    private static final String FILE_NAME = "data.json";
    static final String LOG_TAG = "myLogs";
    // Log.d(JSONHelper.LOG_TAG, "itemSelect: position = всем привет" );


    static boolean exportToJSONdata(Context context, List<CheckItem> dataList) {

        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems. setCheckList(dataList);                             //setCheckList(dataList);
        String jsonString = gson.toJson(dataItems);

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(jsonString.getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    static List<CheckItem> importFromJSONdata(Context context) {

        InputStreamReader streamReader = null;
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = context.openFileInput(FILE_NAME);
            streamReader = new InputStreamReader(fileInputStream);
            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(streamReader, DataItems.class);
            return  dataItems.getCheckList();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    static List<CheckItem> importFromJSONassets(Context context, String filename) {



        InputStreamReader streamReader = null;
        try {
            AssetManager assetManager = context.getAssets();
            streamReader = new InputStreamReader(assetManager.open(filename));
            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(streamReader, DataItems.class);
            return dataItems.getCheckList();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return null;
    }

    private static class DataItems {
        private List<CheckItem> checkList;

        List<CheckItem> getCheckList() {
            return checkList;
        }

        void setCheckList(List<CheckItem> checkList) {
            this.checkList = checkList;
        }
    }


}
