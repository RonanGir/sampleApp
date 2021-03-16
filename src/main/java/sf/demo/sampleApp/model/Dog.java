package sf.demo.sampleApp.model;

public class Dog {

    private String name;

    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public final String bark() {
        return "Waf waf";
    }

    public static String barkLikeACat() {
        return "Meow Meow";
    }
}
