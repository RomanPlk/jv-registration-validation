package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private static final int MIN_AGE_VALUE = 18;
    private static final int MIN_PASSWORD_SIZE = 6;
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user.getAge() < MIN_AGE_VALUE) {
            throw new RuntimeException("It's unable to register user with age less than "
                    + MIN_AGE_VALUE + ".");
        }
        if (user.getPassword().length() < MIN_PASSWORD_SIZE) {
            throw new RuntimeException("Error. Your password should contain at least "
                    + MIN_PASSWORD_SIZE + " characters.");
        }
        if (Storage.people.contains(user)) {
            throw new RuntimeException("Error. User with such login already exists.");
        }
        if (user == null || user.getAge() == null
                || user.getLogin() == null
                || user.getPassword() == null) {
            throw new RuntimeException("Error. Invalid data type.");
        }
        return storageDao.add(user);
    }
}
