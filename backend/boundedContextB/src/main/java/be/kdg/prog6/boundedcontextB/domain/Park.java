package be.kdg.prog6.boundedcontextB.domain;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Park {
    private static Park instance;
    private int totalPeopleInside;

    private int numberOfRefreshmentStandsOpen;

    private Park(int initialTotalPeopleInside) {
        this.totalPeopleInside = initialTotalPeopleInside;
    }
    public static Park getInstance() {
        if (instance == null) {
            instance = new Park(0);
        }
        return instance;
    }

    public void setTotalPeopleInside(int totalPeopleInside) {
        this.totalPeopleInside = totalPeopleInside;
    }

    public boolean isTimeToOpenAttraction(boolean isColdRainyStormy){
        if (isColdRainyStormy && getTotalPeopleInside()/1000>=numberOfRefreshmentStandsOpen){
            return true;
        }
        else if (!isColdRainyStormy && getTotalPeopleInside()/1000>=numberOfRefreshmentStandsOpen*2){
            return true;
        }
        else{return false;}
    }
}
