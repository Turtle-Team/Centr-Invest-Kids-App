package com.turtleteam.impl.data.api.service

import com.turtleteam.api.data.api.model.UserDTOReceive
import com.turtleteam.api.data.api.repository.AccountRepository
import com.turtleteam.api.data.api.service.AccountService
import com.turtleteam.impl.extension.hashString

class AccountServiceImpl(private val repository: AccountRepository) : AccountService {

    override suspend fun registerUser(user: UserDTOReceive) {
        val passwordHashing = user.password.hashString("SHA-256")
        val userHashing = UserDTOReceive(
            login = user.login,
            password = passwordHashing
        )
        repository.registerUser(userHashing)
    }

    override suspend fun authUser(login: String, password: String): String {
        val passwordHashing = password.hashString("SHA-256")
        return repository.authUser(login, passwordHashing)
    }
}
