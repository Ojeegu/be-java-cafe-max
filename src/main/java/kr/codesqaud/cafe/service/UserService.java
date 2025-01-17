package kr.codesqaud.cafe.service;

import kr.codesqaud.cafe.controller.dto.UserJoinDTO;
import kr.codesqaud.cafe.controller.dto.UserReadDTO;
import kr.codesqaud.cafe.domain.User;
import kr.codesqaud.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(final UserJoinDTO userJoinDTO) {
        //1. UserJoinDTO -> User 변환
        User user = User.toUser(userJoinDTO);

        //2. repository signUp 메서드 호출
        userRepository.join(user);
    }

    public UserReadDTO findOne(final long id) {
        Optional<User> wantedUser = userRepository.findById(id);
        return wantedUser.map(UserReadDTO::toUserReadDTO).orElse(null);
    }

    public List<UserReadDTO> findUsers() {
        List<User> userList = userRepository.findAll();
        List<UserReadDTO> userReadDTOList = new ArrayList<>();
        for (User user : userList) {
            userReadDTOList.add(UserReadDTO.toUserReadDTO(user));
        }
        return userReadDTOList;
    }
}



/*
        if (wantedUser.isPresent()) {
        //Optional 객체를 꺼내 가져오려면 get() 메서드 사용
            return UserReadDTO.toUserReadDTO(wantedUser.get());
        }
        return null;
        을 한줄로 변환하면
        => return wantedUser.map(UserReadDTO::toUserReadDTO).orElse(null);

 */
