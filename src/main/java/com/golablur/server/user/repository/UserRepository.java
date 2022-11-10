package com.golablur.server.user.repository;

import com.golablur.server.user.domain.LoginDTO;
import com.golablur.server.user.domain.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    public boolean findUser(String user_id) {
        return false;
    }

    public boolean signUp(UserDTO userDTO) {
        return false;
    }

    public boolean login(LoginDTO loginDTO) {
        return false;
    }

    public List getRowData(String sessionToken) {
        return null;
    }

    public boolean removeRowData(String sessionToken) {
        return false;
    }

    public boolean updateRowData(String user_id, List rowData) {
        return false;
    }

}
