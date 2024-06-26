package net.royalur.model.dice;

import net.royalur.name.Name;
import net.royalur.name.Named;

import javax.annotation.Nonnull;
import java.util.random.RandomGenerator;

/**
 * A factory that creates dice.
 * @param <R> The type of roll that this dice produce.
 */
public interface DiceFactory<R extends Roll> extends Named<Name> {

    /**
     * Create an instance of the dice using a default source of randomness.
     * @return The instance of the dice using a default source of randomness.
     */
    @Nonnull Dice<R> createDice();

    /**
     * Generates a roll with the given value.
     * This is used solely for serialisation, and should not be used
     * for simulating games. Implementations may override this and
     * throw an error if they require additional information to create
     * their rolls.
     * @param value The value of the dice roll.
     * @return A roll with the given value.
     */
    default @Nonnull R createRoll(int value) {
        return createDice().generateRoll(value);
    }
}
