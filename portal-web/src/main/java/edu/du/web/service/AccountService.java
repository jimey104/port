package edu.du.web.service;

import edu.du.web.dto.AccountDTO;
import edu.du.web.entity.Account;
import edu.du.web.repository.AccountRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

//    private final RedisTemplate <String, Object> redisTemplate;
//
//    public AccountService(RedisTemplate<String, Object> redisTemplate, AccountRepository accountRepository, AccountRepository accountRepository1) {
//        this.redisTemplate = redisTemplate;
//        this.accountRepository = accountRepository1;
//    }

    private final AccountRepository accountRepository;


    /**
     *  계정을 로그인하는 메서드
     */
    public boolean loginAccount(AccountDTO accountDTO) {
        Account account = accountRepository.findByEmail(accountDTO.getEmail());
        if(account == null) {
            return false;
        }
        if(!accountDTO.getPassword().equals(account.getPassword())) {
            return false;
        }
//        redisTemplate.opsForValue().set("accountKey", account.getName());
        return true;
    }

    /**
     * 로그인한 계정정보를 받는 메서드
     */
    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public String getAccount(HttpSession session) {
        return session.getAttribute("accountName").toString();
    }

    /**
     * 로그인 유무를 확인하는 메서드
     */
    public boolean checkAccount(HttpSession session) {
        return session.getAttribute("accountName") != null;
    }

    /**
     * 새로운 계정을 저장하는 메서드
     */
    public void saveAccount(AccountDTO accountDTO) {
        List<Account> list = accountRepository.findAll();

        int result = 0;
        for(Account account : list) {
            if(account.getId().equals(accountDTO.getId())) {
                account.setPassword(accountDTO.getPassword());
                account.setName(accountDTO.getName());
                account.setEmail(accountDTO.getEmail());
                accountRepository.save(account);
                result = 1;
                break;
            }
        }
        if(result == 0) {
            Account account = convertToEntity(accountDTO);
            accountRepository.save(account);
        }
    }

    /**
     * 엔티티를 DTO로 변환하는 메서드
     */
    private AccountDTO convertToDTO(Account account) {
        return AccountDTO.builder()
                .id(account.getId())
                .password(account.getPassword())
                .name(account.getName())
                .email(account.getEmail())
                .build();
    }

    /**
     * DTO를 엔터티로 변환하는 메서드
     */
    private Account convertToEntity(AccountDTO accountDTO) {
        return Account.builder()
                .id(accountDTO.getId())
                .password(accountDTO.getPassword())
                .name(accountDTO.getName())
                .email(accountDTO.getEmail())
                .build();
    }
}
