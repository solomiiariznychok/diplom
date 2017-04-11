package com.riznuchok.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {

    @Id
    String id;
    String firstName;
    String lastName;
    @Email
    @NotEmpty @NonNull
    String email;
    @NotEmpty @NonNull
    String password;

    Role role;
}
