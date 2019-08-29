package prpoductshop.domain.dtos;

import com.google.gson.annotations.Expose;
import prpoductshop.domain.entities.User;

import java.util.List;

public class BasicDtosQuery04 {
    @Expose
    private Integer usersCount;
    @Expose
    private List<UserDtoQuery04> users;

    public BasicDtosQuery04() {
    }

    public Integer getUsersCount() {
        return this.usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserDtoQuery04> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserDtoQuery04> users) {
        this.users = users;
    }
}
