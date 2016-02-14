package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {

    private static int getPoissonRandom(double mean)
    {
        Random r = new Random();
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do
        {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }

    public static void main(String[] args) {

        int duration;
        Double planeFreq;
        AirTrafficControl atc = new AirTrafficControl();

        // input and input control
        try{
            BufferedReader bufferRead;
            // Simulation duration input
            System.out.print("Simulation duration:");
            bufferRead = new BufferedReader(new InputStreamReader(System.in));
            duration = new Integer(bufferRead.readLine());
            // plane frequency input
            System.out.print("Plane landing/takeoff frequency:");
            bufferRead = new BufferedReader(new InputStreamReader(System.in));
            planeFreq = new Double(bufferRead.readLine());
        } catch (Exception e){
            System.out.println("Wrong input!");
            duration = 0;
            planeFreq = 0D;
            System.exit(-1);
        }
        // simulation loop
        for (int i = 0; i<duration;i++){
            System.out.println("Time unit nr: " +(i+1));
            atc.landingRequest(getPoissonRandom(planeFreq));
            atc.takeoffRequest(getPoissonRandom(planeFreq));
            atc.getNext();
            System.out.println("-----------------------------------------------");
        }
        System.out.println("Summary after " + duration + " time units:");
        atc.printStats();
    }
}