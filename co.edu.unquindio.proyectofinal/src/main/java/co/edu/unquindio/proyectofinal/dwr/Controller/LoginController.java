package co.edu.unquindio.proyectofinal.dwr.Controller;

public class LoginController {
    ModelFactoryController modelFactory;

    public LoginController() {
        modelFactory = ModelFactoryController.getInstance();
    }

    public boolean iniciarSesion(String user, String password) {
        return modelFactory.iniciarSesion(user,password);
    }
}
