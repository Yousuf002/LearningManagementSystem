public abstract class User {
    private String name;
    private int id;
    private String email;
    private String password;
    public abstract boolean login(String email, String password);


    public User() {
        name = "";
        id = 0;
        email = "";
        password = "";
    }

    public User(String name, int id, String email, String password) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.password = password;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getters
    public  String getName()
    {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
