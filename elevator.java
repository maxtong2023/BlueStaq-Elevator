import java.util.Queue;

public class elevator {
    private int currentFloor; 
    private int highestFloor; 
    private int lowestFloor; 
    private int maxElevatorWeight; 
    private Queue<Integer> floorQueue;

    public elevator(int currentFloor, int highestFloor, int lowestFloor, int maxElevatorWeight) {
        this.currentFloor = currentFloor;
        this.highestFloor = highestFloor;
        this.lowestFloor = lowestFloor;
        this.maxElevatorWeight = maxElevatorWeight;
        this.floorQueue = new LinkedList<>();
    }

    // So I never thought about how an elevator actually works, but I think it works something like a queue. 

    // second thought, let's say that a more efficient pathing exists. Let's say that one person is on the top 
    // floor and wants to go to the bottom floor. While they are traveling, another person on the 3rd floor wants to 
    // go down, and after them a person on the 5 floor wants to also go down. We should pick up the 5th floor guy first
    // this sounds more like a priority queue. 


    public void setFloor(int floor) {

    }

    public int getFloor(){
        return currentFloor;
    }



}



public static void main(String[] args) {
    System.out.println("Hello, World!");
}