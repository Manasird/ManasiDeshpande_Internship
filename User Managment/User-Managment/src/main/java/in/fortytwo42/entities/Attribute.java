package in.fortytwo42.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name="attribute")
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="attribute_name")
    private String attributeName;
    @Column(name="attribute_value")
    private String attributeValue;
    @Column(name="created_at")
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name  ="user_id")
    @JsonBackReference
    private User user;

    public Attribute(int id, String attributeName, String attributeValue, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Attribute() {

    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "id=" + id +
                ", attributeName='" + attributeName + '\'' +
                ", attributeValue='" + attributeValue + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", user=" + user +
                '}';
    }


}
