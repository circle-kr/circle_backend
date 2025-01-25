package com.circle.circle_backend.user.infrastructure.entity;
import com.circle.circle_backend.user.domain.User;
import com.circle.circle_backend.user.domain.UserPatchRequest;
import com.circle.circle_backend.user.domain.enums.Language;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "major")
    private String major;

    @ElementCollection
    @CollectionTable(name = "available_languages", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "language")
    private List<Language> languages = new ArrayList<>();

    @Column(name = "bio")
    private String bio;

    public static UserEntity from(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.email = user.getEmail();
        userEntity.password = user.getPassword();
        userEntity.firstName = user.getFirstName();
        userEntity.lastName = user.getLastName();
        userEntity.nickname = user.getNickname();
        userEntity.school = user.getSchool();
        userEntity.country = user.getCountry();
        userEntity.profileImage = user.getProfileImage();
        userEntity.major = user.getMajor();
        userEntity.languages = user.getLanguages();
        userEntity.bio = user.getBio();
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
                .major(major)
                .languages(languages)
                .bio(bio)
                .build();
    }

    public UserEntity patch(UserPatchRequest userPatchRequest) {
        if (userPatchRequest.getFirstName() != null && !userPatchRequest.getFirstName().equals(this.firstName)) {
            this.firstName = userPatchRequest.getFirstName();
        }

        if (userPatchRequest.getLastName() != null && !userPatchRequest.getLastName().equals(this.lastName)) {
            this.lastName = userPatchRequest.getLastName();
        }

        if (userPatchRequest.getNickname() != null && !userPatchRequest.getNickname().equals(this.nickname)) {
            this.nickname = userPatchRequest.getNickname();
        }

        if (userPatchRequest.getSchool() != null && !userPatchRequest.getSchool().equals(this.school)) {
            this.school = userPatchRequest.getSchool();
        }

        if (userPatchRequest.getMajor() != null && !userPatchRequest.getMajor().equals(this.major)) {
            this.major = userPatchRequest.getMajor();
        }

        if (userPatchRequest.getCountry() != null && !userPatchRequest.getCountry().equals(this.country)) {
            this.country = userPatchRequest.getCountry();
        }

        if (userPatchRequest.getLanguages() != null && !userPatchRequest.getLanguages().isEmpty()) {
            this.languages.clear();
            this.languages.addAll(userPatchRequest.getLanguages());
        }

        if (userPatchRequest.getProfileImage() != null && !userPatchRequest.getProfileImage().equals(this.profileImage)) {
            this.profileImage = userPatchRequest.getProfileImage();
        }

        if (userPatchRequest.getBio() != null && !userPatchRequest.getBio().equals(this.bio)) {
            this.bio = userPatchRequest.getBio();
        }

        return this;
    }

}
