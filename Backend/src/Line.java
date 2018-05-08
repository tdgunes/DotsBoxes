public enum Line {
    NONE(0), FULL(1), EDGE(2);

    private final int value;

    Line(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Line create(int value){
        return Line.values()[value];
    }

}
