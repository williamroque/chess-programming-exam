package chess.strategies.classic;

import chess.*;

import java.util.ArrayList;
import java.util.Collection;

public class KnightStrategy implements ChessStrategy {
    private final int[][] offsets = {
            {2, -1},
            {2, 1},
            {-2, -1},
            {-2, 1},
            {1, 2},
            {-1, 2},
            {1, -2},
            {-1, -2}
    };

    @Override
    public Collection<ChessMove> getValidMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor team) {
        ArrayList<ChessMove> validMoves = new ArrayList<>();

        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        for (int[] offset : offsets) {
            ChessPosition position = new ChessPosition(row + offset[0], col + offset[1]);

            if (!board.isValidPosition(position)) continue;

            ChessPiece target = board.getPiece(position);

            if (target == null || target.getTeamColor() != team) {
                validMoves.add(new ChessMove(myPosition, position, null));
            }
        }

        return validMoves;
    }
}
