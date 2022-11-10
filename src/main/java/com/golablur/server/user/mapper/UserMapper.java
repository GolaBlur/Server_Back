package com.golablur.server.user.mapper;

import com.golablur.server.user.domain.LoginDTO;
import com.golablur.server.user.domain.UserDTO;

import java.util.List;

public interface UserMapper {

    public boolean findUser(String user_id);

    public String signUp(UserDTO userDTO);

    public boolean login(LoginDTO loginDTO);

    public List getRowData(String sessionToken);

    public boolean removeRowData(String sessionToken);

    public boolean updateRowData(String user_id, List rowData);

}
