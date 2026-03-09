package web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.Dao.DaoUsers;
import web.Model.User;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private DaoUsers daoUsers;


    @Transactional
    public void add(User user) {
        if (user != null) {
            daoUsers.add(user);
        }
    }

    @Transactional
    public void update(User user) {
        daoUsers.update(user);
    }

    @Transactional
    public void delete(User user) {
        daoUsers.deletebyid(user.getId());
    }

    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return daoUsers.listUsers();
    }
}
