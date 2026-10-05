package com.hibernate.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name="student_name",
            nullable = false,
            length = 100
    )
    private String name;
    private int age;

    @ElementCollection
    @CollectionTable(name = "student_addresses",
            joinColumns=@JoinColumn(name="student_id")
    )
    private Set<Address> addresses;

    @ElementCollection
    @CollectionTable(
            name="student_skills",
            joinColumns = @JoinColumn(name="student_id")
    )
    private List<String> skills;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "houseNo",
                    column = @Column(name = "current_house_no")
            ),
            @AttributeOverride(
                    name = "street",
                    column = @Column(name = "current_street")
            ),
            @AttributeOverride(
                    name="city",
                    column=@Column(name="current_city")
            ),
            @AttributeOverride(
                    name="state",
                    column = @Column(name="current_state")
            ),
            @AttributeOverride(
                    name="pincode",
                    column = @Column(name="current_pincode")
            )
    })
    private Address currentAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "houseNo",
                    column = @Column(name = "permanent_house_no")
            ),
            @AttributeOverride(
                    name = "street",
                    column = @Column(name = "permanent_street")
            ),
            @AttributeOverride(
                    name="city",
                    column=@Column(name="permanent_city")
            ),
            @AttributeOverride(
                    name="state",
                    column = @Column(name="permanent_state")
            ),
            @AttributeOverride(
                    name="pincode",
                    column = @Column(name="permanent_pincode")
            )
    })
    private Address permanentAddress;
    @Column(precision = 5, scale = 2)
    private BigDecimal percentage;

    @Column(
            unique = true,
            nullable = false,
            length = 100,
            insertable = true,
            updatable = true
    )
    private String email;

    private LocalDate dateOfBirth;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)//ORDINAL
    private StudentStatus status;

    @Lob
    private String profileDescription;

    @Transient
    private String displayName;

    @Convert(converter = BooleanToStringConverter.class)
    private Boolean isMonitor;

    public Student(){}

    public Student(Long id, String name, int age, Set<Address> addresses, Address permanentAddress, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.addresses = addresses;
        this.permanentAddress = permanentAddress;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}