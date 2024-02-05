package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="tbl_persons")
public class Person implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="firstName", length = 255, nullable = false)
    private String firstName;
    @Column(name="lastName", length = 255, nullable = false)
    private String lastName;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Person p = (Person)o;
        int result = this.lastName.compareTo(p.lastName);
        if(result==0) {
            result=this.firstName.compareTo(p.firstName);
        }
        return result;
    }
}
