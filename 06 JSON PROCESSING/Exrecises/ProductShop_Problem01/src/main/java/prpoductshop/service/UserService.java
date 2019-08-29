package prpoductshop.service;

import prpoductshop.domain.dtos.*;

import java.io.IOException;


public interface UserService {
    void seedUsers(UserDto[] userDtos);
    UserDtoQuery02[] query2();
   BasicDtosQuery04 query04();
}
