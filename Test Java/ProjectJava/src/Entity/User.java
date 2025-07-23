package Entity;
import java.time.LocalDateTime;
import java.sql.Date;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private String name;
    private Date dob;
    private String email;
    private boolean sex;
    private String phone;
    private LocalDateTime createAt;

    public User() {}

    public User(int id, String username, String password, String role,
                String name, Date dob, String email, boolean sex, String phone, LocalDateTime createAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.createAt = createAt;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isSex() { return sex; }
    public void setSex(boolean sex) { this.sex = sex; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public LocalDateTime getCreateAt() { return createAt; }
    public void setCreateAt(LocalDateTime createAt) { this.createAt = createAt; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
