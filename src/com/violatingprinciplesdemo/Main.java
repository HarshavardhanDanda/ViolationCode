package com.violatingprinciplesdemo;

import java.util.Scanner;

//violated dependancy inversion
class DebitCard{

    public void doTransaction(long amount){
        System.out.println("payment using Debit card of"+ amount + " rupees");
    }
}
class CreditCard{

    public void doTransaction(long amount){
        System.out.println("payment using Credit card of "+ amount + " rupees");
    }
}
class DMart {

    private final CreditCard creditcard;

    public DMart(CreditCard bankCard) {
        this.creditcard = bankCard;
    }

    public void doPurchaseSomething(long amount){
        creditcard.doTransaction(amount);
    }


}

//violated interface segregation
interface Notification {

    void sendOTP();
    void sendMessage(long amount);

}

//violated open closed principle
class MobileNotificationService implements Notification {

    public void sendOTP() {
        System.out.println("sent otp to Mobile");
    }
    public void sendMessage(long amount) {

        System.out.println("MobileNotification: Transaction of "+" has been done from your account");
    }
}
//interface segregation violation effect
class EmailNotificationService implements Notification{

    public void sendOTP() {
        System.out.println("sent otp to Mobile");
    }
    public void sendMessage(long amount) {
        System.out.println("EmailNotification: Transaction of "+amount+" has been done from your account");
    }
}
//violated liskov substitution and single responsibility
class WhatsAppNotificationService extends MobileNotificationService {


    @Override
    public void sendMessage(long amount) {
        System.out.println("WhatsAppNotification: Transaction of "+amount+" has been done from your account");
    }
    public void readMessage(){
        System.out.println("reading out the message:");
    }
}

public class Main {

    public static void sending(MobileNotificationService mns){
        mns.sendOTP();

    }
    public static void main(String[] args) {
        System.out.print("Enter Amount:");
        Scanner sc = new Scanner(System.in);
        long amount=sc.nextInt();

        CreditCard bankCard = new CreditCard();
        DMart dmart = new DMart(bankCard);
        sending(new WhatsAppNotificationService());
        sending(new MobileNotificationService());

        WhatsAppNotificationService w1= new WhatsAppNotificationService();
        w1.sendMessage(amount);

        MobileNotificationService m1= new MobileNotificationService();
        m1.sendMessage(amount);

        EmailNotificationService e1= new EmailNotificationService();
        e1.sendMessage(amount);

        dmart.doPurchaseSomething(amount);

    }

}