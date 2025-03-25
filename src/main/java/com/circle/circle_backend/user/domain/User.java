package com.circle.circle_backend.user.domain;
import com.circle.circle_backend.user.dto.request.UserPatchRequest;
import com.circle.circle_backend.user.domain.enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

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
    @Enumerated(EnumType.STRING)
    private List<Language> languages = new ArrayList<>();

    @Column(name = "bio")
    private String bio;

    @Builder
    public User(String email, String password, String firstName, String lastName, String nickname, String school, String country, String profileImage, String major, List<Language> languages, String bio) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.school = school;
        this.country = country;
        this.profileImage = profileImage;
        this.major = major;
        this.languages = languages;
        this.bio = bio;
    }

    public void patch(UserPatchRequest userPatchRequest) {
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

    }

}
