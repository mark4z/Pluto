package pojo;

public class Hello {
    private String hello;
    private String world;

    @Override
    public String toString() {
        return "Hello{" +
                "hello='" + hello + '\'' +
                ", world='" + world + '\'' +
                '}';
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }
}
