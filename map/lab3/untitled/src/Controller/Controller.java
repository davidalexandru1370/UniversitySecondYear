package Controller;

import Model.ProgramState;
import Repository.Interfaces.IRepository;

public class Controller {

    IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    void add(ProgramState programState){
        repository.add(programState);
    }
}
