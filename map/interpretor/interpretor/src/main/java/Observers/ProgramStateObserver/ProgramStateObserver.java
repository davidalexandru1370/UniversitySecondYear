package Observers.ProgramStateObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import Model.ProgramState;

public abstract class ProgramStateObserver {
    protected List<Consumer<ProgramState>> displayMethods = new ArrayList<>();

    public void addObservant(Consumer<ProgramState> method) {
        displayMethods.add(method);
    }

    public abstract void sendNotify(ProgramState programState);
}
