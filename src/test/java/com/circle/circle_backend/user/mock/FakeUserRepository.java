//package com.circle.circle_backend.user.mock;
//
//import com.circle.circle_backend.user.domain.User;
//import com.circle.circle_backend.user.service.UserRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//public class FakeUserRepository implements UserRepository {
//
//    private final List<User> data = new ArrayList<>();
//
//    @Override
//    public User save(User user) {
//        if (user.getId() == null || user.getId() == 0) {
//            User newUser = User.builder()
//                    .id(user.getId())
//                    .email(user.getEmail())
//                    .password(user.getPassword())
//                    .firstName(user.getFirstName())
//                    .lastName(user.getLastName())
//                    .nickname(user.getNickname())
//                    .country(user.getCountry())
//                    .school(user.getSchool())
//                    .profileImage(user.getProfileImage())
//                    .build();
//            data.add(newUser);
//            return newUser;
//        } else {
//            data.removeIf(item -> Objects.equals(item.getId(), user.getId()));
//            data.add(user);
//            return user;
//        }
//    }
//}
