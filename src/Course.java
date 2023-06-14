public class Course {
    
    private String name;
    private String uniqueId;
    private int crdthrs;
    char attendance[];

    public Course(String name, String uniqueId, int crdthrs) {
        this.name = name;
        this.uniqueId = uniqueId;
        this.crdthrs = crdthrs;
        attendance = new char[100];
    }
    public Course(String name, String uniqueId)
    {
        this.name = name;
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
    public void setCrdthrs(int ch)
    {
        crdthrs = ch;
    }
    int getCrdthrs()
    {
        return crdthrs;
    }
}