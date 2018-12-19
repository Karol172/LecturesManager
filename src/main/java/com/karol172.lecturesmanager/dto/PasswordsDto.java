package com.karol172.lecturesmanager.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class PasswordsDto implements Serializable {

    @NotNull
    private String oldPassword;

    @NotNull
    private String newPassword;

    public PasswordsDto() { }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
