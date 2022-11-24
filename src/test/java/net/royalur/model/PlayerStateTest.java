package net.royalur.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerStateTest {

    @Test
    public void testNew() {
        PlayerState player = new PlayerState(Player.LIGHT, 1, 1);
        assertEquals(Player.LIGHT, player.player);
        assertEquals(Player.LIGHT.name, player.name);
        assertEquals(1, player.pieces);
        assertEquals(1, player.score);

        player = new PlayerState(Player.DARK, 2, 3);
        assertEquals(Player.DARK, player.player);
        assertEquals(Player.DARK.name, player.name);
        assertEquals(2, player.pieces);
        assertEquals(3, player.score);

        player = new PlayerState(Player.LIGHT, "Jeff", 5, 4);
        assertEquals(Player.LIGHT, player.player);
        assertEquals("Jeff", player.name);
        assertEquals(5, player.pieces);
        assertEquals(4, player.score);

        player = new PlayerState(Player.DARK, "Bob", 10, 11);
        assertEquals(Player.DARK, player.player);
        assertEquals("Bob", player.name);
        assertEquals(10, player.pieces);
        assertEquals(11, player.score);

        assertThrows(IllegalArgumentException.class, () -> new PlayerState(Player.LIGHT, -1, 1));
        assertThrows(IllegalArgumentException.class, () -> new PlayerState(Player.LIGHT, 1, -1));
        assertThrows(IllegalArgumentException.class, () -> new PlayerState(Player.DARK, -9, -9));
    }

    @Test
    public void testWithPieceCount() {
        PlayerState player = new PlayerState(Player.LIGHT, 1, 1);
        assertEquals(Player.LIGHT, player.player);
        assertEquals(Player.LIGHT.name, player.name);
        assertEquals(1, player.pieces);
        assertEquals(1, player.score);

        PlayerState player2 = player.withPieceCount(7);
        assertEquals(Player.LIGHT, player.player);
        assertEquals(Player.LIGHT.name, player.name);
        assertEquals(1, player.pieces);
        assertEquals(1, player.score);
        assertEquals(Player.LIGHT, player2.player);
        assertEquals(Player.LIGHT.name, player2.name);
        assertEquals(7, player2.pieces);
        assertEquals(1, player2.score);

        player2 = player.withPieceCount(6);
        assertEquals(Player.LIGHT, player.player);
        assertEquals(Player.LIGHT.name, player.name);
        assertEquals(1, player.pieces);
        assertEquals(1, player.score);
        assertEquals(Player.LIGHT, player2.player);
        assertEquals(Player.LIGHT.name, player2.name);
        assertEquals(6, player2.pieces);
        assertEquals(1, player2.score);

        player2 = player2.withPieceCount(8);
        assertEquals(Player.LIGHT, player2.player);
        assertEquals(Player.LIGHT.name, player2.name);
        assertEquals(8, player2.pieces);
        assertEquals(1, player2.score);

        player = new PlayerState(Player.DARK, 2, 3);
        assertEquals(Player.DARK, player.player);
        assertEquals(Player.DARK.name, player.name);
        assertEquals(2, player.pieces);
        assertEquals(3, player.score);

        player2 = player.withPieceCount(8);
        assertEquals(Player.DARK, player.player);
        assertEquals(Player.DARK.name, player.name);
        assertEquals(2, player.pieces);
        assertEquals(3, player.score);
        assertEquals(Player.DARK, player2.player);
        assertEquals(Player.DARK.name, player2.name);
        assertEquals(8, player2.pieces);
        assertEquals(3, player2.score);

        PlayerState finalPlayer = player;
        assertThrows(IllegalArgumentException.class, () -> finalPlayer.withPieceCount(-1));
    }

    @Test
    public void testWithScore() {
        PlayerState player = new PlayerState(Player.LIGHT, 1, 1);
        assertEquals(Player.LIGHT, player.player);
        assertEquals(Player.LIGHT.name, player.name);
        assertEquals(1, player.pieces);
        assertEquals(1, player.score);

        PlayerState player2 = player.withScore(7);
        assertEquals(Player.LIGHT, player.player);
        assertEquals(Player.LIGHT.name, player.name);
        assertEquals(1, player.pieces);
        assertEquals(1, player.score);
        assertEquals(Player.LIGHT, player2.player);
        assertEquals(Player.LIGHT.name, player2.name);
        assertEquals(1, player2.pieces);
        assertEquals(7, player2.score);

        player2 = player.withScore(6);
        assertEquals(Player.LIGHT, player.player);
        assertEquals(Player.LIGHT.name, player.name);
        assertEquals(1, player.pieces);
        assertEquals(1, player.score);
        assertEquals(Player.LIGHT, player2.player);
        assertEquals(Player.LIGHT.name, player2.name);
        assertEquals(1, player2.pieces);
        assertEquals(6, player2.score);

        player2 = player2.withScore(8);
        assertEquals(Player.LIGHT, player2.player);
        assertEquals(Player.LIGHT.name, player2.name);
        assertEquals(1, player2.pieces);
        assertEquals(8, player2.score);

        player = new PlayerState(Player.DARK, 3, 2);
        assertEquals(Player.DARK, player.player);
        assertEquals(Player.DARK.name, player.name);
        assertEquals(3, player.pieces);
        assertEquals(2, player.score);

        player2 = player.withScore(8);
        assertEquals(Player.DARK, player.player);
        assertEquals(Player.DARK.name, player.name);
        assertEquals(3, player.pieces);
        assertEquals(2, player.score);
        assertEquals(Player.DARK, player2.player);
        assertEquals(Player.DARK.name, player2.name);
        assertEquals(3, player2.pieces);
        assertEquals(8, player2.score);

        PlayerState finalPlayer = player;
        assertThrows(IllegalArgumentException.class, () -> finalPlayer.withScore(-1));
    }

    @Test
    public void testWithOneMorePiece() {
        PlayerState player = new PlayerState(Player.LIGHT, 1, 1);
        assertEquals(Player.LIGHT, player.player);
        assertEquals(Player.LIGHT.name, player.name);
        assertEquals(1, player.pieces);
        assertEquals(1, player.score);

        PlayerState player2 = player.withOneMorePiece();
        assertEquals(Player.LIGHT, player.player);
        assertEquals(Player.LIGHT.name, player.name);
        assertEquals(1, player.pieces);
        assertEquals(1, player.score);
        assertEquals(Player.LIGHT, player2.player);
        assertEquals(Player.LIGHT.name, player2.name);
        assertEquals(2, player2.pieces);
        assertEquals(1, player2.score);

        player2 = player.withOneMorePiece();
        assertEquals(Player.LIGHT, player.player);
        assertEquals(Player.LIGHT.name, player.name);
        assertEquals(1, player.pieces);
        assertEquals(1, player.score);
        assertEquals(Player.LIGHT, player2.player);
        assertEquals(Player.LIGHT.name, player2.name);
        assertEquals(2, player2.pieces);
        assertEquals(1, player2.score);

        player2 = player2.withOneMorePiece();
        assertEquals(Player.LIGHT, player2.player);
        assertEquals(Player.LIGHT.name, player2.name);
        assertEquals(3, player2.pieces);
        assertEquals(1, player2.score);

        player = new PlayerState(Player.DARK, 3, 2);
        assertEquals(Player.DARK, player.player);
        assertEquals(Player.DARK.name, player.name);
        assertEquals(3, player.pieces);
        assertEquals(2, player.score);

        player2 = player.withOneMorePiece();
        assertEquals(Player.DARK, player.player);
        assertEquals(Player.DARK.name, player.name);
        assertEquals(3, player.pieces);
        assertEquals(2, player.score);
        assertEquals(Player.DARK, player2.player);
        assertEquals(Player.DARK.name, player2.name);
        assertEquals(4, player2.pieces);
        assertEquals(2, player2.score);
    }

    @Test
    public void testWithOneLessPiece() {
        PlayerState player = new PlayerState(Player.LIGHT, 3, 1);
        assertEquals(Player.LIGHT, player.player);
        assertEquals(Player.LIGHT.name, player.name);
        assertEquals(3, player.pieces);
        assertEquals(1, player.score);

        PlayerState player2 = player.withOneLessPiece();
        assertEquals(Player.LIGHT, player.player);
        assertEquals(Player.LIGHT.name, player.name);
        assertEquals(3, player.pieces);
        assertEquals(1, player.score);
        assertEquals(Player.LIGHT, player2.player);
        assertEquals(Player.LIGHT.name, player2.name);
        assertEquals(2, player2.pieces);
        assertEquals(1, player2.score);

        player2 = player.withOneLessPiece();
        assertEquals(Player.LIGHT, player.player);
        assertEquals(Player.LIGHT.name, player.name);
        assertEquals(3, player.pieces);
        assertEquals(1, player.score);
        assertEquals(Player.LIGHT, player2.player);
        assertEquals(Player.LIGHT.name, player2.name);
        assertEquals(2, player2.pieces);
        assertEquals(1, player2.score);

        player2 = player2.withOneLessPiece();
        assertEquals(Player.LIGHT, player2.player);
        assertEquals(Player.LIGHT.name, player2.name);
        assertEquals(1, player2.pieces);
        assertEquals(1, player2.score);

        player2 = player2.withOneLessPiece();
        assertEquals(Player.LIGHT, player2.player);
        assertEquals(Player.LIGHT.name, player2.name);
        assertEquals(0, player2.pieces);
        assertEquals(1, player2.score);

        assertThrows(IllegalArgumentException.class, player2::withOneLessPiece);

        player = new PlayerState(Player.DARK, 3, 2);
        assertEquals(Player.DARK, player.player);
        assertEquals(Player.DARK.name, player.name);
        assertEquals(3, player.pieces);
        assertEquals(2, player.score);

        player2 = player.withOneLessPiece();
        assertEquals(Player.DARK, player.player);
        assertEquals(Player.DARK.name, player.name);
        assertEquals(3, player.pieces);
        assertEquals(2, player.score);
        assertEquals(Player.DARK, player2.player);
        assertEquals(Player.DARK.name, player2.name);
        assertEquals(2, player2.pieces);
        assertEquals(2, player2.score);

        player2 = player2.withOneLessPiece().withOneLessPiece();
        assertThrows(IllegalArgumentException.class, player2::withOneLessPiece);
    }

    @Test
    public void testWithOneMoreScore() {
        PlayerState player = new PlayerState(Player.LIGHT, 1, 1);
        assertEquals(Player.LIGHT, player.player);
        assertEquals(Player.LIGHT.name, player.name);
        assertEquals(1, player.pieces);
        assertEquals(1, player.score);

        PlayerState player2 = player.withOneMoreScore();
        assertEquals(Player.LIGHT, player.player);
        assertEquals(Player.LIGHT.name, player.name);
        assertEquals(1, player.pieces);
        assertEquals(1, player.score);
        assertEquals(Player.LIGHT, player2.player);
        assertEquals(Player.LIGHT.name, player2.name);
        assertEquals(1, player2.pieces);
        assertEquals(2, player2.score);

        player2 = player.withOneMoreScore();
        assertEquals(Player.LIGHT, player.player);
        assertEquals(Player.LIGHT.name, player.name);
        assertEquals(1, player.pieces);
        assertEquals(1, player.score);
        assertEquals(Player.LIGHT, player2.player);
        assertEquals(Player.LIGHT.name, player2.name);
        assertEquals(1, player2.pieces);
        assertEquals(2, player2.score);

        player2 = player2.withOneMoreScore();
        assertEquals(Player.LIGHT, player2.player);
        assertEquals(Player.LIGHT.name, player2.name);
        assertEquals(1, player2.pieces);
        assertEquals(3, player2.score);

        player = new PlayerState(Player.DARK, 3, 2);
        assertEquals(Player.DARK, player.player);
        assertEquals(Player.DARK.name, player.name);
        assertEquals(3, player.pieces);
        assertEquals(2, player.score);

        player2 = player.withOneMoreScore();
        assertEquals(Player.DARK, player.player);
        assertEquals(Player.DARK.name, player.name);
        assertEquals(3, player.pieces);
        assertEquals(2, player.score);
        assertEquals(Player.DARK, player2.player);
        assertEquals(Player.DARK.name, player2.name);
        assertEquals(3, player2.pieces);
        assertEquals(3, player2.score);
    }

    @Test
    public void testHashCode() {
        PlayerState one = new PlayerState(Player.LIGHT, 2, 3);
        PlayerState two = new PlayerState(Player.DARK, 3, 2);
        PlayerState three = new PlayerState(Player.LIGHT, "Jeff", 1, 1);
        PlayerState four = new PlayerState(Player.DARK, "Bob", 9, 9);

        assertEquals(one.hashCode(), one.hashCode());
        assertEquals(two.hashCode(), two.hashCode());
        assertEquals(three.hashCode(), three.hashCode());
        assertEquals(four.hashCode(), four.hashCode());

        assertEquals(one.hashCode(), new PlayerState(Player.LIGHT, 2, 3).hashCode());
        assertEquals(two.hashCode(), new PlayerState(Player.DARK, 3, 2).hashCode());
        assertEquals(three.hashCode(), new PlayerState(Player.LIGHT, "Jeff", 1, 1).hashCode());
        assertEquals(four.hashCode(), new PlayerState(Player.DARK, "Bob", 9, 9).hashCode());
    }

    @Test
    public void testEquals() {
        PlayerState one = new PlayerState(Player.LIGHT, 2, 3);
        PlayerState two = new PlayerState(Player.DARK, 3, 2);
        PlayerState three = new PlayerState(Player.LIGHT, "Jeff", 1, 1);
        PlayerState four = new PlayerState(Player.DARK, "Bob", 9, 9);

        assertEquals(one, one);
        assertEquals(two, two);
        assertEquals(three, three);
        assertEquals(four, four);

        assertEquals(one, new PlayerState(Player.LIGHT, 2, 3));
        assertEquals(two, new PlayerState(Player.DARK, 3, 2));
        assertEquals(three, new PlayerState(Player.LIGHT, "Jeff", 1, 1));
        assertEquals(four, new PlayerState(Player.DARK, "Bob", 9, 9));

        assertNotEquals(one, two);
        assertNotEquals(one, three);
        assertNotEquals(one, four);

        assertNotEquals(two, one);
        assertNotEquals(two, three);
        assertNotEquals(two, four);

        assertNotEquals(three, one);
        assertNotEquals(three, two);
        assertNotEquals(three, four);

        assertNotEquals(four, one);
        assertNotEquals(four, two);
        assertNotEquals(four, three);

        Object notPlayerState = new Object();
        assertNotEquals(one, notPlayerState);
        assertNotEquals(two, notPlayerState);
        assertNotEquals(three, notPlayerState);
        assertNotEquals(four, notPlayerState);
        assertNotEquals(one, null);
    }

    @Test
    public void testToString() {
        PlayerState one = new PlayerState(Player.LIGHT, 2, 3);
        PlayerState two = new PlayerState(Player.DARK, 3, 2);
        PlayerState three = new PlayerState(Player.LIGHT, "Jeff", 1, 1);
        PlayerState four = new PlayerState(Player.DARK, "Bob", 9, 9);

        assertEquals(
                "Player: Light\n" +
                "Pieces: 2\n" +
                "Score: 3",
                one.toString()
        );
        assertEquals(
                "Player: Dark\n" +
                "Pieces: 3\n" +
                "Score: 2",
                two.toString()
        );
        assertEquals(
                "Player: Jeff (Light)\n" +
                "Pieces: 1\n" +
                "Score: 1",
                three.toString()
        );
        assertEquals(
                "Player: Bob (Dark)\n" +
                "Pieces: 9\n" +
                "Score: 9",
                four.toString()
        );

        assertEquals(one.toString(), one.toString());
        assertEquals(two.toString(), two.toString());
        assertEquals(three.toString(), three.toString());
        assertEquals(four.toString(), four.toString());

        assertEquals(one.toString(), new PlayerState(Player.LIGHT, 2, 3).toString());
        assertEquals(two.toString(), new PlayerState(Player.DARK, 3, 2).toString());
        assertEquals(three.toString(), new PlayerState(Player.LIGHT, "Jeff", 1, 1).toString());
        assertEquals(four.toString(), new PlayerState(Player.DARK, "Bob", 9, 9).toString());

        assertNotEquals(one.toString(), two.toString());
        assertNotEquals(one.toString(), three.toString());
        assertNotEquals(one.toString(), four.toString());

        assertNotEquals(two.toString(), one.toString());
        assertNotEquals(two.toString(), three.toString());
        assertNotEquals(two.toString(), four.toString());

        assertNotEquals(three.toString(), one.toString());
        assertNotEquals(three.toString(), two.toString());
        assertNotEquals(three.toString(), four.toString());

        assertNotEquals(four.toString(), one.toString());
        assertNotEquals(four.toString(), two.toString());
        assertNotEquals(four.toString(), three.toString());
    }
}