package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.exceptions.InvalidDataException;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private static final int MINIMAL_AGE = 18;
    private static final int MINIMAL_LENGTH = 6;
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user.getLogin() == null
                || user.getLogin().length() < MINIMAL_LENGTH
                || user.getLogin().isEmpty()) {
            throw new InvalidDataException("Wrong login length given!");
        }
        if (user.getAge() == null || user.getAge() < MINIMAL_AGE) {
            throw new InvalidDataException("Wrong age given!");
        }
        if (user.getPassword() == null
                || user.getPassword().length() < MINIMAL_LENGTH
                || user.getPassword().isEmpty()) {
            throw new InvalidDataException("Wrong password length given!");
        }
        if (storageDao.get(user.getLogin()) != null) {
            throw new InvalidDataException("User with this login already exists!");
        }

        storageDao.add(user);
        return user;
    }
}
