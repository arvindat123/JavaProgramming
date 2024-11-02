package org.SOLID;

/**
 * This principle states that “Derived or child classes must be substitutable for their base or parent classes”.
 * In other words, if class A is a subtype of class B,
 * then we should be able to replace B with A without interrupting the behavior of the program.
 */
public class LiskovSubstitutionPrinciple {
}

abstract class SocialMedia {

    public abstract void chatWithFriend();

    public abstract void publishPost(Object post);

    public abstract void sendPhotosAndVideos();

    public abstract void groupVideoCall(String... users);
}

//This class is a substitute of SocialMedia class because it implements all methods
class Facebook extends SocialMedia {

    public void chatWithFriend() {
        //logic
    }

    public void publishPost(Object post) {
        //logic
    }

    public void sendPhotosAndVideos() {
        //logic
    }

    public void groupVideoCall(String... users) {
        //logic
    }
}

//This class doesn't follow LSP because it doesn't use publishPost method
class WhatsApp extends SocialMedia {
    public void chatWithFriend() {
        //logic
    }

    public void publishPost(Object post) {
        //Not Applicable
    }

    public void sendPhotosAndVideos() {
        //logic
    }

    public void groupVideoCall(String... users) {
        //logic
    }
}

//Solution
interface SocialMedia1 {
    public void chatWithFriend();
    public void sendPhotosAndVideos();
}
interface SocialPostAndMediaManager {
    public void publishPost(Object post);
}
interface VideoCallManager{
    public void groupVideoCall(String... users);
}
//implements interface which is required
class Instagram implements SocialMedia1 ,SocialPostAndMediaManager{
    public void chatWithFriend(){
        //logic
    }
    public void sendPhotosAndVideos(){
        //logic
    }
    public void publishPost(Object post){
        //logic
    }
}
