package net.royalur;

import net.royalur.agent.Agent;
import net.royalur.agent.FinkelLUTAgent;
import net.royalur.lut.StateLUT;
import net.royalur.model.Board;
import net.royalur.model.GameSettings;
import net.royalur.model.Move;
import net.royalur.model.Piece;
import net.royalur.model.PlayerState;
import net.royalur.model.PlayerType;
import net.royalur.model.Tile;
import net.royalur.model.dice.Roll;
import net.royalur.model.shape.StandardBoardShape;
import net.royalur.notation.FullStateSource;
import net.royalur.rules.state.GameState;
import net.royalur.rules.state.PlayableGameState;
import net.royalur.lut.store.BigEntryStore;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import static spark.Spark.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        //Game<Piece, PlayerState, Roll> sample = Game.builder().finkel().build();
        //Board<Piece> board = sample.getBoard();
        String[] lightPath = {"A4", "A3", "A2", "A1", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "A8", "A7"};
        String[] darkPath = {"C4", "C3", "C2", "C1", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "C8", "C7"};
        //set(sample, lightPath, PlayerType.LIGHT, 12);
        //set(sample, lightPath, PlayerType.LIGHT, 9);
        //
        //set(sample, darkPath, PlayerType.DARK, 5);
        //set(sample, darkPath, PlayerType.DARK, 1);
        //set(sample, darkPath, PlayerType.DARK, 10);
        //FullStateSource<Piece, PlayerState, Roll> source = new FullStateSource<>(
        //    sample.getBoard(),
        //    new PlayerState(PlayerType.LIGHT, 5, 0),
        //    new PlayerState(PlayerType.DARK, 3, 1)
        //);
        //sample.addState(source.createWaitingForMoveState(sample.getRules(), PlayerType.DARK, sample.getDice().roll(1)));
        //System.out.println(board.toString());

        StateLUT lut = new StateLUT(GameSettings.FINKEL);
        BigEntryStore states = lut.readStateStore(new File("./finkel.rgu"));
        String[] gamePath = new String[] {
            "A1", "B1", "C1", "A2", "B2", "C2", "A3", "B3", "C3", 
            "A4", "B4", "C4", "A5", "B5", "C5", "A6", "B6", "C6", 
            "A7", "B7", "C7", "A8", "B8", "C8"
        };
        FinkelLUTAgent<Piece, PlayerState, Roll> flut = new FinkelLUTAgent<>(states);

        //List<Move<Piece>> moves = sample.findAvailableMoves();
        //Move<Piece> pickedMove = flut.decideMove(sample, moves);
        
        //System.out.println(pickedMove.toString());
        //flut.playTurn(sample);
        //System.out.println(sample.getBoard().toString());
        // Set up the server on port 4567
        port(4567);

        // Define a POST endpoint that accepts JSON
        post("/jsonEndpoint", "application/json", (request, response) -> {
            // Extract JSON from the request body
            String requestBody = request.body();
            
            // Use Gson library to parse JSON into a Java object
            Gson gson = new Gson();
            YourClass yourClassObject = gson.fromJson(requestBody, YourClass.class);
            
            // Do something with the parsed object (replace this with your logic)
            System.out.println("Received JSON object: " + yourClassObject.toString());
            // TODO
            // Decode game and set board state
            Game<Piece, PlayerState, Roll> sample = Game.builder().finkel().build();
            // A1, B1, C1, A2, B2, C2, A3, B3, C3, A4, B4, C4, A5, B5, C5, A6, B6, C6, A7, B7, C7, A8, B8, C8
            // iterate all characters in game string
            String rp = yourClassObject.game.replace(" ", "");
            for (int i = 0; i < rp.length(); i++) {
                // get character at index i
                char c = rp.charAt(i);

                if (c == 'L') {
                    // find gamePath[i] in lightPath
                    for (int j = 0; j < lightPath.length; j++) {
                        if (lightPath[j].equals(gamePath[i])) {
                            // set lightPath[j] to 1
                            set(sample, lightPath, PlayerType.LIGHT, j);
                            break;
                        }
                    }
                }
                if (c == 'D') {
                    // find gamePath[i] in darkPath
                    for (int j = 0; j < darkPath.length; j++) {
                        if (darkPath[j].equals(gamePath[i])) {
                            // set darkPath[j] to 1
                            set(sample, darkPath, PlayerType.DARK, j);
                            break;
                        }
                    }
                }
            }
            Board<Piece> board = sample.getBoard();
            System.out.println(board.toString());
            FullStateSource<Piece, PlayerState, Roll> source = new FullStateSource<>(
                sample.getBoard(),
                new PlayerState(PlayerType.LIGHT, yourClassObject.light_left, yourClassObject.light_score),
                new PlayerState(PlayerType.DARK, yourClassObject.dark_left, yourClassObject.dark_score)
            );
            sample.addState(source.createWaitingForMoveState(sample.getRules(), yourClassObject.light_turn ? PlayerType.LIGHT : PlayerType.DARK, sample.getDice().roll(yourClassObject.roll)));
            //flut.playTurn(sample);
            List<Move<Piece>> moves = sample.findAvailableMoves();
            Move<Piece> pickedMove = flut.decideMove(sample, moves);
            // Send a response (replace this with your response logic)
            Tile dest = pickedMove.getDestOrNull();
            if (dest == null) {
                return "null";
            }
            board = sample.getBoard();
            //System.out.println(board.toString());
            pickedMove.apply(board);
            System.out.println(board.toString());

            return dest.getXIndex() + "," + dest.getYIndex();
        });
        //RGUStatistics.main(args);
    }
    // Define your Java class to represent the structure of the JSON
    public static class YourClass {
        // Define fields that correspond to the JSON structure
        private String game; // A1, B1, C1, A2, B2, C2, A3, B3, C3, A4, B4, C4, A5, B5, C5, A6, B6, C6, A7, B7, C7, A8, B8, C8
        private int roll;
        private boolean light_turn;
        private int light_score;
        private int dark_score;
        private int light_left;
        private int dark_left;

        // Add getters and setters

        @Override
        public String toString() {
            return "YourClass{" +
                    "game='" + game + '\'' +
                    ", roll=" + roll +
                    ", light_turn=" + light_turn +
                    ", light_score=" + light_score +
                    ", dark_score=" + dark_score +
                    ", light_left=" + light_left +
                    ", dark_left=" + dark_left +
                    '}';
        }
    }

    public static void set(Game<Piece, PlayerState, Roll> sample, String[] path, PlayerType playerType, int pathIndex) {
        Tile tile = Tile.fromString(path[pathIndex]);
        Board<Piece> board = sample.getBoard();
        Piece p = sample.getRules().getPieceProvider().create(
            playerType, pathIndex
        );
        board.set(tile, p);
    }
}
