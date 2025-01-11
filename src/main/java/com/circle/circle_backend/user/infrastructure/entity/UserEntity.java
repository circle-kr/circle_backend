package com.circle.circle_backend.user.infrastructure.entity;
import com.circle.circle_backend.user.domain.User;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "school")
    private String school;

    @Column(name = "country")
    private String country;

    @Column(name = "profile_image")
    private String profileImage;

    public static UserEntity from (User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.id = user.getId();
        userEntity.email = user.getEmail();
        userEntity.password = user.getPassword();
        userEntity.firstName = user.getFirstName();
        userEntity.lastName = user.getLastName();
        userEntity.nickname = user.getNickname();
        userEntity.school = user.getSchool();
        userEntity.country = user.getCountry();
        userEntity.profileImage = user.getProfileImage();
        return userEntity;
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .nickname(nickname)
                .school(school)
                .country(country)
                .profileImage(profileImage)
                .build();
    }
}
