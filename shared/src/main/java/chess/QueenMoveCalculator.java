package chess;

import java.util.Collection;
import java.util.HashSet;

public class QueenMoveCalculator implements PieceMovesCalculator{

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> moves =new HashSet<>();

        // Add possible moves
        addVerticalAndHorizontalMoves(board, position, moves);
        addDiagonalMoves(board, position, moves);

        return moves;

    }

    private void addVerticalAndHorizontalMoves(ChessBoard board, ChessPosition position, Collection<ChessMove> moves) {
        int[] directions = {-1, 1};

        //Vertical moves
        for (int direction : directions) {
            int row = position.getRow();
            while (true) {
                row += direction;
                if (row < 1 || row > 8) break;
                ChessPosition newPosition = new ChessPosition(row, position.getColumn());
                if (!addMoveIfValid(board, position, newPosition, moves)) break;
            }
        }

        //Horizontal Moves
        for (int direction : directions) {
            int col = position.getColumn();
            while (true) {
                col += direction;
                if (col < 1 || col > 8) break;
                ChessPosition newPosition = new ChessPosition(position.getRow(), col);
                if (!addMoveIfValid(board, position, newPosition, moves)) break;
            }
        }
    }

    private void addDiagonalMoves(ChessBoard board, ChessPosition position, Collection<ChessMove> moves) {
        int[] directions = {-1, 1};

        for (int rowdirection : directions) {
            for (int coldirection : directions) {
                int row = position.getRow();
                int col = position.getColumn();
                while (true) {
                    row += rowdirection;
                    col += coldirection;
                    if (row < 1 || row > 8 || col < 1 || col > 8) break;
                    ChessPosition newPosition = new ChessPosition(row, col);
                    if (!addMoveIfValid(board, position, newPosition, moves)) break;
                }
            }
        }
    }

    private boolean addMoveIfValid(ChessBoard board, ChessPosition start, ChessPosition end, Collection<ChessMove> moves) {
        ChessPiece pieceAtEnd = board.getPiece(end);
        if (pieceAtEnd != null) {
            if(pieceAtEnd.getTeamColor() != board.getPiece(start).getTeamColor()){
                moves.add(new ChessMove(start, end, null)); //Capture move
            }
            return false;
        }
        moves.add(new ChessMove(start, end,null)); //Normal move
        return true;
    }
}
