package com.dzq.service.user;

import com.dzq.bean.Users;
import com.dzq.service.IBaseService;

public interface UserService extends IBaseService<Users> {
    String validateName(String username);
    Users login (String username,String password);
}
