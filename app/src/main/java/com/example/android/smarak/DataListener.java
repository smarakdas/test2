package com.example.android.smarak;

import java.util.ArrayList;

/**
 * Created by sanu on 08-May-16.
 */
public interface DataListener {


        public void addData(Data data);

        public ArrayList<Data> getData();

        public int getDataCount();

}
