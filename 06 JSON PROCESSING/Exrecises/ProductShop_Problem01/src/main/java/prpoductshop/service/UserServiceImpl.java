package prpoductshop.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;
import prpoductshop.domain.dtos.*;
import prpoductshop.domain.entities.Product;
import prpoductshop.domain.entities.User;
import prpoductshop.repository.ProductRepository;
import prpoductshop.repository.UserRepository;
import prpoductshop.util.ValidatorUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public UserServiceImpl(ValidatorUtil validatorUtil, UserRepository userRepository, ModelMapper modelMapper, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.userRepository = userRepository;

        this.productRepository = productRepository;
    }


    @Override
    public void seedUsers(UserDto[] userDtos) {
        for (UserDto userDto : userDtos) {
            if (this.validatorUtil.isValid(userDto)) {
                User user = this.modelMapper.map(userDto, User.class);
                this.userRepository.saveAndFlush(user);
            }
        }
    }

    @Override
    public UserDtoQuery02[] query2() {
        List<User> user = this.userRepository.findAllwithOneSellerAndBuyerQuery2();
        UserDtoQuery02[] userDtos = this.modelMapper.map(user, UserDtoQuery02[].class);
        return userDtos;
    }

    @Override
    public BasicDtosQuery04 query04() {
        List<User> user = this.userRepository.findAllWithOneSellerQuery04();
        BasicDtosQuery04 basicDtosQuery04 = new BasicDtosQuery04();
        UserDtoQuery04[] usersDto = this.modelMapper.map(user, UserDtoQuery04[].class);
        int count=-1;
        for (User user1 : user) {
            count++;
            Long id=user1.getId();
                List<Product> products = this.productRepository.findAllBySellerId(user1.getId());
                ProductDtoQuery04[] productsDto = this.modelMapper.map(products, ProductDtoQuery04[].class);
              ProductsDtoQuery04 pr =new ProductsDtoQuery04();
               pr.setCount(productsDto.length);
               pr.setSoldProducts(Arrays.asList(productsDto));
              usersDto[count].setSoldProducts(pr);
                System.out.println();

        }
        basicDtosQuery04.setUsersCount(user.size());
        basicDtosQuery04.setUsers(Arrays.asList(usersDto));

        return basicDtosQuery04;
    }


}
