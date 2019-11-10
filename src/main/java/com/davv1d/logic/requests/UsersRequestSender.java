package com.davv1d.logic.requests;

import com.davv1d.logic.domain.user.User;
import com.davv1d.logic.requests.url.UserUrlCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class UsersRequestSender extends Request {
    @Autowired
    private UserUrlCreator userUrlCreator;

    public ResponseEntity<User[]> getAllUsers() {
        URI usersRootUri = userUrlCreator.getUsersRootUri();
        return sendRequestWithAuthorize(usersRootUri, HttpMethod.GET, User[].class, null);
    }

    public ResponseEntity<Void> changeUserEmail(final String email) {
        URI changeEmailUri = userUrlCreator.getChangeEmailUri();
        return sendRequestWithAuthorize(changeEmailUri, HttpMethod.PUT, Void.class, email);
    }

    public ResponseEntity<Void> deleteUserByUsername(String username) {
        URI userByUsernameUri = userUrlCreator.getUserByUsernameUri(username);
        return sendRequestWithAuthorize(userByUsernameUri, HttpMethod.DELETE, Void.class, null);
    }

    public ResponseEntity<User> getUserByUsername(final String username) {
        URI userByUsernameUri = userUrlCreator.getUserByUsernameUri(username);
        return sendRequestWithAuthorize(userByUsernameUri, HttpMethod.GET, User.class, null);
    }

    public ResponseEntity<User> getLoggedUser() {
        URI loggedUser = userUrlCreator.getLoggedUserUri();
        return sendRequestWithAuthorize(loggedUser, HttpMethod.GET, User.class, null);
    }
}
