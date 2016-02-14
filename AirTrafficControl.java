package com.company;

/**
 * A class for plane controll
 *
 * @author Alexander Kjeserud
 *
 */
public class AirTrafficControl {

    private MyQueue<Plane> landingQueue = new MyQueue<>(Plane[].class, 10);
    private MyQueue<Plane> takeoffQueue = new MyQueue<>(Plane[].class, 10);

    private int landingRequestsDenied = 0;
    private int takeoffRequestsDenied = 0;
    private int numberOfTakeoffs = 0;
    private int numberOfLandings = 0;

    private int timeCount = 0;
    private Double landingQueueAverage = 0D;
    private Double takeoffQueueAverage = 0D;


    /**
     * Removes the next plane to use the runway from one of the queues, prints action to console and logs the event.
     */
    public void getNext(){
        summarizeQueueAverage();
        if (landingQueue.size() != 0){
            System.out.println("Plane " + landingQueue.poll() + " has landed");
            numberOfLandings ++;
            return;
        }else if (takeoffQueue.size() != 0) {
            System.out.println("Plane " + takeoffQueue.poll() + " has taken off");
            numberOfTakeoffs++;
            return;
        }else{
            System.out.println("Runway not used");
        }
    }

    /**
     * Adds a number of planes requesting to land in landing queue.
     * Rejects planes if queue is full.
     *
     * @param nPlanes number of new planes wanting to land
     */
    public void landingRequest(int nPlanes){
        int n = requests(landingQueue, nPlanes);
        landingRequestsDenied += n;
        if (n > 0){
            System.out.println("Plane(s) redirected: " + n);
        }
    }

    /**
     * Adds a number of planes requesting to take off in takeoff queue.
     * Rejects planes if queue is full.
     *
     * @param nPlanes number of new planes wanting to take off
     */
    public void takeoffRequest(int nPlanes){
        int n = requests(takeoffQueue, nPlanes);
        takeoffRequestsDenied += n;
        if (n > 0){
            System.out.println("Plane(s) denied takeoff: " + n);
        }
    }

    /**
     * Prints accumulated statistics
     */
    public void printStats(){
        System.out.println(" Planes redirected: " + landingRequestsDenied + "\n Planes denied takeoff: " + takeoffRequestsDenied);
        System.out.println(" Planes landed: " + numberOfLandings + "\n Planes taken off: " + numberOfTakeoffs);
        System.out.println(" Planes left in landing queue: " + landingQueue.size() + "\n Planes left in takeoff queue: " + takeoffQueue.size());
        System.out.println(" Landing queue average: " + String.format("%.2f", landingQueueAverage) +
                "\n Takeoff queue average: " + String.format("%.2f", takeoffQueueAverage));
    }

    private static int requests(MyQueue<Plane> queue, int nPlanes){
        int ret = 0;
        for (int i = 0; i<nPlanes;i++){
            Plane p = new Plane();
            if (!queue.put(p)){
                ret++;
            }
        }
        return ret;
    }

    private void summarizeQueueAverage(){
        timeCount++;
        landingQueueAverage = newAverage(landingQueueAverage, landingQueue.size(), timeCount);
        takeoffQueueAverage = newAverage(takeoffQueueAverage, takeoffQueue.size(), timeCount);
    }

    private static Double newAverage(Double prevAverage, int additionalValue, int timeCount){
        return ((prevAverage*(timeCount-1)) + additionalValue) / timeCount;
    }

}
