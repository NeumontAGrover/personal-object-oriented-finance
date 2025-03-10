package poof.controller.AuthController;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import poof.models.Account.Account;
import poof.models.Meta.Adapters.DateSerializer;
import poof.models.Meta.Adapters.DateDeserializer;
import poof.models.Meta.Logger.Loggable;

public class AuthHandler extends Loggable {
    private HashMap<String, Account> users = new HashMap<>();
    private final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .registerTypeAdapter(DateSerializer.class, new DateSerializer())
            .registerTypeAdapter(DateDeserializer.class, new DateDeserializer())
            .setPrettyPrinting()
            .create();

    private static final String USERSTATEPATH = "users_state.json";

    /* == Public interface == */
    public AuthHandler() {
        setUsers(mergePrevStates());
    }

    public void addAccount(Account account) {
        if (users == null) return;

        users.put(account.getUsername(), account);
        writeUserState();
    }

    public Account getAccount(String username) {
        return users.get(username);
    }

    public boolean authenticate(String username, String password) {
        Account account = getAccount(username);
        return account != null && account.getPassword().equals(password);
    }

    public boolean contains(String username) {
        if (users == null) return false;

        return users.containsKey(username);
    }

    /* == Private interface == */
    private HashMap<String, Account> mergePrevStates() {
        HashMap<String, Account> prevStates = new HashMap<>();

        try(FileReader reader = new FileReader(USERSTATEPATH)) {
            Account[] accounts = gson.fromJson(reader, Account[].class);

            if (accounts == null) {
                return prevStates;
            }

            for(Account account : accounts) {
                prevStates.put(account.getUsername(), account);
            }

            return prevStates;
        }

        catch(IOException ex) {
            System.err.println(ex.getMessage());
            return null;
        }

        catch(JsonIOException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    private void writeUserState() {
        try(FileWriter writer = new FileWriter(USERSTATEPATH)) {
            gson.toJson(getUsers().values(), writer);
        }

        catch(IOException ex) {
            System.err.println("Could not write users");
            System.err.println(ex.getMessage());
        }
    }

    /* == Getters & setters == */
    public HashMap<String, Account> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, Account> users) {
        this.users = users;
    }
}
