package chess;

import java.util.Collection;

public interface ChessStrategy {
    public Collection<ChessMove> getValidMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor team);
}
