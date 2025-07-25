package Entity;

import java.time.LocalDateTime;

public class Course {
    private int id;
    private String name;
    private int duration;
    private String instructor;
    private LocalDateTime createAt;

    public Course() {}

    public Course(String name, int duration, String instructor) {
        this.name = name;
        this.duration = duration;
        this.instructor = instructor;
    }

    public Course(int id, String name, int duration, String instructor, LocalDateTime createAt) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.instructor = instructor;
        this.createAt = createAt;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public LocalDateTime getCreateAt() { return createAt; }
    public void setCreateAt(LocalDateTime createAt) { this.createAt = createAt; }

    @Override
    public String toString() {


        return String.format("%-5s | %-15s | %-15s | %-15s | %-15s",
                this.id, this.name, this.duration, this.instructor, this.createAt);

    }


}
