package be.kdg.prog6.boundedcontextB.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Queue {
    private Long id;
    private int amountOfPeople;
    private double throughRate;
    private double waitingTime;
    private static Queue instance;
    public void getWaitingTime(int amountOfPeople, double throughRate){
        double value = amountOfPeople / throughRate * 60;
        setWaitingTime(value);
    }

    public Queue(int amountOfPeople) {
        this.amountOfPeople = amountOfPeople;
    }

    public static Queue getInstance(){
        if (instance == null){
            instance = new Queue(0);
        }
        return instance;
    }
}
