package chess.strategies.classic;

import chess.*;

import java.util.ArrayList;
import java.util.Collection;

public class PawnStrategy implements ChessStrategy {
    public ArrayList<ChessMove> getPromotionVariants(ChessPosition startPosition, ChessPosition endPosition) {
        ArrayList<ChessMove> variants = new ArrayList<>();

        int endRow = endPosition.getRow();

        if (endRow == 1 || endRow == 8) {
            variants.add(new ChessMove(startPosition, endPosition, ChessPiece.PieceType.BISHOP));
            variants.add(new ChessMove(startPosition, endPosition, ChessPiece.PieceType.KNIGHT));
            variants.add(new ChessMove(startPosition, endPosition, ChessPiece.PieceType.QUEEN));
            variants.add(new ChessMove(startPosition, endPosition, ChessPiece.PieceType.ROOK));
        } else {
            variants.add(new ChessMove(startPosition, endPosition, null));
        }

        return variants;
    }

    @Override
    public Collection<ChessMove> getValidMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor team) {
        ArrayList<ChessMove> validMoves = new ArrayList<>();

        int direction = team == ChessGame.TeamColor.BLACK ? -1 : 1;

        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        ChessPosition forwardPosition = new ChessPosition(row + direction, col);
        boolean canMoveForward = (
                board.isValidPosition(forwardPosition)
                        && board.getPiece(forwardPosition) == null
        );

        if (canMoveForward) {
            validMoves.addAll(this.getPromotionVariants(myPosition, forwardPosition));
        }

        ChessPosition doubleForwardPosition = new ChessPosition(row + 2*direction, col);
        boolean isInitial = (
                team == ChessGame.TeamColor.BLACK && row == 7
                || team == ChessGame.TeamColor.WHITE && row == 2
        );
        boolean canMoveDoubleForward = (
                isInitial
                && canMoveForward
                && board.isValidPosition(doubleForwardPosition)
                && board.getPiece(doubleForwardPosition) == null
        );

        if (canMoveDoubleForward) {
            validMoves.add(new ChessMove(myPosition, doubleForwardPosition, null));
        }

        ChessPosition leftAttackPosition = new ChessPosition(row + direction, col - 1);
        boolean canAttackLeft = (
                board.isValidPosition(leftAttackPosition)
                        && board.getPiece(leftAttackPosition) != null
                        && board.getPiece(leftAttackPosition).getTeamColor() != team
        );

        if (canAttackLeft) {
            validMoves.addAll(this.getPromotionVariants(myPosition, leftAttackPosition));
        }

        ChessPosition rightAttackPosition = new ChessPosition(row + direction, col + 1);
        boolean canAttackRight = (
                board.isValidPosition(rightAttackPosition)
                        && board.getPiece(rightAttackPosition) != null
                        && board.getPiece(rightAttackPosition).getTeamColor() != team
        );

        if (canAttackRight) {
            validMoves.addAll(this.getPromotionVariants(myPosition, rightAttackPosition));
        }

        return validMoves;
    }
}
