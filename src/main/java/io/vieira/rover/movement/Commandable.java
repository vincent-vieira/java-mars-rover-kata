package io.vieira.rover.movement;

/**
 * Interface defining the behavior of anything capable of being commanded through a single command or an array of commands.
 * This interface is generically-typed with a T parameter in order to allow methods calls chaining to expose a fluent API.
 *
 * @author <a href="mailto:vincent.vieira@supinfo.com">Vincent Vieira</a>
 */
public interface Commandable<T> {

    T receiveCommand(char command);
    default T receiveCommands(String commands){
        commands.chars().forEach(i -> receiveCommand((char) i));
        return (T) this;
    }
}
