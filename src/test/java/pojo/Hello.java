package pojo;

public class Hello {
    private String HELLO;
    private String WORLD;

    @Override
    public String toString() {
        return "Hello{" +
                "HELLO='" + HELLO + '\'' +
                ", WORLD='" + WORLD + '\'' +
                '}';
    }

    public String getHELLO() {
        return HELLO;
    }

    public void setHELLO(String HELLO) {
        this.HELLO = HELLO;
    }

    public String getWORLD() {
        return WORLD;
    }

    public void setWORLD(String WORLD) {
        this.WORLD = WORLD;
    }
}
