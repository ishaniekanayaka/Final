package lk.ijse.util.Util;

import java.util.regex.Pattern;

public class RegExFactory {
    private static RegExFactory regExFactory;

    private final Pattern namePattern;
    private final Pattern emailPattern;
    private final Pattern cityPattern;
    private final Pattern mobilePattern;
    private final Pattern doublePattern;
    private final Pattern passwordPattern;
    private final Pattern duration;
    private final Pattern price;


    private RegExFactory() {
        namePattern = Pattern.compile("^[a-zA-Z0-9 '.-]{4,}$");
        emailPattern = Pattern.compile("(^[a-zA-Z0-9_.-]+)@([a-zA-Z]+)([\\.])([a-zA-Z]+)$");
        cityPattern = Pattern.compile("[a-zA-Z]{4,}$");
        doublePattern = Pattern.compile("^[0-9]+\\.?[0-9]*$");
        mobilePattern = Pattern.compile("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$");
        passwordPattern = Pattern.compile("^[a-zA-Z0-9_.-]{4,}$");
        duration = Pattern.compile("^[a-zA-Z0-9 '.-]{4,}$");
        price = Pattern.compile("^[0-9]+$");
    }

    public static RegExFactory getInstance() {
        return regExFactory == null ? regExFactory = new RegExFactory() : regExFactory;
    }

    public Pattern getPattern(RegExType regExType) throws RuntimeException {
        switch (regExType) {
            case NAME:
                return namePattern;
            case PASSWORD:
                return passwordPattern;
            case MOBILE:
                return mobilePattern;
            case DOUBLE:
                return doublePattern;
            case CITY:
                return cityPattern;
            case EMAIL:
                return emailPattern;
            case DURATION:
                return duration;
            case PRICE:
                return price;
            default:
                throw new RuntimeException("Pattern not found");
        }
    }

}
