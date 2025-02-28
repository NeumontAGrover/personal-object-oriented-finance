package poof.controller.AuthController;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import poof.models.Account.Account;
import poof.models.Meta.Adapters.DateSerializer;
import poof.models.Meta.Adapters.DateDeserializer;

public class AuthHandler {
    private final HashMap<String, Account> users = new HashMap<>();
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(DateSerializer.class, new DateSerializer())
            .registerTypeAdapter(DateDeserializer.class, new DateDeserializer())
            .setPrettyPrinting()
            .create();

    private static final String USERSTATEPATH = "user_state.json";

    public AuthHandler() {
        // mergeUsers()
    }
}