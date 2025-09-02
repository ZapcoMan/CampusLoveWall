package org.campuswall.springbootcampuslovewall.auth.strategy;

import org.campuswall.springbootcampuslovewall.entity.Account;

public interface AccountConvertStrategy<T> {
    /**
     * 将领域对象 T 转换成 Account DTO，并生成 token
     *
     * @param domain 领域对象
     * @return Account DTO
     */
    Account convertToAccount(T domain);
    /**
     * 将 Account DTO 转换成领域对象 T
     */
    T convertToDomain(Account account);
}
