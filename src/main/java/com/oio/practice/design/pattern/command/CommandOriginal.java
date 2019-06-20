package com.oio.practice.design.pattern.command;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 命令模式
 * @author Liqc
 * @date 2019/6/20 10:00
 */
public class CommandOriginal {

    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.executeCommand();
    }

}

@NoArgsConstructor
@AllArgsConstructor
abstract class Command {

    protected Receiver receiver;

    public abstract void execute();
}

@NoArgsConstructor
class ConcreteCommand extends Command {

    public ConcreteCommand (Receiver receiver) {
        super.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }
}

@Setter
class Invoker {
    private Command command;
    public void executeCommand() {
        command.execute();
    }
}

class Receiver {

    public void action() {
        System.out.println("receive and action");
    }

}
