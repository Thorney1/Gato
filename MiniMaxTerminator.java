public class MiniMaxTerminator {
    public static int obtenerMejorMovimiento(char[][] tablero, char jugador) {
        int mejorPuntaje = Integer.MIN_VALUE;
        int mejorMovimiento = -1;

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == ' ') {
                    tablero[i][j] = jugador;
                    int puntaje = minimax(tablero, 0, false, jugador);
                    tablero[i][j] = ' ';

                    if (puntaje == 1) {
                        // Si el movimiento resulta en una victoria, retornar ese movimiento
                        return i * tablero.length + j;
                    } else if (puntaje > mejorPuntaje) {
                        mejorPuntaje = puntaje;
                        mejorMovimiento = i * tablero.length + j;
                    }
                }
            }
        }

        return mejorMovimiento;
    }

    public static int minimax(char[][] tablero, int profundidad, boolean esMaximizador, char jugador) {
        char resultado = JuegoDelGato.verificarGanador(tablero);

        // Caso base: si hay un ganador o el tablero está lleno, retornar el puntaje
        if (resultado == jugador) {
            return 1;
        } else if (resultado == JuegoDelGato.getOpponent(jugador)) {
            return -1;
        } else if (JuegoDelGato.esTableroLleno(tablero)) {
            return 0;
        }

        if (esMaximizador) {
            int mejorPuntaje = Integer.MIN_VALUE;
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero[i].length; j++) {
                    if (tablero[i][j] == ' ') {
                        tablero[i][j] = jugador;
                        int puntaje = minimax(tablero, profundidad + 1, false, jugador);
                        tablero[i][j] = ' ';
                        mejorPuntaje = Math.max(mejorPuntaje, puntaje);
                    }
                }
            }
            return mejorPuntaje;
        } else {
            int mejorPuntaje = Integer.MAX_VALUE;
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero[i].length; j++) {
                    if (tablero[i][j] == ' ') {
                        tablero[i][j] = JuegoDelGato.getOpponent(jugador);
                        int puntaje = minimax(tablero, profundidad + 1, true, jugador);
                        tablero[i][j] = ' ';
                        mejorPuntaje = Math.min(mejorPuntaje, puntaje);
                    }
                }
            }
            return mejorPuntaje;
        }
    }
}
