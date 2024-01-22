package be.kdg.prog6.boundedContextA.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Guest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private GuestUUID sso;
    private int age;
    public record GuestUUID(UUID uuid) { }
    ActivityWindow activityWindow = new ActivityWindow();

    public Guest(String firstName, String lastName, String email, String phoneNumber, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public Guest(String firstName, String lastName, GuestUUID sso) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sso = sso;
    }

    public TicketActivity giveMoney(double money) {
        int age = getAge();
        TicketAction ticketAction = age >= 5 && age <= 18 ? TicketAction.CHILD : TicketAction.ADULT;
        TicketActivity activity = new TicketActivity(ticketAction, money, LocalDateTime.now());
        activityWindow.add(activity);
        return activity;
    }

    public Optional<TicketActivity> getMoney(double money) {
        int age = getAge();
        TicketAction ticketAction = age >= 5 && age <= 18 ? TicketAction.CHILD : TicketAction.ADULT;
        TicketActivity activity = new TicketActivity(ticketAction, money, LocalDateTime.now());
        activityWindow.add(activity);
        return Optional.of(activity);
    }
}
