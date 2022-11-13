package com.golablur.server.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DuringWorkDTO {
    public String id;
    public String pw;
    public String sessionToken;
}
