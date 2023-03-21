package UI;

public enum MenuOptions {
    UNKNOWN,
    ADD,
    DELETE,

    PRINTBYCOLOR,

    PRINTALL,

    EXIT;

    public static MenuOptions parseInt(int value){
        switch (value){
            case (1) ->{
                return ADD;
            }
            case(2) -> {
                return DELETE;
            }
            case(3) -> {
                return PRINTBYCOLOR;
            }
            case(4) -> {
                return PRINTALL;
            }
            case (5)-> {
                return EXIT;
            }
            default -> {
                return UNKNOWN;
            }
        }

    }
}
