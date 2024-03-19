package br.com.ExpensesPortal.listeners;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import br.com.ExpensesPortal.entities.UserEntity;
import br.com.ExpensesPortal.services.RequestService;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Component
@Configurable(autowire = Autowire.BY_TYPE, dependencyCheck = true)
public class UserListener {

    private static RequestService requestService;

    @Autowired
    public void init(RequestService requestService) {
        UserListener.requestService = requestService;
    }
    
    @PrePersist
    public void onPersist(UserEntity user) {
        requestService.requestToRegisterOrderer(user);
    }

    @PreUpdate
    public void onUpdate(UserEntity user) {
        requestService.requestToUpdateOrderer(user);
    }

}