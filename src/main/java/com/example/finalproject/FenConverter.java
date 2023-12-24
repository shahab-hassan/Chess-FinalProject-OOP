package com.example.finalproject;

public class FenConverter {
    public static String boardToFEN(Block[][] chessBoard) {
        StringBuilder fen = new StringBuilder();
        for (int row = 0; row < 8; row++) {
            int emptySquares = 0;
            for (int col = 0; col < 8; col++) {
                if(chessBoard[row][col]==null)
                    continue;
                Piece piece = chessBoard[row][col].getPiece();
                if (piece == null) {
                    emptySquares++;
                } else {
                    if (emptySquares > 0) {
                        fen.append(emptySquares);
                        emptySquares = 0;
                    }
                    char pieceChar = getFENChar(piece);
                    fen.append(pieceChar);
                }
            }
            if (emptySquares > 0) {
                fen.append(emptySquares);
            }
            if (row < 7) {
                fen.append('/');
            }
        }

        // Add information about the player to move, castling, and en passant
        fen.append(" ").append(Game.chessBoard.isBlackTurn ? "b" : "w");
        fen.append(" ").append(getCastlingRights());
        fen.append(" ").append(getEnPassantTarget());
        fen.append(" 0 1");

        return fen.toString();
    }

    private static char fenChar(Piece piece)
    {
        if(piece!=null)
        {
            if(piece.type==PieceType.KING)
            {
                return 'K';
            }
            else if(piece.type==PieceType.QUEEN)
            {
                return 'Q';
            }
            else if(piece.type==PieceType.ROOK)
            {
                return 'R';
            }
            else if(piece.type==PieceType.BISHOP)
            {
                return 'B';
            }else if(piece.type==PieceType.KNIGHT)
            {
                return 'N';
            }
            else if(piece.type==PieceType.PAWN)
            {
                return 'P';
            }

        }
        return 0;

    }

    private static char getFENChar(Piece piece) {
        char pieceChar;
        if (piece.isBlack) {
            pieceChar = Character.toLowerCase(fenChar(piece));
        } else {
            pieceChar = (fenChar(piece));
        }
        return pieceChar;
    }

    private static String getCastlingRights() {
        return "-";
    }

    private static String getEnPassantTarget() {
        return "-";
    }

}