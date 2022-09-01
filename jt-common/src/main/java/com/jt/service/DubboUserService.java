package com.jt.service;

import com.jt.pojo.User;
import org.eclipse.jdt.internal.compiler.codegen.DoubleCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public interface DubboUserService {
    @Transactional
    void saveUser(User user);

    String doLogin(User user);
}
