package taxi.service;

import java.util.Optional;
import taxi.exception.AuthenticationException;
import taxi.lib.Inject;
import taxi.lib.Service;
import taxi.model.Driver;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverService driverService;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Optional<Driver> driver = driverService.findByLogin(login);
        if (driver.isEmpty()) {
            throw new AuthenticationException("Username or password are incorrect, "
                    + "please try again");
        }
        if (driver.get().getPassword().equals(password)) {
            return driver.get();
        }
        throw new AuthenticationException("Username or password are incorrect, "
                + "please try again");
    }
}
