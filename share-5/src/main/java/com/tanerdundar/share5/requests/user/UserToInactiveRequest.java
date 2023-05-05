package com.tanerdundar.share5.requests.user;

import com.tanerdundar.share5.entities.Statu;
import lombok.Data;

@Data
public class UserToInactiveRequest {


    private Statu userStatu;
}
