package com.stackroute.MuzixApplication;

import com.stackroute.Service.MuzixServiceImpl;

public class check {
    public static void main(String[] args) {

        MuzixServiceImpl muzixService=new MuzixServiceImpl();
        try{
            String result=muzixService.getAllTracks();
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
