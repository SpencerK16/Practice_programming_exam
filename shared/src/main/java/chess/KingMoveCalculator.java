package chess;

import java.util.Collection;
import java.util.HashSet;

public class KingMoveCalculator implements PieceMovesCalculator{

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> moves =new HashSet<>();
        //Add possible moves
        addKingMoves(board, position, moves);

        return moves;
    }

    private void addKingMoves(ChessBoard board, ChessPosition position, Collection<ChessMove> moves) {
        int[] rowDirections = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colDirections = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < rowDirections.length; i++) {
            int newRow = position.getRow() + rowDirections[i];
            int newCol = position.getColumn() + colDirections[i];
            if (newRow >= 1 && newRow <= 8 && newCol >= 1 && newCol <= 8) {
                ChessPosition newPosition = new ChessPosition(newRow, newCol);
                addMoveIfValid(board, position, newPosition, moves);
            }

        }
    }

    private void addMoveIfValid(ChessBoard board, ChessPosition start, ChessPosition end, Collection<ChessMove> moves) {
        ChessPiece pieceAtEnd = board.getPiece(end);
        if (pieceAtEnd != null) {
            if(pieceAtEnd.getTeamColor() != board.getPiece(start).getTeamColor()){
                moves.add(new ChessMove(start, end, null)); //Capture move
            }
            return;
        }
        moves.add(new ChessMove(start, end,null)); //Normal move
    }

}
