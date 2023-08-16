package net.royalur.rules.state;

import net.royalur.model.*;
import net.royalur.model.dice.Roll;

import javax.annotation.Nonnull;

/**
 * A game state represents a single point within a game.
 * @param <P> The type of pieces that are stored on the board in this game state.
 * @param <S> The type of state that is stored for each player.
 * @param <R> The type of rolls that may be stored in this game state.
 */
public interface GameState<P extends Piece, S extends PlayerState, R extends Roll> {

    /**
     * Get the state of the pieces on the board.
     * @return The state of the pieces on the board.
     */
    @Nonnull Board<P> getBoard();

    /**
     * Get the state of the light player.
     * @return The state of the light player.
     */
    @Nonnull S getLightPlayer();

    /**
     * Get the state of the dark player.
     * @return The state of the dark player.
     */
    @Nonnull S getDarkPlayer();

    /**
     * Retrieves the state of the player {@code player}.
     * @param player The player to retrieve the state of.
     * @return The state of the player {@code player}.
     */
    default @Nonnull S getPlayer(@Nonnull PlayerType player) {
        return switch (player) {
            case LIGHT -> getLightPlayer();
            case DARK -> getDarkPlayer();
        };
    }

    /**
     * Returns whether this state is a valid state to be played from.
     * @return Whether this state is a valid state to be played from.
     */
    boolean isPlayable();

    /**
     * Returns whether this state represents a finished game.
     * @return Whether this state represents a finished game.
     */
    boolean isFinished();

    /**
     * Generates an English text description of the state of the game.
     * @return An English text description of the state of the game.
     */
    @Nonnull String describe();
}
