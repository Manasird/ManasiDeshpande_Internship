package in.fortytwo42.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table (name="users")
public class User  {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    @Column(name="user_name")
    private  String userName;
    @Column(name="created_at")
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name="updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    List<Attribute> attributes;

    public User(String userId, String userName, Timestamp createdAt,Timestamp updatedAt) {
        this.userId = userId;
        this.userName = userName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", attributes=" + attributes +
                '}';
    }
}
