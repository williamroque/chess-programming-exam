package chess.strategies.classic;

import chess.*;

import java.util.ArrayList;
import java.util.Collection;

public class BishopStrategy implements ChessStrategy {
    private final int[][] offsets = {
            {-1, -1},
            {1, 1},
            {-1, 1},
            {1, -1}
    };

    @Override
    public Collection<ChessMove> getValidMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor team) {
        ArrayList<ChessMove> validMoves = new ArrayList<>();

        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        for (int[] offset : offsets) {
            int scalar = 1;

            ChessPosition position = new ChessPosition(row + offset[0], col + offset[1]);

            while (board.isValidPosition(position)) {
                ChessPiece target = board.getPiece(position);

                if (target == null || target.getTeamColor() != team) {
                    validMoves.add(new ChessMove(myPosition, position, null));
                }

                if (target != null) break;

                scalar++;
                position = new ChessPosition(row + scalar * offset[0], col + scalar * offset[1]);
            }
        }

        return validMoves;
    }
}
