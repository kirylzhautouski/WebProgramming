package dev.kirillzhelt.db.daos.interfaces;

import javax.ejb.Remote;

@Remote
public interface UserDaoInterface {

    boolean login(String login, String password);

}
