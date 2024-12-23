package model;

// extra klasse voor het inlogscherm
public class User {
    private final String name;
    private final String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User(" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ')';
    }
}
