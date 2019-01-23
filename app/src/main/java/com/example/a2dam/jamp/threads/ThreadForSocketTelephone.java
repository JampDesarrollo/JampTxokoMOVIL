package com.example.a2dam.jamp.threads;

import com.example.a2dam.jamp.logics.TelephoneLogic;

public class ThreadForSocketTelephone extends Thread {
    private TelephoneBean telephone;
    private TelephoneLogic ilogic;
    private int code;

    public TelephoneBean getTelephone(){
        return telephone;
    }

    public ThreadForSocketTelephone(TelephoneBean receivedTelephone, TelephoneLogic ilogic, int i){
        this.telephone=receivedTelephone;
        this.ilogic=ilogic;
        this.code=i;
    }

    /**
     * Method that start the thread
     */
    @Override
    public void run() {
        switch (code){
            case 1:
                try {
                    ilogic.findAllTelephones(telephone);
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
