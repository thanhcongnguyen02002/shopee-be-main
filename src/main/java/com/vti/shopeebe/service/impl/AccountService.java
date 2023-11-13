package com.vti.shopeebe.service.impl;

import com.vti.shopeebe.exception.AppException;
import com.vti.shopeebe.exception.ErrorResponseBase;
import com.vti.shopeebe.modal.entity.Account;
import com.vti.shopeebe.modal.entity.Role;
import com.vti.shopeebe.modal.request.CreateAccountRequest;
import com.vti.shopeebe.modal.request.UpdateAccountRequest;
import com.vti.shopeebe.repository.AccountRepository;
import com.vti.shopeebe.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService, UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optional = accountRepository.findByUsername(username);
        if (optional.isPresent()) {
            Account account = optional.get();
            // Lấy giá trị authorities để phân quyền
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(account.getRole());
            return new User(account.getUsername(), account.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getById(int id) {
        Optional<Account> optional = accountRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppException(ErrorResponseBase.NOT_FOUND);
        }
        return optional.get();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void create(CreateAccountRequest request) {
        Account account = new Account();
        BeanUtils.copyProperties(request, account);
//        Kiểm tra username đã tồn tại hay chưa
        Optional<Account> acccountCheck = accountRepository.findByUsername(request.getUsername());
        if (acccountCheck.isPresent()) {
//            username đã tồn tại thì sẽ bắn lỗi
            throw new AppException(ErrorResponseBase.USERNAME_EXISTED);
        }
        String encodePassword = encoder.encode(request.getPassword());
        account.setPassword(encodePassword);
        account.setRole(Role.CUSTOMER);
        accountRepository.save(account);
    }

    @Override
    public Account update(int id, UpdateAccountRequest request) {
        Account accountDb = getById(id);
        if (accountDb != null) {
            BeanUtils.copyProperties(request, accountDb);
            accountDb.setId(id);
            return accountRepository.save(accountDb);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        accountRepository.deleteById(id);
    }

}
