package core;

import org.osbot.rs07.script.Script;

public abstract class Node {

    public Script script;

    public Node(Script script) {
        this.script = script;
    }

    //returns status of Node
    public String status() {
        return "";
    }

    //When conditions are met the Node's execute function is called
    public abstract boolean validate() throws InterruptedException;

    //performs all the tasks of Node
    public abstract boolean execute() throws InterruptedException;
}
