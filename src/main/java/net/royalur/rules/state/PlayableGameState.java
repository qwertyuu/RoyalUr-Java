package net.royalur.rules.state;

import net.royalur.model.*;
import net.royalur.name.Name;

import javax.annotation.Nonnull;

/**
 * A game state where we are waiting for interactions from a player.
 * @param <P> The type of pieces that are stored on the board in this game state.
 * @param <S> The type of state that is stored for each player.
 * @param <R> The type of rolls that may be stored in this game state.
 * @param <A> The type of name given to this action.
 */
public interface PlayableGameState<
        P extends Piece,
        S extends PlayerState,
        R extends Roll,
        A extends Name
> extends OngoingGameState<P, S, R> {

    /**
     * Gets the type of action that is expected from the current turn player.
     * @return The type of action that is expected from the current player.
     */
    @Nonnull A getExpectedActionType();
}