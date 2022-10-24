package Repository;

import Model.ProgramState;

import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{

    private List<ProgramState> programs = new ArrayList<>();
    public Repository() {

    }

    void add(ProgramState program){
        programs.add(program);
    }

    @Override
    public ProgramState getCurrentProgram() {
        return null;
    }
}
