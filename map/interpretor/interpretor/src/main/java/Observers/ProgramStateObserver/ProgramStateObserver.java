package Observers.ProgramStateObserver;

import java.util.ArrayList;
import java.util.List;

import Model.ProgramState;

public abstract class ProgramStateObserver {
    protected List<Runnable> displayMethods = new ArrayList<>();

    public void addObservant(Runnable method) {
        displayMethods.add(method);
    }

    public abstract void sendNotify();
}
