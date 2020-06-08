package tictak;

public class Data {
    private int state = 1;

    public int getState() {
        return state;
    }

    public void Tic() {
        while (state != 1)
            Thread.yield();
        System.out.print("Tic-");
        state = 2;
    }

    public void Tak() {
        while (state != 2)
            Thread.yield();
        System.out.print("Tak-");
        state = 3;
    }

    public void Toe() {
        while (state != 3)
            Thread.yield();
        System.out.println("Toy");
        state = 1;
    }
}
