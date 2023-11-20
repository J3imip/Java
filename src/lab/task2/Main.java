package lab.task2;

import java.time.LocalDate;

public class Main {
    public enum MonthName {
        JANUARY(31, "Січень", Season.WINTER),
        FEBRUARY(28, "Лютий", Season.WINTER),
        MARCH(31, "Березень", Season.SPRING),
        APRIL(30, "Квітень", Season.SPRING),
        MAY(31, "Травень", Season.SPRING),
        JUNE(30, "Червень", Season.SUMMER),
        JULY(31, "Липень", Season.SUMMER),
        AUGUST(31, "Серпень", Season.SUMMER),
        SEPTEMBER(30, "Вересень", Season.AUTUMN),
        OCTOBER(31, "Жовтень", Season.AUTUMN),
        NOVEMBER(30, "Листопад", Season.AUTUMN),
        DECEMBER(31, "Грудень", Season.WINTER);

        private final int days;
        private final String nameUA;
        private final Season season;

        MonthName(int days, String nameUA, Season season) {
            this.days = days;
            this.nameUA = nameUA;
            this.season = season;
        }

        public int getDays() {
            return days;
        }

        public String getNameUA() {
            return nameUA;
        }

        public Season getSeason() {
            return season;
        }

        public MonthName getNextMonth() {
            return values()[(this.ordinal() + 1) % values().length];
        }

        public MonthName getPreviousMonth() {
            int previousIndex = this.ordinal() - 1;
            if (previousIndex < 0) {
                previousIndex = values().length - 1;
            }
            return values()[previousIndex];
        }
    }

    public enum Season {
        WINTER("Зима"),
        SPRING("Весна"),
        SUMMER("Літо"),
        AUTUMN("Осінь");

        private final String nameUA;

        Season(String nameUA) {
            this.nameUA = nameUA;
        }

        @Override
        public String toString() {
            return nameUA;
        }
    }

    public static void printAllMonths() {
        for (MonthName month : MonthName.values()) {
            System.out.println(month.getNameUA() + " - " + month.getDays() + " днів, Сезон: " + month.getSeason());
        }
    }

    public static void main(String[] args) {
        LocalDate currentDate = LocalDate.now();
        int currentMonthIndex = currentDate.getMonthValue();
        MonthName currentMonth = MonthName.values()[currentMonthIndex - 1];

        System.out.println("Поточний місяць: " + currentMonth.getNameUA());
        System.out.println("Наступний місяць: " + currentMonth.getNextMonth().getNameUA());
        System.out.println("Попередній місяць: " + currentMonth.getPreviousMonth().getNameUA());
        System.out.println("Сезон поточного місяця: " + currentMonth.getSeason());

        System.out.println("\nУсі місяці:");
        printAllMonths();
    }
}