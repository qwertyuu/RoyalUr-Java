package net.royalur.agent.utility;

import net.royalur.Game;
import net.royalur.model.Piece;
import net.royalur.model.PlayerState;
import net.royalur.model.PlayerType;
import net.royalur.model.dice.Roll;
import net.royalur.name.Name;
import net.royalur.name.Named;
import net.royalur.rules.state.GameState;

import javax.annotation.Nonnull;

/**
 * A function that is used to score game states.
 */
public abstract class UtilityFunction<
        P extends Piece,
        S extends PlayerState,
        R extends Roll
> implements Named<Name> {

    /**
     * The name of this path pair.
     */
    private final @Nonnull Name name;

    /**
     * Instantiates a utility function.
     * @param name The name of this utility function.
     */
    public UtilityFunction(@Nonnull Name name) {
        this.name = name;
    }

    @Override
    public @Nonnull Name getName() {
        return name;
    }

    /**
     * Scores the state of the game numerically, where a positive
     * value represents that light is advantaged, and a negative
     * value represents that dark is advantaged.
     * @param state The state of the game.
     * @return A utility value for light in the given state.
     */
    public abstract float scoreGameStateForLight(@Nonnull GameState<P, S, R> state);

    /**
     * Scores the state of the game numerically, where a positive
     * value represents that the current player is advantaged, and
     * a negative value represents that the waiting player is advantaged.
     * @param game The game to evaluate.
     * @return A utility value for the current player of the game.
     */
    public float scoreGame(@Nonnull Game<P, S, R> game) {
        float lightUtility = scoreGameStateForLight(game.getCurrentState());
        return game.getTurnOrWinner() == PlayerType.LIGHT ? lightUtility : -lightUtility;
    }
}