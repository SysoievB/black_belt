package enums;

public class EnumClass {
    public static void main(String[] args) {
        Today today = new Today(WeekDays.SATURDAY);
        today.showInfo();
        System.out.println(today.getWeekDays().getMood());
    }
}

enum WeekDays {
    MONDAY("bad"),
    TUESDAY("bad"),
    WEDNESDAY("bad"),
    THURSDAY("good"),
    FRIDAY("good"),
    SATURDAY("great"),
    SUNDAY("good");

    private String mood;

    private WeekDays(String mood) {
        this.mood = mood;
    }

    private WeekDays() {
    }

    public String getMood() {
        return mood;
    }
}

class Today {
    private WeekDays weekDays;

    public Today(WeekDays weekDays) {
        this.weekDays = weekDays;
    }

    public WeekDays getWeekDays() {
        return weekDays;
    }

    void showInfo() {
        switch (weekDays) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                System.out.println("go to work!!!");
                break;
            case SATURDAY:
            case SUNDAY:
                System.out.println("have a sleep)");
                break;
        }
    }
}