package org.designpattern.singleton;

import java.util.Objects;

public class RevisionSingleton {
    private static volatile RevisionSingleton instance;

    private RevisionSingleton(){

    }

    public static RevisionSingleton getInstance(){
        if(instance == null){
            synchronized (RevisionSingleton.class) {
                if(instance == null) {
                    instance = new RevisionSingleton();
                }
            }
        }
        return instance;
    }


}
